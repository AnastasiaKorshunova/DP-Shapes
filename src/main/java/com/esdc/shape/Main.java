package com.esdc.shape;

import com.esdc.shape.comparator.*;
import com.esdc.shape.entity.Pyramid;
import com.esdc.shape.facade.PyramidFacade;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/resources/points.txt";

        PyramidFacade facade = new PyramidFacade();
        facade.loadPyramidsFromFile(filePath);

        System.out.println("Всего загружено: " + facade.getPyramids().size());

        print("По объёму",  facade.sortPyramids(new VolumeComparator()));
        print("По площади", facade.sortPyramids(new AreaComparator()));
        print("По высоте",  facade.sortPyramids(new HeightComparator()));
        print("По ID",      facade.sortPyramids(new IdComparator()));
        print("По типу",    facade.sortPyramids(new TypeComparator()));
    }

    private static void print(String title, List<Pyramid> list) {
        System.out.println("\n=== " + title + " ===");
        list.forEach(System.out::println);
    }
}
