package ru.geekbrains;

public class MainClass {



    private interface KeyboardListener {
        void keyPressed();
    }




    public static void addKeyboardListener(KeyboardListener listener) {
    }



    public static void main(String[] args) {

        class KeyboardHandler implements KeyboardListener{
            @Override
            public void keyPressed() {

            }

        }
        KeyboardHandler handler = new KeyboardHandler();

        addKeyboardListener(handler);

        addKeyboardListener(new KeyboardListener() {
            @Override
            public void keyPressed() {
                System.out.println();
            }
        });

        addKeyboardListener(
                () -> { System.out.println(); } );
    }



}
