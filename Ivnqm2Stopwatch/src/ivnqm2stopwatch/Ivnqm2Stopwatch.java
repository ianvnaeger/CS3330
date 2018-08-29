/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2stopwatch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ianvn
 */
public class Ivnqm2Stopwatch extends Application {
    
    private String appName = "Ian's StopWatch";
    
    @Override
    public void start(Stage primaryStage) {
        StopWatch stopwatch = new StopWatch();
        
        Scene scene = new Scene(stopwatch.getRootContainer(),
                                stopwatch.getWidth(), 
                                stopwatch.getHeight()/*
                                digital.getRootContainer()
                                digital.getWidth(),
                                digital.getHeight()*/);
        
        primaryStage.setTitle(appName);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //analog.setTickTimeInSeconds(1.0);
        
        //stopwatch.start();
        
        //analog.setTickTimeInSeconds(1.0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
