package asgn2Tests;

import asgn2Pizzas.*;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaTopping;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalTime;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import asgn2Exceptions.PizzaException;


/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author n9450106 Cameron Short
 *
 */
public class PizzaTests {
	MeatLoversPizza MeatOne;
	MeatLoversPizza MeatTwo;
	MargheritaPizza MargheritaOne;
	MargheritaPizza Margheritatwo;

	VegetarianPizza VegOne;

	@Before
	public void setUp() throws Exception {
		this.MeatTwo = new MeatLoversPizza(1, LocalTime.of(19,20), LocalTime.of(19,30));
		this.VegOne = new VegetarianPizza(1, LocalTime.of(19, 20), LocalTime.of(19, 30));
		this.MargheritaOne = new MargheritaPizza(1, LocalTime.of(19, 20), LocalTime.of(19, 30));

	}
	
	@Test
	public void calculateCostForPizza() throws Exception {
		double pizzaCostTwo = MeatTwo.getCostPerPizza();
		assertTrue(pizzaCostTwo == 5);
	}
	
	
	
	@Test
	public void getPricePerPizzaTest() throws Exception {
		double pizzaPriceOne = MeatTwo.getPricePerPizza();
		assertTrue(pizzaPriceOne == 12);
	}

	@Test
	public void getOrderCostTest() throws Exception {
		double pizzaOrderCostTwo = MeatTwo.getOrderCost();
		assertTrue(pizzaOrderCostTwo == 5);

	}
	@Test
	public void getOrderPriceTest() throws Exception {
		double pizzaOrderPriceOne = MeatTwo.getOrderPrice();
		assertTrue(pizzaOrderPriceOne == 12);
		
	}
	
	@Test
	public void getOrderProfitTest() throws Exception {
		double pizzaOrderPriceOne = MeatTwo.getOrderProfit();
		assertTrue(pizzaOrderPriceOne == 7);
		
	}
	
	
	@Test
	public void getPizzaType() throws Exception {
		String pizzaOrderSizeTwo = MeatTwo.getPizzaType();
		assertEquals(pizzaOrderSizeTwo, "Meat Lovers");
	}
	
	@Test
	public void getPizzaTypeMeat() throws Exception {
		String pizzaType = MeatTwo.getPizzaType();
		assertEquals(pizzaType, "Meat Lovers");
	}
	
	public void getPizzaTypeMargherita() throws Exception {
		String pizzaType = MargheritaOne.getPizzaType();
		assertEquals(pizzaType, "Margherita");
	}
	
	public void getPizzaTypeVeg() throws Exception {
		String pizzaType = VegOne.getPizzaType();
		assertEquals(pizzaType, "Vegetaria");
	}
	

	@Test
	public void doesitcontainsToppingTest() throws Exception {
		assertFalse(MeatTwo.containsTopping(PizzaTopping.CAPSICUM));
		assertFalse(MeatTwo.containsTopping(PizzaTopping.MUSHROOM));
		assertFalse(MeatTwo.containsTopping(PizzaTopping.EGGPLANT));
	}
	
	
	@Test(expected = PizzaException.class)
	public void orderSizeBIG() throws Exception {
		new MeatLoversPizza(12, LocalTime.of(19, 20), LocalTime.of(19, 40));
	}
		


	@Test(expected = PizzaException.class)
	public void deliverBeforeOrderTest() throws Exception {
		new MeatLoversPizza(1, LocalTime.of(16, 20), LocalTime.of(19, 10));
	}
	
	
	@Test(expected = PizzaException.class)
	public void pizzaOldTest() throws Exception {
		new MeatLoversPizza(1, LocalTime.of(20, 20, 00), LocalTime.of(22, 49, 00));
	}
	
	
	@Test(expected = PizzaException.class)
	public void orderAfterShutTest() throws Exception {
		new MeatLoversPizza(11, LocalTime.of(23, 01, 00), LocalTime.of(23, 30, 00));
	}
	
	@Test(expected = PizzaException.class)
	public void orderBeforeOpenTest() throws Exception {
		new MeatLoversPizza(11, LocalTime.of(6, 30, 59), LocalTime.of(7, 30, 00));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
