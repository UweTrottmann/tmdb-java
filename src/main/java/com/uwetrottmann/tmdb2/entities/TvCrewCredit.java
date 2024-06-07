package com.uwetrottmann.tmdb2.entities;

import java.util.List;

public class TvCrewCredit extends BaseTvCredit {

    public List<Job> jobs;
    public String department;

    public static class Job {
        public String credit_id;
        public String job;
        public Integer episode_count;
    }

}

