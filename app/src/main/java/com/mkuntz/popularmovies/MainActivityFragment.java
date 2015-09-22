package com.mkuntz.popularmovies;

import android.content.AsyncTaskLoader;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mkuntz.popularmovies.dataaccess.Discover;
import com.mkuntz.popularmovies.datamodel.Movie;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    CoverAdapter mCoverAdapter;
    ListView mListView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_main, container, false);

        ArrayList<Movie> defaultMovies = new ArrayList<>();

        mCoverAdapter = new CoverAdapter(getActivity(), defaultMovies);

        mListView = (ListView) v.findViewById(R.id.list_covers);
        mListView.setAdapter(mCoverAdapter);

//        new DiscoverTask().doInBackground();

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
