<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Recipes</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">HomeMade Recipes</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Features</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Pricing</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

	<div="container">
		<div class="col"></div>
		<div class="row"></div>
		<div class="row"></div>
		<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#createModal"
		>Create Receipe</button>
		
		<table class="table table-dark table-striped" id="recipeTable">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">NAME</th>
					<th scope="col">INDICATOR</th>
					<th scope="col">DATE_OF_CREATION</th>
					<th scope="col">INGREDIENTS</th>
					<th scope="col">NO_OF_PEOPLE</th>
					<th scope="col">COOKING_INSTRUCTIONS</th>
				</tr>
			</thead>
			<tbody>
		
			</tbody>
		</table>
	</div>
	
	
	
	

<!-- Modal -->
<div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Create Receipe</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
        <form class="createRecipeForm">
      <div class="modal-body">
    
  <div class="mb-3">
    <label for="name" class="form-label">Name Of Recipe</label>
    <input type="text" class="form-control" id="name" aria-describedby="NameHelp" name="name">
    <div id="NameHelp" class="form-text">We'll never share your email with anyone else.</div>
  </div>
  <div class="mb-3">
    <label for="indicator" class="form-label">Indicator Of Recipe</label>
    <input type="text" class="form-control" id="indicator" aria-describedby="indicatorHelp" name="indicator">
    <div id="indicatorHelp" class="form-text">We'll never share your email with anyone else.</div>
  </div>
 <div class="mb-3">
    <label for="ingredients" class="form-label">Ingredients Of Recipe</label>
    <input type="text" class="form-control" id="name" aria-describedby="ingredientsHelp" name="ingredients">
    <div id="ingredientsHelp" class="form-text">We'll never share your email with anyone else.</div>
  </div>
  
   <div class="mb-3">
    <label for="cookingInstructions" class="form-label">Cooking Instructions</label>
    <input type="text" class="form-control" id="cookingInstructions" aria-describedby="cookingInstructionsHelp" name="cookingInstructions">
    <div id="cookingInstructionsHelp" class="form-text">We'll never share your email with anyone else.</div>
  </div>
   <div class="mb-3">
    <label for="noOfPeople" class="form-label">No of People</label>
    <input type="number" class="form-control" id="name" aria-describedby="noOfPeopleHelp" name="noOfPeople">
    <div id="noOfPeopleHelp" class="form-text">We'll never share your email with anyone else.</div>
  </div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-secondary" onclick="createRecipe()">Save changes</button>
        <label id="message"> </label>
      </div>
      </form>
    </div>
  </div>
</div>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
		crossorigin="anonymous"></script>
	<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
  <script>
  getRecipe();
  function getRecipe(){
	  $.ajax({
			type : "GET",
			url : "/api/homeRecipes",
			contentType: 'application/json',
			headers: { 'token_value': 'Bearer zxzxgjeufbskn89gfd' },
			success: function(recipeData)
			  {
				debugger;
				//alert("Data Saved: " + recipe);
				for(index=0;index<=recipeData.length; index++){
					var recipe=recipeData[index];
					$("#recipeTable tbody").append('<tr><td>'+recipe.id+'</td><td>'
							+recipe.name+'</td><td>'
							+recipe.indicator+'</td><td>'
							+recipe.dateOfCreation+'</td><td>'
							+recipe.ingredients+'</td><td>'
							+recipe.noOfPeople+'</td><td>'
							+recipe.cookingInstructions+'</td></tr>'); 
					}
				 
			  }
		});
	  }
		


		function createRecipe(){
			debugger;
			var formData = $("form.createRecipeForm").serializeObject();

			$.ajax({
				type : "POST",
				url : "/api/homeRecipes",
				contentType: 'application/json',
				headers: { 'token_value': 'Bearer zxzxgjeufbskn89gfd' },
				data:JSON.stringify(formData),
				success: function(recipeData)
				  {
					  $("#message").append("<b>Data Saved successfully</b>");
					  $('#createModal').modal('toggle');
					  $('#recipeTable tbody').empty();
					  getRecipe();
					  $("#message").text("");
				  }
			});
			
			}


		$.fn.serializeObject = function()
		{
		   var o = {};
		   var a = this.serializeArray();
		   $.each(a, function() {
		       if (o[this.name]) {
		           if (!o[this.name].push) {
		               o[this.name] = [o[this.name]];
		           }
		           o[this.name].push(this.value || '');
		       } else {
		           o[this.name] = this.value || '';
		       }
		   });
		   return o;
		};
	</script>
</body>
</html>