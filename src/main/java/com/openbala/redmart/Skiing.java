package com.openbala.redmart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Skiing {

    private int[][] map;

    public Skiing(int[][] map) {
        this.map = map;
    }

    /**
     * This method uses brute force to find all valid paths in a given map and
     * returns the best possible path among them.
     *
     * @return best path to ski
     */
    public List<Integer> findBestPath() {
        List<List<Integer>> skiPaths = new ArrayList<>();

        List<List<Integer[]>> possiblePathCoOrds = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                possiblePathCoOrds.add(Arrays.asList(new Integer[][]{new Integer[]{i, j}}));
                findPath(possiblePathCoOrds, skiPaths);
            }
        }
        return bestPath(skiPaths);
    }

    List<Integer> bestPath(List<List<Integer>> skiPaths) {
        int largestLength = -1;
        List<Integer> bestSkiPaths = Collections.emptyList();
        for (List<Integer> skiPath : skiPaths) {
            //find the path with the highest length.
            if (skiPath.size() > largestLength) {
                bestSkiPaths = skiPath;
                largestLength = skiPath.size();
            } else if (skiPath.size() == largestLength) {
                // If there is a new path with the same length, then check if it has a bigger drop.
                int currentDrop = findDrop(bestSkiPaths);
                int newDrop = findDrop(skiPath);

                if (newDrop > currentDrop) {
                    bestSkiPaths = skiPath;
                }
            }
        }
        return bestSkiPaths;
    }

    private int findDrop(List<Integer> bestSkiPaths) {
        return bestSkiPaths.get(0) - bestSkiPaths.get(bestSkiPaths.size() - 1);
    }

    private void findPath(List<List<Integer[]>> possiblePathCoOrds, List<List<Integer>> skiPaths) {

        int previousPathSize = possiblePathCoOrds.size();
        if (previousPathSize == 0) {
            return;
        }

        List<Integer[]> lastPath = possiblePathCoOrds.remove(previousPathSize - 1);
        Integer[] lastCoOrds = lastPath.get(lastPath.size() - 1);

        for (Direction direction : Direction.values()) {
            addNewCoOrdsToPossiblePaths(lastPath, lastCoOrds, direction, possiblePathCoOrds);
        }

        if (previousPathSize - 1 == possiblePathCoOrds.size()) {
            //No new paths were added, hence this path ends.
            skiPaths.add(lastPath.stream().map(coOrds -> map[coOrds[0]][coOrds[1]]).collect(Collectors.toList()));
        }
        findPath(possiblePathCoOrds, skiPaths);
    }

    void addNewCoOrdsToPossiblePaths(List<Integer[]> lastPath,
                                     Integer[] lastCoOrds,
                                     Direction direction,
                                     List<List<Integer[]>> possiblePathCoOrds) {

        List<Integer[]> modifiedPath = new ArrayList<>(lastPath);
        int[] newCoOrds = direction.newCoOrds(lastCoOrds[0], lastCoOrds[1]);
        int x = newCoOrds[0];
        int y = newCoOrds[1];

        if (x >= 0 && y >= 0
                && x < map.length && y < map[x].length
                && map[lastCoOrds[0]][lastCoOrds[1]] > map[x][y]) {
            modifiedPath.add(new Integer[]{x, y});
            possiblePathCoOrds.add(modifiedPath);
        }
    }
}
