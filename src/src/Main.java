import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        //Ask for un number to the user thanks to the scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number of population: ");
        int population = scanner.nextInt();
        //Génrate randoms numbers between 1 and 255 in range of the population and create a list of Solution
        ArrayList<Solution> solutions = new ArrayList<Solution>();
        Random random = new Random();
        for (int i = 0; i < population; i++) {
            int decimal = random.nextInt(255) + 1;
            Solution solution = new Solution(decimal,"");
            solution.setBinary(solution.decimalToBinary(decimal));
            solution.fitnessFunction(solution.getDecimal());
            solutions.add(solution);
        }
        //sort the list of Solution by ascending order of the equation
        solutions.sort((s1, s2) -> s1.getFitnescore() - s2.getFitnescore()); //lambda expression
        //print the list of Solution
        for (Solution solution : solutions) {
            System.out.println(solution.toString());
        }
        //Ask for a pourcentage of selection to the user thanks to the scanner (all input are between 0 and 100) and selection and mutation must be équal to 100
        System.out.println("Enter a pourcentage of selection: ");
        int selection = scanner.nextInt();
        while (selection < 0 || selection > 100) {
            System.out.println("Error: Enter a pourcentage of selection between 0 and 100:");
            System.out.println("Enter a pourcentage of selection: ");
            selection = scanner.nextInt();
        }
        int mutation = 100 - selection;
        //print it's good
        System.out.println("It's good\n" + "CrossOver: " + selection + " Mutation: " + mutation);
        int randomSelection = random.nextInt(100)+1;
        if (randomSelection <= selection) {
            System.out.println("CrossOver");
        } else {
            System.out.println("Mutation");
        }

        //test
        //System.out.println(solutions.get(0).toString());
        System.out.println("Test :");

        ArrayList<Solution> solutions1 = new ArrayList<Solution>();
        Solution solu1 =new Solution();
        solu1.cpt = 0;
        for(int i=0;i<2;i++){
            solutions1.add(solu1.Selection(solutions));
            solu1.cpt++;
        }

        for (Solution solution : solutions1) {
            System.out.println(solution.toString());
        }
    }
}
