package com.esdc.shape.repository;

import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.specification.Specification;

import java.util.Comparator;
import java.util.List;

public interface PyramidRepository {
    void add(Pyramid pyramid);
    void remove(Pyramid pyramid);
    List<Pyramid> query(Specification<Pyramid> spec);
    List<Pyramid> sort(Comparator<Pyramid> comparator);
    List<Pyramid> getAll();
}
