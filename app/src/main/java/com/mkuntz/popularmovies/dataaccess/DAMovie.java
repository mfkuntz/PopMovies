package com.mkuntz.popularmovies.dataaccess;

import android.content.Context;

import com.mkuntz.popularmovies.datamodel.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mkuntz on 9/21/15.
 */
public class DAMovie {

    static final String MOVIE_URL = "movie/%d";

    public static void Get(Context context, int movieId, final MovieCallback callback){

        String url = String.format(MOVIE_URL, movieId);

        ApiAccess.Get(url, context, new ApiAccess.ApiCallback() {
            @Override
            public void onSuccess(String jsonString) {
                if (jsonString == null) return;

                try {
                    JSONObject jsonRoot = new JSONObject(jsonString);

                    Movie movie =  new Movie(jsonRoot);

                    callback.onSuccess(movie);

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onError();
                }

            }
        });

    }

    public interface MovieCallback {
        void onSuccess(com.mkuntz.popularmovies.datamodel.Movie movie);
        void onError();
    }

}
