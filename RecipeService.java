package com.abnamro.dkw.recipes.service;

import java.sql.Timestamp;
import java.util.List;

import com.abnamro.dkw.recipes.model.Recipe;

public interface RecipeService {

	Recipe createRecipe(Recipe recipe);
	Recipe updateRecipe(Recipe recipe);
	List<Recipe> getAllRecipe();
	List<Recipe> getRecipeByCreationDate(String timestamp);
	List<Recipe> getRecipeByIndicator(String indicator);
	List<Recipe> getRecipeByNoOfPeople(int noOfPeople);
	List<Recipe> getRecipeByIngredients(String ingredients);
	List<Recipe> getRecipeByCookingInstructions(String cookingInstructions);
	void deleteRecipe(long id);
	boolean validateToken(String token); 
}
