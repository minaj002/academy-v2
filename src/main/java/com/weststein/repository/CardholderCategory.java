package com.weststein.repository;

public enum CardholderCategory {

    Directors_Shareholders("Shareholders"), Employees("Employees"), Affiliates("Affiliates"), Consumers("Affiliates"), Other("Other");

    private final String type;

    CardholderCategory(String type) {
        this.type = type;

    }

    public String getType() {
        return type;
    }
}
