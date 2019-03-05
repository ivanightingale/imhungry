package info;

import java.util.ArrayList;

public class RecipeInfo extends Info implements Comparable<RecipeInfo> {
	public int prepTime;
	public int cookTime;
	public ArrayList<String> ingredients;
	public String instructions;  //FIXME
	
	public RecipeInfo(String name, int rating, int prepTime, int cookTime, ArrayList<String> ingredients,
			String instructions) {
		this.name = name;
		this.rating = rating;
		this.prepTime = prepTime;
		this.cookTime = cookTime;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}
	
	
	//FIXME
	public boolean equals(RecipeInfo other) {
		return this.name == other.name && this.prepTime == other.prepTime && this.cookTime == other.cookTime;
	}
	
	public int compareTo(RecipeInfo other) {
		return this.prepTime - other.prepTime;
	}
}
