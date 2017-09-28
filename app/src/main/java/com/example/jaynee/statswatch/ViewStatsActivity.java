package com.example.jaynee.statswatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RetryPolicy;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.jaynee.statswatch.R;
import com.google.gson.*;

import org.json.JSONObject;


public class ViewStatsActivity extends AppCompatActivity
{
   // Example of API call: https://owapi.net/api/v3/u/{battle-tag}/stats
   static final String API_CALL = "https://owapi.net/api/v3/u/";

   private ProgressBar statsProgress;
   private String battleTag;
   private String server;
   private String mode;
   private ImageView profPic;
   private TextView tagView;
   private TextView serverInfo;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_view_stats);

      battleTag = getIntent().getStringExtra(SearchFragment.BATTLE_TAG);
      mode = getIntent().getStringExtra(SearchFragment.GAME_MODE);
      server = getIntent().getStringExtra(SearchFragment.CURR_SERVER);

//      if (battleTag != null)
//         Log.v("battleId: ", battleTag);

      battleTag = battleTag.replace('#', '-');
      server = server.replaceAll("\\s","").toLowerCase();
      mode = mode.replaceAll("\\s","").toLowerCase();

      Log.v("Api format", battleTag);
      Log.v("Api format", server);
      Log.v("Api format", mode);

      final String url = API_CALL + battleTag + "/stats";
      Log.v("API url", url);

      fetchStats(url);

//        final String user = getIntent().getStringExtra("battleID");
//        final String urlStr = "https://api.lootbox.eu/pc/us/" + user + "/competitive/allHeroes/";
////        String urlStr = "badURL";   // For testing onErrorResponse()
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gson = gsonBuilder.create();
//
//        requestQueue = Volley.newRequestQueue(this);
//        fetchPosts(urlStr);
   }

   private void displayStats(JSONObject stats)
   {
      profPic = (ImageView) findViewById(R.id.prof_icon);
      tagView = (TextView) findViewById(R.id.battle_tag);
      serverInfo = (TextView) findViewById(R.id.server_info);

      try
      {
         JSONObject serverStats = stats.getJSONObject("stats");
         serverStats = serverStats.getJSONObject(mode);

         JSONObject gameStats = serverStats.getJSONObject("game_stats");
         JSONObject overallStats = serverStats.getJSONObject("overall_stats");
//         JSONObject averageStats = serverStats.getJSONObject("average_stats");

         String game = gameStats.toString();
         String overall = overallStats.toString();
//         String average = averageStats.toString();

         Log.v("game", game);
         Log.v("overall", overall);
//         Log.v("average", average);

         String imgUrl = overallStats.getString("avatar");
         Glide.with(getBaseContext()).load(imgUrl).into(profPic);

         // TODO fix formating of tag and server info to not all lowercase
         String info = server + " - " + mode;
         tagView.setText(battleTag);
         serverInfo.setText(info);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   private void fetchStats(final String endPoint)
   {
      statsProgress = (ProgressBar) findViewById(R.id.progressBar_cyclic);
      final int timeout = 30000;
      final RetryPolicy policy = new DefaultRetryPolicy(timeout, 2,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
      RequestQueue queue = Volley.newRequestQueue(this);

      statsProgress.setVisibility(ProgressBar.VISIBLE);
      StringRequest statsReq = new StringRequest(
            Request.Method.GET, endPoint,
            new Response.Listener<String>()
            {
               @Override
               public void onResponse(String response)
               {
                  statsProgress.setVisibility(ProgressBar.INVISIBLE);
//                  Log.v("Response", response);

                  try
                  {
                     JSONObject jsonStats = new JSONObject(response);
                     jsonStats = jsonStats.getJSONObject(server);

                     displayStats(jsonStats);
                  }
                  catch (Exception e)
                  {
                     errorMessage("Unable to find data.");
                     finish();
                  }
               }
            },
            new Response.ErrorListener()
            {
               @Override
               public void onErrorResponse(VolleyError error)
               {
                  Log.v("Error: ", "Can't find data");

                  errorMessage("Unable to retrieve data.");
                  finish();
               }
            });

      statsReq.setRetryPolicy(policy);
      queue.add(statsReq);
   }

   private void errorMessage(String message)
   {
      Toast.makeText(ViewStatsActivity.this, message,
            Toast.LENGTH_LONG).show();
   }
}
