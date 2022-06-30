package com.jun.annotion_invoke_dynamic;

import android.app.Activity;
import android.view.View;

import com.jun.annotation.AnnotationType;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Jun
 * @time 2022/6/29
 */
public class EventUtils {

    public static void inject(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        //获取方法
        for (Method declaredMethod : declaredMethods) {
            //获取方法上的注解
            Annotation[] declaredAnnotations = declaredMethod.getAnnotations();
            //拿到注解上的注解
            for (Annotation annotation : declaredAnnotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                //头上是否有该注解
                if (annotationType.isAnnotationPresent(AnnotationType.class)) {
                    //拿到该注解实体 并且获取对于的值
                    AnnotationType typeAnnotation = annotationType.getAnnotation(AnnotationType.class);
                    Class eventClass = typeAnnotation.eventClass();
                    String value = typeAnnotation.value();
                    //拿到该动态代理的方法反射
                    Object proxy = Proxy.newProxyInstance(activity.getClassLoader(), new Class[]{eventClass}, new InvocationHandler() {
                        @Override
                        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                            return declaredMethod.invoke(activity, objects);
                        }
                    });
                    try {
                        Method method = annotationType.getDeclaredMethod("ids");
                        try {
                            int[] ids = (int[]) method.invoke(annotation);
                            for (int id : ids) {
                                View view = activity.findViewById(id);
                                Method method1 = view.getClass().getMethod(value, eventClass);
                                method1.invoke(view, proxy);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    public static void inject2(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        //getDeclaredMethods 获取所有的方法
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            //获取该方法上的注解
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            for (Annotation declaredAnnotation : declaredAnnotations) {
                //拿到该注解类型
                Class<? extends Annotation> annotationType = declaredAnnotation.annotationType();
                //如果是该类型定义的 则获取其值
                if (annotationType.isAnnotationPresent(AnnotationType.class)) {
                    {
                        //强转拿到值
                        AnnotationType annotation = annotationType.getAnnotation(AnnotationType.class);
                        Class eventClass = annotation.eventClass();
                        String value = annotation.value();
                        Object proxyInstance = Proxy.newProxyInstance(activity.getClassLoader(), new Class[]{eventClass}, new InvocationHandler() {
                            @Override
                            public Object invoke(Object o, Method m, Object[] objects) throws Throwable {
                                return method.invoke(activity, objects);
                            }
                        });
                        try {
                            Method method1 = annotationType.getDeclaredMethod("ids");
                            //invoke 即拿到该方法对于上成员变量
                            int[] ids = (int[]) method1.invoke(declaredAnnotation);
                            for (int id : ids) {
                                View view = activity.findViewById(id);
                                //拿到该方法 即setonclick
                                Method method2 = view.getClass().getMethod(value, eventClass);
                                //把view.setonclickxx设置上去
                                method2.invoke(view, proxyInstance);
                            }
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void inject3(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType.isAnnotationPresent(AnnotationType.class)) {
                    AnnotationType annotation1 = annotationType.getAnnotation(AnnotationType.class);
                    Class eventClass = annotation1.eventClass();
                    String value = annotation1.value();
                    Object proxyInstance = Proxy.newProxyInstance(activity.getClassLoader(), new Class[]{eventClass}, new InvocationHandler() {
                        @Override
                        public Object invoke(Object o, Method m, Object[] objects) throws Throwable {
                            return method.invoke(activity, objects);
                        }
                    });
                    //获取id
                    try {
                        Method ids = annotationType.getDeclaredMethod("ids");
                        try {
                            int[] idArray = (int[]) ids.invoke(annotation);
                            for (int id : idArray) {
                                View view = activity.findViewById(id);
                                Method method1 = view.getClass().getMethod(value, eventClass);
                                method1.invoke(view, proxyInstance);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
