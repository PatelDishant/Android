package com.example.ngo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NGOCommunity extends AppCompatActivity {

    ListView userListView;
    ArrayList<String> userList;
    DatabaseAdapter dataAdap;
    ArrayAdapter<String> listAdap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngocommunity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Drawable bgImage = getResources().getDrawable(R.drawable.bgg);
        bgImage.setAlpha(200);
        dataAdap = new DatabaseAdapter(this);
        userListView = (ListView) findViewById(R.id.userListView);
        userList = new ArrayList<String>();
        ArrayList<String> userNames = dataAdap.userNameList();
        listAdap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userNames);
        userListView.setAdapter(listAdap);



    }

}
