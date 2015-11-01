package com.project.jholt_000.pamyupamyulyrics;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearcherActivity extends FragmentActivity implements View.OnClickListener{

    private EditText meditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        TextView textViewCustom = (TextView) findViewById(R.id.textview);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/himaji.ttf");
        textViewCustom.setTypeface(myCustomFont);
        meditText = (EditText) findViewById(R.id.editTextadventure);
        meditText.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        ImageButton imgbtn = (ImageButton) findViewById(R.id.imageButton4);
        imgbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                search();
            }
        });

        ImageButton imgbtn2 = (ImageButton) findViewById(R.id.imageButton);
        imgbtn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                home();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_searcher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void search(){

        TableLayout table = (TableLayout) findViewById(R.id.table);
        for(int i = table.getChildCount(); i > -1; i--){
            View row = table.getChildAt(i);
            table.removeView(row);
        }

        String searchtext = meditText.getText().toString();
        if (searchtext.equals("")){
            Toast.makeText(getApplicationContext(), "No results",
                    Toast.LENGTH_LONG).show();
            return;
        }
        String[] string_array1 = getResources().getStringArray(R.array.album1);
        String[] string_array2 = getResources().getStringArray(R.array.album2);
        String[] string_array3 = getResources().getStringArray(R.array.album3);
        String[] string_array4 = getResources().getStringArray(R.array.album4);
        String[][] arrays = { string_array1, string_array2, string_array3, string_array4};
        ArrayList<String> resultarray = new ArrayList<String>();

        for (int row = 0; row < arrays.length; row++) {
            for (int i = 0; i < arrays[row].length; i++) {
                String str1 = arrays[row][i];
                if (str1.toLowerCase().contains(searchtext.toLowerCase())) {
                    resultarray.add(arrays[row][i]);
                }
            }
        }

        for (int i = 0; i < resultarray.size(); i++) {
            TableRow row = new TableRow(this);
            Button btn = new Button(this);
            btn.setId(i);
            btn.setOnClickListener(this);
            btn.setText(resultarray.get(i));
            btn.setTransformationMethod(null);
            btn.setTextColor(getResources().getColor(R.color.pink));
            btn.setBackgroundResource(R.drawable.button_selector);
            row.addView(btn);
            table.addView(row);
        }

        if(resultarray.size() == 0){
            Toast.makeText(getApplicationContext(), "No results",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void onClick(View v) {
        int id = v.getId(); // you get ID of your dynamic button
        String pressed = ((Button)v).getText().toString();
        Intent myIntent = new Intent(this, MainActivity3.class);
        Bundle extras = new Bundle();
        extras.putString("song", pressed);
        myIntent.putExtras(extras);
        startActivity(myIntent);
    }

    public void home() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}
