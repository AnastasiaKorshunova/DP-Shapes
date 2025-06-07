package com.esdc.shape.repository.impl;

import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.repository.PyramidRepository;
import com.esdc.shape.specification.Specification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PyramidRepositoryImpl implements PyramidRepository {
    private static final Logger logger = LoggerFactory.getLogger(PyramidRepositoryImpl.class);
    private final List<Pyramid> pyramids = new ArrayList<>();

    @Override
    public void add(Pyramid pyramid) {
        pyramids.add(pyramid);
        logger.info("Pyramid added: {}", pyramid);
    }

    @Override
    public void remove(Pyramid pyramid) {
        pyramids.remove(pyramid);
        logger.info("Pyramid removed: {}", pyramid);
    }

    @Override
    public List<Pyramid> query(Specification<Pyramid> spec) { // <-- обновлено
        List<Pyramid> result = pyramids.stream()
                .filter(spec::isSatisfiedBy)
                .collect(Collectors.toList());
        logger.info("Query executed. Found {} pyramids.", result.size());
        return result;
    }

    @Override
    public List<Pyramid> sort(Comparator<Pyramid> comparator) {
        List<Pyramid> result = pyramids.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        logger.info("Pyramids sorted using comparator: {}", comparator.getClass().getSimpleName());
        return result;
    }

    @Override
    public List<Pyramid> getAll() {
        logger.info("Retrieving all pyramids. Total: {}", pyramids.size());
        return new ArrayList<>(pyramids);
    }
}
