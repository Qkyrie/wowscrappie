package com.deswaef.weakauras.util;

@FunctionalInterface
public interface ExceptionAssertiable {
    <T extends Throwable> void apply(T throwable);
}
