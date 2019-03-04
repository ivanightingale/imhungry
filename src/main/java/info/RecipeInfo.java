package info;

import java.util.ArrayList;

public class RecipeInfo extends Info implements Comparable<RecipeInfo> {
	private int prepTime;
	private int cookTime;
	private ArrayList<String> ingredients;
	private String instructions;
	
	public RecipeInfo(String name, int rating, int prepTime, int cookTime, ArrayList<String> ingredients,
			String instructions) {
		this.name = name;
		this.rating = rating;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}
	
	public int getPrepTime() { return prepTime; }
	
	public int getCookTime() { return cookTime; }
	
	public ArrayList<String> getIngredients() { return ingredients; }
	
	public String getInstructions() { return instructions; }
	
	public boolean equals(RecipeInfo other) {
		return this.name == other.name && this.prepTime == other.prepTime && this.cookTime == other.cookTime;
	}
	
	public int compareTo(RecipeInfo other) {
		return this.prepTime - other.prepTime;
	}
}
