package com.abnamro.dkw.recipes.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.abnamro.dkw.recipes.controller.RecipeController;
import com.abnamro.dkw.recipes.model.Recipe;
import com.abnamro.dkw.recipes.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = RecipeController.class)
public class RecipeControllerTest {

	@InjectMocks
	private RecipeController recipeController;

	@Mock
	private RecipeService recipeService;

	private MockMvc mockMvc;

	private MockHttpServletRequestBuilder linkRequest;

	@Before
	public void init() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		
		String token = "Bearer skfgdubjfbg";
		Mockito.when(recipeService.validateToken(token)).thenReturn(true);
	}

	@Test
	public void WhenGetAllRecipesThenStatusOK() throws Exception {
		String token = "Bearer skfgdubjfbg";
		List<Recipe> recipeList = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setIndicator("N");
		recipe.setName("Soup");
		recipe.setNoOfPeople(2);
		recipe.setIngredients("Tomato, Chicken, Salt, Water");
		recipe.setCookingInstructions("Mix the ingredients and boil");
		recipeList.add(recipe);
		String InputJson = this.mapToJson(recipe);
		InputJson = "["+InputJson+"]";
		linkRequest = MockMvcRequestBuilders
				.get("/api/homeRecipes/")
				.header("token_value", token)
				.contentType("APPLICATION/JSON");
		Mockito.when(recipeService.getAllRecipe()).thenReturn(recipeList);
		mockMvc.perform(linkRequest).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(InputJson));
	}
	
	@Test
	public void WhenGetRecipesByIndicatorThenReturnRecipes() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		
		String token = "Bearer skfgdubjfbg";
		Mockito.when(recipeService.validateToken(token)).thenReturn(true);
		
		linkRequest = MockMvcRequestBuilders
				.get("/api/homeRecipes/indicator/{indicator}","N")
				.header("token_value", token)
				.contentType("APPLICATION/JSON");
		
		List<Recipe> recipeList = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setIndicator("N");
		recipe.setName("Soup");
		recipe.setNoOfPeople(2);
		recipe.setIngredients("Tomato, Chicken, Salt, Water");
		recipe.setCookingInstructions("Mix the ingredients and boil");
		recipeList.add(recipe);
		String InputJson = this.mapToJson(recipe);
		InputJson = "["+InputJson+"]";
		Mockito.when(recipeService.getRecipeByIndicator(Mockito.any())).thenReturn(recipeList);
		mockMvc.perform(linkRequest).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(InputJson));
	}
	
	
	@Test
	public void WhenGetRecipesByIndicatorThenReturnRecipesPassingEmptyValue() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		
		String token = "Bearer skfgdubjfbg";
		linkRequest = MockMvcRequestBuilders
				.get("/api/homeRecipes/indicator/{indicator}","")
				.header("token_value", token)
				.contentType("APPLICATION/JSON");
		
		List<Recipe> recipeList = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setIndicator("N");
		recipe.setName("Soup");
		recipe.setNoOfPeople(2);
		recipe.setIngredients("Tomato, Chicken, Salt, Water");
		recipe.setCookingInstructions("Mix the ingredients and boil");
		recipeList.add(recipe);
		String InputJson = this.mapToJson(recipe);
		InputJson = "["+InputJson+"]";
		mockMvc.perform(linkRequest).andExpect(MockMvcResultMatchers.status().isMethodNotAllowed())
		.andExpect(MockMvcResultMatchers.content().string(""));
	}
	
	
	
	@Test
	public void getRecipeByNoOfPeopleTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		
		String token = "Bearer skfgdubjfbg";
		Mockito.when(recipeService.validateToken(token)).thenReturn(true);
		
		linkRequest = MockMvcRequestBuilders
				.get("/api/homeRecipes/noOfPeople/{noOfPeople}",2)
				.header("token_value", token)
				.contentType("APPLICATION/JSON");
		
		List<Recipe> recipeList = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setIndicator("N");
		recipe.setName("Soup");
		recipe.setNoOfPeople(2);
		recipe.setIngredients("Tomato, Chicken, Salt, Water");
		recipe.setCookingInstructions("Mix the ingredients and boil");
		recipeList.add(recipe);
		String InputJson = this.mapToJson(recipe);
		InputJson = "["+InputJson+"]";
		Mockito.when(recipeService.getRecipeByNoOfPeople(Mockito.anyInt())).thenReturn(recipeList);
		mockMvc.perform(linkRequest).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(InputJson));
	}
	
	@Test
	public void getRecipeByNoOfPeoplePassingEmptyParamValueTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		
		String token = "Bearer skfgdubjfbg";	
		linkRequest = MockMvcRequestBuilders
				.get("/api/homeRecipes/noOfPeople/{noOfPeople}","")
				.header("token_value", token)
				.contentType("APPLICATION/JSON");
		
		List<Recipe> recipeList = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setIndicator("N");
		recipe.setName("Soup");
		recipe.setNoOfPeople(2);
		recipe.setIngredients("Tomato, Chicken, Salt, Water");
		recipe.setCookingInstructions("Mix the ingredients and boil");
		recipeList.add(recipe);
		String InputJson = this.mapToJson(recipe);
		InputJson = "["+InputJson+"]";
		mockMvc.perform(linkRequest).andExpect(MockMvcResultMatchers.status().isMethodNotAllowed())
		.andExpect(MockMvcResultMatchers.content().string(""));
	}
	
	@Test
	public void getRecipeByCreationDateTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		
		String token = "Bearer skfgdubjfbg";
		Mockito.when(recipeService.validateToken(token)).thenReturn(true);
		
		linkRequest = MockMvcRequestBuilders
				.get("/api/homeRecipes/dateOfCreation/{dateOfCreation}","2021-01-19 12:15:53.459")
				.header("token_value", token)
				.contentType("APPLICATION/JSON");
		
		List<Recipe> recipeList = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setIndicator("N");
		recipe.setName("Soup");
		recipe.setNoOfPeople(2);
		recipe.setIngredients("Tomato, Chicken, Salt, Water");
		recipe.setCookingInstructions("Mix the ingredients and boil");
		recipeList.add(recipe);
		String InputJson = this.mapToJson(recipe);
		InputJson = "["+InputJson+"]";
		Mockito.when(recipeService.getRecipeByCreationDate(Mockito.any())).thenReturn(recipeList);
		mockMvc.perform(linkRequest).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(InputJson));
	}
	
	@Test
	public void getRecipeByCreationDatePassingEmptyValueTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		
		String token = "Bearer skfgdubjfbg";
		linkRequest = MockMvcRequestBuilders
				.get("/api/homeRecipes/dateOfCreation/{dateOfCreation}","")
				.header("token_value", token)
				.contentType("APPLICATION/JSON");
		
		List<Recipe> recipeList = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setIndicator("N");
		recipe.setName("Soup");
		recipe.setNoOfPeople(2);
		recipe.setIngredients("Tomato, Chicken, Salt, Water");
		recipe.setCookingInstructions("Mix the ingredients and boil");
		recipeList.add(recipe);
		String InputJson = this.mapToJson(recipe);
		InputJson = "["+InputJson+"]";
		//Mockito.when(recipeService.getRecipeByCreationDate(Mockito.any())).thenReturn(recipeList);
		mockMvc.perform(linkRequest).andExpect(MockMvcResultMatchers.status().isMethodNotAllowed())
		.andExpect(MockMvcResultMatchers.content().string(""));
	}
	
	@Test
	public void getRecipeByIngredientsTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		
		String token = "Bearer skfgdubjfbg";
		linkRequest = MockMvcRequestBuilders
				.get("/api/homeRecipes/ingredients/{ingredients}","Tomato")
				.header("token_value", token)
				.contentType("APPLICATION/JSON");
		
		List<Recipe> recipeList = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setIndicator("N");
		recipe.setName("Soup");
		recipe.setNoOfPeople(2);
		recipe.setIngredients("Tomato, Chicken, Salt, Water");
		recipe.setCookingInstructions("Mix the ingredients and boil");
		recipeList.add(recipe);
		String InputJson = this.mapToJson(recipe);
		InputJson = "["+InputJson+"]";
		Mockito.when(recipeService.getRecipeByIngredients(Mockito.any())).thenReturn(recipeList);
		mockMvc.perform(linkRequest).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(InputJson));
	}
	
	

	@Test
	public void getRecipeByIngredientsPassingEmptyParamTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		
		String token = "Bearer skfgdubjfbg";		
		linkRequest = MockMvcRequestBuilders
				.get("/api/homeRecipes/ingredients/{ingredients}","")
				.header("token_value", token)
				.contentType("APPLICATION/JSON");
		
		List<Recipe> recipeList = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setIndicator("N");
		recipe.setName("Soup");
		recipe.setNoOfPeople(2);
		recipe.setIngredients("Tomato, Chicken, Salt, Water");
		recipe.setCookingInstructions("Mix the ingredients and boil");
		recipeList.add(recipe);
		String InputJson = this.mapToJson(recipe);
		InputJson = "["+InputJson+"]";
		mockMvc.perform(linkRequest).andExpect(MockMvcResultMatchers.status().isMethodNotAllowed())
		.andExpect(MockMvcResultMatchers.content().string(""));
	}
	
	@Test
	public void getRecipeByCookingInstructionsTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		String token = "Bearer skfgdubjfbg";
		linkRequest = MockMvcRequestBuilders
				.get("/api/homeRecipes/cookingInstructions/{cookingInstructions}","Mix")
				.header("token_value", token)
				.contentType("APPLICATION/JSON");
		
		List<Recipe> recipeList = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setIndicator("N");
		recipe.setName("Soup");
		recipe.setNoOfPeople(2);
		recipe.setIngredients("Tomato, Chicken, Salt, Water");
		recipe.setCookingInstructions("Mix the ingredients and boil");
		recipeList.add(recipe);
		String InputJson = this.mapToJson(recipe);
		InputJson = "["+InputJson+"]";
		Mockito.when(recipeService.getRecipeByCookingInstructions(Mockito.any())).thenReturn(recipeList);
		mockMvc.perform(linkRequest).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(InputJson));
	}
	
	@Test
	public void createRecipeTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		String token = "Bearer skfgdubjfbg";
		List<Recipe> recipeList = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setIndicator("N");
		recipe.setName("Soup");
		recipe.setNoOfPeople(2);
		recipe.setIngredients("Tomato, Chicken, Salt, Water");
		recipe.setCookingInstructions("Mix the ingredients and boil");
		String InputPostJson=this.mapToJson(recipe);
		recipeList.add(recipe);
		
		linkRequest = MockMvcRequestBuilders
				.post("/api/homeRecipes")
				.header("token_value", token)
				.contentType(
		                MediaType.APPLICATION_JSON).content(InputPostJson);
				
		Mockito.when(recipeService.createRecipe(Mockito.any())).thenReturn(recipe);
		mockMvc.perform(linkRequest).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(InputPostJson));
	}
	
	@Test
	public void updateRecipeTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		String token = "Bearer skfgdubjfbg";
		List<Recipe> recipeList = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setIndicator("N");
		recipe.setName("Soup");
		recipe.setNoOfPeople(2);
		recipe.setIngredients("Tomato, Chicken, Salt, Water");
		recipe.setCookingInstructions("Mix the ingredients and boil");
		String InputUpdateJson=this.mapToJson(recipe);
		recipeList.add(recipe);
		
		linkRequest = MockMvcRequestBuilders
				.put("/api//homeRecipes/{id}",1)
				.header("token_value", token)
				.contentType(
		                MediaType.APPLICATION_JSON).content(InputUpdateJson);
		Mockito.when(recipeService.updateRecipe(Mockito.any())).thenReturn(recipe);
		mockMvc.perform(linkRequest).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(InputUpdateJson));
	}
	
	@Test
	public void deleteRecipeTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		String token = "Bearer skfgdubjfbg";
		List<Recipe> recipeList = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setIndicator("N");
		recipe.setName("Soup");
		recipe.setNoOfPeople(2);
		recipe.setIngredients("Tomato, Chicken, Salt, Water");
		recipe.setCookingInstructions("Mix the ingredients and boil");
		recipeList.add(recipe);
		linkRequest = MockMvcRequestBuilders
				.delete("/api//homeRecipes/{id}",1)
				.header("token_value", token)
				.contentType(
		                MediaType.APPLICATION_JSON);
		mockMvc.perform(linkRequest).andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}


}
