package com.uwetrottmann.tmdb2.utils;

import com.uwetrottmann.tmdb2.enumerations.ReleaseType;
import com.uwetrottmann.tmdb2.enumerations.Separator;
import com.uwetrottmann.tmdb2.interfaces.ITmdbIdentifiedEntity;

import java.util.ArrayList;

public class DiscoverFilter {

    private final Separator separator;
    private final ArrayList<Integer> items = new ArrayList<>();

    public DiscoverFilter(Separator separator, ITmdbIdentifiedEntity... entities) {
        this.separator = separator;
        if (entities != null) {
            for (ITmdbIdentifiedEntity entity : entities) {
                if (entity != null) {
                    this.items.add(entity.getId());
                }
            }
        }
    }

    public DiscoverFilter(Separator separator, Integer... integers) {
        this.separator = separator;
        if (items != null) {
            for (Integer integer : integers) {
                if (integer != null) {
                    this.items.add(integer);
                }
            }
        }
    }

    public DiscoverFilter(Separator separator, ReleaseType... types) {
        this.separator = separator;
        if (types != null) {
            for (ReleaseType type : types) {
                if (type != null) {
                    this.items.add(type.id);
                }
            }
        }
    }

    @Override
    public String toString() {
        if (items == null || items.size() == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (Integer item : items) {
            if (item == null) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append(separator.getSymbol());
            }
            sb.append(item);
        }

        return sb.toString();
    }

}
