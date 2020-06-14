package com.example.fireworkapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Recipe {
    public static ArrayList<Recipe> knownRecipes;
    public static ArrayList<Recipe> favoriteRecipes;

    static {
        knownRecipes = new ArrayList<>();
        favoriteRecipes = new ArrayList<>();
    }

    public String name;
    public int[] ingredientIds;
    public String description;

    public static ArrayList<Recipe> FilterRecipes(Ingredient[] ingredients) {
        return FilterRecipes(ingredients, false);
    }

    public static ArrayList<Recipe> FilterRecipes(Ingredient[] ingredients, boolean inFavorites) {
        ArrayList<Recipe> result = new ArrayList<>();
        Log.i("recipe", "got ingredients:");
        for (Ingredient i : ingredients)
            Log.i("recipe", "" + i.id);

        if (!inFavorites) {
            for (Recipe recipe : knownRecipes) {
                boolean fits = true;
                for (Ingredient ingredient : ingredients) {
                    if (!recipe.RequiresIngredient(ingredient))
                        fits = false;
                }

                if (fits || ingredients.length == 0)
                    result.add(recipe);
            }
        } else {
            for (Recipe recipe : favoriteRecipes) {
                boolean fits = true;
                for (Ingredient ingredient : ingredients) {
                    if (!recipe.RequiresIngredient(ingredient))
                        fits = false;
                }

                if (fits || ingredients.length == 0)
                    result.add(recipe);
            }
        }

        return result;
    }

    public Recipe(String name, Ingredient[] ingredients, String description) {
        this.name = name;
        this.description = description;

        this.ingredientIds = new int[ingredients.length];
        for (int i = 0; i < ingredients.length; i++)
            if (ingredients[i] != null)
                ingredientIds[i] = ingredients[i].id;
    }

    public boolean RequiresIngredient(Ingredient ingredient) {
        boolean result = false;
        Log.i("recipe", "checking recipe: " + this.name);

        for (int id : this.ingredientIds) {
            Log.i("recipe", "checking " + id);
            if (id == ingredient.id) {
                result = true;
                Log.i("recipe", "requires");
            }
        }

        return result;
    }

    public boolean IsFavorited(){
        return favoriteRecipes.contains(this);
    }

    public String ToJsonString() {
        String result = "{\n";
        result += "\"name\": \"" + this.name + "\",\n";
        result += "\"ingredientIds\": [\n";
        for (int id : this.ingredientIds) {
            result += "\t" + Integer.toString(id) + ",\n";
        }
        result += "\"description\": \"" + this.description + "\"\n";
        result += "\"isFavorite\": \"" + Boolean.toString(this.IsFavorited()) + "\"\n";
        result += "},\n";

        return result;
    }
}
