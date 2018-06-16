package com.hyperdiamond.app;

import java.util.HashSet;
import java.util.Set;

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

	private Set<DiscountOffer> discountOffers = new HashSet<>(); 
	
	public void addDiscountOffer(DiscountOffer offer) {
		discountOffers.add(offer);
	}
	
	public MonetaryAmount totalCost(Basket basket) {
		
		if (basket == null) {
			throw new IllegalArgumentException("Basket cannot be null");
		}

		MonetaryAmount grossTotal = FastMoney.of(0, Monetary.getCurrency("GBP"));
		
		// simply add up all the items in the basket to make the gross figure
		//
		for (BasketItem basketItem : basket.getAllItems()) {
			grossTotal = grossTotal.add(basketItem.getPrice().multiply(basketItem.getQuantity()));
		}
		
		// now apply any discounts configured
		return applyDiscounts(basket, grossTotal);
	}

	private MonetaryAmount applyDiscounts(Basket basket, MonetaryAmount grossTotal) {
		
		// apply each discount in turn
		// note that this assumes that discounts can be applied by reducing the cost on
		
		MonetaryAmount netTotal = grossTotal;
		for (DiscountOffer offer: discountOffers) {
			netTotal = offer.apply(basket, netTotal);
		}
		return netTotal;
	}

}
