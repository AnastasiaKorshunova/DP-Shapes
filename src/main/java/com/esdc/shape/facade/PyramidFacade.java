package com.esdc.shape.facade;

import com.esdc.shape.comparator.VolumeComparator;
import com.esdc.shape.entity.Point;
import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.exception.FileParsingException;
import com.esdc.shape.factory.PyramidFactory;
import com.esdc.shape.factory.impl.PyramidFactoryImpl;
import com.esdc.shape.parser.PointParser;
import com.esdc.shape.parser.impl.PointParserImpl;
import com.esdc.shape.reader.FileReaderUtility;
import com.esdc.shape.repository.PyramidRepository;
import com.esdc.shape.repository.impl.PyramidRepositoryImpl;
import com.esdc.shape.specification.Specification;
import com.esdc.shape.state.ValidPyramidState;
import com.esdc.shape.validation.PyramidValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class PyramidFacade {

    private static final Logger logger = LoggerFactory.getLogger(PyramidFacade.class);

    private final PyramidRepository pyramidRepository = new PyramidRepositoryImpl();
    private final PyramidFactory pyramidFactory = new PyramidFactoryImpl();
    private final PointParser pointParser = new PointParserImpl();

    public void createPyramid(Point apex, List<Point> base) {
        Pyramid pyramid = pyramidFactory.createPyramid(apex, base);
        PyramidValidator.validateAndSetState(pyramid);

        if (pyramid.getState() instanceof ValidPyramidState) {
            pyramidRepository.add(pyramid);
            logger.info("Pyramid ID {} added to repository", pyramid.getPyramidId());
        } else {
            logger.warn("Pyramid ID {} is invalid and was not added to repository", pyramid.getPyramidId());
        }
    }

    public List<Point> readPointsFromFile(String filePath) throws IOException {
        List<String> lines = FileReaderUtility.readLines(filePath);
        return pointParser.parsePoints(lines);
    }

    public void loadPyramidsFromFile(String filePath) {
        try {
            List<Point> points = readPointsFromFile(filePath);

            if (points.size() < 4 || points.size() % 4 != 0) {
                logger.error("Invalid number of points: {}, should be a multiple of 4", points.size());
                throw new FileParsingException("Invalid number of points: " + points.size(), null);
            }

            for (int i = 0; i < points.size(); i += 4) {
                Point apex = points.get(i);
                List<Point> base = points.subList(i + 1, i + 4);
                createPyramid(apex, base);
            }

            logger.info("Successfully loaded pyramids from file: {}", filePath);

        } catch (FileParsingException e) {
            logger.error("File parsing error: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while loading pyramids: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to load pyramids from file", e);
        }
    }

    public List<Pyramid> getPyramids() {
        return pyramidRepository.getAll();
    }

    public List<Pyramid> sortPyramids(Comparator<Pyramid> comparator) {
        return pyramidRepository.sort(comparator);
    }

    public List<Pyramid> queryPyramidsBySpecification(Specification<Pyramid> spec) {
        return pyramidRepository.query(spec);
    }
}
