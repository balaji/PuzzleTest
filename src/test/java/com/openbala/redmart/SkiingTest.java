package com.openbala.redmart;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class SkiingTest {
    Skiing skiing;

    @Before
    public void setUp() {
        int[][] map = new int[4][4];
        map[0] = new int[]{4, 8, 7, 3};
        map[1] = new int[]{2, 5, 9, 3};
        map[2] = new int[]{6, 3, 2, 5};
        map[3] = new int[]{4, 4, 1, 6};
        skiing = new Skiing(map);
    }

    @Test
    public void shouldFindTheBestPath() {
        List<Integer> bestPath = skiing.findBestPath();

        assertThat(bestPath, is(equalTo(Arrays.asList(9, 5, 3, 2, 1))));
    }

    @Test
    public void shouldAddAdjacentCoOrdsOnlyIfItSatisfies() {
        List<List<Integer[]>> possiblePathCoOrds = new ArrayList<>();
        skiing.addNewCoOrdsToPossiblePaths(Collections.emptyList(), new Integer[]{0, 0}, Direction.EAST, possiblePathCoOrds);
        assertThat(possiblePathCoOrds.size(), is(1));
        assertThat(possiblePathCoOrds.get(0), hasItem(new Integer[]{1, 0}));

        possiblePathCoOrds = new ArrayList<>();
        skiing.addNewCoOrdsToPossiblePaths(Collections.emptyList(), new Integer[]{0, 0}, Direction.SOUTH, possiblePathCoOrds);
        assertThat(possiblePathCoOrds.size(), is(0));
    }

    @Test
    public void shouldFindTheBestPathWithLongLengthAndHighDrop() {
        List<List<Integer>> skiPaths = new ArrayList<>();
        List<Integer> path1 = Arrays.asList(9, 0);
        List<Integer> path2 = Arrays.asList(9, 5, 3, 2, 1);
        List<Integer> path3 = Arrays.asList(8, 5, 3, 2, 1);

        skiPaths.add(path1);
        skiPaths.add(path2);
        skiPaths.add(path3);
        List<Integer> bestPath = skiing.bestPath(skiPaths);

        assertThat(bestPath, is(path2));
    }
}