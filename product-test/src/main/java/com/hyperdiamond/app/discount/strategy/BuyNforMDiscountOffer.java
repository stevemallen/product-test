package com.hyperdiamond.app.discount.strategy;

import javax.money.MonetaryAmount;

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

	private Product product; // the product that the offer applies to
	private int groupSize; // N - the group size whose cost is reduced (e.g. 3)
	private int costSize; // M - the group size which determines actual cost (e.g. 2)
	
	public BuyNforMDiscountOffer(Product product) {
		this.product = product;
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
		// by the appropriate amount (costSize * unit cost) * Number of Groups
		long count = basket.productCount(product);
		if (count >= groupSize) {
			long numberOfGroups = count / groupSize;
			MonetaryAmount costPricePerGroup = product.getPrice().multiply(costSize);
			MonetaryAmount fullGroupPrice = product.getPrice().multiply(groupSize);
			MonetaryAmount reductionPerGroup = fullGroupPrice.subtract(costPricePerGroup);
			MonetaryAmount reduction = reductionPerGroup.multiply(numberOfGroups);
			return amount.subtract(reduction);
		} else {
			// only two products, no discount
			return amount;
		}
	}

}
