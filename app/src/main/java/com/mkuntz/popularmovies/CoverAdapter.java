package com.mkuntz.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.mkuntz.popularmovies.datamodel.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mkuntz on 9/21/15.
 */
public class CoverAdapter extends ArrayAdapter<Movie> {

    public CoverAdapter(Context context, ArrayList<Movie> data) {
        super(context, 0, data);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CoverViewHolder coverViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cover_list_item, parent, false);
            coverViewHolder = new CoverViewHolder();

            coverViewHolder.CoverImage = (ImageView) convertView.findViewById(R.id.cover_image);
            convertView.setTag(coverViewHolder);

        }else{
            coverViewHolder = (CoverViewHolder) convertView.getTag();
        }

        Movie movie = getItem(position);


        Picasso.with(getContext()).load(movie.getCoverUrl()).into(coverViewHolder.CoverImage);

        return convertView;
    }

    private static class CoverViewHolder {
        ImageView CoverImage;
    }
}
