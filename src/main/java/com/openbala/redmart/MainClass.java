package com.openbala.redmart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MainClass {

    public static void main(String[] args) {
        int[][] map = createMap();
        Skiing skiing = new Skiing(map);
        List<Integer> bestPath = skiing.findBestPath();
        System.out.println(bestPath);
    }

    private static int[][] createMap() {
        int[][] map = new int[0][];
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(MainClass.class.getClassLoader().getResourceAsStream("map.txt")));

            String line1 = bufferedReader.readLine();
            String[] mapBounds = line1.split(" ");
            int x = parseInt(mapBounds[0]);
            int y = parseInt(mapBounds[1]);

            map = new int[x][y];
            String line;
            int count = 0;
            while (x > count) {
                line = bufferedReader.readLine();
                String[] coOrds = line.split(" ");
                int[] plane = new int[y];
                for (int i = 0; i < y; i++) {
                    plane[i] = parseInt(coOrds[i]);
                }
                map[count++] = plane;
            }
            return map;

        } catch (IOException e) {
            System.err.println("Error in reading input file.");
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ignored) {
                }
            }
        }
        return map;
    }
}
