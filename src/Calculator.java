package com.company.src;

/**
 * A class that calculates an expression
 */
public class Calculator {

    /**
     * Expression which contains numbers, brackets, operators, spaces and other characters
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
     * Method for checking if the expression is correct
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

    /**
     * Method that determines the priority for operators
     * @param character Symbol - operator or brackets
     * @return Symbol priority
     */
    public int priority(char character) {
        switch (character) {
            case '*':
            case '/':
                return 3;
            case '+':
            case '-':
                return 2;
            case '(':
                return 1;
            case ')':
                return -1;
            default:
                return 0;
        }
    }

    /**
     * Method that converts expression in postfix form
     * @return True if it's possible, false if not
     */
    private boolean postfixConvertion() {

        if (!isCorrect() || Expression.isEmpty())
            return false;

        else {

            return true;
        }
    }
}

