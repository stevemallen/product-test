package com.hyperdiamond.app;

import javax.money.MonetaryAmount;

public class ThreeForTwo implements DiscountOffer {

	// XXX consider creating a base class ProductDiscountOffer
	// Effectively same algorithm as TwoForOne (consider merging)

	private Product product; // the product that the offer applies to

	public ThreeForTwo(Product product) {
		this.product = product;
	}

	@Override
	public MonetaryAmount apply(Basket basket, MonetaryAmount amount) {
		// count how many of the product we have
		// if there is more than two, then reduce the cost
		// by the appropriate amount
		long count = basket.productCount(product);
		if (count > 2) {
			long numberOfSets = count / 3; // number of groups of 3
			MonetaryAmount unitPrice = product.getPrice();
			MonetaryAmount reduction = unitPrice.multiply(numberOfSets);
			return amount.subtract(reduction);
		} else {
			// only two products, no discount
			return amount;
		}
	}

}
