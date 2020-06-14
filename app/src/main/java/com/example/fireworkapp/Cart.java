package com.example.fireworkapp;

import android.util.Log;

import java.util.ArrayList;

public class Cart {
    public static ArrayList<Ingredient> selectedIngredients;

    static {
        selectedIngredients = new ArrayList<Ingredient>();
    }

    public static boolean RemoveIngredient(Ingredient ingredient){
        if (!selectedIngredients.contains(ingredient))
            return false;

        selectedIngredients.remove(ingredient);
        return  true;
    }

    public static boolean SelectIngredient(Ingredient ingredient) {
        if (selectedIngredients.contains(ingredient))
            return false;

        selectedIngredients.add(ingredient);
        return true;
    }
}
