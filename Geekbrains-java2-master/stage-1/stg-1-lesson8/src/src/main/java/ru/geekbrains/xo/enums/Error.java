package ru.geekbrains.xo.enums;

public enum Error {
    DIVISION_BY_ZERO("DIVISION_BY_ZERO_CODE", "Ошибка деления"),
    RUNTIME_EXCEPTION("RUNTIME_EXCEPTION_CODE", "Ошибка выполнения"),
    VALIDATION_ERROR("VALIDATION_ERROR_CODE", "Ошибка валидации значений");

    String code;
    String message;

    Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
