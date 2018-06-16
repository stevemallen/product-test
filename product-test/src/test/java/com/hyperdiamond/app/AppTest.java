package com.hyperdiamond.app;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;
import org.junit.Test;
import junit.framework.TestCase;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for the checkout app
 */
public class AppTest extends TestCase
{
    
	@Test
    public void testCheckoutSingleItem()
    {
		CheckoutService checkoutService = new CheckoutServiceImpl();
		Basket basket = new Basket();
		basket.add(new Apple());
		MonetaryAmount total = checkoutService.totalCost(basket);
		assertEquals("Incorrect total", FastMoney.of(0.60, Monetary.getCurrency("GBP")), total);
    }
	
	@Test
    public void testCheckoutMulipleItems()
    {
		CheckoutService checkoutService = new CheckoutServiceImpl();
		Basket basket = new Basket();
		basket.add(new Apple(), 3);
		basket.add(new Orange(), 1);
		MonetaryAmount total = checkoutService.totalCost(basket);
		assertEquals("Incorrect total", FastMoney.of(2.05, Monetary.getCurrency("GBP")), total);
    }
	
	@Test
    public void testEmptyBasket()
    {
		CheckoutService checkoutService = new CheckoutServiceImpl();
		Basket basket = new Basket();
		MonetaryAmount total = checkoutService.totalCost(basket);
		assertEquals("Incorrect total", FastMoney.of(0, Monetary.getCurrency("GBP")), total);
    }
	
	@Test
    public void testBadInput()
    {
		try {
			Basket basket = new Basket();
			basket.add(new Apple(), 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("quantity must be greater than zero"));
		}
    }
	
	
}
