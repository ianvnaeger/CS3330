/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2mvcstopwatchfxml;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

/**
 *
 * @author ianvn
 */
public class StopwatchDigitalModel extends StopwatchAnalogModel{
    public Timeline digitalTimeline;
    public KeyFrame digitalKeyFrame;
    protected SimpleDateFormat digitalTime;
        
    
    @Override
    public void reset() {
        timeline.stop();
    }
    
    public String getLap() {
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
    }
    
    public String getTime() {
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
        return digitalTime.format(realTime);
    }
    
    @Override
    public void stop() {
        timeline.pause();
    }
    
    @Override
    public void start() {
        timeline.play();
    }
    
    @Override
    public Timeline getTimeLine(){
        return digitalTimeline; 
    }
    @Override
    public void setTimeLine(Timeline timeline){
        this.digitalTimeline = timeline; 
    }
    @Override
    public KeyFrame getKeyFrame(){
        return digitalKeyFrame; 
    }
    @Override
    public void setKeyFrame(KeyFrame keyFrame){
        this.digitalKeyFrame = keyFrame; 
    }
}
