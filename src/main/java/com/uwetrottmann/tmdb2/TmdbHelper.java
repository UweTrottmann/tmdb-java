package com.uwetrottmann.tmdb2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.uwetrottmann.tmdb2.entities.Account;
import com.uwetrottmann.tmdb2.entities.AccountStates;
import com.uwetrottmann.tmdb2.entities.BaseAccountStates;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BasePerson;
import com.uwetrottmann.tmdb2.entities.BaseTvShow;
import com.uwetrottmann.tmdb2.enumerations.ReleaseType;
import com.uwetrottmann.tmdb2.utils.Country;
import com.uwetrottmann.tmdb2.utils.Language;
import com.uwetrottmann.tmdb2.entities.Media;
import com.uwetrottmann.tmdb2.entities.PersonCastCredit;
import com.uwetrottmann.tmdb2.entities.PersonCrewCredit;
import com.uwetrottmann.tmdb2.entities.RatingObject;
import com.uwetrottmann.tmdb2.utils.TmdbDate;
import com.uwetrottmann.tmdb2.utils.TmdbLocale;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.MediaType;
import com.uwetrottmann.tmdb2.enumerations.VideoType;

import java.lang.reflect.Type;

public class TmdbHelper {

    private static abstract class CombinedSerializer<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    }
    private static Boolean isUpperCase(String str) {
        for (Character chr : str.toCharArray()) {
            if (!Character.isUpperCase(chr)) {
                return false;
            }
        }
        return true;
    }

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

        builder.registerTypeAdapter(ReleaseType.class, new JsonDeserializer<ReleaseType>() {
            @Override
            public ReleaseType deserialize(JsonElement json, Type typeOfT,
                                       JsonDeserializationContext context) throws JsonParseException {
                return ReleaseType.get(json.getAsInt());
            }
        });

        builder.registerTypeAdapter(Account.class, new JsonDeserializer<Account>() {
            @Override
            public Account deserialize(JsonElement json, Type typeOfT,
                                        JsonDeserializationContext context) throws JsonParseException {
                Account account = new Gson().fromJson(json,Account.class);

                account.locale = context.deserialize(json,TmdbLocale.class);

                return account;
            }
        });

        builder.registerTypeAdapter(Videos.Video.class, new JsonDeserializer<Videos.Video>() {
            @Override
            public Videos.Video deserialize(JsonElement json, Type typeOfT,
                                       JsonDeserializationContext context) throws JsonParseException {

                Videos.Video video = new Gson().fromJson(json,Videos.Video.class);
                video.type = context.deserialize(json.getAsJsonObject().get("type"),VideoType.class);
                video.locale = context.deserialize(json,TmdbLocale.class);

                return video;
            }
        });

        builder.registerTypeAdapter(Language.class, new JsonDeserializer<Language>() {
            @Override
            public Language deserialize(JsonElement json, Type typeOfT,
                                         JsonDeserializationContext context) throws JsonParseException {
                String lang;
                try {
                    JsonObject object = json.getAsJsonObject();
                    lang = object.get("iso_639_1").getAsString();
                } catch (Exception exc) {
                    lang = json.getAsString();
                }

                return new Language(new TmdbLocale(lang));
            }
        });

        builder.registerTypeAdapter(Country.class, new JsonDeserializer<Country>() {
            @Override
            public Country deserialize(JsonElement json, Type typeOfT,
                                         JsonDeserializationContext context) throws JsonParseException {
                String cntry;
                try {
                    JsonObject object = json.getAsJsonObject();
                    cntry = object.get("iso_3166_1").getAsString();
                } catch (Exception exc) {
                    cntry = json.getAsString();
                }

                Country country = null;
                if (!cntry.equals("")) {
                    country = new Country(new TmdbLocale("",cntry));
                }

                return country;
            }
        });

        builder.registerTypeAdapter(TmdbLocale.class, new CombinedSerializer<TmdbLocale>() {
            @Override
            public JsonElement serialize(TmdbLocale src, Type typeOfSrc, JsonSerializationContext context) {

                return new JsonPrimitive(src.toString());
            }

            @Override
            public TmdbLocale deserialize(JsonElement json, Type typeOfT,
                                         JsonDeserializationContext context) throws JsonParseException {
                TmdbLocale locale;
                try {
                    JsonObject object = json.getAsJsonObject();

                    String language = "";
                    if (object.has("iso_639_1")) {
                        language = object.get("iso_639_1").getAsString();
                    }

                    String country = "";
                    if (object.has("iso_3166_1")) {
                        country = object.get("iso_3166_1").getAsString();
                    }

                    locale = new TmdbLocale(language,country);
                } catch (Exception exc) {

                    String input = json.getAsString();

                    if (isUpperCase(input)) {
                        locale = new TmdbLocale("",input);
                    }
                    else {
                        locale = new TmdbLocale(input);
                    }
                }

                return locale;
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

        builder.registerTypeAdapter(TmdbDate.class, new JsonDeserializer<TmdbDate>() {
            @Override
            public TmdbDate deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {
                return new TmdbDate(json.getAsString());
            }
        });

        return builder;
    }
}
