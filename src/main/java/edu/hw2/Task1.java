package edu.hw2;

public class Task1 {
    public sealed interface Expr {
        double evaluate();

        record Constant(double num) implements Expr {
            @Override
            public double evaluate() {
                return num;
            }
        }

        record Negate(Expr expression) implements Expr {
            @Override
            public double evaluate() {
                return -expression.evaluate();
            }
        }

        record Exponent(Expr expression, int pow) implements Expr {
            @Override
            public double evaluate() {
                return Math.pow(expression.evaluate(), pow);
            }
        }

        record Addition(Expr expression1, Expr expression2) implements Expr {
            @Override
            public double evaluate() {
                return expression1().evaluate() + expression2().evaluate();
            }
        }

        record Multiplication(Expr expression1, Expr expression2) implements Expr {
            @Override
            public double evaluate() {
                return expression1().evaluate() * expression2().evaluate();
            }
        }
    }
}

