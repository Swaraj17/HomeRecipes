package com.abnamro.dkw.recipes.repository;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.abnamro.dkw.recipes.model.Recipe;

/** RecipeRepository is a interface which extends JpaRepository
 */
public interface RecipeRepository extends JpaRepository<Recipe, Long>{
	
	public List<Recipe> findByDateOfCreation(Timestamp timestamp);
	public List<Recipe> findByIndicator(String indicator);
	public List<Recipe> findByNoOfPeople(int noOfPeople);
	public Collection<? extends Recipe> findByIngredientsContaining(String value);
	public Collection<? extends Recipe> findByCookingInstructionsContaining(String cookingInstructions);

}
