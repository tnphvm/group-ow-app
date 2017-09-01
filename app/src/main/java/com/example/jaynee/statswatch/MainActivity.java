package com.example.jaynee.statswatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.jaynee.statswatch.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
   private Toolbar toolbar;
   private TabLayout tabLayout;
   private ViewPager viewPager;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

//      toolbar = (Toolbar) findViewById(R.id.toolbar);
//      setSupportActionBar(toolbar);
//
//      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      viewPager = (ViewPager) findViewById(R.id.viewpager);
      setupViewPager(viewPager);

      tabLayout = (TabLayout) findViewById(R.id.tabs);
      tabLayout.setupWithViewPager(viewPager);
   }

   // Set up tab fragments onto view pager
   private void setupViewPager(ViewPager viewPager)
   {
      ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
      adapter.addFragment(new SearchFragment(), "Search");
      adapter.addFragment(new HistoryFragment(), "History");
      viewPager.setAdapter(adapter);
   }

   class ViewPagerAdapter extends FragmentPagerAdapter
   {
      private final List<Fragment> mFragmentList = new ArrayList<>();
      private final List<String> mFragmentTitleList = new ArrayList<>();

      public ViewPagerAdapter(FragmentManager manager)
      {
         super(manager);
      }

      @Override
      public Fragment getItem(int position)
      {
         return mFragmentList.get(position);
      }

      @Override
      public int getCount()
      {
         return mFragmentList.size();
      }

      public void addFragment(Fragment fragment, String title)
      {
         mFragmentList.add(fragment);
         mFragmentTitleList.add(title);
      }

      @Override
      public CharSequence getPageTitle(int position)
      {
         return mFragmentTitleList.get(position);
      }
   }
}

///**
// * MainActivity class is the main activity seen at the launch of this application that displays
// * the initials of Overwatch players within my group of friends as buttons. Users can select whom
// * they want to view statistics for or view their own. This app currently only displays
// * competitive overall statistics for each player. Future implementation will also use the API's
// * option that will allow for individual hero plays and quick play data.
// */
//public class MainActivity extends AppCompatActivity
//{
//
//    final private String chuongID = "twochuongz-1567";
//    final private String dylanID = "Dilpills-1678";
//    final private String haoID = "Luser-1435";
//
//    /**
//     * Initializes the application and its UI contents such as the buttons containing the
//     * name initials.
//     *
//     * @param savedInstanceState Data stored about the app's previous state
//     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
////        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////        setSupportActionBar(toolbar);
//
////        final Button chuongBtn = (Button) findViewById(R.id.chuong_button);
////        final Button dylanBtn = (Button) findViewById(R.id.dylan_button);
//    }
//
//    /**
//     * This method listens to button clicks and starts a new activity to display the player's
//     * statistics. The user's respective battle.net ID will be added to the intent for the new activity
//     * to load the appropriate statistics.
//     *
//     * @param v Event handler to determine which button was clicked
//     */
//    public void onClick(View v)
//    {
//        Intent intent = new Intent(MainActivity.this, ViewStatsActivity.class);
//        final String key = "battleID";
//
//        switch (v.getId())
//        {
//            case R.id.chuong_button:
//                intent.putExtra(key, chuongID);
//                break;
//            case R.id.dylan_button:
//                intent.putExtra(key, dylanID);
//                break;
//            default:
//                throw new RuntimeException("Unknown button ID");
//        }
//
//        startActivity(intent);
//    }
//
//    /**
//     * Creates the menu for the app as well as items such as extra app options if it is present.
//     *
//     * @param menu The menu object to be created
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    /**
//     * Listens to options selected from the menu bar and handles the event.
//     * More features and options will be created in the future for the user to choose from.
//     *
//     * @param item Option that was selected from the menu
//     * @return
//     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
////        int id = item.getItemId();
////
////        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings)
////        {
////            return true;
////        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}