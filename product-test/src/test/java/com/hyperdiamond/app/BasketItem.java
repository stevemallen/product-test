package com.hyperdiamond.app;

import javax.money.MonetaryAmount;

/**
 * An item in a basket consists of a product and a quantity
 * A simple POJO.
 * 
 * @author Steve
 *
 */
public class BasketItem {

	private Product product = null;
	private int quantity = 1;

	public BasketItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public BasketItem(Product product) {
		this.product = product;
	}

	public MonetaryAmount getPrice() {
		return product.getPrice();
	}

	public int getQuantity() {
		return quantity;
	}

	

}
