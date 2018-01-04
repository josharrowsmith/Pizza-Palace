package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Restaurant.PizzaRestaurant;


/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author n9450106 Cameron Short
 *
 */
public class RestaurantPizzaTests {
	
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
	
	
	public void getIndex() throws PizzaException{
		assertEquals("Vegetarian", restaurant.getPizzaByIndex(0).getPizzaType());
	}
	
	@Test
	public void getNumOfPizzaOrdersFromLogFile(){
		assertEquals(3, restaurant.getNumPizzaOrders());
	}
	@Test
	public void getTotalProfitFromLogFile(){
		assertEquals(36.5, restaurant.getTotalProfit(),0.001);
	}
	@Test
	public void Resetfunction(){
		restaurant.resetDetails();
		assert(restaurant.getNumPizzaOrders() == 0);
	}
	
	@Test(expected = PizzaException.class)
	public void orderAfterShutTest() throws Exception {
		new MeatLoversPizza(11, LocalTime.of(23, 01, 00), LocalTime.of(23, 30, 00));
	}
	
	@Test(expected = PizzaException.class)
	public void orderBeforeOpenTest() throws Exception {
		new MeatLoversPizza(11, LocalTime.of(6, 30, 59), LocalTime.of(7, 30, 00));
	}
	
	@Test(expected = PizzaException.class)
	public void ProcessLog_BadLogInput() throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant logFileInput = new PizzaRestaurant();
		logFileInput.processLog(".//logs/20170104test2.txt");
	}
	
	@Test(expected = LogHandlerException.class)
	public void ProcessLog_BadFileInput() throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant logFileInput = new PizzaRestaurant();
		logFileInput.processLog(".//logs/20170104test.png");
	}
	
	@Test(expected = PizzaException.class)
	public void getCustomerByIndex_Badparam() throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant logFileInput = new PizzaRestaurant();
		logFileInput.processLog(".//logs/20170101.txt");
		logFileInput.getPizzaByIndex(12);
	}
}