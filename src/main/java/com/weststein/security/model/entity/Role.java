package com.weststein.security.model.entity;

public enum Role {
    OWNER, BOOKKEEPER, FINANCIAL_OFFICER, PRIVATE, NEW;

    public String authority() {
        return "ROLE_" + this.name();
    }

}
