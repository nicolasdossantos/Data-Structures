package com.nicolasDosSantos;

import java.util.ArrayList;


public class Guess {

    private String fNumber;
    private String sNumber;
    private String tNumber;

    boolean followRule;

    public Guess(String fNumber, String sNumber, String tNumber, boolean followRule) {
        this.fNumber = fNumber;
        this.sNumber = sNumber;
        this.tNumber = tNumber;
        this.followRule = followRule;


    }

    public String toString(){
        return "["+ fNumber+ " " + sNumber + " " + tNumber+" "+followRule+"]";
    }
}

