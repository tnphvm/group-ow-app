package com.example.jaynee.group_ow;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

/**
 * MainActivity class is the main activity seen at the launch of this application that displays
 * the initials of Overwatch players within my group of friends as buttons. Users can select whom
 * they want to view statistics for or view their own. This app currently only displays
 * competitive overall statistics for each player. Future implementation will also use the API's
 * option that will allow for individual hero plays and quick play data.
 */
public class MainActivity extends AppCompatActivity
{

    /**
     * Initializes the application and its UI contents such as the buttons containing the
     * name initials.
     *
     * @param savedInstanceState Data stored about the app's previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button chuongBtn = (Button) findViewById(R.id.chuong_button);
//        chuongBtn.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                startActivity(new Intent(MainActivity.this, ViewStatsActivity.class));
//            }
//        });
    }

    /**
     * This method listens to button clicks and starts a new activity to display the player's
     * statistics. The user's respective battle.net ID will be added to the intent for the new activity
     * to load the appropriate statistics.
     *
     * @param v Event handler to determine which button was clicked
     */
    public void onClick(View v)
    {
        Intent intent = new Intent(MainActivity.this, ViewStatsActivity.class);

        switch (v.getId())
        {
            case R.id.chuong_button:
                intent.putExtra("battleID", "twochuongz-1567");
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }

        startActivity(intent);
    }

    /**
     * Creates the menu for the app as well as items such as extra app options if it is present.
     *
     * @param menu The menu object to be created
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Listens to options selected from the menu bar and handles the event.
     * More features and options will be created in the future for the user to choose from.
     *
     * @param item Option that was selected from the menu
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings)
//        {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
