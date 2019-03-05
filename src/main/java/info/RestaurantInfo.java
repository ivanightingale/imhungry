package info;

public class RestaurantInfo extends Info {
	public String address;
	public int price;
	public int driveTime;
	public String phone;
	public String url;
	
	
	public RestaurantInfo(String name, int rating, String address, int price, int driveTime, String phone,
			String url) {
		this.name = name;
		this.rating = rating;
		this.address = address;
		this.price = price;
		this.driveTime = driveTime;
		this.phone = phone;
		this.url = url;
	}
	
	//check whether two RestaurantInfo objects are equal. Used for biases based on lists. Many of the
	//parameters can potentially change during a session, especially driveTime which is influenced by
	//traffic situations. It is reasonable and sufficient to compare only the parameters involved in this
	//function.
	public boolean equals(RestaurantInfo other) {
		return this.name.equals(other.name) && this.address.equals(other.address) &&
				this.url.equals(other.url);
	}
}
