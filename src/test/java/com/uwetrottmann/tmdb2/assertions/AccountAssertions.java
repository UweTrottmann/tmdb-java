package com.uwetrottmann.tmdb2.assertions;

import com.uwetrottmann.tmdb2.entities.AccountStates;
import com.uwetrottmann.tmdb2.entities.BaseAccountStates;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountAssertions {

    public static void assertBaseAccountStates(BaseAccountStates baseAccountStates) {
        assertThat(baseAccountStates).isNotNull();
        assertThat(baseAccountStates.id).isNotNull();
        assertThat(baseAccountStates.rated).isNotNull();
        if (baseAccountStates.rated) {
            assertThat(baseAccountStates.rating).isNotNull();
            assertThat(baseAccountStates.rating.value).isNotNull();
        }
    }

    public static void assertAccountStates(AccountStates accountStates) {
        assertBaseAccountStates(accountStates);
        assertThat(accountStates.favorite).isNotNull();
        assertThat(accountStates.watchlist).isNotNull();

    }
}
