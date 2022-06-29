package com.jun.myprocessor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * @author Jun
 * @time 2022/6/29
 */
//允许该注解器的注解
@SupportedAnnotationTypes("com.jun.annotation.OnClick")
public class OnClickProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //处理注解
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "OnClickProcessor=>>>>>");
        if (!set.isEmpty()) {
            Filer filer = processingEnv.getFiler();
            String code="class A{}";
            OutputStream outputStream = null;
            try {
                JavaFileObject sourceFile = filer.createSourceFile("MyClickProcessor");
                outputStream = sourceFile.openOutputStream();
//                Proxy.newProxyInstance(, , )
                outputStream.write(code.getBytes());
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}
