package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestsForTask1 {
    @Test
    @DisplayName("init test ((2 + 4) * (-1)) ^ 2 + 1 = 37")
    void test1() {
        var two = new Task1.Expr.Constant(2);
        var four = new Task1.Expr.Constant(4);
        var negOne = new Task1.Expr.Negate(new Task1.Expr.Constant(1));
        var sumTwoFour = new Task1.Expr.Addition(two, four);
        var mult = new Task1.Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Task1.Expr.Exponent(mult, 2);
        var res = new Task1.Expr.Addition(exp, new Task1.Expr.Constant(1));
        assertThat(res.evaluate()).isEqualTo(37.0);
    }

    @Test
    @DisplayName("double test ((1.5 + 2) * (-3)) ^ 2 + 1 = 111")
    void test4() {
        var oneAndHalf = new Task1.Expr.Constant(1.5);
        var two = new Task1.Expr.Constant(2);
        var negOne = new Task1.Expr.Negate(new Task1.Expr.Constant(3));
        var sumTwoFour = new Task1.Expr.Addition(two, oneAndHalf);
        var mult = new Task1.Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Task1.Expr.Exponent(mult, 2);
        var res = new Task1.Expr.Addition(exp, new Task1.Expr.Constant(0.75));
        assertThat(res.evaluate()).isEqualTo(111.0);
    }
}
