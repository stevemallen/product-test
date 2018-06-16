package com.hyperdiamond.app;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;

/**
 * A product in an online shop. Has a price and description. Only two types of
 * product currently: apples and oranges
 * 
 * @author Steve
 *
 */
public class Product {

	// constants for now, in a real app would come from a database
	private final static MonetaryAmount APPLE_UNIT_PRICE = FastMoney.of(0.60, Monetary.getCurrency("GBP"));
	private final static String APPLE_ID = "Apple";

	private final static MonetaryAmount ORANGE_UNIT_PRICE = FastMoney.of(0.25, Monetary.getCurrency("GBP"));
	private final static String ORANGE_ID = "Orange";

	// There is only one global instance of apple and orange - we don't need
	// individual instances, since there is just a price and description for each
	
	private static Product apple = new Product(APPLE_ID, APPLE_UNIT_PRICE);
	private static Product orange = new Product(ORANGE_ID, ORANGE_UNIT_PRICE);

	private String id;
	private MonetaryAmount price;

	public Product(String id, MonetaryAmount price) {
		this.id = id;
		this.price = price;
	}

	// a product code (to make each product unique)
	String getId() {
		return id;
	}

	// the standard unit price of a product
	MonetaryAmount getPrice() {
		return price;
	}

	public static Product getApple() {
		return apple;
	}

	public static Product getOrange() {
		return orange;
	}

	@Override
	public String toString() {
		return getId();
	}
	
}
