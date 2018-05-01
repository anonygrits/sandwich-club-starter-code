package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        if (json.equals("")) {
            return null;
        }

        JSONObject sandwichJSON = new JSONObject(json);

        /* get embedded arrays and convert to list of strings */
        JSONArray akaJSONArray = new JSONArray(sandwichJSON.optJSONArray("alsoKnownAs"));
        List<String> parsedAkaList = Arrays.asList(akaJSONArray.toString());

        JSONArray ingredientsJSONArray = new JSONArray(sandwichJSON.optJSONArray("ingredients"));
        List<String> parsedIngredientsList = Arrays.asList(ingredientsJSONArray.toString());

        /* create and populate Sandwich object */
        Sandwich sandwich = new Sandwich();
        sandwich.setMainName(sandwichJSON.optString("mainName"));
        sandwich.setAlsoKnownAs(parsedAkaList);
        sandwich.setDescription(sandwichJSON.optString("description"));
        sandwich.setIngredients(parsedIngredientsList);
        sandwich.setPlaceOfOrigin(sandwichJSON.optString("placeOfOrigin"));
        sandwich.setImage(sandwichJSON.optString("image"));
        return sandwich;
    }
}

