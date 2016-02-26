package com.uwetrottmann.tmdb.entities;

import java.util.List;

/**
 * Creado por jcvallejo en 26/2/16.
 */
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
