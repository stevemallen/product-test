package com.hyperdiamond.app;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;

public class Orange implements Product {

	// a constant for now, in a real app would come from a database
	private final static MonetaryAmount ORANGE_UNIT_PRICE = FastMoney.of(0.25, Monetary.getCurrency("GBP"));
	private final static String ID = "Orange";

	public MonetaryAmount getPrice() {
		return ORANGE_UNIT_PRICE;
	}

	public String getId() {
		return ID;
	}

}
