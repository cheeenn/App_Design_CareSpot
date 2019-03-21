package com.example.meterstoinches.http_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //private final static String URL_string ="file:///C:/Users/毛宸/Desktop/web/新建文本文档.html";
    private final static String URL = "https://jsonplaceholder.typicode.com/posts";
    private final static String URL_EQ = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/1.0_hour.geojson";
    //https://jsonplaceholder.typicode.com/posts
    public RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        //getStringObject(URL_string);
        getJsonObject(URL_EQ);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                       for(int i =0; i<response.length(); i++){
                           try {
                               JSONObject movieObject = response.getJSONObject(i);
                               Log.d("show title:", movieObject.getString("title"));
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }

                       }
                        // Log.d("This is response:",response.toString());
                    }
                }, new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("error",error.getMessage());
                }
        });
        queue.add(arrayRequest);
    }

    private void getJsonObject(String urlEq) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                urlEq, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("Object: ",response.getString("type").toString());
                    JSONObject metadata = response.getJSONObject("metadata");
                    Log.d("Metadata", metadata.toString());
                    Log.d("Meta info", metadata.getString("url"));
                    //jsonArray
                    JSONArray features = response.getJSONArray("features");
                    for(int i =0; i<features.length();i++){
                        //get objects
                        JSONObject typeobj = features.getJSONObject(i).getJSONObject("geometry");
                        JSONObject propertiesObj = features.getJSONObject(i).getJSONObject("properties");
                        Log.d("place:", propertiesObj.getString("place"));
                        Log.d("code:", propertiesObj.getString("code"));
                        Log.d("type:", typeobj.getString("type"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }
//    public void getStringObject(String url){
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("My String", response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        queue.add(stringRequest);
//    }
}
