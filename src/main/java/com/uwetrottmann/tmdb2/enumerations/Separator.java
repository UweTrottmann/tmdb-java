package com.uwetrottmann.tmdb2.enumerations;

public enum Separator {
    AND(","), OR("|");

    private final String symbol;

    Separator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}