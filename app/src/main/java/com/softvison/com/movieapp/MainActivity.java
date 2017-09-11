package com.softvison.com.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.MoviesList;
import network.ApiUtil;
import network.MovieServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView movesListRecycleView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<MoviesList.Result> moviesLists=new ArrayList<>();
    private  MovieServer movieServer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        movieServer= ApiUtil.getMovieServer();
        getMovieList();




    }

    private void getMovieList() {
        movieServer.getMoviesList().enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                if (response.isSuccessful()){
                    showUI( moviesLists=response.body().getResults());

                }
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {

            }
        });

    }

    private void showUI(List<MoviesList.Result> results) {
        movesListRecycleView= (RecyclerView) findViewById(R.id.rv_movies_list);
        movesListRecycleView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        movesListRecycleView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        movesListRecycleView.addItemDecoration(itemDecoration);
        // specify an adapter (see also next example)
        mAdapter = new MoviesDealAdapter(this,results, new MoviesDealAdapter.PostItemListener() {
            @Override
            public void onPostClick(long id) {

            }
        });
        movesListRecycleView.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_share:
                // getProductDetails(dealNumber);
                shareGrouDeal();
                return  true;


        }
        return super.onOptionsItemSelected(item);
    }

    private void shareGrouDeal() {
        String shareGroudeal="share moview";


        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,shareGroudeal );
        sendIntent.setType("text/plain");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(sendIntent, "Share"));
        } else {
            Toast.makeText(MainActivity.this, "No apps found", Toast.LENGTH_LONG).show();
        }
    }


}
