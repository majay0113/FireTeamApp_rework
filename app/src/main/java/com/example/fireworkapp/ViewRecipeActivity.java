package com.example.fireworkapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewRecipeActivity extends AppCompatActivity {
    RecipeIngredientsRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        final Recipe recipe = Recipe.knownRecipes.get(getIntent().getIntExtra("recipe", 0));
        RecyclerView recyclerView = findViewById(R.id.recipe_ingredients_recycler_view);

        Ingredient[] ingredients = new Ingredient[recipe.ingredientIds.length];
        for (int i = 0; i < ingredients.length; i++)
            ingredients[i] = Ingredient.GetIngredientById(recipe.ingredientIds[i]);
        adapter = new RecipeIngredientsRecycleViewAdapter(this, ingredients);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        TextView name = findViewById(R.id.recipe_name_full);
        TextView desc = findViewById(R.id.recipe_description);
        name.setText(recipe.name);
        desc.setText(recipe.description);

        final Button addToFavButton = (Button) findViewById(R.id.add_to_favorite_button);
        if (Recipe.favoriteRecipes.contains(recipe))
            addToFavButton.setText("Remove From Favorites");
        else
            addToFavButton.setText("Add To Favorites");
        addToFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Handle transition to favorite recipes
                if (Recipe.favoriteRecipes.contains(recipe)) {
                    Recipe.favoriteRecipes.remove(recipe);
                    addToFavButton.setText("Add To Favorites");
                } else {
                    Recipe.favoriteRecipes.add(recipe);
                    addToFavButton.setText("Remove From Favorites");
                }
            }
        });
    }
}