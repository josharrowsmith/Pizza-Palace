package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author n9667261 Josh Arrowsmith
 */
public class LogHandlerCustomerTests {
	public LogHandlerCustomerTests() throws CustomerException, LogHandlerException{}
	String createTestInputDriver = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
	String createTestInputDrone = "20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1";
	String createTestInputPickup = "21:00:00,21:35:00,Oroku Saki,0111222333,PUC,0,0,PZL,3";
	
	@Before @Test 
	public void setUp(){
	}
	
	@Test
	public void createCustomer_ClassIsCorrect() throws CustomerException, LogHandlerException{
		Customer driver = LogHandler.createCustomer(createTestInputDriver);
		Customer drone = LogHandler.createCustomer(createTestInputDrone);
		Customer pickup = LogHandler.createCustomer(createTestInputPickup);
		assertEquals(DriverDeliveryCustomer.class, driver.getClass());
		assertEquals(DroneDeliveryCustomer.class, drone.getClass());
		assertEquals(PickUpCustomer.class, pickup.getClass());
	}
	
	@Test
	public void createCustomer_NameIsCorrect() throws CustomerException, LogHandlerException{
		Customer driver = LogHandler.createCustomer(createTestInputDriver);
		Customer drone = LogHandler.createCustomer(createTestInputDrone);
		Customer pickup = LogHandler.createCustomer(createTestInputPickup);
		assertEquals("Casey Jones", driver.getName());
		assertEquals("April O'Neal", drone.getName());
		assertEquals("Oroku Saki", pickup.getName());
	}
	
	@Test
	public void createCustomer_MobileNumberIsCorrect() throws CustomerException, LogHandlerException{
		Customer driver = LogHandler.createCustomer(createTestInputDriver);
		Customer drone = LogHandler.createCustomer(createTestInputDrone);
		Customer pickup = LogHandler.createCustomer(createTestInputPickup);
		assertEquals("0123456789", driver.getMobileNumber());
		assertEquals("0987654321", drone.getMobileNumber());
		assertEquals("0111222333", pickup.getMobileNumber());
	}
	
	@Test
	public void createCustomer_LocationXIsCorrect() throws CustomerException, LogHandlerException{
		Customer driver = LogHandler.createCustomer(createTestInputDriver);
		Customer drone = LogHandler.createCustomer(createTestInputDrone);
		Customer pickup = LogHandler.createCustomer(createTestInputPickup);
		assertEquals(5, driver.getLocationX());
		assertEquals(3, drone.getLocationX());
		assertEquals(0, pickup.getLocationX());
	}
	
	@Test
	public void createCustomer_LocationYIsCorrect() throws CustomerException, LogHandlerException{
		Customer driver = LogHandler.createCustomer(createTestInputDriver);
		Customer drone = LogHandler.createCustomer(createTestInputDrone);
		Customer pickup = LogHandler.createCustomer(createTestInputPickup);
		assertEquals(5, driver.getLocationY());
		assertEquals(4, drone.getLocationY());
		assertEquals(0, pickup.getLocationY());
	}
	
	@Test
	public void populateCustomerDataset_TypeIsCorrect() throws CustomerException, LogHandlerException{
		ArrayList<Customer> customerList = LogHandler.populateCustomerDataset("logs/20170101.txt");
		assertEquals("Driver", customerList.get(0).getCustomerType());
		assertEquals("Drone", customerList.get(1).getCustomerType());
		assertEquals("PickUp", customerList.get(2).getCustomerType());

	}
	
	@Test (expected = LogHandlerException.class)
	public void populateCustomerDataset_InvalidLogFile() throws CustomerException, LogHandlerException{
		ArrayList<Customer> customerList = LogHandler.populateCustomerDataset("logsasd0170101.txt");
	}
	
	@Test (expected = CustomerException.class)
	public void populateCustomerDataset_IncorrectEntriesInLogFile() throws LogHandlerException, CustomerException{
		ArrayList<Customer> customerList = LogHandler.populateCustomerDataset("logs/20170104test.txt");
	}
	
	@Test (expected = LogHandlerException.class)
	public void createPizza_InvalidString() throws LogHandlerException, CustomerException{
		Customer customer = LogHandler.createCustomer("logsasd017asd0101.txt");
	}
	
	@Test (expected = CustomerException.class)
	public void createPizza_IncorrectEntriesInLogFile() throws LogHandlerException, CustomerException{
		Customer customer = LogHandler.createCustomer("20:05:00,20:26:00,Aiden Zhang,0161429209,BADTESTC,-3,9,PBADCODE,2");
	}
}
