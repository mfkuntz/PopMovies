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
public class DADiscover {

    static final String DISCOVER_URL = "discover/movie";

    public static void Discover(Context context, final MovieCallback callback){

        final ArrayList<Movie> movies =  new ArrayList<>();


        ApiAccess.Get(DISCOVER_URL, context, new ApiAccess.ApiCallback() {
            @Override
            public void onSuccess(String jsonString) {
                if (jsonString == null) return;

                try {
                    JSONObject jsonRoot = new JSONObject(jsonString);

                    JSONArray resultArray = jsonRoot.getJSONArray("results");

                    for (int i = 0; i < 12; i++) {

                        JSONObject jsonObject = (JSONObject) resultArray.get(i);

                        movies.add(new Movie(jsonObject));

                    }

                    callback.onSuccess(movies);

                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onError();
                }

            }
        });

    }

    public interface MovieCallback {
        void onSuccess(ArrayList<Movie> movies);
        void onError();
    }

}
