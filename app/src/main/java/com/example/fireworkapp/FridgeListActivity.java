package com.example.fireworkapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FridgeListActivity extends AppCompatActivity implements FridgeListRecycleViewAdapter.IngredientClickListener {

    static FridgeListRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frige_list);

        Button addIngredientButton = (Button) findViewById(R.id.add_ingredient_button);
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Handle add ingredient promt
                Intent newItemIntent = new Intent(FridgeListActivity.this, AddIngredientActivity.class);
                newItemIntent.putExtra("category", getIntent().getStringExtra("category"));
                FridgeListActivity.this.startActivity(newItemIntent);
            }
        });
    }

    @Override
    public void onResume() {
        // After a pause OR at startup
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.fridge_list_view);
        //Log.i("ingredients", "loading category: " + getIntent().getStringExtra("category"));
        adapter = new FridgeListRecycleViewAdapter(this, getIntent().getStringExtra("category"));
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (!Cart.SelectIngredient(Ingredient.knownIngredients.get(getIntent().getStringExtra("category")).get(position)))
            Toast.makeText(this, "Ingredient already selected!", Toast.LENGTH_LONG).show();
    }
}