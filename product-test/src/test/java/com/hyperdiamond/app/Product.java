package com.hyperdiamond.app;

import javax.money.MonetaryAmount;

/**
 * A product in an online shop.  Has a price and description.
 * 
 * @author Steve
 *
 */
public interface Product {

	// a product code (to make each product unique)
	String getId();
	
	// the standard unit price of a product
	MonetaryAmount getPrice();
	
}
