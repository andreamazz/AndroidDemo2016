package com.example.notelist.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.notelist.model.Item;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Item> {

    private Context mContext;

    public CustomAdapter(Context context, ArrayList<Item> data) {
        super(context, 0, data);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Item i = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        // Lookup view for data population
        TextView txt = (TextView) convertView.findViewById(android.R.id.text1);

        // Populate the data into the template view using the data object
        txt.setText(i.getText());

        // Check for completed items
        txt.setAlpha(i.isDone() ? 0.4f : 1f);

        // Return the completed view to render on screen
        return convertView;

    }
}