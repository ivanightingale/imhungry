package info;

public class RestaurantInfo extends Info implements Comparable<RestaurantInfo> {
	//RestaurantInfo objects each store information of a restaurant.
	public String placeID;  //Google Maps unique place ID
	public String address;
	public String priceLevel;
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
		//convert int value of price level to String consists of dollar signs
		priceLevel = "";
		for(int i = 0; i < price; i++) {
			priceLevel += "$";
		}
		this.driveTimeText = driveTimeText;
		this.driveTimeValue = driveTimeValue;
		this.phone = phone;
		this.url = url;
	}
	
	//used for sorting in ascending order based on driveTimeValue.
	public int compareTo(RestaurantInfo other) {
		return this.driveTimeValue - other.driveTimeValue;
	}
	
	//two restaurants must be the same if they have identical names and place IDs.
	@Override
	public boolean equals(Object other) {
		if(other == this) return true;
		if(!(other instanceof RestaurantInfo)) return false;
		RestaurantInfo otherRestaurantInfo = (RestaurantInfo) other;
		return this.name.equals(otherRestaurantInfo.name) && this.placeID.equals(otherRestaurantInfo.placeID);
	}
}