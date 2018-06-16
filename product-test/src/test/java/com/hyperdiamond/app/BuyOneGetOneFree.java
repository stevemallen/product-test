package com.hyperdiamond.app;

import javax.money.MonetaryAmount;

/**
 * A Buy One Get One Free offer on a particular Product (e.g. Apples)
 * 
 * @author Steve
 *
 */
public class BuyOneGetOneFree implements DiscountOffer {

	private Product product; // the product that the BOGOF applies to

	/**
	 * Create a BOGOF offer using the prototype product object
	 * @param apple
	 */
	public BuyOneGetOneFree(Product product) {
		this.product = product;
	}

	@Override
	public MonetaryAmount apply(Basket basket, MonetaryAmount amount) {
		// count how many of the product we have
		// if there is more than one, then reduce the cost
		// by the unit value of the product
		long count = basket.productCount(product);
		if (count > 1) {
			long freeCount = count / 2; // divide by 2 and round down
			MonetaryAmount unitPrice;
			unitPrice = product.getPrice();
			MonetaryAmount reduction = unitPrice.multiply(freeCount);
			return amount.subtract(reduction);
		} else {
			// only one product, no discount
			return amount;
		}
			
	}

}
