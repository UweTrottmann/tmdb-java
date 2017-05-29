package com.uwetrottmann.tmdb2.entities;

import java.util.HashMap;
import java.util.List;

public class Certifications {

    public static class Certification {

        public String certification;
        public String meaning;
        public Integer order;

    }

    public HashMap<String, List<Certification>> certifications;

}
