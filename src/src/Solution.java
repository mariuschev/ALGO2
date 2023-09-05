public class Solution {
    int decimal;
    String binary;
    private int equation = 0;
    //constructor with no arguments
    public Solution() {
        this.decimal = 0;
        this.binary = "";
    }
    //constructor with arguments
    public Solution(int decimal, String binary) {
        this.decimal = decimal;
        this.binary = binary;
    }
    //getters and setters
    public int getDecimal() {
        return decimal;
    }
    public void setDecimal(int decimal) {
        this.decimal = decimal;
    }
    public String getBinary() {
        return binary;
    }
    public void setBinary(String binary) {
        this.binary = binary;
    }
    //method to convert decimal to binary on 8 bits
    public String decimalToBinary(int decimal) {
        String binary = "";
        while (decimal > 0) {
            binary = (decimal % 2) + binary;
            decimal /= 2;
        }
        while (binary.length() < 8) {
            binary = "0" + binary;
        }
        return binary;
    }
    //method to convert binary to decimal
    public int binaryToDecimal(String binary) {
        int decimal = 0;
        int power = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
                decimal += Math.pow(2, power);
            }
            power++;
        }
        return decimal;
    }
    //method to calculate the equation (x+3)²-25=0 and assign the result to the variable equation
    public void calculateEquation(int decimal) {
        equation = (decimal + 3) * (decimal + 3) - 25;
    }
    //getters of equation
    public int getEquation() {
        return equation;
    }
    //method to print the solution
    public void printSolution() {
        System.out.println("Decimal: " + decimal + " Binary: " + binary + " Equation: " + equation);
    }

}
