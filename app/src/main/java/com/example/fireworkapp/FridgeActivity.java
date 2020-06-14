package com.example.fireworkapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FridgeActivity extends AppCompatActivity implements FridgeRecycleViewAdapter.CategoryClickListener {

    static FridgeRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
    }

    @Override
    public void onResume() {
        // After a pause OR at startup
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.fridge_recycler_view);
        adapter = new FridgeRecycleViewAdapter(this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Log.i("category", (String) ((Button) v).getText());
        Intent fridgeListIntent = new Intent(FridgeActivity.this, FridgeListActivity.class);
        fridgeListIntent.putExtra("category", (String) ((Button) v).getText());
        FridgeActivity.this.startActivity(fridgeListIntent);
    }
}