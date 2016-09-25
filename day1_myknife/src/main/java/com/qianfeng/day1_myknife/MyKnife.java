package com.qianfeng.day1_myknife;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xray on 16/9/24.
 */

public class MyKnife {


    public static void bind(final Activity activity){
        Class<? extends Activity> aClass = activity.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for(Field field : fields){
            BindView annotation = field.getAnnotation(BindView.class);
            if(annotation != null){
                int id = annotation.value();
                View view = activity.findViewById(id);
                try {
                    field.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        Method[] methods = aClass.getDeclaredMethods();
        for(final Method method : methods){
            OnClick annotation = method.getAnnotation(OnClick.class);
            if(annotation != null){
                int id = annotation.value();
                View view = activity.findViewById(id);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("xray", "onClick: ");
                        try {
                            method.invoke(activity);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}
