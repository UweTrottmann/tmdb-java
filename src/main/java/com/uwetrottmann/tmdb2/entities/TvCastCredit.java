package com.uwetrottmann.tmdb2.entities;

import java.util.List;

public class TvCastCredit extends BaseTvCredit {

    public List<Role> roles;
    public Integer order;

    public static class Role {
        public String credit_id;
        public String character;
        public Integer episode_count;
    }

}
