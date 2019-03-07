package info;

public class RestaurantInfo extends Info implements Comparable<RestaurantInfo> {
	//RestaurantInfo objects each store information of a restaurant.
	public String placeID;
	public String address;
	public int price;
	public String driveTimeText;  //e.g. "10 min", for display
	public int driveTimeValue;  //drive time expressed in seconds, used for comparison and sorting
	public String phone;
	public String url;
	
	
	public RestaurantInfo(String name, int rating, String placeID, String address, int price, String driveTimeText,
			int driveTimeValue, String phone, String url) {
		this.name = name;
		this.rating = rating;
		this.placeID = placeID;
		this.address = address;
		this.price = price;
		this.driveTimeText = driveTimeText;
		this.driveTimeValue = driveTimeValue;
		this.phone = phone;
		this.url = url;
	}
	
	public int compareTo(RestaurantInfo other) {
		return this.driveTimeValue - other.driveTimeValue;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == this) return true;
		if(!(other instanceof RestaurantInfo)) return false;
		RestaurantInfo otherRestaurantInfo = (RestaurantInfo) other;
		return this.name.equals(otherRestaurantInfo.name) && this.placeID.equals(otherRestaurantInfo.placeID);
	}
}