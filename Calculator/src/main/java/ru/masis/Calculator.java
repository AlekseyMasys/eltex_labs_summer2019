package ru.masis;

import java.util.Scanner;

public class Calculator {
    private Integer numberFirst;
    private Integer numberSecond;
    private String operation;
    private Integer resultNumber;

    public static void main(String[] args) {
        Calculator javaCalculator = new Calculator();
        javaCalculator.app();
    }

    private void app() {
        System.out.println("enter the first number:");
        numberFirst = enterNumber();
        System.out.println("enter the second number:");
        numberSecond = enterNumber();
        System.out.println("enter the operation:");
        operation = enterOperation();
        if (operation == null) {
            System.out.println("Unknown operation");
        }
        else {
            resultNumber = calculateResult(numberFirst, numberSecond, operation);
            if (resultNumber == null) {
                System.out.println("division by zero=(");
            }
            else {
                System.out.println(numberFirst + " " + operation + " " + numberSecond + " = " + resultNumber);
            }
        }

    }

    private Integer enterNumber() {
        Scanner scanner = new Scanner(System.in);
        Integer number = scanner.nextInt();
        return number;
    }

    private String enterOperation() {
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        if (operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/")) {
            return operation;
        }
        else {
            return null;
        }
    }

    private Integer calculateResult(Integer numberFirst, Integer numberSecond, String operation) {
        Integer result = null;
        switch(operation) {
            case "+":
                result = numberFirst + numberSecond;
                return result;
            case "-":
                result = (numberFirst - numberSecond);
                return result;
            case "*":
                result = numberFirst * numberSecond;
                return result;
            case "/":
                if (numberSecond != 0) {
                    result = numberFirst/numberSecond;
                    return result;
                }
        }
        return result;
    }
}
