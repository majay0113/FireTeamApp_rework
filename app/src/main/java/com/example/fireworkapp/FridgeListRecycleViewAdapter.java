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

public class FridgeListRecycleViewAdapter extends RecyclerView.Adapter<FridgeListRecycleViewAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private IngredientClickListener clickListener;
    private final String category;

    FridgeListRecycleViewAdapter(Context context, String category) {
        this.category = category;
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
        Ingredient ingredient = Ingredient.knownIngredients.get(category).get(position);
        Log.i("ingredients", "displaying ingredient: " + Integer.toString(ingredient.id) + " name: " + ingredient.name);
        holder.ingredientName.setText(ingredient.name);
        if (ingredient.imageDrawable != null)
                holder.ingredientImage.setImageDrawable(ingredient.imageDrawable);
    }

    @Override
    public int getItemCount() {
        return Ingredient.knownIngredients.get(category).size();
    }

    void setClickListener(IngredientClickListener itemClickListener) {
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