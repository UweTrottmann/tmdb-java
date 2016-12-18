package com.uwetrottmann.tmdb2.entities;

import java.util.List;

public class TaggedImagesResultsPage {
    public class TaggedImage extends Image {
        public String id;
        public String image_type;
        public String media_type;
        public Media media;
    }

    public int id;
    public int page;
    public List<TaggedImage> results;
}
