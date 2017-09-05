package com.example.jaynee.statswatch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.example.jaynee.statswatch.R;


public class HistoryFragment extends Fragment
{
   static final int ARRAY_LIMIT = 20;

   private static ArrayList<String> searchedPlayers;
   private RecyclerView searchHistory;
   private RecyclerView.Adapter historyAdapter;
//   private RecyclerView.LayoutManager layoutManager;
   private LinearLayoutManager layoutManager;

   public HistoryFragment()
   {
      searchedPlayers = new ArrayList<>();
   }

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState)
   {
      View mainView = inflater.inflate(R.layout.history_fragment, container, false);

      searchHistory = (RecyclerView) mainView.findViewById(R.id.search_history);
      layoutManager = new LinearLayoutManager(getContext());
      layoutManager.setReverseLayout(true);
      layoutManager.setStackFromEnd(true);
      searchHistory.setLayoutManager(layoutManager);

      historyAdapter = new HistoryAdapter(searchedPlayers, getContext());
      searchHistory.setAdapter(historyAdapter);

      return mainView;
   }

   public static void addPlayer(String player)
   {
      trimList();
      searchedPlayers.add(player);
      Log.v("Player added: ", player);
   }

   private static void trimList()
   {
      int length = searchedPlayers.size();

      // TODO Since this is always being checked whenever a player is added, at most it'll exceed by 1
      // so not sure if it's necessary to place a for loop to do this. Precautions?
      if (length > ARRAY_LIMIT)
      {
         for (int i = ARRAY_LIMIT + 1; i < length; i++)
         {
            searchedPlayers.remove(i);
         }
      }
   }
}