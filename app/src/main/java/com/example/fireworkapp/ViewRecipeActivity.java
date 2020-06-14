package com.example.fireworkapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewRecipeActivity extends AppCompatActivity implements  CartRecycleViewAdapter.IngredientClickListener {
    CartRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        RecyclerView recyclerView = findViewById(R.id.cart_list_view);
        adapter = new CartRecycleViewAdapter(this, Cart.selectedIngredients);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());

        TextView name = findViewById(R.id.recipe_name_full);
        TextView desc = findViewById(R.id.recipe_description);

        Recipe recipe = Recipe.knownRecipes.get(getIntent().getIntExtra("recipe", 0));

        name.setText(recipe.name);
        desc.setText(recipe.description);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}