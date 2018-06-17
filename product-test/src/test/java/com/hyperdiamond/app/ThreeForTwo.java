package com.hyperdiamond.app;

public class ThreeForTwo extends BuyNforMDiscountOffer {

	public ThreeForTwo(Product product) {
		super(product);
		setGroupSize(3); // buy 3
		setCostSize(2);  // for 2
	}

}
