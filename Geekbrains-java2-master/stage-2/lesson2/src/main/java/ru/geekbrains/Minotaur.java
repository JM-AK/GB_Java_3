package ru.geekbrains;

public class Minotaur implements Human, Bull{

    @Override
    public void voice() {
        System.out.println("roar");
    }

    @Override
    public void walk() {
        System.out.println("walk like human and bull");
    }

    @Override
    public void think() {
        System.out.println("hmmm......");
    }

    @Override
    public void something() {
        
    }
}
