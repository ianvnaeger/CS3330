/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2stopwatch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author ianvn
 */
public class StopWatch {
    public Timeline timeline;
    public Timeline timeline2;
    public KeyFrame keyFrame;
    public KeyFrame keyFrame2;
    ImageView dialImageView;
    ImageView handImageView;
    
    public double secondsElapsed = 0.0;
    public double tickTimeInSeconds = 0.01; //How to change the resolution
    public double angleDeltaPerSeconds = 6.0;
    public boolean isRunning;
    public boolean isReset;
    public boolean firstRun;
    
    public StackPane rootContainer;
    private Image dialImage;
    private Image handImage;
    private Text timer;
    //private String timeElasped;
    private SimpleDateFormat digitalTime;
    private Calendar initialTime;
    private Calendar stopTime;
    private Calendar startTime;
    private Calendar referenceTime;
    private Calendar madeTime;
    private Calendar time;
    private long totalTime;
    private long realTime;
    private long lastTime;
    private int lapCount;
    
    
    
    public StopWatch(){
        setupUI();
        setupTimer();
    }
    
    public void reset() {
        setupTimer();
    }
    
    public void setupUI(){
        rootContainer = new StackPane();
        dialImageView = new ImageView();
        handImageView = new ImageView();
        
        dialImage = new Image(getClass().getResourceAsStream("clockface.png"));
        handImage = new Image(getClass().getResourceAsStream("hand.png"));
    
        dialImageView.setImage(dialImage); 
        handImageView.setImage(handImage);
        
        GridPane grid = new GridPane(); 
        grid.setAlignment(Pos.TOP_LEFT);
        GridPane digitalGrid = new GridPane();
        digitalGrid.setAlignment(Pos.CENTER_RIGHT);
        GridPane fillerGrid = new GridPane();
        fillerGrid.setAlignment(Pos.CENTER_RIGHT);
        //grid.setVgap(20);
        
        HBox dial = new HBox();
        //dial.setAlignment(Pos.TOP_LEFT);
        dial.getChildren().addAll( dialImageView);
        grid.add(dial, 0 , 0);
        
        HBox hand = new HBox();
        //hand.setAlignment(Pos.TOP_LEFT);
        hand.getChildren().addAll(handImageView);
        grid.add(hand, 0 , 0);
        
        HBox controls = new HBox();
        Button startAndStop = new Button("Start!");
        Button record = new Button("Record");
        startAndStop.setMaxWidth(Double.MAX_VALUE);
        startAndStop.setStyle("-fx-background-color: green; -fx-text-fill: white");
        record.setStyle("-fx-background-color: blue; -fx-text-fill: white");
        record.setMaxWidth(Double.MAX_VALUE);
        controls.setAlignment(Pos.BOTTOM_CENTER);
        controls.setSpacing(10);
        controls.setPadding(new Insets(25,25,25,25));
        controls.getChildren().addAll(startAndStop, record);
        controls.toFront();
        
        Label filler1 = new Label();
        filler1.setText("                   ");
        fillerGrid.add(filler1, 1, 1);
        Label filler2 = new Label();
        filler2.setText("                   ");
        fillerGrid.add(filler2, 2, 1);
        Label filler3 = new Label();
        filler3.setText("                   ");
        fillerGrid.add(filler3, 1, 2);
        Label filler4 = new Label();
        filler4.setText("                   ");
        fillerGrid.add(filler4, 2, 2);
        Label filler5 = new Label();
        filler5.setText("                   ");
        fillerGrid.add(filler5, 1, 3);
        Label filler6 = new Label();
        filler6.setText("                   ");
        fillerGrid.add(filler6, 2, 3);
        
        
        Label lap1Label = new Label();
        Label lap2Label = new Label();
        Label lap3Label = new Label();
        Text lap1Text = new Text(); 
        Text lap2Text = new Text(); 
        Text lap3Text = new Text();
        lap1Label.setFont(Font.font("helvetica", 24));
        lap1Text.setFont(Font.font("helvetica", 24));
        lap2Label.setFont(Font.font("helvetica", 24));
        lap2Text.setFont(Font.font("helvetica", 24));
        lap3Label.setFont(Font.font("helvetica", 24));
        lap3Text.setFont(Font.font("helvetica", 24));
        
        lap1Label.setText("Lap 1: ");
        lap2Label.setText("Lap 2: ");
        lap3Label.setText("Lap 3: ");
        lap1Text.setText("--:--:---");
        lap2Text.setText("--:--:---");
        lap3Text.setText("--:--:---");
        //filler.setText("                   ");
        digitalGrid.add(lap1Label, 1 , 1);
        digitalGrid.add(lap1Text, 2 , 1);
        digitalGrid.add(lap2Label, 1 , 2);
        digitalGrid.add(lap2Text, 2 , 2);
        digitalGrid.add(lap3Label, 1 , 3);
        digitalGrid.add(lap3Text, 2 , 3);
        
        
        HBox digital = new HBox();
        timer = new Text();
        lapCount = 1;
        stopTime = Calendar.getInstance();
        startTime = Calendar.getInstance();
        madeTime = Calendar.getInstance();
        //initialTime = new SimpleDateFormat("mm:ss:SS");
        totalTime = 0;
        timer.setText("00:00:000");
        timer.setFont(Font.font("helvetica", 24));
        digital.getChildren().addAll(timer);
        digital.setAlignment(Pos.CENTER_RIGHT);
        digitalGrid.setPadding(new Insets(25,25,25,25));
        
        fillerGrid.setPadding(new Insets(25,25,100,100));
        fillerGrid.add(digital, 2, 0);
        
        
        record.setOnAction((ActionEvent event) -> {
            if(isReset){
                reset();
                startAndStop.setText("Start!");
                startAndStop.setStyle("-fx-background-color: green; -fx-text-fill: white");
                isRunning = false;
                totalTime = 0;
                timeline.stop();
                timeline2.stop();
                handImageView.setRotate(0.0);
                secondsElapsed = 0.0;
                initialTime = Calendar.getInstance();
                stopTime = Calendar.getInstance();
                startTime = Calendar.getInstance();
                madeTime = Calendar.getInstance();
                isReset = false;
                firstRun = true;
                lapCount = 1;
                lap1Text.setText("--:--:---");
                lap2Text.setText("--:--:---");
                lap3Text.setText("--:--:---");
                timer.setText("00:00:000");
                lastTime = 0;
            }else{
                if(lapCount == 1){ 
                    lapCount++;
                    lap1Text.setText("--:--:--1");
                    if(firstRun){
                        lap1Text.setText(digitalTime.format(realTime));
                        lastTime = realTime;
                    } else {
                        lap1Text.setText(digitalTime.format( (realTime - lastTime) ));
                        lastTime = realTime;
                    }   
                }else if(lapCount == 2){
                    lapCount++;
                    lap2Text.setText(digitalTime.format( (realTime - lastTime) ));
                    lastTime = realTime;
                    //lap2Text.setText("--:--:--2");
                }else if(lapCount == 3){
                    lapCount = 1;
                    lap3Text.setText(digitalTime.format( (realTime - lastTime) ));
                    lastTime = realTime;
                    //lap3Text.setText("--:--:--3");
                }
            }
        });
        
        /*
        tick.setOnAction((ActionEvent event) -> {
           if(tickPressed){
               this.setTickTimeInSeconds(0.01);
               tickPressed = false;
               tick.setText("Tick");
           } else {
               this.setTickTimeInSeconds(1.0);
               tickPressed = true;
               tick.setText("Tock");
           }
        });*/
        
        startAndStop.setOnAction((ActionEvent event) -> {
            //this.start();
            timeline.play();
            timeline2.play();
            if(isRunning){
                timeline.pause();
                timeline2.pause();
                stopTime = Calendar.getInstance();
                startAndStop.setText("Start!");
                startAndStop.setStyle("-fx-background-color: green; -fx-text-fill: white");
                isRunning = false;
                record.setText("Reset");
                isReset = true;
            }
            else{
                if(firstRun){
                    initialTime = Calendar.getInstance();
                    firstRun = false;
                }
                timeline.play();
                timeline2.play();
                startTime = Calendar.getInstance();
                startAndStop.setText("Stop!");
                startAndStop.setStyle("-fx-background-color: red; -fx-text-fill: white");
                isRunning = true;
                record.setText("Record");
                isReset = false;
            }
        });
        
        rootContainer.getChildren().addAll(grid, digitalGrid, fillerGrid, dial, hand/*, digital*/, controls);
    }
        
    public void setupTimer(){
        if(isRunning()){
            timeline.stop();
            timeline2.stop();
        }
        
        firstRun = true;
        
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent) ->  {
           update();
           //time = Calendar.getInstance();
           //digitalTime = new SimpleDateFormat("mm:ss:SS");
           //timer.setText(digitalTime.format(time.getTime()));
            //digital.setText(timeElasped);
        });
        
        keyFrame2 = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent) ->  {
           //time = Calendar.getInstance();
         /*  long currentTime = stopTime.getTimeInMillis() - startTime.getTimeInMillis();
           totalTime = totalTime + currentTime;
           digitalTime = new SimpleDateFormat("mm:ss:SS");
           timer.setText(digitalTime.format(totalTime));
          */ //digital.setText(timeElasped);
          updateDigital();
        });
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline2 = new Timeline(keyFrame2);
        timeline2.setCycleCount(Animation.INDEFINITE);
        
       /* if(!isRunning()){
            timeline.play();
        }*/
    }   
        
    private void update(){
        secondsElapsed += tickTimeInSeconds;
        double rotation = secondsElapsed * angleDeltaPerSeconds;
        handImageView.setRotate(rotation);
        //timeElasped = Double.toString( secondsElapsed);
        //digital.setText(timeElasped);
    }
    
    private void updateDigital(){
        // This may be the most convoluted way to make a timer ever, but it works
        long differenceTime = 0;
        if(!firstRun){
            differenceTime = stopTime.getTimeInMillis() - startTime.getTimeInMillis();
        }
        long catchUp = initialTime.getTimeInMillis() - madeTime.getTimeInMillis();        stopTime = Calendar.getInstance();
        startTime = Calendar.getInstance();
        referenceTime = Calendar.getInstance();
        totalTime = totalTime - differenceTime;
        realTime = (referenceTime.getTimeInMillis() - initialTime.getTimeInMillis() + catchUp) - totalTime;
        digitalTime = new SimpleDateFormat("mm:ss:SS");
        timer.setText(digitalTime.format(realTime));
    }
    
    public void start(){
        timeline.play();
    }
        
    public Double getWidth(){
        if( dialImage != null){
            return dialImage.getWidth()+200.0;
        } else {
            return 0.0;
        }
    }
    
    public Double getHeight(){
        if( dialImage != null) return dialImage.getHeight()+50.0;
        else return 0.0;
    }
    
    public Parent getRootContainer(){
        return rootContainer;
    }

    public void setTickTimeInSeconds(Double tickTimeInSeconds){
        this.tickTimeInSeconds = tickTimeInSeconds;
        setupTimer();
    }
    
    public boolean isRunning(){
        if(timeline != null){
            if(timeline.getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        return false;
    }
}
