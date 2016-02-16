package com.deswaef.wowscrappie.util;

@FunctionalInterface
public interface ExceptionAssertiable {
    <T extends Throwable> void apply(T throwable);
}
