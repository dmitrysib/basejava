package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume r = new Resume();
        Class<? extends Resume> rClass = r.getClass();
        Field field = rClass.getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");

        try {
            Method method = rClass.getDeclaredMethod("toString");
            method.setAccessible(true);
            System.out.println(method.invoke(r));
        } catch (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(r);
    }
}
