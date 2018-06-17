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
public class AppTest extends TestCase {

	private CheckoutService checkoutService = new CheckoutServiceImpl();
	private Basket basket = new Basket();

	@Test
	public void testCheckoutSingleItem() {
		basket.empty();
		basket.add(Product.getApple());
		MonetaryAmount total = checkoutService.totalCost(basket);
		assertEquals("Incorrect total", FastMoney.of(0.60, Monetary.getCurrency("GBP")), total);
	}

	@Test
	public void testCheckoutMulipleItems() {
		basket.empty();
		basket.add(Product.getApple());
		basket.add(Product.getApple());
		basket.add(Product.getOrange());
		basket.add(Product.getApple());
		MonetaryAmount total = checkoutService.totalCost(basket);
		assertEquals("Incorrect total", FastMoney.of(2.05, Monetary.getCurrency("GBP")), total);
	}

	@Test
	public void testEmptyBasket() {
		basket.empty();
		MonetaryAmount total = checkoutService.totalCost(basket);
		assertEquals("Incorrect total", FastMoney.of(0, Monetary.getCurrency("GBP")), total);
	}

	@Test
	public void testBadInput() {
		try {
			basket.empty();
			basket.add(Product.getApple(), 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("quantity must be greater than zero"));
		}
	}

	@Test
	public void testBasketCount() {
		basket.empty();
		basket.add(Product.getOrange(), 3);
		assertEquals(3, basket.productCount(Product.getOrange()), 1);
	}
	
	@Test
	public void testThreeForTwoOranges() {
		
		CheckoutService discountCheckout = new CheckoutServiceImpl();
		ThreeForTwo threeForTwo = new ThreeForTwo(Product.getOrange());
		discountCheckout.addDiscountOffer(threeForTwo);
		
		basket.empty();
		basket.add(Product.getOrange(), 2);
		assertEquals("Incorrect total for basket: " + basket, FastMoney.of(0.50, Monetary.getCurrency("GBP")),
				discountCheckout.totalCost(basket));
		
		// 3rd one free
		basket.add(Product.getOrange());
		assertEquals("Incorrect total for basket: " + basket, FastMoney.of(0.50, Monetary.getCurrency("GBP")),
				discountCheckout.totalCost(basket));
		
		// 4 orange = price of 2 (3) + 1
		basket.add(Product.getOrange());
		assertEquals("Incorrect total for basket: " + basket, FastMoney.of(0.75, Monetary.getCurrency("GBP")),
				discountCheckout.totalCost(basket));
		
		// 5 orange = price of 2 (3) + 2
		basket.add(Product.getOrange());
		assertEquals("Incorrect total for basket: " + basket, FastMoney.of(1, Monetary.getCurrency("GBP")),
				discountCheckout.totalCost(basket));
		
		// 6 orange = price of 4 (3)
		basket.add(Product.getOrange());
		assertEquals("Incorrect total for basket: " + basket, FastMoney.of(1, Monetary.getCurrency("GBP")),
				discountCheckout.totalCost(basket));
		
	}
	
	@Test
	public void testBogofApples() {
		
		CheckoutService discountCheckout = new CheckoutServiceImpl();
		BuyOneGetOneFree buyOneGetOneFree = new BuyOneGetOneFree(Product.getApple());
		discountCheckout.addDiscountOffer(buyOneGetOneFree);
		
		basket.empty();
		basket.add(Product.getApple());
		assertEquals("Incorrect total for basket: " + basket, FastMoney.of(0.60, Monetary.getCurrency("GBP")),
				discountCheckout.totalCost(basket));
		
		basket.add(Product.getApple());
		assertEquals("Incorrect total for basket: " + basket, FastMoney.of(0.60, Monetary.getCurrency("GBP")),
				discountCheckout.totalCost(basket));
		
		basket.add(Product.getApple());
		assertEquals("Incorrect total for basket: " + basket, FastMoney.of(1.20, Monetary.getCurrency("GBP")),
				discountCheckout.totalCost(basket));
		
		basket.add(Product.getApple());
		assertEquals("Incorrect total for basket: " + basket, FastMoney.of(1.20, Monetary.getCurrency("GBP")),
				discountCheckout.totalCost(basket));
		
		basket.add(Product.getApple());
		assertEquals("Incorrect total for basket: " + basket, FastMoney.of(1.80, Monetary.getCurrency("GBP")),
				discountCheckout.totalCost(basket));
	}
	
}
