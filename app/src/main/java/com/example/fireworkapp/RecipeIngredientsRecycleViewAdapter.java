package com.example.fireworkapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeIngredientsRecycleViewAdapter extends RecyclerView.Adapter<RecipeIngredientsRecycleViewAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final Ingredient[] ingredients;

    RecipeIngredientsRecycleViewAdapter(Context context, Ingredient[] ingredients) {
        this.ingredients = ingredients;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ingredient_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ingredientName.setText(ingredients[position].name);
        if (ingredients[position] != null)
            holder.ingredientImage.setImageDrawable(ingredients[position].imageDrawable);
    }

    @Override
    public int getItemCount() {
        return ingredients.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ingredientImage;
        TextView ingredientName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage = itemView.findViewById(R.id.ingredient_image);
            ingredientName = itemView.findViewById(R.id.ingredient_name);
        }
    }
}
