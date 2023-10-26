package edu.hw3.task6;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    private String name;
    private Integer cost;

    public Stock(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public int compareTo(@NotNull Stock o) {
        return cost.compareTo(o.cost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Stock stock = (Stock) obj;
        return Objects.equals(cost, stock.cost) && Objects.equals(name, stock.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
    }
}
