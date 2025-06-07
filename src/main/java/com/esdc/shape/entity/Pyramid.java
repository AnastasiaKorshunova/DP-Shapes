package com.esdc.shape.entity;

import com.esdc.shape.service.impl.CalculationServiceImpl;
import com.esdc.shape.state.PyramidState;
import com.esdc.shape.state.UnverifiedPyramidState;
import com.esdc.shape.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pyramid extends AbstractShape {
    private Point apex;
    private List<Point> base;
    private PyramidType type = PyramidType.UNKNOWN;
    private PyramidState state = new UnverifiedPyramidState();

    public Pyramid(Point apex, List<Point> base) {
        super(IdGenerator.getNextId());
        this.apex = apex;
        this.base = new ArrayList<>(base);
    }

    public long getPyramidId() {
        return getId();
    }

    public Point getApex() {
        return apex;
    }

    public void setApex(Point apex) {
        this.apex = apex;
        notifyObservers();
    }

    public List<Point> getBase() {
        return new ArrayList<>(base);
    }

    public void setBase(List<Point> base) {
        this.base = new ArrayList<>(base);
        notifyObservers();
    }

    public PyramidType getType() {
        return type;
    }

    public void setType(PyramidType type) {
        this.type = type;
    }

    public PyramidState getState() {
        return state;
    }

    public void setState(PyramidState state) {
        this.state = state;
    }

    @Override
    public double getArea() {
        return CalculationServiceImpl.getInstance().calculateArea(base);
    }

    @Override
    public double getVolume() {
        return CalculationServiceImpl.getInstance().calculateVolume(getArea(), apex);
    }

    @Override
    public double getHeight() {
        return CalculationServiceImpl.getInstance().calculateHeight(apex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pyramid pyramid)) return false;
        return Objects.equals(apex, pyramid.apex) &&
                Objects.equals(base, pyramid.base) &&
                type == pyramid.type;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (apex != null ? apex.hashCode() : 0);
        result = 31 * result + (base != null ? base.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format(
                "Pyramid{pyramidId=%d, apex=%s, base=%s, type=%s, state=%s}",
                getId(),
                apex,
                base,
                type,
                state.getClass().getSimpleName()
        );
    }
}
