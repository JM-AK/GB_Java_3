package ru.geekbrains;

public class Box implements Comparable<Box>{

    int length;
    int height;

    public Box(int length, int height) {
        this.length = length;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Box box = (Box) o;
        return length == box.length && height == box.height;
    }

    @Override
    public int hashCode() {
        int result = length;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return "Box{" +
                length + ", " +
                height +
                '}';
    }

    private int square() {
        return length * height;
    }

    @Override
    public int compareTo(Box o) {
        return this == o ? 0 : (this.square() - o.square());
    }
}
