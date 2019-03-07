package info;

import static org.junit.Assert.*;

import org.junit.Test;

public class RestaurantInfoTest {

	@Test
	public void instantiationTest() {
		String name = "OK";
		int rating = 4;
		String placeID = "788";
		String address = "Tommy Trojan";
		int price = 50;
		String driveTimeText = "45 min";
		int driveTimeValue = 45;
		String phone = "2134005888";
		String url = "github.com";
		RestaurantInfo ri = new RestaurantInfo(name,rating,placeID,address,price,driveTimeText,driveTimeValue,phone,url);
		assertEquals("OK",ri.name);
		assertEquals(4,ri.rating);
		assertEquals("788",ri.placeID);
		assertEquals("Tommy Trojan",ri.address);
		assertEquals(50,ri.price);
		assertEquals("45 min",ri.driveTimeText);
		assertEquals(45,ri.driveTimeValue);
		assertEquals("2134005888",ri.phone);
		assertEquals("github.com",ri.url);
	}
	
	@Test
	public void comparisonTest1() {
		String name = "OK";
		int rating = 4;
		String placeID = "788";
		String address = "Tommy Trojan";
		int price = 50;
		String driveTimeText = "45 min";
		int driveTimeValue = 45;
		String phone = "2134005888";
		String url = "github.com";
		RestaurantInfo ri = new RestaurantInfo(name,rating,placeID,address,price,driveTimeText,driveTimeValue,phone,url);
		driveTimeValue = 20;
		RestaurantInfo ri2 = new RestaurantInfo(name,rating,placeID,address,price,driveTimeText,driveTimeValue,phone,url);
		assertEquals("compareTo error",25,ri.compareTo(ri2));
	}
	
	@Test
	public void comparisonTest2() {
		String name = "OK";
		int rating = 4;
		String placeID = "788";
		String address = "Tommy Trojan";
		int price = 50;
		String driveTimeText = "45 min";
		int driveTimeValue = 45;
		String phone = "2134005888";
		String url = "github.com";
		RestaurantInfo ri = new RestaurantInfo(name,rating,placeID,address,price,driveTimeText,driveTimeValue,phone,url);
		price = 40;
		name = "Newone";
		url = "usc.edu";
		RestaurantInfo ri2 = new RestaurantInfo(name,rating,placeID,address,price,driveTimeText,driveTimeValue,phone,url);
		assertEquals("compareTo error",0,ri.compareTo(ri2));
	}
	
	@Test
	public void equalsTest1() {
		String name = "OK";
		int rating = 4;
		String placeID = "788";
		String address = "Tommy Trojan";
		int price = 50;
		String driveTimeText = "45 min";
		int driveTimeValue = 45;
		String phone = "2134005888";
		String url = "github.com";
		RestaurantInfo ri = new RestaurantInfo(name,rating,placeID,address,price,driveTimeText,driveTimeValue,phone,url);
		RestaurantInfo ri2 = new RestaurantInfo(name,rating,placeID,address,price,driveTimeText,driveTimeValue,phone,url);
		assertEquals(true, ri.equals(ri2));
		ri.rating = 99;
		ri.address="";
		ri.price = 99;
		ri.driveTimeText = "";
		ri.driveTimeValue=99;
		ri.phone="";
		ri.url="";
		assertEquals("wrong equal comparison method",true, ri.equals(ri2));
		ri.name = "";
		assertEquals("wrong equal comparison method",false, ri.equals(ri2));
		ri.name = ri2.name;
		assertEquals("wrong equal comparison method",true, ri.equals(ri2));
		ri.placeID = "";
		assertEquals("wrong equal comparison method",false,ri.equals(ri2));
	}
	
	@Test
	public void equalsTest2() {
		String name = "OK";
		int rating = 4;
		String placeID = "788";
		String address = "Tommy Trojan";
		int price = 50;
		String driveTimeText = "45 min";
		int driveTimeValue = 45;
		String phone = "2134005888";
		String url = "github.com";
		RestaurantInfo ri = new RestaurantInfo(name,rating,placeID,address,price,driveTimeText,driveTimeValue,phone,url);
		Object ok = new Object();
		assertEquals("other type of instances equal",false, ri.equals(ok));
	}
	
	@Test
	public void equalsTest3() {
		String name = "OK";
		int rating = 4;
		String placeID = "788";
		String address = "Tommy Trojan";
		int price = 50;
		String driveTimeText = "45 min";
		int driveTimeValue = 45;
		String phone = "2134005888";
		String url = "github.com";
		RestaurantInfo ri = new RestaurantInfo(name,rating,placeID,address,price,driveTimeText,driveTimeValue,phone,url);
		assertEquals("same object doesn't equal",true, ri.equals(ri));
	}
	
}


