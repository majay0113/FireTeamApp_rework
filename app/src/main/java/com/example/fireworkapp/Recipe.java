package com.example.fireworkapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Recipe {
    public static ArrayList<Recipe> knownRecipes;

    static {
        knownRecipes = new ArrayList<>();
    }

    public String name;
    public int[] ingredientIds;
    public String description;

    public static ArrayList<Recipe> FilterRecipes(Ingredient[] ingredients) {
        ArrayList<Recipe> result = new ArrayList<>();

        for (Recipe recipe : knownRecipes) {
            boolean fits = true;
            for (Ingredient ingredient : ingredients) {
                if (!recipe.RequiresIngredient(ingredient))
                    fits = false;
            }

            if (fits || ingredients.length == 0)
                result.add(recipe);
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
        for (int id : this.ingredientIds) {
            if (id == ingredient.id)
                result = true;
        }

        return result;
    }

    public String ToJsonString() {
        String result = "{\n";
        result += "\"name\": \"" + this.name + "\",\n";
        result += "\"ingredientIds\": [\n";
        for (int id : this.ingredientIds) {
            result += "\t" + Integer.toString(id) + ",\n";
        }
        result += "\"description\": \"" + this.description + "\"\n";
        result += "},\n";

        return result;
    }
}
