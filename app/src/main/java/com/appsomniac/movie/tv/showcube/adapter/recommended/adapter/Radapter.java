package com.appsomniac.movie.tv.showcube.adapter.recommended.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.appsomniac.movie.tv.showcube.R;
import com.appsomniac.movie.tv.showcube.adapter.recommended.viewHolder.RviewHolder;
import com.appsomniac.movie.tv.showcube.model.Movie;
import com.appsomniac.movie.tv.showcube.model.Tv;

import java.util.ArrayList;
public class Radapter extends RecyclerView.Adapter<RviewHolder> {

    private static ArrayList<Movie> al_movies;
    private Context context;
    private static ArrayList<Tv> al_tv;
    private static String movieORtv;

    public Radapter(Context context, ArrayList<Movie> al_movies, String movieORtv) {

        this.context = context;
        this.al_movies = al_movies;
        this.movieORtv = movieORtv;
        al_tv = new ArrayList<>();//to avoid null of al_movies.size()
    }

    public Radapter(ArrayList<Tv> al_tv, Context context, String movieORtv){

        this.context = context;
        this.al_tv = al_tv;
        this.movieORtv = movieORtv;
        al_movies = new ArrayList<>();//to avoid null of al_movies.size()
    }

    @Override
    public RviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(al_tv.size()==0) {
            return new RviewHolder(LayoutInflater.from(parent.getContext()), parent, al_movies);
        }else{
            return new RviewHolder(LayoutInflater.from(parent.getContext()), al_tv, parent);
        }
    }

    @Override
    public void onBindViewHolder(RviewHolder holder, int position) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.color.background_lightish);
        requestOptions.error(R.color.background_lightish);

        if(movieORtv.equals("movie")) {

            String posterBaseUrl = "http://image.tmdb.org/t/p/w185/" + al_movies.get(position).getPosterPath();

            Glide.with(context).load(posterBaseUrl).apply(requestOptions).thumbnail(0.5f).into(holder.media_poster);

            try {
                //holder.title.setText(al_movies.get(position).getTitle());

            } catch (IndexOutOfBoundsException e) {

                e.printStackTrace();
            }
        }else
            if(movieORtv.equals("tv")){


                String posterBaseUrl = "http://image.tmdb.org/t/p/w185/" + al_tv.get(position).getPosterPath();

                Glide.with(context).load(posterBaseUrl).apply(requestOptions).thumbnail(0.5f).into(holder.media_poster);

                try {
                    //holder.title.setText(al_tv.get(position).getTitle());

                } catch (IndexOutOfBoundsException e) {

                    e.printStackTrace();
                }
            }

        Log.e("position holder: ", String.valueOf(position));

    }
    @Override
    public int getItemCount() {

        if(al_tv.size()==0) {
            return al_movies.size();
        }else
        {
            return al_tv.size();
        }
    }

}
