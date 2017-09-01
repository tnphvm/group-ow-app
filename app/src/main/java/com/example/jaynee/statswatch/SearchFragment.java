package com.example.jaynee.statswatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jaynee.statswatch.R;


public class SearchFragment extends Fragment
{
   public static final String BATTLE_TAG = "com.example.jaynee.statswatch.BATTLE_TAG";

   private Spinner serverList;
   private Spinner modeList;
   private EditText battleTag;
   private Button submitBtn;
   private String server;
   private String mode;

   public SearchFragment() {}

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
   }

   // Server selection listener
   public class ServerSelectedListener implements AdapterView.OnItemSelectedListener
   {
      String firstItem = String.valueOf(serverList.getSelectedItem());

      public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
      {
         // Default server
         if (firstItem.equals(String.valueOf(serverList.getSelectedItem())))
         {
            server = firstItem.toLowerCase();
         }
         else
         {
            Toast.makeText(parent.getContext(),
                    "You have selected : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_LONG).show();
            server = parent.getItemAtPosition(pos).toString().toLowerCase();
         }

         Log.v("Server", server);
      }

      @Override
      public void onNothingSelected(AdapterView<?> arg) {}
   }

   // Mode selection listener
   public class ModeSelectedListener implements AdapterView.OnItemSelectedListener
   {
      String firstItem = String.valueOf(serverList.getSelectedItem());

      public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
      {
         // Default mode
         if (firstItem.equals(String.valueOf(serverList.getSelectedItem())))
         {
            mode = firstItem.toLowerCase();
         } else
         {
            Toast.makeText(parent.getContext(),
                    "You have selected : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_LONG).show();
            mode = parent.getItemAtPosition(pos).toString().replaceAll("\\s","").toLowerCase();
         }

         Log.v("Mode", mode);
      }

      @Override
      public void onNothingSelected(AdapterView<?> arg) {}
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState)
   {
      // Inflate the layout for this fragment
      View mainView = inflater.inflate(R.layout.search_fragment, container, false);

      // Server and game mode dropdowns
      serverList = (Spinner) mainView.findViewById(R.id.server_list);
      serverList.setOnItemSelectedListener(new ServerSelectedListener());

      modeList = (Spinner) mainView.findViewById(R.id.mode_list);
      modeList.setOnItemSelectedListener(new ModeSelectedListener());

      // Set spinner custom layout
      ArrayAdapter serverAdapter = ArrayAdapter.createFromResource(getContext(), R.array.server_array,
              R.layout.spinner_item);
      serverAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
      serverList.setAdapter(serverAdapter);

      ArrayAdapter modeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.mode_array,
              R.layout.spinner_item);
      modeAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
      modeList.setAdapter(modeAdapter);

      // Battle Id Input
      battleTag = (EditText) mainView.findViewById(R.id.battle_tag);
      submitBtn = (Button) mainView.findViewById(R.id.submit_tag);

      submitBtn.setOnClickListener(new View.OnClickListener()
      {
         public void onClick(View view)
         {
            String battleTagStr = battleTag.getText().toString();

            if (battleTagStr.length() > 0 && battleTagStr.contains("#"))
            {
               Log.v("EditText", battleTagStr);

               Intent viewStats = new Intent(getActivity(), ViewStatsActivity.class);
               viewStats.putExtra(BATTLE_TAG, battleTagStr);
               startActivity(viewStats);
            }
            else
            {
               Log.v("Err", "Empty string.");

               Toast.makeText(getActivity(), "Please enter a valid battle tag.",
                       Toast.LENGTH_SHORT).show();
            }
         }
      });

      return mainView;
   }
}