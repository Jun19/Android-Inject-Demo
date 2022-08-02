package com.jun.annotion_invoke_dynamic

import android.app.Activity
import android.view.View
import com.jun.annotation.AnnotationType
import java.lang.reflect.Proxy

/**
 *
 *
 * @author Jun
 * @time 2022/7/1
 */
object EventKt {

    fun inject(activity: Activity) {
        val classA = activity.javaClass
        //获取该activity下所有的方法
        val declaredMethods = classA.declaredMethods;
        for (declaredMethod in declaredMethods) {
            //获取方法上的注解
            val annotations = declaredMethod.annotations
            for (outSideAnnotation in annotations) {
                //获取注解的class类型
                val annotationClass = outSideAnnotation.annotationClass
                for (annotation in annotationClass.annotations) {
                    //判断注解上的注解是否包含AnnotationType
                    if (annotation.annotationClass == AnnotationType::class) {
                        val type = annotation as AnnotationType
                        val eventClass = type.eventClass
                        val value = type.value
                        val javaClass = arrayOf(eventClass.java)
                        //事件代理
                        val eventProxy = Proxy.newProxyInstance(
                            activity.classLoader,
                            javaClass
                        ) { proxy, method, args -> declaredMethod.invoke(activity, args[0]) }
                        //获取注解里的ids方法
                        val idMethod = annotationClass.java.getDeclaredMethod("ids")
                        //获取注解里的ids值
                        val array = idMethod.invoke(outSideAnnotation) as IntArray
                        for (id in array) {
                            val view = activity.findViewById<View>(id)
                            val method = view.javaClass.getMethod(value, eventClass.java)
                            //对该view的点击事件设置动态代理
                            method.invoke(view, eventProxy)
                        }
                    }

                }
            }
        }
    }
}