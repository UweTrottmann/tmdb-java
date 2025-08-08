// SPDX-License-Identifier: Apache-2.0
// Copyright 2025 Uwe Trottmann

package com.uwetrottmann.tmdb2.entities;

import java.util.List;

public class TvEpisode extends BaseTvEpisode {

    public List<CrewMember> crew;

    public List<CastMember> guest_stars;


    public Images images;
    public TvEpisodeExternalIds external_ids;
    public Credits credits;
    public Videos videos;
    public Translations translations;

}
