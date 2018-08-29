/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2notifier;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ivnqm2
 *          Ian Naeger
 *          12433573
 */
public class Ivnqm2Notifier extends Application {
    
    public String title = "Notifier";
    public int width = 420; 
    public int height = 250; 
    
    @Override
    public void start(Stage primaryStage) {
       primaryStage.setTitle(title);
        
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25,25,25,25));
        
        VBox controls = new VBox();
        Button notify = new Button("Notify");
        Button clearIt = new Button("Clear");
        Button print = new Button("Print");
        Button alert = new Button("Alert");
        notify.setMaxWidth(Double.MAX_VALUE);
        clearIt.setMaxWidth(Double.MAX_VALUE);
        print.setMaxWidth(Double.MAX_VALUE);
        alert.setMaxWidth(Double.MAX_VALUE);
        controls.setAlignment(Pos.BOTTOM_CENTER);
        controls.setSpacing(10);
        controls.setPadding(new Insets(0,0,10,0));
        controls.getChildren().addAll(notify, clearIt, print, alert);
        root.add(controls,0,1);
        
        TextField textBox = new TextField();  
        textBox.setPrefWidth(350); 
        root.add(textBox, 0, 0); 
        
        notify.setOnAction((ActionEvent event) -> {
            String myPawPrint = "ivnqm2"; 
            String printing;
            printing = invokeMe( myPawPrint );
            textBox.setText(printing);
        });
        clearIt.setOnAction((ActionEvent event) -> {
            textBox.clear();
        });
        print.setOnAction((ActionEvent event) -> {
            String userInput;
            userInput = textBox.getText();
            System.out.println(userInput);
        });
        alert.setOnAction((ActionEvent event) -> {
            Alert alertBox = new Alert(AlertType.INFORMATION);
            alertBox.setTitle("This is an alert!");
            alertBox.setContentText(textBox.getText());
            alertBox.show();
        });

        Scene scene = new Scene(root, width, height); 
        primaryStage.setScene(scene);
        
        primaryStage.show(); 
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
        

    private static String invokeMe(String myPawPrint) {
       String returning;
       String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(Calendar.getInstance().getTime());  
       returning = "You have been notified by " + myPawPrint + " on " + timeStamp;
       return returning;
    }
    
}
