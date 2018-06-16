package com.hyperdiamond.app;

/**
 * Simple checkout application, implementing a shopping cart.
 * There are two kinds of products: Apples and Oranges, with different prices
 * The Checkout service takes a list of items and calculates the total cost
 * Apples are 60p each and Oranges are 25p each
 * 
 * Example: [ Apple, Apple, Orange, Apple ] => £2.05
 *
 * We use JSR-354 to represent monetary values (rather than floats or doubles which have
 * problems with precision).  Better to re-use a library, than roll our own.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("product-test App - please use as a jar");
    }
}
