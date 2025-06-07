package com.esdc.shape.entity;

import com.esdc.shape.observer.PyramidObservable;
import com.esdc.shape.observer.PyramidObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractShape implements PyramidObservable {
    private final long id;
    protected final List<PyramidObserver> observers = new ArrayList<>();

    public AbstractShape(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public void addObserver(PyramidObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(PyramidObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (PyramidObserver observer : observers) {
            observer.update(this, null);
        }
    }

    public abstract double getArea();
    public abstract double getVolume();
    public abstract double getHeight();
}

