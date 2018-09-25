package com.nicolasDosSantos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    //Method checks if elements in ArrayList are unique and returns true or false
    public static <E> boolean unique(List<E> list) {

        //compares element i and element j and if elements are equal returns false
        for (int i = 0; i < list.size(); i++) {

            for (int j = i + 1; j < list.size(); j++) {

                if (list.get(i).equals(list.get(j)))
                    return false;
            }
        }
        return true;
    }//End of unique()


    //Method returns a new ArrayList with the elements of "oldList" which are multiples of the int "multiple"
    public static List<Integer> allMultiples(List<Integer> oldList, int multiple) {

        ArrayList<Integer> newList = new ArrayList<Integer>();

        for (int i = 0; i < oldList.size(); i++) {

            if (oldList.get(i) % multiple == 0) {
                newList.add(oldList.get(i));
            }
        }

        return newList;

    }


    public static List<String> allStringsOfSize(List<String> listString, int numberOfLetters) {

        ArrayList<String> newList = new ArrayList<String>();

        for (int i = 0; i < listString.size(); i++) {
            char[] temp = listString.get(i).toCharArray();

            if (temp.length == numberOfLetters) {
                newList.add(listString.get(i));

            }
        }

        return newList;
    }

    public static <E> boolean isPermutation(List<E> listA, List<E> listB) {

        for (int i = 0; i < listA.size(); i++)
        {
            if (!listB.contains(listA.get(i)))
                return false;
        }

        for (int j = 0; j < listB.size(); j++)
        {
            if (!listA.contains(listB.get(j)))
                return false;

            } return true;
        }

    public static List<String> stringToListOfWords(String sentence) {

        List<String> listOfWords = new ArrayList<String>();

        sentence = sentence.trim();

        String[] words = sentence.replaceAll("\\p{P}", "").split(" ");

        for (int i = 0; i < words.length; i++) {
            listOfWords.add(words[i]);

        }
        return listOfWords;
    }

    public static <E> void removeAllInstances(List<E> list, E item) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(item)) {
                list.remove(i);
                i--;
                System.out.println(list);
            }
        }
    }


    public static void main(String[] args) {

        String s = "Hello, how are you today?";

        System.out.println(stringToListOfWords(s));

//        List<Integer> list = new ArrayList<Integer>();
//        List<Integer> list2 = new ArrayList<Integer>();
//
//        Integer[] nick1 = {0, 1, 2, 3, 1, 2, 4, 1,4,3,2,1,8};
//        list.addAll(Arrays.asList(nick1));
//        Integer[] nick2 = {0, 1, 2, 3, 1, 2, 4, 1, 3,8};
//        list2.addAll(Arrays.asList(nick2));
//
//        System.out.println(isPermutation(list,list2));







    }
}


