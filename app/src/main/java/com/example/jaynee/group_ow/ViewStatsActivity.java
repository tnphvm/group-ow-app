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

/**
 * ViewStatsActivity class is the new activity that is launched from the main activity when the user
 * selects a user to view his or her game stats. This activity displays all of the user's overall competitive
 * competitive stats that is pulled from a web API. The API sends the stats in a JSON file, parsed using
 * GSON and Volley, and then the individual stats are stored into its respective variables in the Stats
 * class.
 *
 * Networking Help: https://kylewbanks.com/blog/Implementing-Google-Plus-Style-ListView-Animations-on-Android
 */
public class ViewStatsActivity extends AppCompatActivity
{
    private RequestQueue requestQueue;
    private Gson gson;

    /**
     * Initializes the activity and stores the battle.net ID of the user passed from the previous
     * activity and find his or her game data.
     *
     * @param savedInstanceState Data stored about the app's previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats);

        String user = getIntent().getStringExtra("battleID");
        String urlStr = "https://api.lootbox.eu/pc/us/" + user + "/competitive/allHeroes/";

        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        requestQueue = Volley.newRequestQueue(this);
        fetchPosts(urlStr);
    }

    /**
     * This method will attempt to connect to the API's request URL for the individual data on
     * overall competitive play.
     *
     * @param ENDPOINT The URL request to make a connection to
     */
    private void fetchPosts(String ENDPOINT)
    {
        StringRequest request = new StringRequest(Request.Method.GET, ENDPOINT,
                onPostsLoaded, onPostsError);

        requestQueue.add(request);
    }

    /**
     * When a successful connection has been made, this method displays the player's stats.
     */
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

    /**
     * A failed connection will currently log the error message.
     */
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
