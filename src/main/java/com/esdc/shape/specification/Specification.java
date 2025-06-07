
package com.esdc.shape.specification;

public interface Specification<T> {
    boolean isSatisfiedBy(T item);
}