package com.company.src;
import java.lang.*;
import java.util.Objects;
import java.util.Scanner;
import java.lang.String;

/**
 * Main class
 */
public class Main {
    /**
     * Main method
     * @param args main arguments
     */
    public static void main(String[] args) {
        boolean isCalculated;
        String marker = "start";
        Scanner input = new Scanner(System.in);
        while (!Objects.equals(marker, "exit")){
            System.out.print("Enter the expression: ");
            String str = input.nextLine();

            Calculator expression = new Calculator(str);
            isCalculated = expression.calculate();

            if (!isCalculated) {
                System.out.print("Input is incorrect.");
            } else {
                System.out.print(str + " = ");
                System.out.println(expression.getAnswer().toString());
            }
            System.out.println("Type 'exit' to end program.");
            marker = input.nextLine();
        }
    }
}