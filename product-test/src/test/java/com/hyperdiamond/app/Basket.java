package com.hyperdiamond.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a shopping basket - a set of items being bought
 * 
 * @author Steve
 *
 */
public class Basket {

	private List<BasketItem> basketItems;
	
	public Basket() {
		basketItems = new ArrayList<BasketItem>();
	}
	
	/**
	 * Add a number of products to the basket
	 * 
	 * @param apple
	 * @param quantity
	 */
	public void add(Product product, int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("quantity must be greater than zero");
		}
		if (product == null) {
			throw new IllegalArgumentException("Product cannot be null");
		}
		BasketItem basketItem = new BasketItem(product, quantity);
		basketItems.add(basketItem);
	}

	/**
	 * Add a single product to the basket (convenience method)
	 * 
	 * @param product
	 */
	public void add(Product product) {
		this.add(product, 1);
	}

	public List<BasketItem> getAllItems() {
		return basketItems;
	}

}
