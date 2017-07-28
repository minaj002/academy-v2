package com.weststein.repository;

public enum CardUse {

    Customers("Customer"), CorporateCards("Corporate"), Business("Business"), Employee_Incentive_Rewards("Reward"), Payroll("Reward"), MultiCurrencyTravel_Cards("Multycurrency"), GiftCards("Gift"), Other("Others");

    private final String type;

    CardUse(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
