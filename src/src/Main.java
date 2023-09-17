import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception, IOException, InterruptedException{

        boolean continu = false;

        while(!continu) {

            //print menu
            System.out.println("##################################################################");
            System.out.println("#                        GENETIC ALGORITHM                       #");
            System.out.println("##################################################################");
            System.out.println();
            System.out.println("1 - Solve the equation (x+3)² - 25 = 0");
            System.out.println("2 - Solve the equation and visualise the process");
            System.out.println("3 - Create your own set of population");
            System.out.println("4 - Get out of the program");
            System.out.println();
            System.out.println("##################################################################");
            System.out.println("#                         OPTION SELECTOR                        #");
            System.out.println("##################################################################");
            System.out.println();

            Scanner sc1 = new Scanner(System.in);
            int menu=0;
            while (true) { //We do a loop to make sure that we have the right entry
                try {
                    System.out.println("Select the option : ");
                    menu = sc1.nextInt();

                    if (menu >= 1 && menu <= 4) {
                        break;
                    } else {
                        System.out.println("Error: You must enter a number between 1 and 4");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: You must enter a valid number between 1 and 4");
                    sc1.nextLine();
                }
            }



            switch (menu) {
                case 1:
                    System.out.println("##################################################################");
                    System.out.println("#            I - SOLVE THE EQUATION (x+3)² - 25 = 0              #");
                    System.out.println("##################################################################");

                    System.out.println("You'll now solve the equation with the genetic algorithm.");
                    System.out.println();

                    //The user enter a number of population with secure entry
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter a number of population: ");
                    int population = scanner.nextInt();
                    while (population < 2) {
                        System.out.println("Out of border.");
                        System.out.println("Enter a number of population (greater than 1): ");
                        population = scanner.nextInt();
                    }

                    System.out.println();
                    System.out.println("The randomly generated population is as follows : ");
                    System.out.println();

                    //Generate randoms numbers between 1 and 255 in range of the population and create a list of Solution
                    ArrayList<Solution> solutions = new ArrayList<>();
                    Random random = new Random();
                    for (int i = 0; i < population; i++) {
                        int decimal = random.nextInt(255) + 1;
                        for (Solution solution : solutions) {
                            while (solution.getDecimal() == decimal) {
                                decimal = random.nextInt(255) + 1;
                            }
                        }
                        Solution solution = new Solution(decimal, "");
                        solution.setBinary(solution.decimalToBinary(decimal));
                        solution.fitnessFunction(solution.getDecimal());
                        solutions.add(solution);
                    }

                    //sort the list with absolute value of fitness score in ascending order
                    solutions.sort((o1, o2) -> Integer.compare(Math.abs(o1.getFitnescore()), Math.abs(o2.getFitnescore())));
                    //print the list of Solution
                    for (Solution solution : solutions) {
                        System.out.println(solution.toString());
                    }

                    //Setting the number of tries
                    System.out.println();
                    System.out.println("Select a number of tries (between 2 and 1000) : ");

                    int tries = scanner.nextInt();
                    while (tries < 2 || tries > 1000) {
                        System.out.println("Error: The number of tries must be between 2 and 1000) :");
                        System.out.println("Enter the number of tries : ");
                        tries = scanner.nextInt();
                    }

                    System.out.println();

                    //Setting the percentage of crossover and mutation
                    System.out.println("Now enter a pourcentage of crossover: ");
                    int crossover = scanner.nextInt();
                    while (crossover < 0 || crossover > 100) {
                        System.out.println("Error: The pourcentage of crossover must be between 0 and 100:");
                        System.out.println("Enter a pourcentage of crossover: ");
                        crossover = scanner.nextInt();
                    }
                    //print them
                    System.out.println();
                    System.out.println("- CrossOver: " + crossover + "%  " + "\n" + "- Mutation:  " + (100 - crossover) + "%");
                    TimeUnit.SECONDS.sleep(2);

                    int count = 0;

                    //Genetic algorithm with the use of both Crossover and Mutation methods
                    while (solutions.get(0).getFitnescore() != 0 && count < tries) {
                        int randomSelection = random.nextInt(100) + 1;

                        //Use of the crossover method
                        if (randomSelection <= crossover) {

                            //Selection method
                            ArrayList<Solution> bestsolution = new ArrayList<>();
                            Solution solu1 = new Solution();
                            Solution.cpt = 0;
                            for (int i = 0; i < 2; i++) {
                                bestsolution.add(solu1.Selection(solutions));
                                Solution.cpt++;
                            }

                            //Crossover
                            Solution solution = new Solution();
                            Solution cross = solution.crossover(bestsolution.get(0), bestsolution.get(1));

                            //add the new solution to the list of Solution using the refresh method
                            solutions = solution.refresh(solutions, cross);
                            count++;

                            //Use of the Mutation method
                        } else {

                            //Mutation
                            Solution mutation2 = new Solution();
                            Solution final_solution = mutation2.Mutation(solutions.get(0));

                            //add the new solution to the list of Solution using the refresh method
                            solutions = mutation2.refresh(solutions, final_solution);
                            count++;
                        }

                    }

                    System.out.println();
                    System.out.println("After " + count + " tries, the las population is : ");
                    System.out.println();

                    for (Solution solution : solutions) {
                        System.out.println(solution.toString());
                    }

                    System.out.println();

                    if (count == tries && solutions.get(0).getFitnescore() != 0) {

                        //The solution is not found
                        System.out.println("So the best solution found is : " + solutions.get(0).getDecimal() + ", with a fitness score of: " + solutions.get(0).getFitnescore() + ", after " + count + " tries");
                    } else {

                        //The solution is found
                        System.out.println("So the solution found is : " + solutions.get(0).getDecimal() + ", after " + count + " tries");
                    }

                    break;
                case 2:
                    System.out.println("##################################################################");
                    System.out.println("#        II - SOLVE THE EQUATION AND VISUALISE THE PROCESS       #");
                    System.out.println("##################################################################");

                    System.out.println("You'll now solve the equation with the genetic algorithm and visualise the process.");
                    System.out.println();

                    //The user enter a number of population with secure entry
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Enter a number of population: ");
                    int population2 = scanner2.nextInt();
                    while (population2 < 2) {
                        System.out.println("Out of border.");
                        System.out.println("Enter a number of population (greater than 1): ");
                        population2 = scanner2.nextInt();
                    }

                    System.out.println();
                    System.out.println("The randomly generated population is as follows : ");
                    System.out.println();

                    //Generate randoms numbers between 1 and 255 in range of the population and create a list of Solution
                    ArrayList<Solution> solutions2 = new ArrayList<>();
                    Random random2 = new Random();
                    for (int i = 0; i < population2; i++) {
                        int decimal = random2.nextInt(255) + 1;
                        for (Solution solution : solutions2) {
                            while (solution.getDecimal() == decimal) {
                                decimal = random2.nextInt(255) + 1;
                            }
                        }
                        Solution solution = new Solution(decimal, "");
                        solution.setBinary(solution.decimalToBinary(decimal));
                        solution.fitnessFunction(solution.getDecimal());
                        solutions2.add(solution);
                    }

                    //sort the list with absolute value of fitness score in ascending order
                    solutions2.sort((o1, o2) -> Integer.compare(Math.abs(o1.getFitnescore()), Math.abs(o2.getFitnescore())));
                    //print the list of Solution
                    for (Solution solution : solutions2) {
                        System.out.println(solution.toString());
                    }

                    //Setting the number of tries
                    System.out.println();
                    System.out.println("Select a number of tries (between 2 and 1000) : ");

                        int tries2 = scanner2.nextInt();
                        while (tries2 < 2 || tries2 > 1000) {
                            System.out.println("Error: The number of tries must be between 2 and 1000) :");
                            System.out.println("Enter the number of tries : ");
                            tries2 = scanner2.nextInt();
                    }

                    System.out.println();

                    //Setting the percentage of crossover and mutation
                    System.out.println("Now enter a pourcentage of crossover: ");
                    int crossover2 = scanner2.nextInt();
                    while (crossover2 < 0 || crossover2 > 100) {
                        System.out.println("Error: The pourcentage of crossover must be between 0 and 100:");
                        System.out.println("Enter a pourcentage of crossover: ");
                        crossover2 = scanner2.nextInt();
                    }
                    //print them
                    System.out.println();
                    System.out.println("- CrossOver: " + crossover2 + "%  " + "\n" + "- Mutation:  " + (100 - crossover2) + "%");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println();

                    int count2 = 0;

                    //Genetic algorithm with the use of both Crossover and Mutation methods
                    while (solutions2.get(0).getFitnescore() != 0 && count2 < tries2) {
                        int randomSelection = random2.nextInt(100) + 1;

                        //Use of the crossover method
                        if (randomSelection <= crossover2) {

                            //Selection method
                            ArrayList<Solution> bestsolution = new ArrayList<>();
                            Solution solu2 = new Solution();
                            Solution.cpt = 0;
                            for (int i = 0; i < 2; i++) {
                                bestsolution.add(solu2.Selection(solutions2));
                                Solution.cpt++;
                            }

                            //Crossover
                            Solution solution = new Solution();
                            Solution cross = solution.crossover(bestsolution.get(0), bestsolution.get(1));

                            //add the new solution to the list of Solution using the refresh method
                            solutions2 = solution.refresh(solutions2, cross);

                            //Notice it
                            System.out.println();
                            System.out.println(" - Crossover : ");

                            //print the list of Solution
                            for (Solution solution2 : solutions2) {
                                System.out.println(solution2.toString());
                            }
                            count2++;

                            //Use of the Mutation method
                        } else {

                            //Mutation
                            Solution mutation2 = new Solution();
                            Solution final_solution = mutation2.Mutation(solutions2.get(0));

                            //add the new solution to the list of Solution using the refresh method
                            solutions2 = mutation2.refresh(solutions2, final_solution);

                            //Notice it
                            System.out.println();
                            System.out.println(" - Mutation : ");

                            //print the list of Solution
                            for (Solution solution2 : solutions2) {
                                System.out.println(solution2.toString());
                            }
                            count2++;
                        }

                    }

                    System.out.println();

                    if (count2 == tries2 && solutions2.get(0).getFitnescore() != 0) {

                        //The solution is not found
                        System.out.println("So the best solution found is : " + solutions2.get(0).getDecimal() + ", with a fitness score of: " + solutions2.get(0).getFitnescore() + ", after " + count2 + " tries");
                    } else {

                        //The solution is found
                        System.out.println("So the solution found is : " + solutions2.get(0).getDecimal() + ", after " + count2 + " tries");
                    }


                    break;
                case 3:
                    System.out.println("##################################################################");
                    System.out.println("#             III - CREATE YOUR OWN SET OF POPULATION            #");
                    System.out.println("##################################################################");
                    System.out.println("You'll now create your own set of population.");
                    //ask the user to enter a number of population
                    System.out.println("Enter a number of population: ");
                    Scanner scanner3 = new Scanner(System.in);
                    int population3 = scanner3.nextInt();
                    Random random3 = new Random();
                    while (population3 < 2) {
                        System.out.println("Out of border.");
                        System.out.println("Enter a number of population (greater than 1): ");
                        population3 = scanner3.nextInt();
                    }
                    //ask the user to enter each decimal number
                    System.out.println("Enter each decimal number: ");
                    ArrayList<Solution> solutions3 = new ArrayList<>();
                    for (int i = 0; i < population3; i++) {
                        System.out.println("Enter the decimal number " + (i + 1) + " : ");
                        int decimal = scanner3.nextInt();
                        for (Solution solution : solutions3) {
                            while (solution.getDecimal() == decimal) {
                                System.out.println("Error: This decimal number is already in the list.");
                                System.out.println("Enter the decimal number " + (i + 1) + " : ");
                                decimal = scanner3.nextInt();
                            }
                        }
                        Solution solution = new Solution(decimal, "");
                        solution.setBinary(solution.decimalToBinary(decimal));
                        solution.fitnessFunction(solution.getDecimal());
                        solutions3.add(solution);
                    }
                    solutions3.sort((o1, o2) -> Integer.compare(Math.abs(o1.getFitnescore()), Math.abs(o2.getFitnescore())));
                    //print the list of Solution

                    System.out.println("The list of Solution is as follows : ");
                    for (Solution solution : solutions3) {
                        System.out.println(solution.toString());
                    }
                    System.out.println("Select a number of tries (between 2 and 1000) : ");

                    int tries3 = scanner3.nextInt();
                    while (tries3 < 2 || tries3 > 1000) {
                        System.out.println("Error: The number of tries must be between 2 and 1000) :");
                        System.out.println("Enter the number of tries : ");
                        tries2 = scanner3.nextInt();
                    }

                    System.out.println();
                    Scanner scanner4 = new Scanner(System.in);
                    //Setting the percentage of crossover and mutation
                    System.out.println("Now enter a pourcentage of crossover: ");
                    int crossover3 = scanner4.nextInt();
                    while (crossover3 < 0 || crossover3 > 100) {
                        System.out.println("Error: The pourcentage of crossover must be between 0 and 100:");
                        System.out.println("Enter a pourcentage of crossover: ");
                        crossover2 = scanner4.nextInt();
                    }
                    //print them
                    System.out.println();
                    System.out.println("- CrossOver: " + crossover3 + "%  " + "\n" + "- Mutation:  " + (100 - crossover3) + "%");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println();

                    int count3 = 0;

                    //Genetic algorithm with the use of both Crossover and Mutation methods
                    while (solutions3.get(0).getFitnescore() != 0 && count3 < tries3) {
                        int randomSelection = random3.nextInt(100) + 1;

                        //Use of the crossover method
                        if (randomSelection <= crossover3) {

                            //Selection method
                            ArrayList<Solution> bestsolution = new ArrayList<>();
                            Solution solu2 = new Solution();
                            Solution.cpt = 0;
                            for (int i = 0; i < 2; i++) {
                                bestsolution.add(solu2.Selection(solutions3));
                                Solution.cpt++;
                            }

                            //Crossover
                            Solution solution = new Solution();
                            Solution cross = solution.crossover(bestsolution.get(0), bestsolution.get(1));

                            //add the new solution to the list of Solution using the refresh method
                            solutions2 = solution.refresh(solutions3, cross);

                            //Notice it
                            System.out.println();
                            System.out.println(" - Crossover : ");

                            //print the list of Solution
                            for (Solution solution3 : solutions3) {
                                System.out.println(solution3.toString());
                            }
                            count3++;

                            //Use of the Mutation method
                        } else {

                            //Mutation
                            Solution mutation3 = new Solution();
                            Solution final_solution = mutation3.Mutation(solutions3.get(0));

                            //add the new solution to the list of Solution using the refresh method
                            solutions3 = mutation3.refresh(solutions3, final_solution);

                            //Notice it
                            System.out.println();
                            System.out.println(" - Mutation : ");

                            //print the list of Solution
                            for (Solution solution3 : solutions3) {
                                System.out.println(solution3.toString());
                            }
                            count3++;
                        }

                    }

                    System.out.println();

                    if (count3 == tries3 && solutions3.get(0).getFitnescore() != 0) {

                        //The solution is not found
                        System.out.println("So the best solution found is : " + solutions3.get(0).getDecimal() + ", with a fitness score of: " + solutions3.get(0).getFitnescore() + ", after " + count3 + " tries");
                    } else {

                        //The solution is found
                        System.out.println("So the solution found is : " + solutions3.get(0).getDecimal() + ", after " + count3 + " tries");
                    }



                    break;

                case 4:
                    System.out.println("##################################################################");
                    System.out.println("#                  IV - GET OUT OF THE PROGRAM                   #");
                    System.out.println("##################################################################");
                    System.out.println("Bye !");
                    continu = true;
                    break;

            }


        }
    }
}
