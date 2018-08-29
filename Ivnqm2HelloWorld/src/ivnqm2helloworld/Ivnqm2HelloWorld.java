/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2helloworld;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Ian Naeger
 *          ivnqm2
 *          1/22/2018
 */
public class Ivnqm2HelloWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String myPawPrint = "ivnqm2";
        
        System.out.println("Hello, World!");
        
        invokeMe( myPawPrint );
        
    }

    private static void invokeMe(String myPawPrint) {
       Calendar date = Calendar.getInstance(); 
       String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(Calendar.getInstance().getTime());  
       System.out.println("My PawPrint is: " + myPawPrint);
       System.out.println("Date & Time: " + timeStamp );
    }
    
}