package com.project.jholt_000.pamyupamyulyrics;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class MainActivity2 extends FragmentActivity implements View.OnClickListener{

    private static int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textViewCustom = (TextView) findViewById(R.id.textview);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/himaji.ttf");
        textViewCustom.setTypeface(myCustomFont);
        TextView textViewCustom2 = (TextView) findViewById(R.id.textview2);
        textViewCustom2.setTypeface(myCustomFont);
        TextView disclaimerhide = (TextView) findViewById(R.id.textView2);
        disclaimerhide.setVisibility(View.INVISIBLE);

        ImageButton imgbtn2 = (ImageButton) findViewById(R.id.imageButton4);
        imgbtn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                searcher();
            }
        });

        ImageButton imgbtn = (ImageButton) findViewById(R.id.imageButton);
        imgbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                home();
            }
        });

        loadEntries();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadEntries() {

        String[] string_array = getResources().getStringArray(R.array.albums);
        value = getIntent().getIntExtra("clicked", 0);

        if (value == 0)
            string_array = getResources().getStringArray(R.array.album1);
        else if (value == 1)
            string_array = getResources().getStringArray(R.array.album2);
        else if (value == 2)
            string_array = getResources().getStringArray(R.array.album3);
        else if (value == 3)
            string_array = getResources().getStringArray(R.array.album4);
        else if (value == 4)
            string_array = getResources().getStringArray(R.array.album5);
        else if (value == 5)
            string_array = getResources().getStringArray(R.array.others);

        TableLayout table = (TableLayout) findViewById(R.id.table);
        for (int i = 0; i < string_array.length; i++) {
            TableRow row = new TableRow(this);

            TableLayout.LayoutParams layoutRow = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(layoutRow);

            Button btn = new Button(this);

            TableRow.LayoutParams layoutHistory = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.MATCH_PARENT);
            btn.setLayoutParams(layoutHistory);

            btn.setId(i);
            btn.setOnClickListener(this);
            btn.setText(string_array[i]);
            btn.setTransformationMethod(null);
            btn.setTextColor(getResources().getColor(R.color.pink));
            btn.setBackgroundResource(R.drawable.button_selector);

            btn.setGravity(Gravity.CENTER);
            row.setGravity(Gravity.CENTER);

            row.addView(btn);
            table.addView(row);
        }
    }

    @Override
    public void onClick(View v) {
        String pressed = ((Button)v).getText().toString();
        Intent myIntent = new Intent(this, MainActivity3.class);
        Bundle extras = new Bundle();
        extras.putString("song", pressed);
        myIntent.putExtras(extras);
        startActivity(myIntent);
    }

    public void searcher() {
        Intent myIntent = new Intent(this, SearcherActivity.class);
        startActivity(myIntent);
    }

    public void home() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}
