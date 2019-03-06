package info;

<<<<<<< HEAD
<<<<<<< HEAD
public class RestaurantInfo extends Info {
	private String name;
	private String address;
	private String price;
	private int driveTime;
	private int rating;
	private String phone;
	private String url;
	
	
	public RestaurantInfo(String name, int rating, String address, String price, int driveTime, String phone,
			String url) {
=======
=======
>>>>>>> e1477d2cc702d72b39874e28c2f215818421992e
public class RestaurantInfo extends Info implements Comparable<RestaurantInfo> {
	//RestaurantInfo objects each store information of a restaurant.
	public String placeID;
	public String address;
	public int price;
	public String driveTimeText;
	public int driveTimeValue;
	public String phone;
	public String url;
	
	
	public RestaurantInfo(String name, double rating, String placeID, String address, int price, String driveTimeText,
			int driveTimeValue, String phone, String url) {
<<<<<<< HEAD
>>>>>>> 3668ec296f94964d5697cc559239c44490768a77
=======
>>>>>>> e1477d2cc702d72b39874e28c2f215818421992e
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
	
<<<<<<< HEAD
<<<<<<< HEAD
	public String getAddress() { return address; }
	
	public String getPrice() { return price; }
	
	public int getDriveTime() { return driveTime; }
	
	public String getPhone() { return phone; }
	
	public String getURL() {return url; }
	
	public int getRating() {return rating;}
	
	public boolean equals(RestaurantInfo other) {
		return this.name == other.name && this.address == other.address;
=======
	public int compareTo(RestaurantInfo other) {
		return this.driveTimeValue - other.driveTimeValue;
	}
	
=======
	public int compareTo(RestaurantInfo other) {
		return this.driveTimeValue - other.driveTimeValue;
	}
	
>>>>>>> e1477d2cc702d72b39874e28c2f215818421992e
	@Override
	public boolean equals(Object other) {
		if(other == this) return true;
		if(!(other instanceof RestaurantInfo)) return false;
		RestaurantInfo otherRestaurantInfo = (RestaurantInfo) other;
		return this.name.equals(otherRestaurantInfo.name) && this.placeID.equals(otherRestaurantInfo.placeID);
<<<<<<< HEAD
>>>>>>> 3668ec296f94964d5697cc559239c44490768a77
=======
>>>>>>> e1477d2cc702d72b39874e28c2f215818421992e
	}
}