package com.esdc.shape.warehouse;

import com.esdc.shape.entity.PyramidType;

import java.util.Objects;

public class PyramidProperties {
    private final double volume;
    private final double area;
    private final double height;
    private final boolean isBaseRegular;
    private final PyramidType type;

    public PyramidProperties(double volume, double area, double height, boolean isBaseRegular, PyramidType type) {
        this.volume = volume;
        this.area = area;
        this.height = height;
        this.isBaseRegular = isBaseRegular;
        this.type = type;
    }

    public double getVolume() {
        return volume;
    }

    public double getArea() {
        return area;
    }

    public double getHeight() {
        return height;
    }

    public boolean isBaseRegular() {
        return isBaseRegular;
    }

    public PyramidType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format(
                "PyramidProperties{volume=%.2f, area=%.2f, height=%.2f, isBaseRegular=%s, type=%s}",
                volume, area, height, isBaseRegular, type
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PyramidProperties)) return false;
        PyramidProperties that = (PyramidProperties) o;
        return Double.compare(that.volume, volume) == 0 &&
                Double.compare(that.area, area) == 0 &&
                Double.compare(that.height, height) == 0 &&
                isBaseRegular == that.isBaseRegular &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, area, height, isBaseRegular, type);
    }
}
