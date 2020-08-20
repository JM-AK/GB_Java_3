package ru.geekbrains.ref;

import java.io.File;
import java.lang.reflect.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ReflectionTest {

    public static void main(String[] args) throws Exception {
        Class clazz = Cat.class;
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(MyAnno.class)) {
                System.out.println(method + ", anno val = " + method.getAnnotation(MyAnno.class).val2());
                //method.invoke()
            }
        }
    }

    private static void classLoaderExample() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ClassLoader loader = new URLClassLoader(new URL[]{new File("/").toURL()});
        Class clazz = loader.loadClass("Human");
        Method method = clazz.getDeclaredMethod("sayHello", String.class);
        Object human = clazz.newInstance();
        method.invoke(human, "Igor");
    }

    private static void reflectionExample() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Class clazz = Cat.class;
        for (Constructor con : clazz.getConstructors()) {
//            System.out.println(con);
        }
        Constructor cons = clazz.getConstructor(int.class, String.class, int.class);
        Cat cat = (Cat)cons.newInstance(1, "kot", 10);
//        cat.info();

        for (Method method : clazz.getDeclaredMethods()) {
//            System.out.println(method);
        }

        Method method = clazz.getDeclaredMethod("meow");
//        method.setAccessible(true);
//        method.invoke(cat);

        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field);
        }

        cat.info();
        Field field = clazz.getDeclaredField("id");
        field.setAccessible(true);
        field.setInt(cat, 50);
        cat.info();

        clazz.getModifiers();
        Modifier.isPublic(clazz.getModifiers());
    }


    private static void getClassExample() throws ClassNotFoundException {
        Class clazz = Cat.class;

        Cat cat = new Cat(1, "Kot", 10);
        Class clazz1 = cat.getClass();

        Class clazz2 = Class.forName("ru.geekbrains.ref.Cat");
    }
}
