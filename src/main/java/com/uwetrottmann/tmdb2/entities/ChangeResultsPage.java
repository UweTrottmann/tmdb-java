package com.uwetrottmann.tmdb2.entities;

public class ChangeResultsPage extends BaseResultsPage<ChangeResultsPage.Change> {

    public static class Change {

        public Integer id;
        public Boolean adult;

    }

}
