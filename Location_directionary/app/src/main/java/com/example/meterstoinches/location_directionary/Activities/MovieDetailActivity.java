package com.example.meterstoinches.location_directionary.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.meterstoinches.location_directionary.Model.Location;
import com.example.meterstoinches.location_directionary.R;
import com.example.meterstoinches.location_directionary.Util.Constant;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetailActivity extends AppCompatActivity {
    private Location movie;
    private TextView movieTitle;
    private ImageView movieImage;
    private TextView movieYear;
    private TextView director;
    private TextView actors;
    private TextView category;
    private TextView rating;
    private TextView plot;
    private TextView boxOffice;
    private TextView runtime;
    private TextView writers;

    private RequestQueue queue;
    private String movieId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        queue = Volley.newRequestQueue(this);
        movie = (Location) getIntent().getSerializableExtra("movie");
        movieId = movie.getImbdId();
        System.out.println("movieID"+movieId);
        SetUpUI();
        getMovieDetails(movieId);
    }

    private void getMovieDetails(String movieId) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constant.URL_Detail_LEFT + movieId, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray ratings = response.getJSONArray("Ratings");
                    String source =null;
                    String value = null;
                    if(ratings.length()>0){
                        JSONObject mRatings = ratings.getJSONObject(ratings.length()-1);
                        source = mRatings.getString("Source");
                        value = mRatings.getString("Value");
                        rating.setText(source +":" + value);
                    }
                    else{
                        rating.setText("Ratings: N/A");
                    }
                    movieTitle.setText(response.getString("Title"));
                    movieYear.setText("Released: " + response.getString("Released"));
                    director.setText("Director: " + response.getString("Director"));
                    writers.setText("Writer: " + response.getString("Writer"));
                    runtime.setText("Runtime: " + response.getString("Runtime"));
                    plot.setText("Plot: " + response.getString("Plot"));
                    actors.setText("Actors: " + response.getString("Actors"));
                    Picasso.with(getApplicationContext())
                            .load(response.getString("Poster"))
                            .into(movieImage);
                    boxOffice.setText("Box Office:" + response.getString("BoxOffice"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error:" , error.getMessage());
            }
        });
        queue.add(jsonObjectRequest);
    }

    private void SetUpUI() {
        movieTitle = (TextView) findViewById(R.id.movieTitleIDDetail);
        movieYear = (TextView) findViewById(R.id.movieReleaseIDDetail);
        director = (TextView) findViewById(R.id.movieDirect);
        category = (TextView) findViewById(R.id.movieCatIDDetail);
        rating = (TextView) findViewById(R.id.movieRatingDetail);
        writers = (TextView) findViewById(R.id.Writer);
        plot = (TextView) findViewById(R.id.Plot);
        boxOffice = (TextView) findViewById(R.id.Box_Office);
        actors = (TextView) findViewById(R.id.Actors);
        runtime = (TextView) findViewById(R.id.movieRuntime);

        movieImage = (ImageView) findViewById(R.id.movieImageIDDetail);
    }
}
