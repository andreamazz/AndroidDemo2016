package com.example.notelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Show back icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText input = (EditText) findViewById(R.id.item_text);

        Button actionSave = (Button) findViewById(R.id.action_save);
        actionSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra(Constants.ITEM_VALUE, input.getText().toString());
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }

}
