package info;

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
		this.name = name;
		this.rating = rating;
		this.address = address;
		this.price = price;
		this.driveTime = driveTime;
		this.phone = phone;
		this.url = url;
	}
	
	public String getAddress() { return address; }
	
	public String getPrice() { return price; }
	
	public int getDriveTime() { return driveTime; }
	
	public String getPhone() { return phone; }
	
	public String getURL() {return url; }
	
	public int getRating() {return rating;}
	
	public boolean equals(RestaurantInfo other) {
		return this.name == other.name && this.address == other.address;
	}
}
