package info;

import java.util.ArrayList;

public class RecipeInfo extends Info implements Comparable<RecipeInfo> {
<<<<<<< HEAD
<<<<<<< HEAD
	private String name;
	private String prepTime;
	private String cookTime;
	private String ingredients;
	private String instructions;
	
	public RecipeInfo(String name, String prepTime, String cookTime, String ingredients,
=======
	//RecipeInfo objects each store information of a recipe.
	public int prepTime;
	public int cookTime;
	public ArrayList<String> ingredients;
	public String instructions;  //FIXME
	
	public RecipeInfo(String name, double rating, int prepTime, int cookTime, ArrayList<String> ingredients,
>>>>>>> 3668ec296f94964d5697cc559239c44490768a77
=======
	//RecipeInfo objects each store information of a recipe.
	public int prepTime;
	public int cookTime;
	public ArrayList<String> ingredients;
	public String instructions;  //FIXME
	
	public RecipeInfo(String name, double rating, int prepTime, int cookTime, ArrayList<String> ingredients,
>>>>>>> e1477d2cc702d72b39874e28c2f215818421992e
			String instructions) {
		this.name = name;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}
<<<<<<< HEAD
	
<<<<<<< HEAD
	public String getName() {return name;}
	
	public String getPrepTime() { return prepTime; }
	
	public String getCookTime() { return cookTime; }
	
	public String getIngredients() { return ingredients; }
	
	public String getInstructions() { return instructions; }
	
	public boolean equals(RecipeInfo other) {
		return this.name == other.name && this.prepTime == other.prepTime && this.cookTime == other.cookTime;
=======

	public int compareTo(RecipeInfo other) {
		return this.prepTime - other.prepTime;
>>>>>>> e1477d2cc702d72b39874e28c2f215818421992e
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == this) return true;
		if(!(other instanceof RecipeInfo)) return false;
		RecipeInfo otherRecipeInfo = (RecipeInfo) other;
		return this.name.equals(otherRecipeInfo.name) && this.prepTime == otherRecipeInfo.prepTime &&
				this.cookTime == otherRecipeInfo.cookTime;
	}
}
=======
	public int compareTo(RecipeInfo other) {
		return this.prepTime - other.prepTime;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == this) return true;
		if(!(other instanceof RecipeInfo)) return false;
		RecipeInfo otherRecipeInfo = (RecipeInfo) other;
		return this.name.equals(otherRecipeInfo.name) && this.prepTime == otherRecipeInfo.prepTime &&
				this.cookTime == otherRecipeInfo.cookTime;
	}
}
>>>>>>> 3668ec296f94964d5697cc559239c44490768a77
