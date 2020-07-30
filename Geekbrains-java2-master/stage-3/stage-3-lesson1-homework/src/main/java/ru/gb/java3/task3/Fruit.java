package ru.gb.java3.task3;

public abstract class Fruit {
    private final double weight;

    public enum FRUIT {
        APPLE ("Яблоко", 1.0),
        ORANGE ("Апельсин", 1.5);

        private final String rusTitle;
        private final double weight;

        FRUIT (String rusTitle, double weight){
            this.rusTitle = rusTitle;
            this.weight = weight;
        }

        public double getWeight(){
            return this.weight;
        }
    }

    public Fruit (FRUIT f){
        this.weight = f.getWeight();
    }

    public double getWeight() {
        return weight;
    }

}
