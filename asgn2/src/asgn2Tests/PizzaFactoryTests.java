package asgn2Tests;


import static org.junit.Assert.*;

import java.time.LocalTime;



import asgn2Customers.CustomerFactory;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.*;
import org.junit.Before;
import org.junit.Test;

/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author n9450106 Cameron Short
 * 
 */
public class PizzaFactoryTests {
	// TO DO

	private Pizza Pizzas;
	private MargheritaPizza testMargheritaPizza;
	private MeatLoversPizza testMeatPizza;
	
	@Before
	public void setup() throws Exception{
		
	}
	
	
	@Test (expected = PizzaException.class)
	public void invalidTypeTest1() throws Exception{
		PizzaFactory.getPizza("Margherita", 1, LocalTime.of(19,20), LocalTime.of(19,30));
	}
	
	@Test (expected = PizzaException.class)
	public void invalidTypeTest4() throws Exception{
		PizzaFactory.getPizza("BUT", 1, LocalTime.of(19,20), LocalTime.of(19,30));
	}
	
	@Test
	public void isItMargheritaTest() throws Exception {
		Pizzas = PizzaFactory.getPizza("PZM", 1, LocalTime.of(19,20), LocalTime.of(19,30));
		testMargheritaPizza = new MargheritaPizza(1, LocalTime.of(19,20), LocalTime.of(19,30));
		assertTrue(testMargheritaPizza.equals(Pizzas));
	}
	
	@Test
	public void returnMeatLoversTest() throws Exception {
		Pizzas = PizzaFactory.getPizza("PZL", 1, LocalTime.of(19,20), LocalTime.of(19,30));
		testMeatPizza = new MeatLoversPizza(1, LocalTime.of(19,20), LocalTime.of(19,30));
		assertTrue(testMeatPizza.equals(Pizzas));
	}
	
	@Test (expected = PizzaException.class)
	public void getFactory_WrongId() throws PizzaException{
		PizzaFactory.getPizza("PZMG", 1,  LocalTime.of(19,20), LocalTime.of(19,30));
	}
				
}
