package com.esdc.shape.specification.impl;

import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.entity.PyramidType;
import com.esdc.shape.specification.Specification;

public class TypeSpecification implements Specification<Pyramid> {
    private final PyramidType expectedType;

    public TypeSpecification(PyramidType expectedType) {
        this.expectedType = expectedType;
    }

    @Override
    public boolean isSatisfiedBy(Pyramid pyramid) {
        return pyramid.getType() == expectedType;
    }
}
