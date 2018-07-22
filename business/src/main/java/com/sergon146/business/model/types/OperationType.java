package com.sergon146.business.model.types;

public enum OperationType {
    INPUT("Зачисление"),
    OUTPUT("Списание");

    private final String title;

    OperationType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
