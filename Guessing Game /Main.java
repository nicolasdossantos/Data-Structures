package com.nicolasDosSantos;


import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    //Global variables Array List and Scanner
    static ArrayList<Guess> list = new ArrayList<Guess>();
    static Scanner kb = new Scanner(System.in);

    static boolean done = false;

    //Game menu
    public static void gameSelection() {

        while (!done) {

            //While loop will run until user decides to answer or quit
            System.out.println("Type 'sequence' to enter another 3 number sequence. ");
            System.out.println("Type 'answer' if you are ready to guess the rule. Be careful, you only have one shot!");
            System.out.println("Type 'previous' to see all previous sequences");
            System.out.println("Type 'quit' to exit");
            String userSelect = kb.nextLine();

            if (userSelect.toLowerCase().equals("answer")) {
                tryAnswer();
            } else if (userSelect.toLowerCase().equals("sequence")) {
                guess();
            } else if (userSelect.toLowerCase().equals("previous")) {
                printPrevious();

            } else if (userSelect.toLowerCase().equals("quit")) {
                System.exit(0);

            } else {
                System.out.println(userSelect + " is not a valid selection");

            }
        }
    }

    public static void tryAnswer() {

        String finalAnswer = "The rule was odd numbers multiple of 3 in crescent order";

        System.out.println("What is your answer?");
        String answer = kb.nextLine();

        //If answer is correct set done to true
        if (((answer.toLowerCase().contains("3") || answer.toLowerCase().contains("three")) &&
                answer.toLowerCase().contains("multiple") && answer.toLowerCase().contains("odd")
                && (answer.toLowerCase().contains("crescent") || answer.toLowerCase().contains("increasing")
                || answer.toLowerCase().contains("increase")))) {

            System.out.println("Congratulations! Your answer is correct!");
            done = true;
            //If answer is wrong break, ending the game
        } else {
            System.out.println("Sorry!Wrong answer. " + finalAnswer);
            System.exit(0);

        }
    }

    public static void guess() {
        System.out.println("Enter a sequence of three numbers separated by spaces:");
        String input = kb.nextLine();

        //"1 3 5"  --> ["1","3","5"]
        //Trims white space from beginning and end
        input = input.trim();
        String[] nums = input.split(" ");


        try {
            boolean meetCond = false;

            if (Integer.parseInt(nums[0]) % 3 == 0 && Integer.parseInt(nums[1]) % 3 == 0 && Integer.parseInt(nums[2]) % 3 == 0
                    && Integer.parseInt(nums[0]) % 2 != 0 && Integer.parseInt(nums[1]) % 2 != 0 && Integer.parseInt(nums[2]) % 2 != 0
                    && Integer.parseInt(nums[2]) > Integer.parseInt(nums[1]) && Integer.parseInt(nums[1]) > Integer.parseInt(nums[0])) {

                meetCond = true;

                System.out.println("This sequence satisfies the rule!");
                System.out.println("What do you want to do now?");


            } else {
                System.out.println("This sequence does not satisfy the rule");
                System.out.println("What do you want to do now?");
            }

            Guess newG = new Guess(nums[0], nums[1], nums[2], meetCond);

            list.add(newG);

            gameSelection();

        } catch (Exception e) {
            System.out.println("PLease make sure to type only numbers");
            System.out.println("What do you want to do now?");
            gameSelection();
        }

    }


    public static void printPrevious() {
        System.out.println("Here is the list of previous guesses: \n" +list);

    }


    public static void main(String[] args) {
        System.out.println("I've chosen a rule that some sequences of three numbers obey — and some do not.\n" +
                "Your job is to guess what the rule is.\n" +
                "I’ll start by telling you that the sequence 9, 27, 81  obeys the rule.");
        System.out.println("Now it’s your turn. Enter a number sequence below, and we’ll tell you\n" +
                "whether it satisfies the rule or not. You can test as many sequences as you want.");
        Guess ng = new Guess("9", "27", "81", true);
        list.add(ng);
        guess();


    }
}