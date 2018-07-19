package com.hyperdiamond.app.discount.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.FastMoney;

import com.hyperdiamond.app.checkout.Basket;
import com.hyperdiamond.app.checkout.Product;

/**
 * A discount strategy which implements the algorithm for buying N of a product
 * for M of a product, where M < N
 * 
 * @author Steve
 *
 */
public abstract class BuyNforMDiscountOffer implements DiscountOffer {

	private List<Product> products = new ArrayList<Product>(); // the products that the offer applies to
	private int groupSize; // N - the group size whose cost is reduced (e.g. 3)
	private int costSize; // M - the group size which determines actual cost (e.g. 2)
	
	public BuyNforMDiscountOffer(List<Product> product) {
		this.products = product;
	}
	
	public BuyNforMDiscountOffer(Product product) {
		this.products.add(product);
	}

	protected void setCostSize(int costSize) {
		this.costSize = costSize;
	}

	protected void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}
	
	@Override
	public MonetaryAmount apply(Basket basket, MonetaryAmount amount) {
		// count how many of the product we have
		// if there is at least one group (the discount applies), then reduce the total cost
		// by the appropriate amount (costSize * unit cost) * Number of Groups#
		
		//MonetaryAmount totalReduction = FastMoney.of(0, Monetary.getCurrency("GBP"));
		double totalr = 0;
		for (Product p: products) {
			
			long numberOfGroups = basket.productCount(p) / groupSize;
			if (basket.productCount(p) >= groupSize) {
				MonetaryAmount costPricePerGroup = p.getPrice().multiply(costSize);
				MonetaryAmount fullGroupPrice = p.getPrice().multiply(groupSize);
				MonetaryAmount reductionPerGroup = fullGroupPrice.subtract(costPricePerGroup);
				MonetaryAmount reduction = reductionPerGroup.multiply(numberOfGroups);
//				totalReduction.add(reduction);
				totalr += reduction.getNumber().doubleValue();
			}
		}
		MonetaryAmount totalReduction = FastMoney.of(totalr, Monetary.getCurrency("GBP"));
		return amount.subtract(totalReduction);
	}

}
