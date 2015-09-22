package com.mkuntz.popularmovies.datamodel;

/**
 * Created by mkuntz on 9/21/15.
 */
public class Movie {

    private final String BASE_URL = "http://image.tmdb.org/t/p";
    private final String SIZE = "/w342";

    public static final String COVER_API = "poster_path";

    public Movie(){
    }

    private String coverUrl;

    public String getCoverUrl() {
        return BASE_URL + SIZE + coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
