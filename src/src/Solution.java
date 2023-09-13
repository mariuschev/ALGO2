import java.util.ArrayList;
import java.util.Random;


public class Solution {
    private int decimal;
    private String binary;
    private int fitnescore;
    public static int cpt;
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
    public int getFitnescore() {
        return fitnescore;
    }
    //method to convert decimal to binary on 8 bits
    public String decimalToBinary(int decimal) {
        String binary = "";
        while (decimal > 0) {
            binary = (decimal % 2) + binary;
            decimal /= 2;
        }
        //if decimal is 0 then binary is 00000000
        if (binary.length() == 0) {
            binary = "00000000";
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
    public ArrayList<Solution> refresh(ArrayList<Solution> solutions, Solution solution) {
        //if solution decimal is not already in the list then add it or replace it
        boolean found = false;
        for (int i = 0; i < solutions.size(); i++) {
            if (solutions.get(i).getDecimal() == solution.getDecimal()) {
                found = true;
            }
        }
        if (!found) {
            solutions.add(solution);
            solutions.sort((o1, o2) -> Integer.compare(Math.abs(o1.getFitnescore()), Math.abs(o2.getFitnescore())));
            solutions.remove(solutions.size() - 1);
        }

        return solutions;
    }
    //method to calculate the equation (x+3)²-25=0 and assign the result to the variable equationn
    public void fitnessFunction(int decimal) {
        fitnescore = (decimal + 3) * (decimal + 3) - 25;
    }
    public Solution Selection(ArrayList<Solution> select){
        Solution sol1;
        sol1= select.get(cpt);
        return sol1;
    }
    //method to print the solution
    @Override
    public String toString() {
        return "Decimal: " + this.decimal + " Binary: " + this.binary + " Fitness level: " + this.fitnescore;
    }
    //method to make the crossover between two binary solutions choosing a crossover point randomly
    public Solution crossover(Solution solution1, Solution solution2) {
        Solution solution = new Solution();
        int crossoverPoint = (int) (Math.random() * 8);
        String binary1 = solution1.getBinary();
        String binary2 = solution2.getBinary();
        String binary = binary2.substring(0, crossoverPoint) + binary1.substring(crossoverPoint);
        solution.setBinary(binary);
        solution.setDecimal(solution.binaryToDecimal(binary));
        solution.fitnessFunction(solution.getDecimal());
        return solution;
    }
    public Solution Mutation(Solution solutions2){
        Solution solution = new Solution();
        //duplique the solution
        solution.setBinary(solutions2.getBinary());
        solution.setDecimal(solutions2.getDecimal());
        solution.fitnessFunction(solution.getDecimal());
        Random random = new Random();
        //choose a random iteration between 0 inclued and 8 exclued
        int randomIteration = random.nextInt(8);
        char[] charArray = solution.getBinary().toCharArray();
        if(charArray[randomIteration]=='1'){
            charArray[randomIteration]=(char)('0');
        }else{
            charArray[randomIteration]=(char)('1');
        }
        String modifiedString=new String(charArray);
        solution.setBinary(modifiedString);
        solution.setDecimal(solution.binaryToDecimal(modifiedString));
        solution.fitnessFunction(solution.getDecimal());
        return solution;

    }
}
