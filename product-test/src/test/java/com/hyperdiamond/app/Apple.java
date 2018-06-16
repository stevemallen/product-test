package com.hyperdiamond.app;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;

/**
 * Represents an Apple sold in an online shop.  In a real app, the prices
 * would be held in a database, and we probably wouldn't have concrete subclasses
 * for each product.  However, we may have subclasses for "Fruit" or other types of products
 * for which behaviour is the same. 
 * 
 * @author Steve
 *
 */
public class Apple implements Product {

	// a constant for now, in a real app would come from a database
	private final static MonetaryAmount APPLE_UNIT_PRICE = FastMoney.of(0.60, Monetary.getCurrency("GBP")); 
	private final static String ID = "Apple";
	
	public MonetaryAmount getPrice() {
		return APPLE_UNIT_PRICE;
	}

	public String getId() {
		return ID;
	}

}
