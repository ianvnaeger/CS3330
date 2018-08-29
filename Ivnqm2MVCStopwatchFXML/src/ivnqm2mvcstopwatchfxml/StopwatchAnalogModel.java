/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2mvcstopwatchfxml;

import java.util.Calendar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;


/**
 *
 * @author ianvn
 */
public class StopwatchAnalogModel {
    public Timeline timeline;
    public KeyFrame keyFrame;
    
    public double secondsElapsed = 0.0;
    public double tickTimeInSeconds = 0.01; //How to change the resolution
    public double angleDeltaPerSeconds = 6.0;
    public boolean isRunning;
    public boolean isReset;
    public boolean firstRun;
    
    //protected SimpleDateFormat digitalTime;
    protected Calendar initialTime;
    protected Calendar stopTime;
    protected Calendar startTime;
    protected Calendar referenceTime;
    protected Calendar madeTime;
    protected Calendar time;
    protected long totalTime;
    protected long realTime;
    protected long lastTime;
    protected int lapCount;
    
    public StopwatchAnalogModel(){
        setup();
    }
    
    public void setup(){
        lapCount = 1;
        stopTime = Calendar.getInstance();
        startTime = Calendar.getInstance();
        madeTime = Calendar.getInstance();
        totalTime = 0;
    }
        
    public void setFirstRun(){
        firstRun = true;
    }
        
    public void reset() {
        isRunning = false;
        totalTime = 0;
        timeline.stop();
        secondsElapsed = 0.0;
        initialTime = Calendar.getInstance();
        stopTime = Calendar.getInstance();
        startTime = Calendar.getInstance();
        madeTime = Calendar.getInstance();
        isReset = false;
        firstRun = true;
        lapCount = 1;
        lastTime = 0;    
    }
    
    /*public String getLap() {
        String output = null;
        if(lapCount == 1){ 
            lapCount++;
            if(firstRun){
                output = digitalTime.format(realTime);
                lastTime = realTime;
            } else {
                output = digitalTime.format( (realTime - lastTime) );
                lastTime = realTime;
            }   
        }else if(lapCount == 2){
            lapCount++;
            output = digitalTime.format( (realTime - lastTime) );
            lastTime = realTime;
        }else if(lapCount == 3){
            lapCount = 1;
            output = digitalTime.format( (realTime - lastTime) );
            lastTime = realTime;
        }
        return output;
    }*/
        
    public int getLapCount(){
        return lapCount;
    }
    
    public boolean getIsReset(){
        return isReset;
    }
    
    public boolean getFirstRun(){
        return firstRun;
    }
    
    public double getRotation() {
        secondsElapsed += tickTimeInSeconds;
        double rotation = secondsElapsed * angleDeltaPerSeconds;
        return rotation;
    }
    
    public void stop() {
        timeline.pause();
        stopTime = Calendar.getInstance();
        isRunning = false;
        isReset = true;
    }
    
    public void start() {
        if(firstRun){
            initialTime = Calendar.getInstance();
            firstRun = false;
        }
        timeline.play();
        startTime = Calendar.getInstance();
        isRunning = true;
        isReset = false;
    }
    
    public Timeline getTimeLine(){
        return timeline; 
    }
    public void setTimeLine(Timeline timeline){
        this.timeline = timeline; 
    }
    public KeyFrame getKeyFrame(){
        return keyFrame; 
    }
    public void setKeyFrame(KeyFrame keyFrame){
        this.keyFrame = keyFrame; 
    }
        
    public double getTickTimeInSeconds(){
        return tickTimeInSeconds;
    }
    
    
}

