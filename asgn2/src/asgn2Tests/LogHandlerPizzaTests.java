package asgn2Tests;


import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.PickUpCustomer;
import asgn2Pizzas.*;
import asgn2Restaurant.LogHandler;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;

/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author n9450106 Cameron Short
* 
*/
public class LogHandlerPizzaTests {
	Pizza createPizza_TestPizza;
	Pizza meatLoversPizza_TestPizza;
	Pizza vegetarianPizza_TestPizza;
	Pizza margheritaPizza_TestPizza;
	
	String createPizza_testMargPizzaLogEntry = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZM,2";
	String createPizza_testMeatPizzaLogEntry = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZL,2";
	String createPizza_testVegPizzaLogEntry = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2";
	
	public void setup() throws Exception{
		
	}
	
	@Test
	public void createPizza_MargharitaPizzaClass() throws Exception {
		createPizza_TestPizza = LogHandler.createPizza(createPizza_testMargPizzaLogEntry);
		meatLoversPizza_TestPizza = new MargheritaPizza(1,LocalTime.of(19,00), LocalTime.of(19,20));
		assertEquals(meatLoversPizza_TestPizza.getClass(),createPizza_TestPizza.getClass());
	}
	
	@Test
	public void createPizza_MeatLoversPizzaClass() throws Exception {
		createPizza_TestPizza = LogHandler.createPizza(createPizza_testMeatPizzaLogEntry);
		meatLoversPizza_TestPizza = new MeatLoversPizza(1,LocalTime.of(19,00), LocalTime.of(19,20));
		assertEquals(meatLoversPizza_TestPizza.getClass(),createPizza_TestPizza.getClass());
	}
	
	@Test
	public void createPizza_VegitarianPizzaClass() throws Exception {
		createPizza_TestPizza = LogHandler.createPizza(createPizza_testVegPizzaLogEntry);
		vegetarianPizza_TestPizza = new VegetarianPizza(1,LocalTime.of(19,00), LocalTime.of(19,20));
		assertEquals(vegetarianPizza_TestPizza.getClass(),createPizza_TestPizza.getClass());
	}
	
	@Test
	public void createPizza_GetOrderPrice() throws Exception {
		Pizza createPizza_TestMargPizza = LogHandler.createPizza(createPizza_testMargPizzaLogEntry);
		Pizza createPizza_TestVegPizza = LogHandler.createPizza(createPizza_testVegPizzaLogEntry);
		Pizza createPizza_TestMeatPizza = LogHandler.createPizza(createPizza_testMeatPizzaLogEntry);
		margheritaPizza_TestPizza = PizzaFactory.getPizza("PZM",2,LocalTime.of(19,00), LocalTime.of(19,20));
		vegetarianPizza_TestPizza = PizzaFactory.getPizza("PZV",2,LocalTime.of(19,00), LocalTime.of(19,20));
		meatLoversPizza_TestPizza = PizzaFactory.getPizza("PZL",2,LocalTime.of(19,00), LocalTime.of(19,20));
		assertEquals(margheritaPizza_TestPizza.getOrderPrice(), createPizza_TestMargPizza.getOrderPrice(),0.01);
		assertEquals(vegetarianPizza_TestPizza.getOrderPrice(), createPizza_TestVegPizza.getOrderPrice(),0.01);
		assertEquals(meatLoversPizza_TestPizza.getOrderPrice(), createPizza_TestMeatPizza.getOrderPrice(),0.01);
	}
	
	@Test
	public void createPizza_GetCostPerPizza() throws Exception {
		Pizza createPizza_TestMargPizza = LogHandler.createPizza(createPizza_testMargPizzaLogEntry);
		Pizza createPizza_TestVegPizza = LogHandler.createPizza(createPizza_testVegPizzaLogEntry);
		Pizza createPizza_TestMeatPizza = LogHandler.createPizza(createPizza_testMeatPizzaLogEntry);
		margheritaPizza_TestPizza = PizzaFactory.getPizza("PZM",2,LocalTime.of(19,00), LocalTime.of(19,20));
		vegetarianPizza_TestPizza = PizzaFactory.getPizza("PZV",2,LocalTime.of(19,00), LocalTime.of(19,20));
		meatLoversPizza_TestPizza = PizzaFactory.getPizza("PZL",2,LocalTime.of(19,00), LocalTime.of(19,20));
		assertEquals(margheritaPizza_TestPizza.getCostPerPizza(), createPizza_TestMargPizza.getCostPerPizza(),0.01);
		assertEquals(vegetarianPizza_TestPizza.getCostPerPizza(), createPizza_TestVegPizza.getCostPerPizza(),0.01);
		assertEquals(meatLoversPizza_TestPizza.getCostPerPizza(), createPizza_TestMeatPizza.getCostPerPizza(),0.01);
	}
	
	@Test
	public void createPizza_GetOrderCost() throws Exception {
		Pizza createPizza_TestMargPizza = LogHandler.createPizza(createPizza_testMargPizzaLogEntry);
		Pizza createPizza_TestVegPizza = LogHandler.createPizza(createPizza_testVegPizzaLogEntry);
		Pizza createPizza_TestMeatPizza = LogHandler.createPizza(createPizza_testMeatPizzaLogEntry);
		margheritaPizza_TestPizza = PizzaFactory.getPizza("PZM",2,LocalTime.of(19,00), LocalTime.of(19,20));
		vegetarianPizza_TestPizza = PizzaFactory.getPizza("PZV",2,LocalTime.of(19,00), LocalTime.of(19,20));
		meatLoversPizza_TestPizza = PizzaFactory.getPizza("PZL",2,LocalTime.of(19,00), LocalTime.of(19,20));
		assertEquals(margheritaPizza_TestPizza.getOrderCost(), createPizza_TestMargPizza.getOrderCost(),0.01);
		assertEquals(vegetarianPizza_TestPizza.getOrderCost(), createPizza_TestVegPizza.getOrderCost(),0.01);
		assertEquals(meatLoversPizza_TestPizza.getOrderCost(), createPizza_TestMeatPizza.getOrderCost(),0.01);
	}
	
	@Test
	public void createPizza_GetOrderProfit() throws Exception {
		Pizza createPizza_TestMargPizza = LogHandler.createPizza(createPizza_testMargPizzaLogEntry);
		Pizza createPizza_TestVegPizza = LogHandler.createPizza(createPizza_testVegPizzaLogEntry);
		Pizza createPizza_TestMeatPizza = LogHandler.createPizza(createPizza_testMeatPizzaLogEntry);
		margheritaPizza_TestPizza = PizzaFactory.getPizza("PZM",2,LocalTime.of(19,00), LocalTime.of(19,20));
		vegetarianPizza_TestPizza = PizzaFactory.getPizza("PZV",2,LocalTime.of(19,00), LocalTime.of(19,20));
		meatLoversPizza_TestPizza = PizzaFactory.getPizza("PZL",2,LocalTime.of(19,00), LocalTime.of(19,20));
		assertEquals(margheritaPizza_TestPizza.getOrderProfit(), createPizza_TestMargPizza.getOrderProfit(),0.01);
		assertEquals(vegetarianPizza_TestPizza.getOrderProfit(), createPizza_TestVegPizza.getOrderProfit(),0.01);
		assertEquals(meatLoversPizza_TestPizza.getOrderProfit(), createPizza_TestMeatPizza.getOrderProfit(),0.01);
	}
	
	@Test
	public void createPizza_GetPricePerPizza() throws Exception {
		Pizza createPizza_TestMargPizza = LogHandler.createPizza(createPizza_testMargPizzaLogEntry);
		Pizza createPizza_TestVegPizza = LogHandler.createPizza(createPizza_testVegPizzaLogEntry);
		Pizza createPizza_TestMeatPizza = LogHandler.createPizza(createPizza_testMeatPizzaLogEntry);
		margheritaPizza_TestPizza = PizzaFactory.getPizza("PZM",2,LocalTime.of(19,00), LocalTime.of(19,20));
		vegetarianPizza_TestPizza = PizzaFactory.getPizza("PZV",2,LocalTime.of(19,00), LocalTime.of(19,20));
		meatLoversPizza_TestPizza = PizzaFactory.getPizza("PZL",2,LocalTime.of(19,00), LocalTime.of(19,20));
		assertEquals(margheritaPizza_TestPizza.getPricePerPizza(), createPizza_TestMargPizza.getPricePerPizza(),0.01);
		assertEquals(vegetarianPizza_TestPizza.getPricePerPizza(), createPizza_TestVegPizza.getPricePerPizza(),0.01);
		assertEquals(meatLoversPizza_TestPizza.getPricePerPizza(), createPizza_TestMeatPizza.getPricePerPizza(),0.01);
	}
	
	@Test
	public void createPizza_GetQuantity() throws Exception {
		Pizza createPizza_TestMargPizza = LogHandler.createPizza(createPizza_testMargPizzaLogEntry);
		Pizza createPizza_TestVegPizza = LogHandler.createPizza(createPizza_testVegPizzaLogEntry);
		Pizza createPizza_TestMeatPizza = LogHandler.createPizza(createPizza_testMeatPizzaLogEntry);
		margheritaPizza_TestPizza = PizzaFactory.getPizza("PZM",2,LocalTime.of(19,00), LocalTime.of(19,20));
		vegetarianPizza_TestPizza = PizzaFactory.getPizza("PZV",2,LocalTime.of(19,00), LocalTime.of(19,20));
		meatLoversPizza_TestPizza = PizzaFactory.getPizza("PZL",2,LocalTime.of(19,00), LocalTime.of(19,20));
		assertEquals(margheritaPizza_TestPizza.getQuantity(), createPizza_TestMargPizza.getQuantity(),0.01);
		assertEquals(vegetarianPizza_TestPizza.getQuantity(), createPizza_TestVegPizza.getQuantity(),0.01);
		assertEquals(meatLoversPizza_TestPizza.getQuantity(), createPizza_TestMeatPizza.getQuantity(),0.01);
	}
	
	@Test
	public void populateCustomerDataset_TypeIsCorrect() throws CustomerException, LogHandlerException, PizzaException{
		ArrayList<Pizza> pizzaList = LogHandler.populatePizzaDataset("logs/20170101.txt");
		assertEquals("Vegetarian", pizzaList.get(0).getPizzaType());
		assertEquals("Margherita", pizzaList.get(1).getPizzaType());
		assertEquals("Meat Lovers", pizzaList.get(2).getPizzaType());
	}
	
	@Test (expected = LogHandlerException.class)
	public void populateCustomerDataset_InvalidLogFile() throws PizzaException, LogHandlerException{
		ArrayList<Pizza> pizzaList = LogHandler.populatePizzaDataset("logsasd0170101.txt");
	}
	
	@Test (expected = PizzaException.class)
	public void populateCustomerDataset_IncorrectEntriesInLogFile() throws PizzaException, LogHandlerException{
		ArrayList<Pizza> pizzaList = LogHandler.populatePizzaDataset("logs/20170104test.txt");
	}
	
	@Test (expected = LogHandlerException.class)
	public void createPizza_InvalidString() throws PizzaException, LogHandlerException{
		Pizza pizza = LogHandler.createPizza("logsasd017asd0101.txt");
	}
	
	@Test (expected = PizzaException.class)
	public void createPizza_IncorrectEntriesInLogFile() throws PizzaException, LogHandlerException{
		Pizza pizza = LogHandler.createPizza("20:05:00,20:26:00,Aiden Zhang,0161429209,DVC,-3,9,PBADCODE,2");
	}
		
}
