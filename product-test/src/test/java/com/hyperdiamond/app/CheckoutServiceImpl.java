package com.hyperdiamond.app;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;

/**
 * A service which calculates the total cost of basket of items 
 * 
 * @author Steve
 *
 */
public class CheckoutServiceImpl implements CheckoutService {

	public MonetaryAmount totalCost(Basket basket) {
		if (basket == null) {
			throw new IllegalArgumentException("Basket cannot be null");
		}
		MonetaryAmount total = FastMoney.of(0, Monetary.getCurrency("GBP"));
		// simply add up all the items in the basket
		for (BasketItem basketItem : basket.getAllItems()) {
			total = total.add(basketItem.getPrice().multiply(basketItem.getQuantity()));
		}
		return total;
	}

}
