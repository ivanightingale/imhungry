package info;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class RecipeInfoTest {

	@Test
	//instantiation test of RecipeInfo class, testing constructors and member variables
	public void instantiationTest() { 
		String name = "pork";
		int rating = 3;
		int recipeID = 7618212;
		int prepTime = 30;
		int cookTime = 25;
		ArrayList<String> ingre = new ArrayList<String>();
		ArrayList<String> instr = new ArrayList<String>();
		ingre.add("tomato");
		ingre.add("pork");
		instr.add("put into water");
		instr.add("wait for 25 minutes");
		String imageURL = "github.com";
		RecipeInfo ri = new RecipeInfo(name,rating,recipeID,prepTime,cookTime,ingre,instr,imageURL);
		assertEquals(name,ri.name);
		assertEquals(rating,ri.rating);
		assertEquals(recipeID, ri.recipeID);
		assertEquals(prepTime,ri.prepTime);
		assertEquals(cookTime,ri.cookTime);
		assertEquals(ingre,ri.ingredients);
		assertEquals(instr,ri.instructions);
		assertEquals(imageURL,ri.imageURL);
	}
	
	@Test
	//testing the compare function in the class
	public void comparisonTest() { 
		String name = "pork";
		int rating = 3;
		int recipeID = 7618212;
		int prepTime = 30;
		int cookTime = 25;
		ArrayList<String> ingre = new ArrayList<String>();
		ArrayList<String> instr = new ArrayList<String>();
		ingre.add("tomato");
		ingre.add("pork");
		instr.add("put into water");
		instr.add("wait for 25 minutes");
		String imageURL = "github.com";
		RecipeInfo ri = new RecipeInfo(name,rating,recipeID,prepTime,cookTime,ingre,instr,imageURL);
		prepTime = 51;
		RecipeInfo ri2 = new RecipeInfo(name,rating,recipeID,prepTime,cookTime,ingre,instr,imageURL);
		assertEquals("value comparison error",-21,ri.compareTo(ri2));
	}
	
	@Test
	//testing the equal function in the class of using recipeID as parameter
	public void equalTest1() { 
		String name = "pork";
		int rating = 3;
		int recipeID = 7618212;
		int prepTime = 30;
		int cookTime = 25;
		ArrayList<String> ingre = new ArrayList<String>();
		ArrayList<String> instr = new ArrayList<String>();
		ingre.add("tomato");
		ingre.add("pork");
		instr.add("put into water");
		instr.add("wait for 25 minutes");
		String imageURL = "github.com";
		RecipeInfo ri = new RecipeInfo(name,rating,recipeID,prepTime,cookTime,ingre,instr,imageURL);
		RecipeInfo ri2 = new RecipeInfo(name,rating,recipeID,prepTime,cookTime,ingre,instr,imageURL);
		assertEquals(true,ri.equals(ri2));
		ArrayList<String> ingre1 = new ArrayList<String>();
		ArrayList<String> instr2 = new ArrayList<String>();
		RecipeInfo ri3 = new RecipeInfo("",0,recipeID,0,0,ingre1,instr2,"");
		assertEquals("Equal() comparison error", true, ri.equals(ri3));
		ri3.recipeID = ri.recipeID-1;
		assertEquals("Equal() comparison error", false, ri.equals(ri3));
	}
	@Test
	public void equalTest2() { // testing the equal function in the class of using the class itself as parameter
		String name = "pork";
		int rating = 3;
		int recipeID = 7618212;
		int prepTime = 30;
		int cookTime = 25;
		ArrayList<String> ingre = new ArrayList<String>();
		ArrayList<String> instr = new ArrayList<String>();
		ingre.add("tomato");
		ingre.add("pork");
		instr.add("put into water");
		instr.add("wait for 25 minutes");
		String imageURL = "github.com";
		RecipeInfo ri = new RecipeInfo(name,rating,recipeID,prepTime,cookTime,ingre,instr,imageURL);
		assertEquals(true,ri.equals(ri));
	}
	@Test
	//testing a recipe class with a random class
	public void equalTest3() { 
		String name = "pork";
		int rating = 3;
		int recipeID = 7618212;
		int prepTime = 30;
		int cookTime = 25;
		ArrayList<String> ingre = new ArrayList<String>();
		ArrayList<String> instr = new ArrayList<String>();
		ingre.add("tomato");
		ingre.add("pork");
		instr.add("put into water");
		instr.add("wait for 25 minutes");
		String imageURL = "github.com";
		Object newone = new Object();
		RecipeInfo ri = new RecipeInfo(name,rating,recipeID,prepTime,cookTime,ingre,instr,imageURL);
		assertEquals(false,ri.equals(newone));
	}
	
	
}
