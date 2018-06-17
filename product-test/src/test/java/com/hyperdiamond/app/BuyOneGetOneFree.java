package com.hyperdiamond.app;

/**
 * A Buy One Get One Free offer on a particular Product (e.g. Apples)
 * 
 * @author Steve
 *
 */
public class BuyOneGetOneFree extends BuyNforMDiscountOffer {

	public BuyOneGetOneFree(Product product) {
		super(product);
		setGroupSize(2); // buy 2
		setCostSize(1);  // get 1 free
	}

}
