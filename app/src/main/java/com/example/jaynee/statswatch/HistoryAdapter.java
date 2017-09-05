package com.example.jaynee.statswatch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ItemViewHolder>
{

   private ArrayList<String> historyList;

   public HistoryAdapter(ArrayList<String> list, Context context)
   {
      historyList = list;
   }

   @Override
   public HistoryAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
   {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,
            parent, false);
      ItemViewHolder viewHolder = new ItemViewHolder(view);
      return viewHolder;
   }

   @Override
   public void onBindViewHolder(HistoryAdapter.ItemViewHolder holder, int position)
   {
      holder.id.setText(historyList.get(position));
   }

   @Override
   public int getItemCount()
   {
      return historyList.size();
   }

   public static class ItemViewHolder extends RecyclerView.ViewHolder
   {
      protected TextView id;

      public ItemViewHolder(View itemView)
      {
         super(itemView);
         id = (TextView) itemView.findViewById(R.id.battle_tag);
      }
   }
}
