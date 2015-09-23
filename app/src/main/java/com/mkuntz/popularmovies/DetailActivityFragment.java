package com.mkuntz.popularmovies;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mkuntz.popularmovies.dataaccess.Discover;
import com.mkuntz.popularmovies.datamodel.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public static final String MOVIE_KEY = "movieID";

    ViewHolder viewHolder;

    int moviewId;

    public DetailActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        moviewId = getActivity().getIntent().getIntExtra(MOVIE_KEY, 0);
    }

    private void loadData(){
        Discover.Discover(getActivity(), new Discover.MovieCallback() {
            @Override
            public void onSuccess(ArrayList<Movie> movies) {

                for (int i = 0; i < movies.size(); i++){
                    if (movies.get(i).getId() == moviewId){
                        buildView(movies.get(i));
                    }
                }

            }

            @Override
            public void onError() {

            }
        });
    }

    private void buildView(Movie m){

        getActivity().setTitle(m.getName());

        viewHolder.Title.setText(m.getName());

        Picasso.with(getActivity()).load(m.getCoverUrl()).into(viewHolder.Poster);

    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        if (savedInstanceState == null){
            viewHolder = new ViewHolder();

            viewHolder.Poster = (ImageView) v.findViewById(R.id.cover_image);
            viewHolder.Title = (TextView) v.findViewById(R.id.title_text);
            v.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) v.getTag();
        }

        loadData();

        return v;
    }


    private static class ViewHolder{
        ImageView Poster;
        TextView Title;
    }
}
