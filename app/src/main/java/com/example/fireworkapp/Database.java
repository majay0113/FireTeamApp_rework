package com.example.fireworkapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Database {
    Context context;

    public Database(Context context, String ingredientsPath, String recipesPath) {
        this.context = context;
        AssetManager am = context.getAssets();

        //Load ingredients
        try {
            InputStream is = am.open(ingredientsPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String jsonString = "";
            String line;
            while ((line = reader.readLine()) != null)
                jsonString += line;

            JSONObject jsonObj = new JSONObject(jsonString);
            JSONArray ingredients = jsonObj.getJSONArray("ingredients");

            for (int i = 0; i < ingredients.length(); i++) {
                JSONObject item = ingredients.getJSONObject(i);

                int id = item.getInt("id");
                Log.i("database", "ingredient " + Integer.toString(id));
                String name = item.getString("name");
                String category = item.getString("category");
                String imagePath = "";

                Ingredient ingredient = new Ingredient(id, category, name, name);
                try {
                    imagePath = item.getString("image");

                    InputStream ims = am.open(imagePath);
                    Drawable d = Drawable.createFromStream(ims, null);
                    ingredient.imageDrawable = d;
                    ingredient.imagePath = imagePath;
                } catch (Exception e) {

                }

                Ingredient.AddIngredient(ingredient);
            }
        } catch (Exception e) {
            Log.e("database", e.getMessage());
        }

        //Load recipies
        try {
            InputStream is = am.open(recipesPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String jsonString = "";
            String line;
            while ((line = reader.readLine()) != null)
                jsonString += line;

            JSONObject jsonObj = new JSONObject(jsonString);
            JSONArray ingredients = jsonObj.getJSONArray("recipes");

            for (int i = 0; i < ingredients.length(); i++) {
                JSONObject item = ingredients.getJSONObject(i);

                //Load recipe
            }
        } catch (Exception e) {
            Log.e("database", e.getMessage());
        }
    }
}
