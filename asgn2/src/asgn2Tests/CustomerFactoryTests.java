package asgn2Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.*;
import asgn2Exceptions.CustomerException;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author n9667261 Josh Arrowsmith
 *
 */
public class CustomerFactoryTests {
	
	Customer pickup = CustomerFactory.getCustomer("PUC", "Test PUC", "0400123456", 0, 0);
	Customer driver = CustomerFactory.getCustomer("DVC", "Test DVC", "0400123456", 10,  -8);
	Customer drone = CustomerFactory.getCustomer("DNC", "Test DNC", "0400123456", 1,  1);

	public CustomerFactoryTests() throws CustomerException{}
	
	@Before @Test
	public void setUp(){
	}
	
	@Test
	public void getCustomer_TypeIsCorrect() throws CustomerException{
		assertEquals("PickUp", pickup.getCustomerType());
		assertEquals("Driver", driver.getCustomerType());
		assertEquals("Drone", drone.getCustomerType());
	}
	
	@Test
	public void getCustomer_NameIsCorrect() throws CustomerException{
		assertEquals("Test PUC", pickup.getName());
		assertEquals("Test DVC", driver.getName());
		assertEquals("Test DNC", drone.getName());
	}
	
	@Test
	public void getCustomer_DistanceIsCorrect() throws CustomerException{
		double pickupDistance = 0;
		float driverDistance = Math.abs((10) + (-8));
		double droneDistance = Math.sqrt(Math.abs((1)^2) + Math.abs((1)^2));
		
		assertEquals(pickupDistance, pickup.getDeliveryDistance(), 0.01);
		assertEquals(driverDistance, driver.getDeliveryDistance(), 0.01);
		assertEquals(droneDistance, drone.getDeliveryDistance(), 0.01);
	}
	
	@Test
	public void getCustomer_LocationXIsCorrect() throws CustomerException{
		int locationXpickup = 0;
		int locationXdriver = 10;
		int locationXdrone = 1;
		assertEquals(locationXpickup, pickup.getLocationX());
		assertEquals(locationXdriver, driver.getLocationX());
		assertEquals(locationXdrone, drone.getLocationX());
	}
	
	@Test
	public void getCustomer_LocationYIsCorrect() throws CustomerException{
		int locationYpickup = 0;
		int locationYdriver = -8;
		int locationYdrone = 1;
		assertEquals(locationYpickup, pickup.getLocationY());
		assertEquals(locationYdriver, driver.getLocationY());
		assertEquals(locationYdrone, drone.getLocationY());
	}
	
	@Test
	public void getCustomer_MobileIsCorrect() throws CustomerException{
		String mobileNumber = "0400123456";
		assertEquals(mobileNumber, pickup.getMobileNumber());
		assertEquals(mobileNumber, driver.getMobileNumber());
		assertEquals(mobileNumber, drone.getMobileNumber());
	}
	
	@Test (expected = CustomerException.class)
	public void getCustomer_WrongId() throws CustomerException{
		CustomerFactory.getCustomer("QWER", "jim", "0123465789", 10, 10);
	}
}
