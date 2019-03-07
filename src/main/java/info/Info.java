package info;

import java.io.Serializable;

//The super class of RestaurantInfo and RecipeInfo, used to store information about a restaurant and a
//recipe, respectively.
public abstract class Info implements Serializable{
	public String name;
	public int rating;
	
	//check whether two Info objects can be treated as the same. Used for biases based on lists. Many of
	//the parameters can potentially change during a session, especially driveTime which is influenced by
	//traffic situations. It is reasonable and sufficient to compare only the parameters involved in this
	//function.
	@Override
	public abstract boolean equals(Object other);
}