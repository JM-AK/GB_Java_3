package ru.geekbrains.xo.enums;

public enum GameMode {
    GAME_MODE_HVAI("Human VS AI", "RED"),
    GAME_MODE_HVH("Human VS Human", "BLUE"),;


    String textValue;
    String color;

    GameMode(String textValue, String color) {
        this.textValue = textValue;
        this.color = color;
    }

    public String getTextValue() {
        return textValue;
    }

    public String getColor() {
        return color;
    }
}
