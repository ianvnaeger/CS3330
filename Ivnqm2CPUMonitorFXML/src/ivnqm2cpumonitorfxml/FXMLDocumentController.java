/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2cpumonitorfxml;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.animation.Animation;
//import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
//import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Ian Naeger
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button startStop;
    @FXML
    private Button recordReset;
    @FXML
    private Text CPULevel;
    @FXML
    private Text CPURecord1;
    @FXML
    private Text CPURecord2;
    @FXML
    private Text CPURecord3;
    @FXML
    ImageView handImageView;
    
    public DecimalFormat formatter = new DecimalFormat("##.###%");
    public String formatted;
    public Timeline timeline;
    public KeyFrame keyFrame;
    public Timeline timeline2;
    public KeyFrame keyFrame2;
    public int startPosition = 220;
    private static double cpu = 0;
    //ImageView handImageView;
    
    public boolean isRunning = false;
    public boolean isReset = false;
    public int recordCount = 1;
    
    //public StackPane rootContainer;
    //private Image dialImage;
    //private Image handImage;
    //private Text CPULevel;
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        CPURecord1.setText("Hello World!");
    }
    
    @FXML
    private void recordReset(ActionEvent event) {
        if(isReset){
            startStop.setText("Start!");
            startStop.setStyle("-fx-background-color: green; -fx-text-fill: white");
            isRunning = false;
            timeline.stop();
            timeline2.stop();
            handImageView.setRotate(startPosition);
            isReset = false;
            recordCount = 1;
            CPURecord1.setText("--.--%");
            CPURecord2.setText("--.--%");
            CPURecord3.setText("--.--%");
            CPULevel.setText("--.--%");
        }else{          
            switch (recordCount) {
                case 1:
                    recordCount++;
                    formatted = formatter.format(this.getCPUUsage());
                    CPURecord1.setText(formatted);
                    break;
                case 2:
                    recordCount++;
                    formatted = formatter.format(this.getCPUUsage());
                    CPURecord2.setText(formatted);
                    break;
                case 3:
                    recordCount = 1;
                    formatted = formatter.format(this.getCPUUsage());
                    CPURecord3.setText(formatted);
                    break;
                default:
                    break;
            }
        }
    }
        
    @FXML
    private void startStop(ActionEvent event) {
        if(isRunning){
            timeline.pause();
            timeline2.pause();
            startStop.setText("Start!");
            startStop.setStyle("-fx-background-color: green; -fx-text-fill: white");
            isRunning = false;
            recordReset.setText("Reset");
            isReset = true;
        }
        else{
            this.start();
            timeline.play();
            timeline2.play();
            startStop.setText("Stop!");
            startStop.setStyle("-fx-background-color: red; -fx-text-fill: white");
            isRunning = true;
            recordReset.setText("Record");
            isReset = false;
        }
    }
    
    @FXML
    public void start(){
                
        keyFrame = new KeyFrame(Duration.millis(100), (ActionEvent) ->  {
            update();
        });
        
        keyFrame2 = new KeyFrame(Duration.millis(100), (ActionEvent) ->  {
            updateHand();
        });
        
        //update();
                
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        //timeline.setCycleCount(100);
        timeline.play();
        timeline2 = new Timeline(keyFrame2);
        timeline2.setCycleCount(Animation.INDEFINITE);
        //timeline.setCycleCount(100);
        timeline2.play();
        
    }   
     
    @FXML    
    private void update(){
        cpu = this.getCPUUsage();
        System.out.println("CPU: " + cpu); 
        formatted = formatter.format(this.getCPUUsage());
        CPULevel.setText(formatted);
    }
    @FXML    
    private void updateHand(){
        
        double rotation = startPosition + (this.getCPUUsage()*280);
        handImageView.setRotate(rotation);
    }
    
    @FXML
    private static double getCPUUsage() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        double value = 0;
        for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getName().startsWith("getSystemCpuLoad")
                    && Modifier.isPublic(method.getModifiers())) {
                try {
                    value = (double) method.invoke(operatingSystemMXBean);
                } catch (Exception e) {
                    value = 0;
                }
                return value;
            }
        }
        return value;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
