package ru.geekbrains;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReflactionTest {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class testClass = Football.class;
        start(testClass);
    }

/*
* Из «класса-теста» вначале должен быть запущен метод с
аннотацией @BeforeSuite, если он присутствует. Далее запускаются методы с аннотациями @Test, а
по завершении всех тестов – метод с аннотацией @AfterSuite .
* */
    public static void  start (Class testClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (!testClass.isAnnotationPresent(TestC.class)) {
            return;
        }

        Constructor cons = testClass.getConstructor(int.class);
        Object game = (testClass)cons.newInstance(1);

        Method[] methods = game.getClass().getDeclaredMethods();

        for (Method method : methods) {
            int countMethod = 0;
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                countMethod++;
                if (countMethod < 2) method.invoke(game, "Hello everyone");
                else throw new RuntimeException("Too much BeforeM");
            }
        }

        HashMap<String, Integer> mapMethods = new HashMap<>();
        for(Method method : methods) {
            if (method.isAnnotationPresent(TestM.class)) {
                mapMethods.put(method.getName(),method.getAnnotation(TestM.class).priority());
            }
        }
        /*сортировка карты*/
        HashMap<String, Integer> sortedMap = new HashMap<>();
        sortedMap = mapMethods.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        for(Map.Entry entry: sortedMap.entrySet()){
            Method method = testClass.getDeclaredMethod((String) entry.getKey(), String.class);
            method.invoke(game);
        }

        for (Method method : methods) {
            int countMethod = 0;
            if (method.isAnnotationPresent(AfterSuite.class)) {
                countMethod++;
                if (countMethod < 2) method.invoke(game);
                else throw new RuntimeException("Too much AfterM");
            }
        }

    }

}
