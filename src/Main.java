import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input:");
        String input = scan.nextLine();
        String output = calc(input);
        System.out.println("Output:");
        System.out.println(output);
    }

    public static String calc (String input) throws IOException {
        String[] stringArray = input.split(" ");
        int a = 0;
        int b = 0;
        boolean checkA = false;
        boolean checkB = false;
        String[] number = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] romans = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };
        String [] romansTens = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C" };
        int resultInt = 0;
        int resultPart;
        String resultString = "";
        String output;

        if (stringArray.length != 3) {
            throw new IOException();
        }

        //конвертирование массива строк с арабскими числами в числа
        for (int i = 0; i < number.length; i++) {
            if (stringArray[0].equals(number[i])) {
                a = Integer.parseInt(number[i]);;
            }
            if (stringArray[2].equals(number[i])) {
                b = Integer.parseInt(number[i]);;
            }
        }

       if ((a == 0 & b != 0) || (a != 0 & b == 0 )) {
           throw new IOException();
       }

       //конвертирование массива строк с римскими числами в числа
        for (int i = 0; i < romans.length; i++) {
            if(romans[i].equals(stringArray[0])){
                a = i + 1;
                checkA = true;
            }
            if (romans[i].equals(stringArray[2])){
                b = i + 1;
                checkB = true;
            }
        }

        if ((a < 1 || a > 10) || (b < 1 || b > 10) ||
                (a % 1 != 0) || (b % 1 != 0)) {
            throw new IOException();
        }

        //выполнение операций над числами
        String operator = stringArray[1];
        switch (operator) {
            case "+": resultInt = a + b;
            break;
            case "-": resultInt = a - b;
            break;
            case "*": resultInt = a * b;
            break;
            case "/": resultInt = a / b;
            break;
            default: throw new IOException();
        }

        //конвертирование результата операции с числами в строку с римским числом
        if (checkA  & checkB )  {
            if (resultInt > 0 && resultInt < 10) {
                for (int i = 0; i < romans.length; i++) {
                    if (resultInt == i) {
                        resultString = romans[i - 1];
                    }
                }
            } else if (resultInt >= 10 && resultInt < 100) {
                 resultPart = resultInt % 10;
                 resultInt = resultInt / 10;
                for (int i = 0; i < romans.length; i++) {
                    if (resultInt == i) {
                        resultString = romansTens[i - 1];
                    }
                }
                for (int i = 0; i < romans.length; i++) {
                    if (resultPart > 0 && resultPart == i) {
                        resultString = resultString + romans[i - 1];
                    }
                }
            } else if (resultInt == 100) {
                resultString = romansTens[9];
            } else {
                throw new IOException();
            }
            output = resultString;
        } else {
            output = Integer.toString(resultInt);
        }

        return output;
    }
}


