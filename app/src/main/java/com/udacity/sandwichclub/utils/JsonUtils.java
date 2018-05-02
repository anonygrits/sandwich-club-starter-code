package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        if (json.equals("")) {
            return null;
        }

        JSONObject sandwichJSON = new JSONObject(json);
        JSONObject sandwichNameJSON = sandwichJSON.getJSONObject("name");

        /* get embedded arrays and convert to list of strings */
        List<String> parsedAkaList = new ArrayList<>();
        JSONArray akaJSONArray = sandwichNameJSON.optJSONArray("alsoKnownAs");
        if (akaJSONArray != null) {
            for (int i=0; i<akaJSONArray.length(); i++) {
                parsedAkaList.add(akaJSONArray.getString(i) );
            }
        }

        List<String> parsedIngredientsList = new ArrayList<>();
        JSONArray ingredientsJSONArray = sandwichJSON.optJSONArray("ingredients");
        if (ingredientsJSONArray != null) {
            for (int i=0; i<ingredientsJSONArray.length(); i++) {
                parsedIngredientsList.add(ingredientsJSONArray.getString(i) );
            }
        }

        /* create and populate Sandwich object */
        Sandwich sandwich = new Sandwich();
        sandwich.setMainName(sandwichNameJSON.optString("mainName"));
        sandwich.setAlsoKnownAs(parsedAkaList);
        sandwich.setDescription(sandwichJSON.optString("description"));
        sandwich.setIngredients(parsedIngredientsList);
        sandwich.setPlaceOfOrigin(sandwichJSON.optString("placeOfOrigin"));
        sandwich.setImage(sandwichJSON.optString("image"));
        return sandwich;
    }
}

