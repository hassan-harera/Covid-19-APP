package com.whiteside.covid19.model;

import java.lang.reflect.Field;

public class ResourcesHandle {

    public static int getResourceID(String name, Class<?> c){
        try {
            Field idField = c.getDeclaredField(name);
            return idField.getInt(idField.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
