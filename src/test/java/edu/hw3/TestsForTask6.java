package edu.hw3;

import edu.hw3.task6.Market;
import edu.hw3.task6.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask6 {
    @Test
    @DisplayName("add and remove stocks test")
    void test1() {
        Market market = new Market();
        Stock sber = new Stock("Sberbank", 270);
        Stock gazp = new Stock("Gazprom", 170);
        Assertions.assertEquals(0, market.stocks.size());

        market.add(sber);
        market.add(gazp);
        Assertions.assertEquals(2, market.stocks.size());
        Assertions.assertTrue(market.stocks.contains(sber));
        Assertions.assertTrue(market.stocks.contains(gazp));

        market.remove(sber);
        Assertions.assertEquals(1, market.stocks.size());
        Assertions.assertTrue(market.stocks.contains(gazp));

        market.remove(gazp);
        Assertions.assertEquals(0, market.stocks.size());
    }

    @Test
    @DisplayName("most valuable stock test")
    void test2() {
        Market market = new Market();
        Stock sber = new Stock("Sberbank", 270);
        Stock gazp = new Stock("Gazprom", 170);
        Stock hhru = new Stock("HeadHunter", 3700);
        market.add(sber);
        market.add(gazp);
        market.add(hhru);
        Assertions.assertEquals(market.mostValuableStock(), hhru);
    }
}
