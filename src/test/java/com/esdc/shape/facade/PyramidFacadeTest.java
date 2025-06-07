package com.esdc.shape.facade;

import com.esdc.shape.comparator.VolumeComparator;
import com.esdc.shape.entity.Point;
import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.exception.FileParsingException;
import com.esdc.shape.specification.Specification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PyramidFacadeTest {
    private PyramidFacade facade;

    @BeforeEach
    void setUp() {
        facade = new PyramidFacade();
    }

    @Test
    void createPyramid_validData_pyramidAdded() {
        Point apex = new Point(0, 0, 1);
        List<Point> base = List.of(
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0)
        );
        facade.createPyramid(apex, base);
        List<Pyramid> pyramids = facade.getPyramids();
        assertEquals(1, pyramids.size());
        Pyramid p = pyramids.get(0);
        assertEquals(apex, p.getApex());
        assertIterableEquals(base, p.getBase());
    }

    @Test
    void loadPyramidsFromFile_invalidPoints_throwsException() throws Exception {
        Path tmp = Files.createTempFile("pyramid", ".txt");
        Files.writeString(tmp, "0,0,0\n0,0,1\n0,1,0\n");
        assertThrows(FileParsingException.class, () -> facade.loadPyramidsFromFile(tmp.toString()));
    }

    @Test
    void sortAndQuery_functionsWork() {
        Point apex1 = new Point(0, 0, 1);
        List<Point> base1 = List.of(
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0)
        );
        Point apex2 = new Point(0, 0, 2);
        List<Point> base2 = List.of(
                new Point(0, 0, 0),
                new Point(2, 0, 0),
                new Point(0, 2, 0)
        );
        facade.createPyramid(apex2, base2);
        facade.createPyramid(apex1, base1);

        Comparator<Pyramid> comparator = new VolumeComparator();
        List<Pyramid> sorted = facade.sortPyramids(comparator);
        assertEquals(2, sorted.size());
        assertTrue(comparator.compare(sorted.get(0), sorted.get(1)) <= 0);

        double threshold = sorted.get(0).getVolume();
        Specification<Pyramid> spec = p -> p.getVolume() > threshold;
        List<Pyramid> queried = facade.queryPyramidsBySpecification(spec);
        assertEquals(1, queried.size());
        assertTrue(queried.get(0).getVolume() > threshold);
    }
}
