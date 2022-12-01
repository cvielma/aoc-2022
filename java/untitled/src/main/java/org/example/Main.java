package org.example;

import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {

public static void main(String[]args){

    Scanner scanner = new Scanner(System.in);
    int maxCals = Integer.MIN_VALUE;
    TreeSet<Integer> elvesCalories = new TreeSet<>(Comparator.reverseOrder());
        int maxElve = 1;
        int currentElve = 1;

    while(scanner.hasNext())
        {

            int totalCalories = 0;
            while (scanner.hasNext()) {
                String calories = scanner.nextLine();

                if (calories == null || "".equals(calories)) {
                    break;
                }

                totalCalories += Integer.parseInt(calories);
            }

            System.out.println("Current Elve: " + currentElve + ", Calories: " + totalCalories);

            if (totalCalories > maxCals) {
                maxCals = totalCalories;
                maxElve = currentElve;
            }

            elvesCalories.add(totalCalories);

            currentElve++;

            System.out.println("Curent Max elve: " + maxElve + ", max calories: " + maxCals);

            Integer reduce = elvesCalories.stream().limit(3).reduce(0, Integer::sum);
            System.out.println(elvesCalories);
            System.out.println("Total from top 3: " + reduce);
        }


    }
}