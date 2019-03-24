package com.example.meterstoinches.caresupport.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.meterstoinches.caresupport.Data.LocationRecyclerViewAdapter;
import com.example.meterstoinches.caresupport.Model.Location;
import com.example.meterstoinches.caresupport.R;
import com.example.meterstoinches.caresupport.Util.Contants;
import com.example.meterstoinches.caresupport.Util.Prefs;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Map_Search_Activity_a10 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LocationRecyclerViewAdapter locationRecyclerViewAdapter;
    private List<Location> movieList;
    private RequestQueue queue;

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_search_a10);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        queue = Volley.newRequestQueue(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                showInputDialog();
            }
        });
        recyclerView =(RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Prefs preference = new Prefs(Map_Search_Activity_a10.this);
        String search = preference.getSearch();
        movieList=new ArrayList<>();
        //getMovies(search);

        //set the adapter
        movieList =getMovies(search);
        System.out.println("this movie list is" + movieList.size());
        locationRecyclerViewAdapter = new LocationRecyclerViewAdapter(this,movieList);
        recyclerView.setAdapter(locationRecyclerViewAdapter);
        locationRecyclerViewAdapter.notifyDataSetChanged();
    }

    //GET movies
    public List<Location> getMovies(String searchTerm){
        movieList.clear();
        //String url = Contants.URL_LEFT + searchTerm + Contants.URL_RIGHT;
        
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Contants.URL_LEFT + searchTerm + Contants.URL_RIGHT, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray moviesArray = response.getJSONArray("Search");
                    for (int i = 0; i < moviesArray.length(); i++) {
                        JSONObject movieObj = moviesArray.getJSONObject(i);
                        Location movie = new Location();
                        movie.setTitle(movieObj.getString("Title"));
                        movie.setYear("Year Released: "+movieObj.getString("Year"));
                        movie.setMovieType("Type: "+movieObj.getString("Type"));
                        movie.setPoster(movieObj.getString("Poster"));
                        movie.setImbdId(movieObj.getString("imdbID"));
                        movieList.add(movie);
                        System.out.println("inside movie list len" + movieList.size());
                        //Log.d("movies", movie.getTitle());
                    }
                    locationRecyclerViewAdapter.notifyDataSetChanged();//very important
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // never forget to do the queue
        queue.add(jsonObjectRequest);
        System.out.println("after process length " + movieList.size());
        return movieList;
    }

    public  void showInputDialog(){
        alertDialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_row,null);
        final EditText newSearchEdt = (EditText) view.findViewById(R.id.searchEdt_a3);
        Button submitButton = (Button) view.findViewById(R.id.submitButton_a3);
        alertDialogBuilder.setView(view);
        dialog = alertDialogBuilder.create();
        dialog.show();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs prefence = new Prefs(Map_Search_Activity_a10.this);
                if(!newSearchEdt.getText().toString().isEmpty()){
                    String search = newSearchEdt.getText().toString();
                    prefence.setSearch((search));
                    movieList.clear();
                    getMovies(search);
                    //locationRecyclerViewAdapter.notifyDataSetChanged();//very important
                }
                dialog.dismiss();
                //recreate();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_m,menu);
        return true;
    }
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.new_search){
            showInputDialog();
            //return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
