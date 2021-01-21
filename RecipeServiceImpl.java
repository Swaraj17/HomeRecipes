package com.abnamro.dkw.recipes.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abnamro.dkw.recipes.dao.SecurityConfigDao;
import com.abnamro.dkw.recipes.exception.ResourceNotFoundException;
import com.abnamro.dkw.recipes.model.Recipe;
import com.abnamro.dkw.recipes.repository.RecipeRepository;
import com.abnamro.dkw.recipes.service.RecipeService;

/**This is a implementation of RecipeService Interface. This class handles the business logic and perform CRUD related functionalities
 */
@Service
@Transactional
public class RecipeServiceImpl implements RecipeService{

	/**
	 * The recipeRepository is a autowired with the service impl.
	 */
	@Autowired
	private RecipeRepository recipeRepository;

	/**
	 * securityConfigDao is autowired to get the unique token values from DB
	 */
	@Autowired
	private SecurityConfigDao securityConfigDao;

	/**The createRecipe method get the recipe from controller and create a Recipe in DB
	 * @param recipe is a object of Entity class Recipe
	 * @return recipe
	 */
	@Override
	public Recipe createRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	/**The updateRecipe method get the updated recipe from controller and updates a Recipe in DB
	 * @param recipe is a object of Entity class Recipe
	 * @return recipe
	 */
	@Override
	public Recipe updateRecipe(Recipe recipe) {
		Optional<Recipe> recipeDb = this.recipeRepository.findById(recipe.getId());
		if(recipeDb.isPresent()) {
			Recipe recipeUpdated = recipeDb.get();
			recipeUpdated.setId(recipe.getId());
			recipeUpdated.setName(recipe.getName());
			recipeUpdated.setIndicator(recipe.getIndicator());
			recipeUpdated.setIngredients(recipe.getIngredients());
			recipeUpdated.setNoOfPeople(recipe.getNoOfPeople());
			recipeUpdated.setCookingInstructions(recipe.getCookingInstructions());
			return recipeUpdated;
		}else {
			throw new ResourceNotFoundException("Record not found with ID: "+recipe.getId());
		}
	}

	/**The getAllRecipe calls the repository and provide list of all recipes
	 * @return List of recipes
	 */
	@Override
	public List<Recipe> getAllRecipe() {
		return this.recipeRepository.findAll();
	}

	/**The getRecipeByCreationDate get all the recipes based on the date of creation attribute
	 * @param is a string which has the timestamp of creation date
	 * @return List of recipes
	 */
	@Override
	public List<Recipe> getRecipeByCreationDate(String dateOfCreation) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Date parsedDate = dateFormat.parse(dateOfCreation);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
		}catch(Exception e) { 
			System.err.println("Date Format is not correct" + e);
		}
		return this.recipeRepository.findByDateOfCreation(timestamp);
	}

	/**The getRecipeByIndicator get all the recipes based on the indicator attribute
	 * @param indicator is a string which tells user is veg or non-veg
	 * @return List of recipes
	 */
	@Override
	public List<Recipe> getRecipeByIndicator(String indicator) {
		return this.recipeRepository.findByIndicator(indicator);
	}

	/**The getRecipeByNoOfPeople get all the recipes based on the noOfPeople attribute
	 * @param noOfPeople is a string which tells how many people can eat the dish
	 * @return List of recipes
	 */
	@Override
	public List<Recipe> getRecipeByNoOfPeople(int noOfPeople) {
		return this.recipeRepository.findByNoOfPeople(noOfPeople);
	}

	/**The getRecipeByIngredients get all the recipes based on the noOfPeople attribute
	 * @param ingredients is a string which tells the ingredients in the recipe
	 * @return List of recipes
	 */
	@Override
	public List<Recipe> getRecipeByIngredients(String ingredients) {
		String[] arr = ingredients.split(",");
		List<Recipe> recipes = new ArrayList<>();
		for(String value:arr) {
			recipes.addAll(this.recipeRepository.findByIngredientsContaining(value));
		}
		return recipes;
	}

	/**The getRecipeByCookingInstructions get all the recipes based on the cookingInstructions attribute
	 * @param cookingInstructions is a string which tells the instructions of the recipe
	 * @return List of recipes
	 */
	@Override
	public List<Recipe> getRecipeByCookingInstructions(String cookingInstructions) {
		String[] arr = cookingInstructions.split("/");
		List<Recipe> recipes = new ArrayList<>();
		for(String value:arr) {
			recipes.addAll(this.recipeRepository.findByCookingInstructionsContaining(value));
		}
		return recipes;
	}

	/**The deleteRecipe deletes the recipe selected by the user.
	 * @param id is a long which tells the id of the recipe to be deleted
	 */
	@Override
	public void deleteRecipe(long id) {
		Optional<Recipe> recipeDb = this.recipeRepository.findById(id);
		if(recipeDb.isPresent()) {
			this.recipeRepository.delete(recipeDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with ID: "+id);
		}
	}

	/**The validateToken validates the user token for security attacks.
	 * @param token is a user provided header value
	 * @return boolean value if the token matches with the db unique value
	 */
	@Override
	public boolean validateToken(String token) {
		String tokenDB = securityConfigDao.getTokenValue();
		if(token.equals(tokenDB)) {
			return true;
		}else {
			return false;
		}
	}

}
