/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2javalanguagebasics;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author ianvn
 */
public class Ivnqm2JavaLanguageBasics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(Calendar.getInstance().getTime());  
        byte test1 = 0x7c;
        byte test2 = 124;
        short currentSpeed = 85;
        long balance = (long)751532445;
        float acceleration = (float) 9.8;
        float weight = 195.7f;
        double length = 9.763001;
        boolean lost = false;
        boolean smart = true;
        String hourString = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());  
        int hour = Integer.parseInt( hourString );
        String minString = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());  
        int minute = Integer.parseInt( minString );
        char integral = '\u222B';
        char character1 = 'a';
        char character2 = 97;
        String greeting = "Ni Hao";
        String myPawPrint = "ivnqm2";
        
        if( test1 == test2 ){
            System.out.println("The examples are equal.");
        } else {
            System.out.println("The examples are not equal.");
        }
        
        if( currentSpeed >= 55 && currentSpeed <= 80 ){
            System.out.println("\nCurrent Speed is legal.");
        } else {
            System.out.println("\nCurrent Speed may get a ticket");
        }
        
        if( balance >= (long)100000000 ){
            System.out.println("\nI'm rich!!!");
        } else {
            System.out.println("\nI'm poor...");
        }
        
        float force = weight * acceleration;
        
        System.out.printf("\nForce = %f\n", force);
        
        System.out.println("\n" + length + " is the length.");
        
        if( lost == true && smart == true ){
            System.out.println("\nI am really sorry! I will get the teaching assistant");
        } else if( lost == false && smart == true ){
            System.out.println("\nCongratulations for understanding and working hard!");
        } else {
            System.out.println("\nNo message available...");
        }
        
        switch( hour ){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                System.out.println("\nThe current time is " + hour + ":" + minute + " and it is the night");
                break;
            case 5:     
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                System.out.println("\nThe current time is " + hour + ":" + minute + " and it is the morning");
                break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:     
            case 16:
                System.out.println("\nThe current time is " + hour + ":" + minute + " and it is the afternoon");
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
                System.out.println("\nThe current time is " + hour + ":" + minute + " and it is the evening");
                break;
            case 23:
                System.out.println("\nThe current time is " + hour + ":" + minute + " and it is the night");
                break;
            default:
                System.out.println("\nYou have the wrong time...");
                break;
        }
        
        System.out.println("\n" + integral + " is an integral");
        
        if( character1 == character2 ){
            System.out.println("\n" + character1 + " and " + character2 + " are the same.\n");
        } else {
            System.out.println("\n" + character1 + " and " + character2 + " are NOT the same.\n");
        }
        
        for( int i = 1; i <= 15; i++ ){
            if( (i % 3) == 0 ){
                System.out.println("i = " + i);
            }
        }
        System.out.printf("\n");
        
        int age = 0;
        
        while( age < 13 ){
            System.out.println("age = " + age);
            age++;
        }
        
        invokeMe(greeting, myPawPrint);
        
        // TODO code application logic here
    }
    
    private static void invokeMe(String greeting ,String myPawPrint) {
       String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(Calendar.getInstance().getTime());  
       System.out.println( "\n" + greeting +", my PawPrint is " + myPawPrint + " and today's date is "+ timeStamp );
    }
    
}
