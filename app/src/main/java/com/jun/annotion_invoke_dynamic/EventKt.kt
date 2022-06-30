package com.jun.annotion_invoke_dynamic

import android.app.Activity
import com.jun.annotation.AnnotationType

/**
 *
 *
 * @author Jun
 * @time 2022/7/1
 */
object EventKt {

    fun inject(activity: Activity) {
        val classA = activity.javaClass
        val declaredMethods = classA.declaredMethods;
        for (declaredMethod in declaredMethods) {
            val annotations = declaredMethod.annotations
            for (annotation in annotations) {
                val annotationClass = annotation.annotationClass
                for (annotation in annotationClass.annotations) {
                    //相等了
                    if (annotation.annotationClass == AnnotationType::class) {
                        val type = annotation as AnnotationType
                        val eventClass = type.eventClass
                        val value = type.value
                        //没有头绪
//                        Proxy.newProxyInstance(activity.classLoader, arrayOf(eventClass),object :InvocationHandler{
//
//                        })
                    }
                }
            }
        }
    }
}