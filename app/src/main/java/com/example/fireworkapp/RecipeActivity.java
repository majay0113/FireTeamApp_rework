package com.example.fireworkapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecipeActivity extends AppCompatActivity implements RecipeRecycleViewAdapter.RecipeClickListener {
    RecipeRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        /* Disabled untill saving is fixed
        Button newRecipeButton = (Button) findViewById(R.id.add_new_recipe_button);
        newRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Handle transition to favorite recipes
                Recipe.knownRecipes.add(new Recipe("test", new Ingredient[0], "test\ntest"));

                RecyclerView recyclerView = findViewById(R.id.recipe_recycle_view_list);
                adapter = new RecipeRecycleViewAdapter(RecipeActivity.this,  Cart.selectedIngredients.toArray(new Ingredient[Cart.selectedIngredients.size()]));
                adapter.setClickListener(RecipeActivity.this);
                recyclerView.setAdapter(adapter);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
                recyclerView.addItemDecoration(dividerItemDecoration);
            }
        });
         */
    }

    @Override
    public void onResume() {
        // After a pause OR at startup
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.recipe_recycle_view_list);
        adapter = new RecipeRecycleViewAdapter(this, Cart.selectedIngredients.toArray(new Ingredient[Cart.selectedIngredients.size()]),
                getIntent().getBooleanExtra("favorites", false));
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent recipeViewIntent = new Intent(RecipeActivity.this, ViewRecipeActivity.class);
        Recipe res = Recipe.FilterRecipes(Cart.selectedIngredients.toArray(new Ingredient[Cart.selectedIngredients.size()]),
                getIntent().getBooleanExtra("favorites", false)).get(position);
        recipeViewIntent.putExtra("recipe", Recipe.knownRecipes.indexOf(res));
        RecipeActivity.this.startActivity(recipeViewIntent);
    }
}