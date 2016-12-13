package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appstry.android.retrofitglide.Movies;
import com.appstry.android.retrofitglide.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by RAHUL CHAUDHARY on 11/15/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<Movies> movieSet;
    Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView posterPathView;
        TextView release_dateView;
        TextView titleView;
        TextView popularityView;
        TextView vote_countView;
        ImageButton vote_average;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.posterPathView = (ImageView) itemView.findViewById(R.id.posterpath);
            this.release_dateView = (TextView) itemView.findViewById(R.id.releasedate);
            this.titleView = (TextView) itemView.findViewById(R.id.title);
            this.popularityView = (TextView) itemView.findViewById(R.id.popularity);
            this.vote_countView = (TextView) itemView.findViewById(R.id.votecount);
            this.vote_average = (ImageButton) itemView.findViewById(R.id.star_vote_average);
        }
    }

    public CustomAdapter(ArrayList<Movies> movieData) {
        this.movieSet = movieData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        mContext = parent.getContext();
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String posterPathUrl = "http://image.tmdb.org/t/p/w500/" + movieSet.get(position).getPosterPath();
        Glide.with(mContext)
                .load(posterPathUrl)
                .into(holder.posterPathView);
        holder.release_dateView.setText("Release Date : " + movieSet.get(position).getReleaseDate());
        holder.titleView.setText(movieSet.get(position).getTitle());
        holder.popularityView.setText("Popularity : " + movieSet.get(position).getPopularity().toString());
        holder.vote_countView.setText("Votes : " + movieSet.get(position).getVoteCount().toString());
        holder.vote_average.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Vote Average for movie : " + movieSet.get(position).getVoteAverage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieSet.size();
    }
}
