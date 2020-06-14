package com.example.fireworkapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class FridgeRecycleViewAdapter extends RecyclerView.Adapter<FridgeRecycleViewAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    public static ArrayList<String> categories;
    private CategoryClickListener clickListener;

    FridgeRecycleViewAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.categories = Collections.list(Ingredient.knownIngredients.keys());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i("categories", categories.get(position));
        holder.categoryButton.setText(categories.get(position));
        holder.categoryButton.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    void setClickListener(CategoryClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button categoryButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryButton = itemView.findViewById(R.id.category_button);
        }
    }

    public interface CategoryClickListener extends Button.OnClickListener {
    }
}
