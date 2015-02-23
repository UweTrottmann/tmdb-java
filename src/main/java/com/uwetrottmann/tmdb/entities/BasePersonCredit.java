/*
 * Copyright 2014 Uwe Trottmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uwetrottmann.tmdb.entities;

import java.util.Date;

public abstract class BasePersonCredit {

    public String credit_id;
    public Integer id;
    public String media_type;

    // both
    public String poster_path;

    // movies
    public Boolean adult;
    public Date release_date;
    public String title;
    public String original_title;

    // tv
    public Date first_air_date;
    public String name;
    public String original_name;

}
