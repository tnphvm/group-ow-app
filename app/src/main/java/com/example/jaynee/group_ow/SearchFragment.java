package com.example.jaynee.group_ow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class SearchFragment extends Fragment
{
   private Spinner serverList;
   private Spinner modeList;

   public SearchFragment()
   {
   }

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
   }

   public class ItemSelectedListener implements AdapterView.OnItemSelectedListener
   {
      String firstItem = String.valueOf(serverList.getSelectedItem());

      public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
      {
         if (firstItem.equals(String.valueOf(serverList.getSelectedItem())))
         {
            // ToDo when first item is selected
         } else
         {
            Toast.makeText(parent.getContext(),
                    "You have selected : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_LONG).show();
            // Todo when item is selected by the user
         }
      }

      @Override
      public void onNothingSelected(AdapterView<?> arg)
      {
      }
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState)
   {
      // Inflate the layout for this fragment
      View mainView = inflater.inflate(R.layout.search_fragment, container, false);

      serverList = (Spinner) mainView.findViewById(R.id.server_list);
      serverList.setOnItemSelectedListener(new ItemSelectedListener());

      modeList = (Spinner) mainView.findViewById(R.id.mode_list);
      modeList.setOnItemSelectedListener(new ItemSelectedListener());

      // Set spinner custom layout
      ArrayAdapter serverAdapter = ArrayAdapter.createFromResource(getContext(), R.array.server_array,
              R.layout.spinner_item);
      serverAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
      serverList.setAdapter(serverAdapter);

      ArrayAdapter modeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.mode_array,
              R.layout.spinner_item);
      modeAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
      modeList.setAdapter(modeAdapter);

      return mainView;
   }
}