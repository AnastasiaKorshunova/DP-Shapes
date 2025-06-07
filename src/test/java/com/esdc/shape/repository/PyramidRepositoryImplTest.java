package com.esdc.shape.repository;

import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.repository.impl.PyramidRepositoryImpl;
import com.esdc.shape.specification.Specification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PyramidRepositoryImplTest {
    private PyramidRepositoryImpl repo;
    private Pyramid p1;
    private Pyramid p2;
    private Pyramid p3;

    @BeforeEach
    void setUp() {
        repo = new PyramidRepositoryImpl();
        p1 = Mockito.mock(Pyramid.class);
        p2 = Mockito.mock(Pyramid.class);
        p3 = Mockito.mock(Pyramid.class);
    }

    @Test
    void add_storesObjectInRepository() {
        repo.add(p1);
        assertEquals(List.of(p1), repo.getAll(), "add() should make the pyramid retrievable via getAll()");
    }

    @Test
    void remove_deletesObjectFromRepository() {
        repo.add(p1);
        repo.add(p2);
        repo.remove(p1);
        assertEquals(List.of(p2), repo.getAll(), "remove() should eliminate the pyramid from storage");
    }

    @Test
    void getAll_returnsDefensiveCopy() {
        repo.add(p1);
        List<Pyramid> copy = repo.getAll();
        assertEquals(1, copy.size());

        copy.clear();
        assertEquals(1, repo.getAll().size(), "Mutating the list returned by getAll() must not change repository state");
    }

    @Test
    void query_returnsObjectsMatchingSpecification() {
        repo.add(p1);
        repo.add(p2);
        repo.add(p3);

        Specification<Pyramid> onlyP2 = candidate -> candidate == p2;

        List<Pyramid> result = repo.query(onlyP2);
        assertEquals(List.of(p2), result, "query() should return pyramids that satisfy the specification only");
    }

    @Test
    void sort_returnsSortedCopyUsingComparator() {
        repo.add(p1);
        repo.add(p2);
        repo.add(p3);

        Comparator<Pyramid> comparator = Comparator.comparingInt(System::identityHashCode);
        List<Pyramid> sorted = repo.sort(comparator);

        assertNotSame(repo.getAll(), sorted, "sort() must return a new list, not expose internal storage");

        List<Pyramid> expected = new ArrayList<>(List.of(p1, p2, p3));
        expected.sort(comparator);
        assertEquals(expected, sorted, "sort() must order elements using the provided comparator");
    }
}
