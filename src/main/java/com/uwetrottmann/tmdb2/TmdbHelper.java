package com.uwetrottmann.tmdb2;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TmdbHelper {

    public static final String TMDB_DATE_PATTERN = "yyyy-MM-dd";
    private static final SimpleDateFormat TMDB_DATE_FORMAT = new SimpleDateFormat(TMDB_DATE_PATTERN);

    /**
     * Create a {@link com.google.gson.GsonBuilder} and register all of the custom types needed in order to properly
     * deserialize complex TMDb-specific types.
     *
     * @return Assembled GSON builder instance.
     */
    public static GsonBuilder getGsonBuilder() {
        GsonBuilder builder = new GsonBuilder();

        // class types
        builder.registerTypeAdapter(Integer.class, new JsonDeserializer<Integer>() {
            @Override
            public Integer deserialize(JsonElement json, Type typeOfT,
                    JsonDeserializationContext context) throws JsonParseException {
                return json.getAsInt();
            }
        });

        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT,
                    JsonDeserializationContext context) throws JsonParseException {

                try {
                    return TMDB_DATE_FORMAT.parse(json.getAsString());
                } catch (ParseException e) {
                    // return null instead of failing (like default parser would)
                    return null;
                }
            }
        });

        return builder;
    }
}
