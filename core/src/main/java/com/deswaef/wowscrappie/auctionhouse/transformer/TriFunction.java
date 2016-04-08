package com.deswaef.wowscrappie.auctionhouse.transformer;

@FunctionalInterface
public interface TriFunction<T, U, R, A> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    T apply(U u, R r, A a);

}
