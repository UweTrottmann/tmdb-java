package com.uwetrottmann.tmdb2.entities;

import com.uwetrottmann.tmdb2.enumerations.ReleaseType;

public class DiscoverFilter {

    private final Separator separator;
    private final Integer[] items;

    public enum Separator {
        AND(","), OR("|");

        private final String symbol;

        Separator(String symbol) {
            this.symbol = symbol;
        }
    }

    public DiscoverFilter(Integer... items) {
        this(Separator.AND, items);
    }

    public DiscoverFilter(Separator separator, Integer... items) {
        this.separator = separator;
        this.items = items;
    }

    public DiscoverFilter(Separator separator, ReleaseType... types) {
        if (types == null){
            throw new IllegalArgumentException("types must not be null");
        }
        this.separator = separator;
        this.items = new Integer[types.length];
        for (int i = 0; i < types.length; i++) {
            ReleaseType type = types[i];
            if (type != null) {
                items[i] = type.id;
            }
        }
    }

    @Override
    public String toString() {
        if (separator == null){
            throw new NullPointerException();
        }
        if (items == null || items.length == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (Integer item : items) {
            if (item == null) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append(separator.symbol);
            }
            sb.append(item);
        }

        return sb.toString();
    }

}
