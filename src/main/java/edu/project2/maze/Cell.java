package edu.project2.maze;

import java.util.Objects;

public record Cell(int row, int col, Type type) {
    public enum Type {
        HORIZONTAL_WALL,
        VERTICAL_WALL,
        CROSS_WALL,
        PASSAGE,
        BROKEN_WALL,
        PATH
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return row == cell.row && col == cell.col && type == cell.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, type);
    }
}
