package ru.geekbrains;

public class BoxGen <T>{
    T obj;

    // T - type
    // K - key
    // V - Value
    // E - Element
    // N - Number

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public BoxGen(T obj) {
        this.obj = obj;
    }

    public String info() {
        return "Box ( " + obj.getClass().getSimpleName() + " value = " + obj + ")";
    }
}
