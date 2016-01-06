package com.openbala.redmart;

public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    public int[] newCoOrds(int x, int y) {

        switch (this) {
            case NORTH:
                return new int[]{x, y + 1};
            case SOUTH:
                return new int[]{x, y - 1};
            case EAST:
                return new int[]{x + 1, y};
            case WEST:
                return new int[]{x - 1, y};
            default:
                return new int[]{x, y};
        }

    }
}
