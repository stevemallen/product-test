package com.hyperdiamond.app;

import javax.money.MonetaryAmount;

/**
 * Represents a singleton service used to calculate the total cost of 
 * a shopping basket
 * 
 * @author Steve
 *
 */
public interface CheckoutService {

	/**
	 * Calculate the total cost of a shopping basket
	 * @param basket
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	MonetaryAmount totalCost(Basket basket);
	
	/**
	 * Add an offer strategy to this checkout service
	 * 
	 * @param offer
	 */
	void addDiscountOffer(DiscountOffer offer);

}
