package com.company.src;

import java.util.Objects;
import java.util.Stack;
import java.util.Vector;

/**
 * A class that calculates an expression
 */
public class Calculator {

    /**
     * Expression which contains numbers, brackets, operators, spaces and other characters
     */
    private String Expression;

    /**
     * Variable which contains the answer for expression
     */
    private Double Answer;

    /**
     * Constructor of the Calculator class
     *
     * @param str Expression
     */
    Calculator(String str) {
        this.Expression = str;
        this.Answer = 0.0;
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
                            else
                                if ( Expression.charAt(pos - 1) == '(')
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
            StringBuilder newExpression = new StringBuilder();
            Vector<Character> characters = new Vector<Character>();

            int operationType = 0;

            for (int pos = 0; pos < Expression.length(); pos++) {
                operationType = priority(Expression.charAt(pos));

                switch(operationType){
                    case 0: newExpression.append(Expression.charAt(pos)); break;
                    case 1: characters.add(0, Expression.charAt(pos)); break;
                    case 2: case 3: {
                        newExpression.append(' ');
                        while (!characters.isEmpty()) {
                            if (priority(characters.elementAt(0)) >= operationType)
                                newExpression.append(characters.remove(0));
                            else break;
                        }
                        characters.add(0, Expression.charAt(pos));
                    } break;
                    case -1:{
                        newExpression.append(' ');
                        while (priority(characters.elementAt(0)) != 1)
                            newExpression.append(characters.remove(0));
                        characters.remove(0);
                    } break;
                }
            }

            while (!characters.isEmpty()) newExpression.append(characters.remove(0));
            Expression = newExpression.toString();

            return true;
        }
    }

    /**
     * Method that calculates the value of expression in postfix form and put this value in Answer
     * @return True if it's calculated, false if not
     */
    public boolean calculate() {
        boolean isConverted = postfixConvertion();
        boolean isAnyCalculation = false;
        if (!isConverted) return false;
        else {

            StringBuilder result = new StringBuilder();
            Vector<Double> numbers = new Vector<Double>();

            for (int pos = 0;pos < Expression.length(); pos++) {

                if (Expression.charAt(pos) == ' ') continue;

                if (priority(Expression.charAt(pos)) == 0){

                    while (Expression.charAt(pos) != ' ' && priority(Expression.charAt(pos)) == 0) {
                        result.append(Expression.charAt(pos++));
                        if (pos == Expression.length()) break;
                    }

                    numbers.add(0, Double.parseDouble(result.toString()));
                    result.setLength(0);
                }
                if (pos == Expression.length()) break;
                if (priority(Expression.charAt(pos)) > 1) {
                    isAnyCalculation = true;

                    double num1 = numbers.remove(0);
                    double num2 = numbers.remove(0);

                    switch(Expression.charAt(pos)){
                        case '+': numbers.add(0,num2 + num1); break;
                        case '-': numbers.add(0,num2 - num1); break;
                        case '*': numbers.add(0,num2 * num1); break;
                        case '/': numbers.add(0,num2 / num1); break;
                    }
                }
            }
            if (isAnyCalculation) {
                Answer = numbers.remove(0);
                return true;
            }
            else return false;
        }
    }

    /**
     * Method that returns answer
     */
    public Double getAnswer() {
        return Answer;
    }
}

