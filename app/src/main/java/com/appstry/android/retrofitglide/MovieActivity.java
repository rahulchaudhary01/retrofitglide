package com.appstry.android.retrofitglide;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.CustomAdapter;
import networking.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofitusage.MovieService;

public class MovieActivity extends AppCompatActivity {


    private ListView listView;
    private CustomAdapter movieAdapter;
    public RecyclerView recList;
    public RecyclerView.Adapter mAdapter;
    private ArrayList<com.appstry.android.retrofitglide.Movies> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_moviecards);
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.movieJsonUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieService myService = retrofit.create(MovieService.class);
        Call<MovieDetails> call = myService.getMoviesList();
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                loading.dismiss();
                movies = response.body().getMovies();
                recList = (RecyclerView) findViewById(R.id.cardListMovies);
                recList.setHasFixedSize(true);
                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recList.setLayoutManager(llm);
                mAdapter = new CustomAdapter(movies);
                recList.setAdapter(null);
                recList.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                // Log error here since request failed
            }
        });
    }
}
