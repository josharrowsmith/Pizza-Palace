package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 * 
 * @author n9667261 Josh Arrowsmith
 */
public class RestaurantCustomerTests {
	PizzaRestaurant restaurant = new PizzaRestaurant();
	@Before
	public void inputData() throws CustomerException, PizzaException, LogHandlerException{
		restaurant.processLog(".//logs/20170101.txt");
	}
	
	@Test
	public void logFileInput() throws CustomerException, PizzaException, LogHandlerException{
		PizzaRestaurant logFileInput = new PizzaRestaurant();
		assert(logFileInput.processLog(".//logs/20170101.txt"));
	}

	@Test
	public void GetCustName()throws CustomerException, PizzaException, LogHandlerException{
		String name = "";
		name = restaurant.getCustomerByIndex(2).getName();
		assertEquals(name, "Oroku Saki");
	}
	
	
	@Test
	public void testNumCustomerOrders(){
		int num = 0;
		num = restaurant.getNumCustomerOrders();
		assertEquals(num, 3);
	}
	
	
	@Test
	public void Resetfunction(){
		restaurant.resetDetails();
		assert(restaurant.getNumCustomerOrders() == 0);
	}
	
	@Test(expected = CustomerException.class)
	public void ProcessLog_BadLogInput() throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant logFileInput = new PizzaRestaurant();
		logFileInput.processLog(".//logs/20170104test.txt");
	}
	
	@Test(expected = LogHandlerException.class)
	public void ProcessLog_BadFileInput() throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant logFileInput = new PizzaRestaurant();
		logFileInput.processLog(".//logs/20170104test.png");
	}
	
	@Test(expected = CustomerException.class)
	public void getCustomerByIndex_Badparam() throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant logFileInput = new PizzaRestaurant();
		logFileInput.processLog(".//logs/20170101.txt");
		logFileInput.getCustomerByIndex(12);
	}
}
