package com.mkuntz.popularmovies.datamodel;

/**
 * Created by mkuntz on 9/21/15.
 */
public class Movie {

    private final String BASE_URL = "http://image.tmdb.org/t/p";
    private final String SIZE = "/w342";

    public static final String COVER_API = "poster_path";
    public static final String ID_API = "id";
    public static final String TITLE_API = "title";

    public Movie(Builder b){
        id = b.id;
        name = b.name;
        coverUrl = b.coverUrl;
    }

    private String coverUrl;
    private String name;
    private int id;

    public String getCoverUrl() {
        return BASE_URL + SIZE + coverUrl;
    }

    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }

    public static class Builder{
        private String coverUrl = "";
        private String name = "";

        public Builder coverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        private int id;

        public Builder(int id){
            this.id = id;
        }

        public Movie build(){
            return new Movie(this);
        }
    }

}
