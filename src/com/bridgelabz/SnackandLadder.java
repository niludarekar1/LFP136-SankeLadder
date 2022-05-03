package com.bridgelabz;

import java.util.Random;
import java.util.Scanner;

public class SnackandLadder {

    //Constants for Moves
    private static final int IS_NO_PLAY = 0;
    private static final int IS_LADDER = 1;
    private static final int IS_SNAKE = 2;

    //Scanner Object for User Input
    Scanner sc = new Scanner(System.in);

    //Declaring Variables for Players Position
    int playerPosition = 0;
    int diceCount = 0;
    int dieNo = 0;
    static int player1Play = 1;

    // Random Class Object for Generating Random Numbers
    Random randomNo = new Random();

    //Method to Show Player Current Position
    private void showPosition(int playerNo) {
        //Displaying Player Position
        System.out.println("Player "+playerNo+" Position : "+ playerPosition);
    }

    //Method to Roll the Die and Get No. between 1 and 6
    private int rollDie() {
        int dieNo = randomNo.nextInt(6)+1;

        //Incrementing the Dice Count on each die roll
        diceCount++;
        System.out.println("Dice Count : "+diceCount);

        return dieNo;
    }

    //Method to Play the Game by Rolling Die
    private void playGame(int playerNo) {
        System.out.println("Player "+playerNo+" Chance :\nPress 1 to Roll the Dice :");
        int choice = sc.nextInt();
        //Show Die Rolling No
        dieNo = rollDie();
        System.out.println("Die Number for Player "+playerNo+": "+ dieNo);

        //Player going for Options
        optionPlay(dieNo);
        showPosition(playerNo);
    }

    //Method to Check for Option
    private void optionPlay(int dieNo) {

        int optionNo = randomNo.nextInt(2)+1;
        System.out.println("Option No : "+ optionNo);

        switch (optionNo) {
            case IS_NO_PLAY:
                System.out.println("Player got NO PLAY");
                break;
            case IS_LADDER:
                System.out.println("Yahoo!!! Player got LADDER");
                playerPosition += dieNo;
                //If Player Position greater than 100 then skip the Play
                if(playerPosition > 100) {
                    System.out.println("Sorry The Position is Out of Board.");
                    playerPosition -= dieNo;
                }
                break;
            case IS_SNAKE:
                System.out.println("OOPS!!! Player got SNAKE");
                playerPosition -= dieNo;
                if (playerPosition < 0) {
                    playerPosition = 0;
                }
                break;
        }
    }

    //Make Toss for Player Chance
    private static void flipToss() {
        Random tossValue = new Random();
        int toss = tossValue.nextInt(2)+1;
        if(toss == 1) {
            player1Play = 1;
            System.out.println("Player 1 Won the Toss.");
        } else {
            player1Play = 0;
            System.out.println("Player 2 Won the Toss.");
        }
    }

    //Method for Toss to Player Chance
    private static void playerChance() {
        if(player1Play == 1) {
            player1Play = 0;
        } else {
            player1Play = 1;
        }
    }

    //Method to check for Win
    private boolean checkWin() {
        if(playerPosition == 100) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        int checkLadder =0;

        //Displaying Welcome Message
        System.out.println("Welcome to Snake and Ladder Simulator Problem Developed by Nilesh Darekar.");

        //Creating Object for Player
        SnackandLadder player1 = new SnackandLadder();
        SnackandLadder player2 = new SnackandLadder();

        //Showing Player Position
        player2.showPosition(1);
        player1.showPosition(2);

        //Flipping the toss for getting whcih player play first
        flipToss();

        //Iterating unto Player got Position 100
        while( !player1.checkWin() && !player2.checkWin()) {
            if(player1Play ==1) {
                player1.playGame(1);
            } else {
                player2.playGame(2);
            }
            playerChance();
        }
    }
}