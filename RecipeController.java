package com.abnamro.dkw.recipes.controller;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abnamro.dkw.recipes.exception.ResourceNotFoundException;
import com.abnamro.dkw.recipes.model.Recipe;
import com.abnamro.dkw.recipes.service.RecipeService;

@RestController
@RequestMapping("/api/")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	/**
	 * The method will give getAllRecipe with the given parameters request header 
	 * @param request header the status code
	 * @return the required json 
	 */
	@GetMapping("/homeRecipes")
	public ResponseEntity<List<Recipe>> getAllRecipe(@RequestHeader(name="token_value") String value){
		if(recipeService.validateToken(value)) {try {
			return ResponseEntity.ok().body(recipeService.getAllRecipe());}
		catch(Exception e) {
			System.err.println("Invalid Request ");
			return null;}
		}else {
			System.err.println("Invalid Token ");
			return null;
		}
	}

	/**
	 * The method will give getRecipesByIndicator with the given parameters request header 
	 * @param request header
	 * @param path variable indicator
	 * @return the required json 
	 */
	@GetMapping("/homeRecipes/indicator/{indicator}")
	public ResponseEntity<List<Recipe>> getRecipesByIndicator(@RequestHeader(name="token_value") String value, @PathVariable String indicator){
		if(recipeService.validateToken(value)) {
			try {
				return ResponseEntity.ok().body(recipeService.getRecipeByIndicator(indicator));}
			catch(Exception e) {
				System.err.println("Invalid Request ");
				return null;}
		}else {
			System.err.println("Invalid Token");
			return null;
		}

	}
	/**
	 * Create a new method  with the given parameters request header 
	 * @param request header
	 * @param path variable number of people
	 * @return the required json 
	 */
	@GetMapping("/homeRecipes/noOfPeople/{noOfPeople}")
	public ResponseEntity<List<Recipe>> getRecipeByNoOfPeople(@RequestHeader(name="token_value") String value, @PathVariable int noOfPeople){
		if(recipeService.validateToken(value)) {
			try {
			return ResponseEntity.ok().body(recipeService.getRecipeByNoOfPeople(noOfPeople));}
			catch(Exception e) {
				System.err.println("Invalid Request ");
				return null;}
		}else {
			System.err.println("Invalid Token");
			return null;
		}

	}

	/**
	 * Will give a recipe save in database as per the cooking ing  
	 * @param request header
	 * @param path variable dateOfCreation
	 * @return the required json 
	 */
	@GetMapping("/homeRecipes/dateOfCreation/{dateOfCreation}")
	public ResponseEntity<List<Recipe>> getRecipeByCreationDate(@RequestHeader(name="token_value") String value, @PathVariable String dateOfCreation){
		if(recipeService.validateToken(value)) {try {
			return ResponseEntity.ok().body(recipeService.getRecipeByCreationDate(dateOfCreation));}
		catch(Exception e) {
			System.err.println("Invalid Request ");
			return null;}
		}else {
			System.err.println("Invalid Token");
			return null;
		}

	}

	/**
	 * Will give a recipe save in database as per the cooking ingridents  
	 * @param request header
	 * @param path variable ingredients
	 * @return the required json 
	 */
	@GetMapping("/homeRecipes/ingredients/{ingredients}")
	public ResponseEntity<List<Recipe>> getRecipeByIngredients(@RequestHeader(name="token_value") String value, @PathVariable String ingredients){
		if(recipeService.validateToken(value)) {try {
			return ResponseEntity.ok().body(recipeService.getRecipeByIngredients(ingredients));}
		catch(Exception e) {
			System.err.println("Invalid Request ");
			return null;}
		}else {
			System.err.println("Invalid Token");
			return null;
		}

	}
	/**
	 * Will give a recipe save in database as per the cooking instruction  
	 * @param request header
	 * @param path variable cookingInstructions
	 * @return the required json 
	 */
	@GetMapping("/homeRecipes/cookingInstructions/{cookingInstructions}")
	public ResponseEntity<List<Recipe>> getRecipeByCookingInstructions(@RequestHeader(name="token_value") String value, @PathVariable String cookingInstructions){
		if(recipeService.validateToken(value)) {try {
			return ResponseEntity.ok().body(recipeService.getRecipeByCookingInstructions(cookingInstructions));}
		catch(Exception e) {
			System.err.println("Invalid Request ");
			return null;}
		}else {
			System.err.println("Invalid Token");
			return null;
		}

	}
	/**
	 * Create and save the recipes in database
	 * @param request header
	 * @param request body recipe
	 * @return the required json 
	 */
	@PostMapping("/homeRecipes")
	public ResponseEntity<Recipe> createRecipe(@RequestHeader(name="token_value") String value, @RequestBody Recipe recipe){
		if(recipeService.validateToken(value)) {try {
			return ResponseEntity.ok().body(this.recipeService.createRecipe(recipe));}
		catch(Exception e) {
			System.err.println("Invalid Request ");
			return null;}
		}else {
			System.err.println("Invalid Token");
			return null;
		}

	}
	/**
	 * Update the recipe with given id for given request body 
	 * @param request header
	 * @PathVariable long id
	 * @RequestBody Recipe recipe
	 * @return the required json 
	 */
	@PutMapping("/homeRecipes/{id}")
	public ResponseEntity<Recipe> updateRecipe(@RequestHeader(name="token_value") String value, @PathVariable long id, @RequestBody Recipe recipe){
		recipe.setId(id);
		if(recipeService.validateToken(value)) {try {
			return ResponseEntity.ok().body(this.recipeService.updateRecipe(recipe));}
		catch(Exception e) {
			System.err.println("Invalid Request ");
			return null;}
		}else {
			System.err.println("Invalid Token");
			return null;
		}

	}
	/**
	 * Delete the recipe from the database
	 * @param request header
	 * @PathVariable long id
	 * @return the required json 
	 */
	@DeleteMapping("/homeRecipes/{id}")
	public HttpStatus deleteRecipe(@RequestHeader(name="token_value") String value, @PathVariable long id) {
		if(recipeService.validateToken(value)) {try {
			this.recipeService.deleteRecipe(id);
			return HttpStatus.OK;}
		catch(Exception e) {
			System.err.println("Invalid Request ");
			return null;}
		}else {
			System.err.println("Invalid Token");
			return null;
		}
	}
}
