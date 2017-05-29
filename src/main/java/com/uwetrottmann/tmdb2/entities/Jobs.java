package com.uwetrottmann.tmdb2.entities;

import java.util.List;

public class Jobs {

    public static class Department {

        public String department;
        public List<String> job_list;

    }
    
    public List<Department> jobs;

}
