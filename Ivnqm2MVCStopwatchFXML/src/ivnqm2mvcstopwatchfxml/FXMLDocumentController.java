/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2mvcstopwatchfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author ivnqm2
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ImageView handImageView;
    @FXML
    private Text digitalClock;
    @FXML
    private Text lap1Text;
    @FXML
    private Text lap2Text;
    @FXML
    private Text lap3Text;
    @FXML
    private Button startStop;
    @FXML
    private Button recordReset;
      
    /*@FXML
    public*/
    StopwatchAnalogModel analogModel;
    /*@FXML
    public*/
    StopwatchDigitalModel digitalModel;
    
    //model takes care of all the logic (like starting and stoping the timelines)
    //controller just modifies the UI
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //analogModel.setup();
        //System.out.println("help");
        setupTimer();
    }    
    
    @FXML
    private void handleStartStop(ActionEvent event) {
        //if start or stop
        //set to opposite
        //call startStop model function
            //for analog
            //for digital
        if( isRunning() ){
            //setupTimer();
            analogModel.start();
            digitalModel.start();
            startStop.setText("Start");
            startStop.setStyle("-fx-background-color: green; -fx-text-fill: white");
            recordReset.setText("Reset");
        } else {
            analogModel.stop();
            digitalModel.stop();
            startStop.setText("Stop");
            startStop.setStyle("-fx-background-color: red; -fx-text-fill: white");
            recordReset.setText("Record");
        }
    }
    
    @FXML
    private void handleRecordReset(ActionEvent event) {
        //if Record or Reset
        //set to opposite
        //call recordReset model function
            //for analog
            //for digital
        if( analogModel.getIsReset() ){
            analogModel.reset();
            digitalModel.reset();
            setupTimer();
            startStop.setText("Start!");
            startStop.setStyle("-fx-background-color: green; -fx-text-fill: white");    
            handImageView.setRotate(0.0);
            lap1Text.setText("--:--:---");
            lap2Text.setText("--:--:---");
            lap3Text.setText("--:--:---");
            digitalClock.setText("00:00:000");
        } else {
            if(analogModel.getLapCount() == 1){ 
                lap1Text.setText( digitalModel.getLap() );   
            }else if(analogModel.getLapCount() == 2){
                lap2Text.setText( digitalModel.getLap() );
            }else if(analogModel.getLapCount() == 3){
                lap3Text.setText( digitalModel.getLap() );
            }
        }
    }
    
    public void setupTimer(){

        //System.out.print("hi\n");
        analogModel.setFirstRun();
        
        analogModel.setKeyFrame(new KeyFrame(Duration.millis(analogModel.getTickTimeInSeconds() * 1000), (ActionEvent event) -> {
                updateAnalog(); 
        }));
        analogModel.setTimeLine(new Timeline(analogModel.getKeyFrame()));
        analogModel.getTimeLine().setCycleCount(Animation.INDEFINITE);
        
        digitalModel.setKeyFrame(new KeyFrame(Duration.millis(digitalModel.getTickTimeInSeconds() * 1000), (ActionEvent event) -> {
                updateDigital(); 
        }));
        digitalModel.setTimeLine(new Timeline(digitalModel.getKeyFrame()));
        digitalModel.getTimeLine().setCycleCount(Animation.INDEFINITE);
        
    }   
        
    private void updateAnalog(){ 
        handImageView.setRotate(analogModel.getRotation());
    }
    
    private void updateDigital(){
        digitalClock.setText(digitalModel.getTime());
    }
    
    public boolean isRunning(){
        if(analogModel.getTimeLine() != null){
            if(analogModel.getTimeLine().getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        return false;
    }
    
    
}
