package com.esdc.shape.entity;

public abstract class AbstractPoint {
    public abstract double getX();
    public abstract double getY();
    public abstract double getZ();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();
}
