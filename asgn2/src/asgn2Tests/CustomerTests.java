package asgn2Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;

/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author n9667261 Josh Arrowsmith
 * 
 *
 */
public class CustomerTests {
	public CustomerTests() throws CustomerException{}
	Customer driver = new DriverDeliveryCustomer("name", "0400123456", -10, 4);
	Customer drone = new DroneDeliveryCustomer("name", "0400123456", 3, -5);
	Customer pickup = new PickUpCustomer("name", "0400123456", 0, 0);
	
	@Before @Test
	public void setUp(){
	}
	//Super Tests-----
	
	@Test
	public void getMobileNumber(){
		String mobileNumber = "0400123456";
		assertEquals(mobileNumber, driver.getMobileNumber());
		assertEquals(mobileNumber, drone.getMobileNumber());
		assertEquals(mobileNumber, pickup.getMobileNumber());
	}
	
	@Test
	public void getCustomerType(){
		assertEquals("Driver", driver.getCustomerType());
		assertEquals("Drone", drone.getCustomerType());
		assertEquals("PickUp", pickup.getCustomerType());
	}
	
	@Test
	public void getLocationX(){
		int locationXdriver = -10;
		int locationXdrone = 3;
		int locationXpickup = 0;
		assertEquals(locationXdriver, driver.getLocationX());
		assertEquals(locationXdrone, drone.getLocationX());
		assertEquals(locationXpickup, pickup.getLocationX());
	}
	
	@Test
	public void getLocationY(){
		int locationYdriver = 4;
		int locationYdrone = -5;
		int locationYpickup = 0;
		assertEquals(locationYdriver, driver.getLocationY());
		assertEquals(locationYdrone, drone.getLocationY());
		assertEquals(locationYpickup, pickup.getLocationY());
	}
	//other tests-----
	@Test
	public void driverDistance(){
		double deliveryDistance = Math.abs(-10 + 4);
		assertEquals(deliveryDistance, driver.getDeliveryDistance(), 0.001);
	}
	
	@Test
	public void droneDistance(){
		double deliveryDistance = Math.sqrt(Math.abs((3)^2) + Math.abs((-5)^2));
		assertEquals(deliveryDistance, drone.getDeliveryDistance(), 0.001);
	}
	
	@Test
	public void pickupDistance(){
		double deliveryDistance = 0;
		assertEquals(deliveryDistance, pickup.getDeliveryDistance(), 0.001);
	}
	
	//Exception Tests
	@Test (expected = CustomerException.class)
	public void createCustomer_nameTooLong() throws CustomerException{
		new DriverDeliveryCustomer("NameLongerThan20 Chars", "0400123456", -54321, -12345);
	}
	
	@Test (expected = CustomerException.class)
	public void createCustomer_nameTooShort() throws CustomerException{
		new DriverDeliveryCustomer("", "0400123456", -54321, -12345);
	}
	
	@Test (expected = CustomerException.class)
	public void createCustomer_mobileNumberWrongLengthLong() throws CustomerException{
		new DriverDeliveryCustomer("Name", "04001234562134", -5, -6);
	}
	
	@Test (expected = CustomerException.class)
	public void createCustomer_mobileNumberWrongLengthShort() throws CustomerException{
		new DriverDeliveryCustomer("Name", "0", -5, -7);
	}
	
	@Test (expected = CustomerException.class)
	public void createCustomer_mobileNumberNo0Start() throws CustomerException{
		new DriverDeliveryCustomer("Name", "1234567890", -2, -3);
	}
	
	@Test (expected = CustomerException.class)
	public void createCustomer_locationXToLarge() throws CustomerException{
		new DriverDeliveryCustomer("Name", "0123456789", 99, -6);
	}
	
	@Test (expected = CustomerException.class)
	public void createCustomer_locationXToSmall() throws CustomerException{
		new DriverDeliveryCustomer("Name", "0123456789", -12, -6);
	}
	
	@Test (expected = CustomerException.class)
	public void createCustomer_locationYToLarge() throws CustomerException{
		new DriverDeliveryCustomer("Name", "0123456789", 3, 6764574);
	}
	
	@Test (expected = CustomerException.class)
	public void createCustomer_locationYToSmall() throws CustomerException{
		new DriverDeliveryCustomer("Name", "0123456789", 3, -55);
	}
	
	@Test (expected = CustomerException.class)
	public void createCustomer_pickUpDistanceNot0() throws CustomerException{
		new PickUpCustomer("Name", "0123456789", 3, -55);
	}
}
