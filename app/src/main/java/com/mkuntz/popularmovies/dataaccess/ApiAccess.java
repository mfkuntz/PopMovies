package com.mkuntz.popularmovies.dataaccess;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mkuntz.popularmovies.R;

/**
 * Created by mkuntz on 9/21/15.
 */
public class ApiAccess {
    static final String LOG_TAG = ApiAccess.class.getSimpleName();

    static final String BASE_URL = "http://api.themoviedb.org/3";

    public static void Get(String url, Context context, final ApiCallback apiCallback) {


        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendEncodedPath(url)
                .appendQueryParameter("api_key", context.getString(R.string.movie_api_key))
                .appendQueryParameter("sort_by", "popularity.desc")
                .build();


        StringRequest request = new StringRequest(Request.Method.GET, uri.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        apiCallback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }

        );

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);


    }



    public interface ApiCallback {
        void onSuccess(String jsonString);
    }

}
