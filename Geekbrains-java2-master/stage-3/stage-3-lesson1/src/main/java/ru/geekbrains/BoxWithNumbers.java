package ru.geekbrains;

import java.io.Serializable;

public class BoxWithNumbers <N extends Number> {

    N[] value;

    public BoxWithNumbers(N... value) {
        this.value = value;
    }

    public double avg() {
        double result = 0.0;
        for (int i = 0; i < this.value.length; i++) {
            result += this.value[i].doubleValue();
        }
        return result / this.value.length;
    }

    public boolean isSame(BoxWithNumbers<?> another) {
        return Math.abs(this.avg() - another.avg()) < 0.001;
    }
}
