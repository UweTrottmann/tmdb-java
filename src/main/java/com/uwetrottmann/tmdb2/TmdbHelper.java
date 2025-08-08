// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.uwetrottmann.tmdb2.entities.AccountStates;
import com.uwetrottmann.tmdb2.entities.BaseAccountStates;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BasePerson;
import com.uwetrottmann.tmdb2.entities.BaseTvShow;
import com.uwetrottmann.tmdb2.entities.Media;
import com.uwetrottmann.tmdb2.entities.PersonCastCredit;
import com.uwetrottmann.tmdb2.entities.PersonCrewCredit;
import com.uwetrottmann.tmdb2.entities.RatingObject;
import com.uwetrottmann.tmdb2.entities.Trending;
import com.uwetrottmann.tmdb2.enumerations.EpisodeType;
import com.uwetrottmann.tmdb2.enumerations.MediaType;
import com.uwetrottmann.tmdb2.enumerations.Status;
import com.uwetrottmann.tmdb2.enumerations.VideoType;
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
        builder.registerTypeAdapter(Integer.class,
                (JsonDeserializer<Integer>) (json, typeOfT, context) -> json.getAsInt());

        builder.registerTypeAdapter(MediaType.class,
                (JsonDeserializer<MediaType>) (json, typeOfT, context) -> MediaType.get(json.getAsString()));

        builder.registerTypeAdapter(VideoType.class,
                (JsonDeserializer<VideoType>) (json, typeOfT, context) -> VideoType.get(json.getAsString()));

        builder.registerTypeAdapter(BaseAccountStates.class,
                (JsonDeserializer<BaseAccountStates>) (jsonElement, type, jsonDeserializationContext) -> {
                    JsonObject object = jsonElement.getAsJsonObject();
                    BaseAccountStates accountStates = new BaseAccountStates();
                    deserializeBaseAccountStates(jsonDeserializationContext, object, accountStates);

                    return accountStates;
                });

        builder.registerTypeAdapter(AccountStates.class,
                (JsonDeserializer<AccountStates>) (jsonElement, type, jsonDeserializationContext) -> {
                    JsonObject object = jsonElement.getAsJsonObject();
                    AccountStates accountStates = new AccountStates();
                    deserializeBaseAccountStates(jsonDeserializationContext, object, accountStates);
                    if (object.get("favorite") != null) {
                        accountStates.favorite = object.get("favorite").getAsBoolean();
                        accountStates.watchlist = object.get("watchlist").getAsBoolean();
                    }
                    if (object.get("episode_number") != null) {
                        accountStates.episode_number = object.get("episode_number").getAsInt();
                    }

                    return accountStates;
                });

        builder.registerTypeAdapter(Media.class,
                (JsonDeserializer<Media>) (jsonElement, type, jsonDeserializationContext) -> {
                    Media media = new Media();
                    if (jsonElement.getAsJsonObject().get("media_type") != null) {
                        media.media_type = jsonDeserializationContext
                                .deserialize(jsonElement.getAsJsonObject().get("media_type"), MediaType.class);
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
                });

        builder.registerTypeAdapter(PersonCastCredit.class,
                (JsonDeserializer<PersonCastCredit>) (jsonElement, type, jsonDeserializationContext) -> {
                    PersonCastCredit personCastCredit = new PersonCastCredit();
                    personCastCredit.media = jsonDeserializationContext.deserialize(jsonElement, Media.class);
                    JsonElement character = jsonElement.getAsJsonObject().get("character");
                    if (character != null) {
                        personCastCredit.character = character.getAsString();
                    }
                    JsonElement creditId = jsonElement.getAsJsonObject().get("credit_id");
                    if (creditId != null) {
                        personCastCredit.credit_id = creditId.getAsString();
                    }
                    if (personCastCredit.media.media_type == MediaType.TV) {
                        personCastCredit.episode_count =
                                jsonElement.getAsJsonObject().get("episode_count").getAsInt();
                    }

                    return personCastCredit;
                });

        builder.registerTypeAdapter(PersonCrewCredit.class,
                (JsonDeserializer<PersonCrewCredit>) (jsonElement, type, jsonDeserializationContext) -> {
                    PersonCrewCredit personCrewCredit = new PersonCrewCredit();
                    personCrewCredit.media = jsonDeserializationContext.deserialize(jsonElement, Media.class);
                    personCrewCredit.department = jsonElement.getAsJsonObject().get("department").getAsString();
                    personCrewCredit.job = jsonElement.getAsJsonObject().get("job").getAsString();
                    personCrewCredit.credit_id = jsonElement.getAsJsonObject().get("credit_id").getAsString();
                    if (personCrewCredit.media.media_type == MediaType.TV) {
                        if (jsonElement.getAsJsonObject().get("episode_count") != null) {
                            personCrewCredit.episode_count =
                                    jsonElement.getAsJsonObject().get("episode_count").getAsInt();
                        }
                    }
                    return personCrewCredit;
                });

        builder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> {
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
        });

        builder.registerTypeAdapter(Status.class,
                (JsonDeserializer<Status>) (jsonElement, type, jsonDeserializationContext) -> {
                    String value = jsonElement.getAsString();
                    if (value != null) {
                        return Status.fromValue(value);
                    } else {
                        return null;
                    }
                });

        builder.registerTypeAdapter(Trending.class,
                (JsonDeserializer<Trending>) (jsonElement, type, jsonDeserializationContext) -> {
                    Trending trending = new Trending();
                    trending.media_type = jsonDeserializationContext
                            .deserialize(jsonElement.getAsJsonObject().get("media_type"), MediaType.class);
                    switch (trending.media_type) {
                        case MOVIE:
                            trending.movie = jsonDeserializationContext.deserialize(jsonElement, BaseMovie.class);
                            break;
                        case TV:
                            trending.tvShow = jsonDeserializationContext.deserialize(jsonElement, BaseTvShow.class);
                            break;
                        case PERSON:
                            trending.person = jsonDeserializationContext.deserialize(jsonElement, BasePerson.class);
                            break;
                    }
                    return trending;
                });

        builder.registerTypeAdapter(EpisodeType.class,
                (JsonDeserializer<EpisodeType>) (jsonElement, type, jsonDeserializationContext) -> {
                    String value = jsonElement.getAsString();
                    if (value != null) {
                        return EpisodeType.fromValue(value);
                    } else {
                        return null;
                    }
                });

        return builder;
    }

    private static void deserializeBaseAccountStates(JsonDeserializationContext context, JsonObject object,
            BaseAccountStates accountStates) {
        accountStates.id = object.get("id").getAsInt();
        try {
            accountStates.rated = object.get("rated").getAsBoolean();
        } catch (Exception exc) {
            accountStates.rated = true;
            accountStates.rating = context.deserialize(object.get("rated"), RatingObject.class);
        }
    }
}
