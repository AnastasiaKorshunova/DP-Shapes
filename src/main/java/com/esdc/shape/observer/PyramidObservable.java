package com.esdc.shape.observer;

public interface PyramidObservable {
    void addObserver(PyramidObserver observer);
    void removeObserver(PyramidObserver observer);
    void notifyObservers();
}
