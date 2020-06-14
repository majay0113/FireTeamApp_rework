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

public class MainActivity extends AppCompatActivity implements CartRecycleViewAdapter.IngredientClickListener {

    public static MainActivity instance;
    static Database database;
    CartRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (instance == null)
            instance = this;

        Button fridgeButton = (Button) findViewById(R.id.open_fridge_button);
        fridgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Handle transition to fridge
                Intent fridgeIntent = new Intent(MainActivity.this, FridgeActivity.class);
                MainActivity.this.startActivity(fridgeIntent);
            }
        });

        Button recipesButton = (Button) findViewById(R.id.open_recipes_button);
        recipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Handle transition to recipes
                Intent recipeIntent = new Intent(MainActivity.this, RecipeActivity.class);
                recipeIntent.putExtra("favorites", false);
                MainActivity.this.startActivity(recipeIntent);
            }
        });

        Button favRecipesButton = (Button) findViewById(R.id.open_favorite_recipes_button);
        favRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Handle transition to favorite recipes
                Intent recipeIntent = new Intent(MainActivity.this, RecipeActivity.class);
                recipeIntent.putExtra("favorites", true);
                MainActivity.this.startActivity(recipeIntent);
            }
        });
    }

    @Override
    public void onResume() {
        // After a pause OR at startup
        super.onResume();

        if (database == null)
            database = new Database(getApplicationContext(), "ingredients.json", "recipes.json");

        RecyclerView recyclerView = findViewById(R.id.cart_list_view);
        adapter = new CartRecycleViewAdapter(this, Cart.selectedIngredients);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (Cart.RemoveIngredient(Cart.selectedIngredients.get(position))) {
            Toast.makeText(this, "Removed ingredient!", Toast.LENGTH_LONG).show();

            RecyclerView recyclerView = findViewById(R.id.cart_list_view);
            adapter = new CartRecycleViewAdapter(this, Cart.selectedIngredients);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
        }
    }

    @Override
    protected void onDestroy() {
        database.SaveData();
        super.onDestroy();
    }
}