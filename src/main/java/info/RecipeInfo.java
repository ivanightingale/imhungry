package info;

import java.util.ArrayList;

public class RecipeInfo extends Info implements Comparable<RecipeInfo> {
	private String name;
	private String prepTime;
	private String cookTime;
	private String ingredients;
	private String instructions;
	
	public RecipeInfo(String name, String prepTime, String cookTime, String ingredients,
			String instructions) {
		this.name = name;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}
	
	public String getName() {return name;}
	
	public String getPrepTime() { return prepTime; }
	
	public String getCookTime() { return cookTime; }
	
	public String getIngredients() { return ingredients; }
	
	public String getInstructions() { return instructions; }
	
	public boolean equals(RecipeInfo other) {
		return this.name == other.name && this.prepTime == other.prepTime && this.cookTime == other.cookTime;
	}
	
	//public int compareTo(RecipeInfo other) {
		//return this.prepTime - other.prepTime;
	//}
}
