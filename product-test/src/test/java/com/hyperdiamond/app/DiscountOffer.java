package com.hyperdiamond.app;

import javax.money.MonetaryAmount;

/**
 * A rule which applies a discount to a shopping basket based on 
 * how many of a particular item is bought
 * 
 * @author Steve
 *
 */
public interface DiscountOffer {

	/**
	 * Apply the discount and return a new total for the basket.
	 * 
	 * @param basket
	 * @param netTotal
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	MonetaryAmount apply(Basket basket, MonetaryAmount amount);

}
