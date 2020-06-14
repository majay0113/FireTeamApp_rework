package com.example.fireworkapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeRecycleViewAdapter extends RecyclerView.Adapter<RecipeRecycleViewAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private RecipeClickListener clickListener;
    private final Ingredient[] ingredients;
    private final ArrayList<Recipe> recipes;
    private final boolean favorites;

    RecipeRecycleViewAdapter(Context context, Ingredient[] ingredients, boolean favorites) {
        this.ingredients = ingredients;
        this.favorites = favorites;
        this.inflater = LayoutInflater.from(context);
        this.recipes = Recipe.FilterRecipes(ingredients, favorites);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.recipeName.setText(recipe.name);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    void setClickListener(RecipeClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView recipeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipe_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface RecipeClickListener {
        void onItemClick(View view, int position);
    }
}