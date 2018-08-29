/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2musicgenerator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author ianvn
 */
public class Controller implements Initializable , PropertyChangeListener {
    //UI
   
    @FXML
    private ComboBox songMenu;
    
    @FXML
    private Label generatedLabel;
    
    @FXML
    private TextField songNameTextField;
    
    @FXML
    private TabPane mainTabs;
    
    @FXML 
    private AnchorPane aboutAnchor;
    
    @FXML
    private AnchorPane thePane;
    
    @FXML
    public Ellipse lowCNote;
    @FXML
    public Ellipse dNote;
    @FXML
    public Ellipse eNote;
    @FXML
    public Ellipse fNote;
    @FXML
    public Ellipse gNote;
    @FXML
    public Ellipse aNote;
    @FXML
    public Ellipse bNote;
    @FXML
    public Ellipse highCNote;
    
    Model model;
    
    ArrayList<MusicGeneratorSong> UIArrayList;
      
    @FXML
    private void playButtonPress(ActionEvent event) { //Plays the song selected
        System.out.println("Play pressed!");
        if((String)songMenu.getValue() != null){
            model.playSong( model.findSongByName((String)songMenu.getValue()), model );   
        }
    }
    
    @FXML
    private void generateButtonPress(ActionEvent event){ //Tells the program to generate a song with a user submitted name
        model.generateNewSong(songNameTextField.getText());
        songMenu.getItems().add(("\""+songNameTextField.getText()+"\""));
        generatedLabel.setText("Song \""+songNameTextField.getText()+"\" created!");
    }
        
    @FXML
    private void switchScenes(ActionEvent event){ //This switches the Scenes (One of a pair; See Below)
        thePane.getChildren().remove(mainTabs);
        thePane.getChildren().add(aboutAnchor);
    }
    
    @FXML
    private void switchBack(ActionEvent event){ //This switches them back (One of a pair; See Above)
        thePane.getChildren().remove(aboutAnchor);
        thePane.getChildren().add(mainTabs);
    }
    
    public void changeNote(Integer notePlaying){ //Lights up the note currently being played
        switch (notePlaying) {
            case 60:
                lowCNote.setOpacity(100.0);
                break;
            case 62:
                dNote.setOpacity(100.0);
                break;
            case 64:
                eNote.setOpacity(100.0);
                break;
            case 65:
                fNote.setOpacity(100.0);
                break;
            case 67:
               gNote.setOpacity(100.0);
                break;
            case 69:
                aNote.setOpacity(100.0);
                break;
            case 71:
                bNote.setOpacity(100.0);
                break;
            case 72:
                highCNote.setOpacity(100.0);
                break;
            default:
                break;
        }
    }
    
    public void changeBack(Integer notePlaying){ //Turns off the note that was just playing
        switch (notePlaying) {
            case 60:
                lowCNote.setOpacity(0.0);
                break;
            case 62:
                dNote.setOpacity(0.0);
                break;
            case 64:
                eNote.setOpacity(0.0);
                break;
            case 65:
                fNote.setOpacity(0.0);
                break;
            case 67:
                gNote.setOpacity(0.0);
                break;
            case 69:
                aNote.setOpacity(0.0);
                break;
            case 71:
                bNote.setOpacity(0.0);
                break;
            case 72:
                highCNote.setOpacity(0.0);
                break;
            default:
                break;
        }
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) { 
//This is pretty much magic so that I can communicate between the class, Model, and Controller
        System.out.println(evt.getNewValue());
        if(evt.getOldValue() == (Integer)1){
            changeNote((Integer)evt.getNewValue());
        } else {
            changeBack((Integer)evt.getNewValue());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { //Gets everything ready to ROCK!!!
        model = new Model();
        UIArrayList = model.getArrayList();
        for(int i=0;i<UIArrayList.size();i++){
            songMenu.getItems().add(UIArrayList.get(i).title);
        }
        thePane.getChildren().remove(aboutAnchor);
        model.addPropertyChangeListener(this);
    }    
    
}
