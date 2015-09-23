package com.mkuntz.popularmovies;

import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.mkuntz.popularmovies.dataaccess.Discover;
import com.mkuntz.popularmovies.datamodel.Movie;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    CoverAdapter mCoverAdapter;
    GridView mListView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_main, container, false);

        ArrayList<Movie> defaultMovies = new ArrayList<>();

        mCoverAdapter = new CoverAdapter(getActivity(), defaultMovies);

        mListView = (GridView) v.findViewById(R.id.list_covers);
        mListView.setAdapter(mCoverAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie m = (Movie) parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra(DetailActivityFragment.MOVIE_KEY, m.getId());

                startActivity(intent);
            }
        });

        loadData();

        return v;
    }

    private void loadData(){
        Discover.Discover(getActivity(), new Discover.MovieCallback() {
            @Override
            public void onSuccess(ArrayList<Movie> movies) {
                mCoverAdapter.addAll(movies);
            }

            @Override
            public void onError() {

            }
        });
    }




//    class DiscoverTask extends AsyncTask<Void, Void, ArrayList<Movie>>{
//
//        @Override
//        protected ArrayList<Movie> doInBackground(Void... params) {
//            return Discover.Discover(getActivity());
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<Movie> movies) {
//            mCoverAdapter.addAll(movies);
//        }
//    }

}
