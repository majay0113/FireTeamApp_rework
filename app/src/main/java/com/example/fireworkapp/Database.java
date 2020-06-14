package com.example.fireworkapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

public class Database {
    Context context;
    String ingredientsPath;
    String recipesPath;

    public Database(Context context, String ingredientsPath, String recipesPath) {
        this.context = context;
        AssetManager am = context.getAssets();
        this.ingredientsPath = ingredientsPath;
        this.recipesPath = recipesPath;

        //Load ingredients
        try {
            File file = new File(context.getFilesDir(), ingredientsPath);

            InputStream is = am.open(ingredientsPath);

            //if (file.exists())
            //    is = context.openFileInput(ingredientsPath);
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
                    Log.e("database", "invalid image" + e.getMessage());
                }

                Ingredient.AddIngredient(ingredient);
            }
        } catch (Exception e) {
            Log.e("database", e.getMessage());
        }

        //Load recipies
        try {
            File file = new File(context.getFilesDir(), recipesPath);

            InputStream is = am.open(recipesPath);

            //if (file.exists())
            //    is = context.openFileInput(recipesPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String jsonString = "";
            String line;
            while ((line = reader.readLine()) != null)
                jsonString += line;

            JSONObject jsonObj = new JSONObject(jsonString);
            JSONArray recipes = jsonObj.getJSONArray("recipes");

            for (int i = 0; i < recipes.length(); i++) {
                JSONObject item = recipes.getJSONObject(i);
                String name = item.getString("name");

                JSONArray ids = item.getJSONArray("ingredientIds");
                Ingredient[] ingredients = new Ingredient[ids.length()];
                for (int j = 0; j < ids.length(); j++){
                    Log.i("database", "getting ingredient: " + ids.opt(i));
                    ingredients[i] = Ingredient.GetIngredientById(ids.optInt(i));
                }

                String description = item.getString("description");

                Recipe.knownRecipes.add(new Recipe(name, ingredients, description));
                //Load recipe
            }
        } catch (Exception e) {
            Log.e("database", e.getMessage());
        }
    }

    public void SaveData() {
        //Save ingredients
        try {
            File file = new File(context.getFilesDir(), ingredientsPath);
            if (file.exists())
                file.delete();

            String ingredientContents = "{\n\t\"ingredients\": [\n";

            for (ArrayList<Ingredient> ingredients : Ingredient.knownIngredients.values())
                for (Ingredient ingredient : ingredients)
                    ingredientContents += "\t" + ingredient.ToJsonString();

            ingredientContents += "\t]\n}\n";

            FileOutputStream fos = context.openFileOutput(ingredientsPath, Context.MODE_PRIVATE);
            fos.write(ingredientContents.getBytes());

            Log.i("database", "saving data: \n" + ingredientContents);
        } catch (Exception e) {
            Log.e("database", e.getMessage());
        }

        //Save recipes
        try {
            File file = new File(context.getFilesDir(), recipesPath);
            if (file.exists())
                file.delete();

            String recipeContents = "{\n\t\"recipes\": [\n";

            for (Recipe recipe : Recipe.knownRecipes)
                recipeContents += "\t" + recipe.ToJsonString();

            recipeContents += "\t]\n}\n";

            FileOutputStream fos = context.openFileOutput(recipesPath, Context.MODE_PRIVATE);
            fos.write(recipeContents.getBytes());
        } catch (Exception e) {
            Log.e("database", e.getMessage());
        }
    }
}
