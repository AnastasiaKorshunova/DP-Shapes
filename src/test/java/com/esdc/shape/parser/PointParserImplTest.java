package com.esdc.shape.parser;

import com.esdc.shape.entity.Point;
import com.esdc.shape.parser.impl.PointParserImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PointParserImplTest {
    private final PointParserImpl parser = new PointParserImpl();


    @Test
    void parsePoints_returnsPointsForValidLines() {
        List<String> lines = List.of(
                "1.0,2.0,3.0",
                " -4.5 , 0 , 8 ",
                "0,0,0"
        );

        List<Point> points = parser.parsePoints(lines);

        assertEquals(3, points.size(), "Expected three parsed points");
        assertIterableEquals(List.of(
                new Point(1.0, 2.0, 3.0),
                new Point(-4.5, 0.0, 8.0),
                new Point(0.0, 0.0, 0.0)
        ), points, "Parsed points differ from expected");
    }


    @Test
    void parsePoints_skipsMalformedLines() {
        List<String> lines = List.of(
                "",
                "abc,def,ghi",
                "1,2",
                "1,2,3,4",
                "5.5,6.6,7.7"
        );

        List<Point> points = parser.parsePoints(lines);

        assertEquals(1, points.size(), "Only the single well‑formed line should be parsed");
        assertEquals(new Point(5.5, 6.6, 7.7), points.get(0));
    }

    @Test
    void parsePoints_handlesMixedValidAndInvalidLines() {
        List<String> lines = List.of(
                "3,4,5",
                "bad line",
                "  ",
                "-1,-2,-3"
        );

        List<Point> points = parser.parsePoints(lines);

        assertEquals(2, points.size(), "Should parse two valid points and ignore the rest");
        assertIterableEquals(List.of(
                new Point(3, 4, 5),
                new Point(-1, -2, -3)
        ), points);
    }
}
