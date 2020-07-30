package ru.geekbrains;

public class BoxWithTwoGenerics <T, R> {
    T firstValue;
    R secondValue;

    public BoxWithTwoGenerics(T firstValue, R secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @Override
    public String toString() {
        return "BoxWithTwoGenerics{" +
                "firstValue=" + firstValue +
                ", secondValue=" + secondValue +
                '}';
    }
}
