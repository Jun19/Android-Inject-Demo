package com.jun.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@AnnotationType(value = "setOnLongClickListener", eventClass = View.OnLongClickListener.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnLongClick {
    int[] ids();//传入id
}
