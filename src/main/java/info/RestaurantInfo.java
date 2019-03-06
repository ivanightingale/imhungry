package info;

public class RestaurantInfo extends Info {
	//RestaurantInfo objects each store information of a restaurant.
	public String placeID;
	public String address;
	public int price;
	public String driveTime;
	public String phone;
	public String url;
	
	
	public RestaurantInfo(String name, double rating, String placeID, String address, int price, String driveTime,
			String phone, String url) {
		this.name = name;
		this.rating = rating;
		this.placeID = placeID;
		this.address = address;
		this.price = price;
		this.driveTime = driveTime;
		this.phone = phone;
		this.url = url;
	}
}