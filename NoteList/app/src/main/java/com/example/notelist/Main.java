package com.example.notelist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.notelist.adapter.CustomAdapter;
import com.example.notelist.model.Item;

import java.util.ArrayList;
import java.util.Iterator;

public class Main extends AppCompatActivity {

    int NEW_ITEM_REQUEST_CODE = 1;

    ArrayList<Item> mItems = new ArrayList<>();
    CustomAdapter mCustomAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main.this, AddItem.class);
                startActivityForResult(i, NEW_ITEM_REQUEST_CODE);
            }
        });

        // Initialize the store
        mItems.add(new Item("First Item"));
        mItems.add(new Item("Second Item"));
        mItems.add(new Item("Last Item"));

        // Find the list view inside the layout with its ID.
        ListView listView = (ListView) findViewById(R.id.listView);


        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the row layout as a second parameter and your
        // array as a third parameter.
        mCustomAdapter = new CustomAdapter(this, mItems);

        listView.setAdapter(mCustomAdapter);

        listView.setOnItemClickListener(mCallback);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == NEW_ITEM_REQUEST_CODE) {

            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                // Make sure the result has item value extra
                if (data.hasExtra(Constants.ITEM_VALUE)) {
                    String newItem = data.getStringExtra(Constants.ITEM_VALUE);

                    // It is not necessary to check if it is empty
                    if (!newItem.isEmpty()) {
                        mCustomAdapter.add(new Item(newItem));
                    }
                }
            }
        }
    }

    AdapterView.OnItemClickListener mCallback = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
            ((Item) parent.getItemAtPosition(position)).toggle();

            // Update adapter
            mCustomAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            new AlertDialog.Builder(Main.this)
                    .setTitle(getString(R.string.delete_dialog_title))
                    .setMessage(getString(R.string.delete_dialog_message))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            clearData();
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, null)
                    .show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clearData() {
        if (mItems.size() > 0 && mCustomAdapter != null) {
            for (Iterator<Item> iterator = mItems.iterator(); iterator.hasNext();) {
                Item i = iterator.next();
                if (i.isDone()) {
                    // Remove the current element from the iterator and the list.
                    iterator.remove();
                }
            }

            // Update adapter
            mCustomAdapter.notifyDataSetChanged();
        }
    }
}
