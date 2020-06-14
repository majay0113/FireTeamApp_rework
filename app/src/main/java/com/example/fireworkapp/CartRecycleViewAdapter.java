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

public class CartRecycleViewAdapter extends RecyclerView.Adapter<CartRecycleViewAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private CartRecycleViewAdapter.IngredientClickListener clickListener;
    private final ArrayList<Ingredient> ingredients;

    CartRecycleViewAdapter(Context context, ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CartRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ingredient_item, parent, false);
        return new CartRecycleViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartRecycleViewAdapter.ViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        Log.i("ingredients", "displaying ingredient: " + Integer.toString(ingredient.id) + " name: " + ingredient.name);
        holder.ingredientName.setText(ingredient.name);
        if (ingredient.imageDrawable != null)
            holder.ingredientImage.setImageDrawable(ingredient.imageDrawable);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    void setClickListener(CartRecycleViewAdapter.IngredientClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ingredientImage;
        TextView ingredientName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage = itemView.findViewById(R.id.ingredient_image);
            ingredientName = itemView.findViewById(R.id.ingredient_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface IngredientClickListener {
        void onItemClick(View view, int position);
    }
}