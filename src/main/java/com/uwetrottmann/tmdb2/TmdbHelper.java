package com.uwetrottmann.tmdb2;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.uwetrottmann.tmdb2.entities.AccountStates;
import com.uwetrottmann.tmdb2.entities.BaseAccountStates;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BasePerson;
import com.uwetrottmann.tmdb2.entities.BaseTvShow;
import com.uwetrottmann.tmdb2.entities.Media;
import com.uwetrottmann.tmdb2.entities.PersonCastCredit;
import com.uwetrottmann.tmdb2.entities.PersonCrewCredit;
import com.uwetrottmann.tmdb2.entities.RatingObject;
import com.uwetrottmann.tmdb2.enumerations.MediaType;
import com.uwetrottmann.tmdb2.enumerations.Status;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TmdbHelper {

    public static final String TMDB_DATE_PATTERN = "yyyy-MM-dd";
    private static final ThreadLocal<SimpleDateFormat> TMDB_DATE_FORMAT = new ThreadLocal<>();

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

        builder.registerTypeAdapter(MediaType.class, new JsonDeserializer<MediaType>() {
            @Override
            public MediaType deserialize(JsonElement json, Type typeOfT,
                                         JsonDeserializationContext context) throws JsonParseException {
                return MediaType.get(json.getAsString());
            }
        });

        builder.registerTypeAdapter(BaseAccountStates.class, new JsonDeserializer<BaseAccountStates>() {

            @Override
            public BaseAccountStates deserialize(JsonElement jsonElement, Type type,
                                                 JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                JsonObject object = jsonElement.getAsJsonObject();
                BaseAccountStates accountStates = new BaseAccountStates();
                accountStates.id = object.get("id").getAsInt();
                try {
                    accountStates.rated = object.get("rated").getAsBoolean();
                } catch (Exception exc) {
                    accountStates.rated = true;
                    accountStates.rating = jsonDeserializationContext.deserialize(object.get("rated"), RatingObject.class);
                }

                return accountStates;
            }
        });

        builder.registerTypeAdapter(AccountStates.class, new JsonDeserializer<AccountStates>() {

            @Override
            public AccountStates deserialize(JsonElement jsonElement, Type type,
                                             JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                JsonObject object = jsonElement.getAsJsonObject();
                AccountStates accountStates = new AccountStates();
                accountStates.id = object.get("id").getAsInt();
                try {
                    accountStates.rated = object.get("rated").getAsBoolean();
                } catch (Exception exc) {
                    accountStates.rated = true;
                    accountStates.rating = jsonDeserializationContext.deserialize(object.get("rated"), RatingObject.class);
                }
                if (object.get("favorite") != null) {
                    accountStates.favorite = object.get("favorite").getAsBoolean();
                    accountStates.watchlist = object.get("watchlist").getAsBoolean();
                }
                if (object.get("episode_number") != null)
                    accountStates.episode_number = object.get("episode_number").getAsInt();

                return accountStates;
            }
        });

        builder.registerTypeAdapter(Media.class, new JsonDeserializer<Media>() {

            @Override
            public Media deserialize(JsonElement jsonElement, Type type,
                                     JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                Media media = new Media();
                if (jsonElement.getAsJsonObject().get("media_type") != null) {
                    media.media_type = jsonDeserializationContext.deserialize(jsonElement.getAsJsonObject().get("media_type"), MediaType.class);
                } else {
                    if (jsonElement.getAsJsonObject().get("first_air_date") != null) {
                        media.media_type = MediaType.TV;
                    } else if (jsonElement.getAsJsonObject().get("name") != null) {
                        media.media_type = MediaType.PERSON;
                    } else if (jsonElement.getAsJsonObject().get("title") != null) {
                        media.media_type = MediaType.MOVIE;
                    }
                }
                switch (media.media_type) {
                    case MOVIE:
                        media.movie = jsonDeserializationContext.deserialize(jsonElement, BaseMovie.class);
                        break;
                    case TV:
                        media.tvShow = jsonDeserializationContext.deserialize(jsonElement, BaseTvShow.class);
                        break;
                    case PERSON:
                        media.person = jsonDeserializationContext.deserialize(jsonElement, BasePerson.class);
                        break;
                }


                return media;
            }
        });

        builder.registerTypeAdapter(PersonCastCredit.class, new JsonDeserializer<PersonCastCredit>() {

            @Override
            public PersonCastCredit deserialize(JsonElement jsonElement, Type type,
                                                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                PersonCastCredit personCastCredit = new PersonCastCredit();
                personCastCredit.media = jsonDeserializationContext.deserialize(jsonElement, Media.class);
                personCastCredit.character = jsonElement.getAsJsonObject().get("character").getAsString();
                personCastCredit.credit_id = jsonElement.getAsJsonObject().get("credit_id").getAsString();
                switch (personCastCredit.media.media_type) {
                    case TV:
                        personCastCredit.episode_count = jsonElement.getAsJsonObject().get("episode_count").getAsInt();
                        break;
                }

                return personCastCredit;
            }
        });

        builder.registerTypeAdapter(PersonCrewCredit.class, new JsonDeserializer<PersonCrewCredit>() {

            @Override
            public PersonCrewCredit deserialize(JsonElement jsonElement, Type type,
                                                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                PersonCrewCredit personCrewCredit = new PersonCrewCredit();
                personCrewCredit.media = jsonDeserializationContext.deserialize(jsonElement, Media.class);
                personCrewCredit.department = jsonElement.getAsJsonObject().get("department").getAsString();
                personCrewCredit.job = jsonElement.getAsJsonObject().get("job").getAsString();
                personCrewCredit.credit_id = jsonElement.getAsJsonObject().get("credit_id").getAsString();
                switch (personCrewCredit.media.media_type) {
                    case TV:
                        if (jsonElement.getAsJsonObject().get("episode_count") != null) {
                            personCrewCredit.episode_count = jsonElement.getAsJsonObject().get("episode_count").getAsInt();
                        }
                        break;
                }
                return personCrewCredit;
            }
        });

        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {
                try {
                    SimpleDateFormat sdf = TMDB_DATE_FORMAT.get();
                    if (sdf == null) {
                        sdf = new SimpleDateFormat(TMDB_DATE_PATTERN);
                        TMDB_DATE_FORMAT.set(sdf);
                    }
                    return sdf.parse(json.getAsString());
                } catch (ParseException e) {
                    // return null instead of failing (like default parser would)
                    return null;
                }
            }
        });


        builder.registerTypeAdapter(Status.class, new JsonDeserializer<Status>() {
            @Override
            public Status deserialize(JsonElement jsonElement, Type type,
                                      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                String value = jsonElement.getAsString();
                if (value != null) {
                    return Status.fromValue(value);
                } else {
                    return null;
                }
            }
        });

        return builder;
    }
}
