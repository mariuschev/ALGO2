import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static int population;

    public static void main(String[] args) {
        //Ask for un number to the user thanks to the scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number of population: ");
        int population = scanner.nextInt();
        //Génrate randoms numbers between 1 and 255 in range of the population and create a list of Solution
        ArrayList<Solution> solutions = new ArrayList<Solution>();
        Random random = new Random();
        for (int i = 0; i < population; i++) {
            int decimal = random.nextInt(255) + 1;
            for (Solution solution : solutions) {
                while (solution.getDecimal() == decimal) {
                    decimal = random.nextInt(255) + 1;
                }
            }
            Solution solution = new Solution(decimal,"");
            solution.setBinary(solution.decimalToBinary(decimal));
            solution.fitnessFunction(solution.getDecimal());
            solutions.add(solution);
        }
        //trier la liste avec la valeur absolue du fitness score par ordre croissant
        solutions.sort((o1, o2) -> Integer.compare(Math.abs(o1.getFitnescore()), Math.abs(o2.getFitnescore())));
        //print the list of Solution
        for (Solution solution : solutions) {
            System.out.println(solution.toString());
        }
        //Ask for a pourcentage of selection to the user thanks to the scanner (all input are between 0 and 100) and selection and mutation must be équal to 100
        System.out.println("Enter a pourcentage of selection: ");
        int crossover = scanner.nextInt();
        while (crossover < 0 || crossover > 100) {
            System.out.println("Error: Enter a pourcentage of selection between 0 and 100:");
            System.out.println("Enter a pourcentage of selection: ");
            crossover = scanner.nextInt();
        }
        int mutation = 100 - crossover;
        //print it's good
        System.out.println("It's good\n" + "CrossOver: " + crossover + " Mutation: " + mutation);
        int count = 0;
        //while il n'existe aucune solution avec un fitness score de 0 ou au bout de 100 essais alors continuer le programme
        while (solutions.get(0).getFitnescore() != 0 && count < 10) {
            int randomSelection = random.nextInt(100)+1;
            if (randomSelection <= crossover) {
                ArrayList<Solution> bestsolution = new ArrayList<Solution>();
                Solution solu1 =new Solution();
                solu1.cpt = 0;
                for(int i=0;i<2;i++){
                    bestsolution.add(solu1.Selection(solutions));
                    solu1.cpt++;
                }
                //do the crossover method with bestsolution arraylist
                Solution solution = new Solution();
                Solution cross = solution.crossover(bestsolution.get(0), bestsolution.get(1));
                //add the new solution to the list of Solution using the refresh method
                solutions = solution.refresh(solutions,cross);
                //print the list of Solution
                for (Solution solution1 : solutions) {
                    System.out.println(solution1.toString());
                }
                count++;


            } else {
                //create a new solution with the same value of the first solution of the list of Solution
                Solution mutation2 = new Solution();
                Solution final_solution=mutation2.Mutation(solutions.get(0));
                solutions = mutation2.refresh(solutions,final_solution);
                //print the list of Solution
                for (Solution solution1 : solutions) {
                    System.out.println(solution1.toString());
                }
                //print finish
                System.out.println("Finish");
                count++;
            }

        }
        if (count == 10 && solutions.get(0).getFitnescore() != 0) {
            System.out.println("The best solution found is: " + solutions.get(0).toString() + " with a fitness score of: " + solutions.get(0).getFitnescore() + " and a decimal value of: " + solutions.get(0).getDecimal() + " after " + count + " tries");
        } else {
            System.out.println("Solution found is : " + solutions.get(0).getDecimal() + " after " + count + " tries");
        }
    }
}
