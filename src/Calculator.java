package com.company.src;
import java.util.Objects;
import java.util.Stack;

/**
 * A class that calculates an expression.
 */
public class Calculator {

    /**
     * Expression which contains numbers, brackets, operators, spaces and other characters.
     */
    private String Expression;

    /**
     * Constructor of the Calculator class
     *
     * @param str Expression
     */
    Calculator(String str) {
        this.Expression = str;
    }

    /**
     * Method for removing spaces from expression
     */
    private void rmvSpaces() {
        StringBuilder newstr = new StringBuilder();
        int pos = 0;
        while (pos < Expression.length()) {
            if (Expression.charAt(pos) != ' ')
                newstr.append(Expression.charAt(pos));
            pos++;
        }
        Expression = newstr.toString();
    }

    /**
     * The method for checking if the expression is correct
     *
     * @return True if the expression is correct, false if not
     */
    private boolean isCorrect() {
        if (Expression.isEmpty()) return false;
        else {
            rmvSpaces();
            String ops = new String("+-*/");
            int brackets = 0;
            int pos = 0;
            while (pos < Expression.length()) {

                if (brackets >= 0) {

                    switch (Expression.charAt(pos)) {

                        case '+':
                        case '-':
                        case '*':
                        case '/': {
                            if (pos == 0 || pos == Expression.length() - 1) return false;
                            else if (ops.contains(String.valueOf(Expression.charAt(pos + 1))) || Expression.charAt(pos + 1) == ')')
                                return false;
                            break;
                        }

                        case '(': {
                            brackets++;
                            if (ops.contains(String.valueOf(Expression.charAt(pos + 1))) || Expression.charAt(pos + 1) == ')')
                                return false;
                            else if (pos == Expression.length() - 1) return false;
                            break;
                        }

                        case ')': {
                            brackets--;
                            if (pos == 0) return false;
                            else if (ops.contains(String.valueOf(Expression.charAt(pos + 1))) || Expression.charAt(pos - 1) == '(')
                                return false;
                            break;
                        }

                        default:
                            if (Expression.charAt(pos) >= '0' && Expression.charAt(pos) <= '9') {
                                if (pos != 0)
                                    if (Expression.charAt(pos - 1) == ')')
                                        return false;
                                if (pos != Expression.length() - 1)
                                    if (Expression.charAt(pos + 1) == '(')
                                        return false;
                            } else return false;
                    }
                } else return false;
                pos++;
            }
            return brackets == 0;
        }
    }
}