package com.example.jaynee.statswatch;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.jaynee.statswatch.R;
import com.google.gson.*;

import org.w3c.dom.Text;

/**
 * ViewStatsActivity class is the new activity that is launched from the main activity when the user
 * selects a user to view his or her game stats. This activity displays all of the user's overall competitive
 * competitive stats that is pulled from a web API. The API sends the stats in a JSON file, parsed using
 * GSON and Volley, and then the individual stats are stored into its respective variables in the Stats
 * class.
 * <p>
 * Networking Help: https://kylewbanks.com/blog/Implementing-Google-Plus-Style-ListView-Animations-on-Android
 */
public class ViewStatsActivity extends AppCompatActivity
{
   // Example of API call: https://owapi.net/api/v3/u/{battle-tag}/stats

   private TextView battleTag;
   private RequestQueue requestQueue;
   private Gson gson;
   private Stats userStats;
   private ProgressDialog parsingStats;

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

      String attr = getIntent().getStringExtra(SearchFragment.BATTLE_TAG);

      if (attr != null)
         Log.v("battleId: ", attr);

      battleTag = (TextView) findViewById(R.id.battle_tag);
      battleTag.setText(attr);

      attr = attr.replace('#', '-');
      Log.v("Api format", attr);

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

   /**
    * Initializes the TextView objects and populates them with the stats property names and values
    * to display on the activity content.
    */
   private void displayStats()
   {
      final TextView statsNames = (TextView) findViewById(R.id.stats_props);
      final TextView statsVals = (TextView) findViewById(R.id.stats_values);
      final String properties[] = userStats.getStatsProperties();
      final String[] values = userStats.getStatsValues();

      for (String prop : properties) {
         statsNames.append(prop + "\n");
      }

      for (String val : values) {
         statsVals.append(val + "\n");
      }
   }

   /**
    * This method will attempt to connect to the API's request URL for the individual data on
    * overall competitive play.
    *
    * @param ENDPOINT The URL request to make a connection to
    */
   private void fetchPosts(String ENDPOINT)
   {
      parsingStats = new ProgressDialog(ViewStatsActivity.this);
      final int timeout = 30000;
      final StringRequest request = new StringRequest(Request.Method.GET, ENDPOINT,
            onPostsLoaded, onPostsError);
      final RetryPolicy policy = new DefaultRetryPolicy(timeout, 2,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

      request.setRetryPolicy(policy);
      requestQueue.add(request);

      parsingStats.setMessage("Retrieving data...");
      parsingStats.setCancelable(true);
      parsingStats.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
         @Override
         public void onCancel(DialogInterface dialog)
         {
            finish();   // Closes the activity if the user cancels during loading
         }
      });
      parsingStats.show();
   }

   /**
    * When a successful connection has been made, this method displays the player's stats.
    */
   private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>()
   {
      @Override
      public void onResponse(String response)
      {
         userStats = gson.fromJson(response, Stats.class);

//            Log.i("PostActivity", "Solo Kills: " + userStats.SoloKills);
         parsingStats.dismiss();

         if (userStats.statsAvailable()) {
            displayStats();
         } else {
            Toast.makeText(ViewStatsActivity.this, "No competitive play data.",
                  Toast.LENGTH_LONG).show();
            finish();
         }
      }
   };

   /**
    * A failed connection will close the activity and display an error toast message.
    */
   private final Response.ErrorListener onPostsError = new Response.ErrorListener()
   {
      @Override
      public void onErrorResponse(VolleyError error)
      {
         // Log.e("PostActivity", error.toString());
         Toast.makeText(ViewStatsActivity.this, "Unable to retrieve data.",
               Toast.LENGTH_LONG).show();
         finish();
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
