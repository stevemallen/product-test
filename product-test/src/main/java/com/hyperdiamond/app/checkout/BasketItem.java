package com.hyperdiamond.app.checkout;

import javax.money.MonetaryAmount;

/**
 * An item in a basket consists of a product and a quantity
 * A simple POJO.
 * 
 * @author Steve
 *
 */
public class BasketItem {

	private Product product = null; // reference to the (singleton) product type
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
	
	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		if (quantity > 1) {
			return product + ": " + quantity;
		} else {
			return product.toString();
		}
	}
	

}
