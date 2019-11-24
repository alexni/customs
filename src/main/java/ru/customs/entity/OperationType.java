package ru.customs.entity;

public enum OperationType {
    FIRST("ОБЕСПЕЧЕНИЕ ТАМОЖЕННОГО ТРАНЗИТА"),
    SECOND("ЭЛЕКТРОННАЯ ТРАНЗИТНАЯ ДЕКЛАРАЦИЯ"),
    THIRD("ПРЕДВАРИТЕЛЬНОЕ ИНФОРМИРОВАНИЕ"),
    FOURTH("ТРАНЗИТНАЯ ДЕКЛАРАЦИЯ Т-1"),
    FIFTH("УВЕДОМЛЕНИЕ В СИСТЕМЕ AREX"),
    SIXTH("ЭКСПОРТНАЯ ДЕКЛАРАЦИЯ EX-1");

    private final String value;

    OperationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
