package com.example.fireworkapp;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Ingredient {
    public static Hashtable<String, ArrayList<Ingredient>> knownIngredients;
    static int maxIndex;

    static {
        maxIndex = 0;
        knownIngredients = new Hashtable<String, ArrayList<Ingredient>>();

        knownIngredients.put("other", new ArrayList<Ingredient>());
        knownIngredients.put("vegetables", new ArrayList<Ingredient>());
        knownIngredients.put("meat", new ArrayList<Ingredient>());
        knownIngredients.put("fruits", new ArrayList<Ingredient>());
    }

    public static void AddIngredient(String category, String imagePath, String name) {
        maxIndex++;
        Ingredient ingredient = new Ingredient(maxIndex, category, imagePath, name);

        AddIngredient(ingredient);
    }

    public static void AddIngredient(Ingredient ingredient) {
        ArrayList<Ingredient> categoryList = knownIngredients.get(ingredient.category);
        categoryList.add(ingredient);
        //knownIngredients.remove(ingredient.category);
        knownIngredients.put(ingredient.category, categoryList);
    }

    public int id;
    public String category;
    public String imagePath;
    public String name;

    public Drawable imageDrawable;

    public Ingredient(int id, String category, String imagePath, String name) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.imagePath = imagePath;
    }
}
