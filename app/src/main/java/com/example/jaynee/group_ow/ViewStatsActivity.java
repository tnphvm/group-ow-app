package com.example.jaynee.group_ow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.*;

import org.w3c.dom.Text;

/*
 * Networking Help: https://kylewbanks.com/blog/Implementing-Google-Plus-Style-ListView-Animations-on-Android
 */
public class ViewStatsActivity extends AppCompatActivity
{
    private RequestQueue requestQueue;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats);

        String user = getIntent().getStringExtra("battleID");
        String urlStr = "https://api.lootbox.eu/pc/us/" + user + "/competitive/allHeroes/";

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        requestQueue = Volley.newRequestQueue(this);
        fetchPosts(urlStr);
    }

    private void fetchPosts(String ENDPOINT)
    {
        StringRequest request = new StringRequest(Request.Method.GET, ENDPOINT,
                onPostsLoaded, onPostsError);

        requestQueue.add(request);
    }

    // Action when network request is successful
    private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>()
    {
        @Override
        public void onResponse(String response)
        {
//            Log.i("PostActivity", response);
            TextView soloKills = (TextView) findViewById(R.id.solo_kills);
            Stats stats = gson.fromJson(response, Stats.class);

            soloKills.setText(stats.soloKills + ": " + stats.SoloKills);
            Log.i("PostActivity", "Solo Kills: " + stats.SoloKills);
        }
    };

    // Error for when network request fails
    private final Response.ErrorListener onPostsError = new Response.ErrorListener()
    {
        @Override
        public void onErrorResponse(VolleyError error)
        {
            Log.e("PostActivity", error.toString());
        }
    };

//    @Override
//    public void onBackPressed()
//    {
//        // Write your code here
//
//        super.onBackPressed();
//    }
}
