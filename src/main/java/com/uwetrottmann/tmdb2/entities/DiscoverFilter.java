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
        this.separator = Separator.AND;
        this.items = items;
    }

    public DiscoverFilter(Separator separator, ReleaseType... types) {
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
