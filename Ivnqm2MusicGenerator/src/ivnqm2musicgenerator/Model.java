/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2musicgenerator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.concurrent.Task;

/**
 *
 * @author ianvn
 */
public class Model {
    //Logic
    private final File file;
    public ArrayList<MusicGeneratorSong> songArrayList;
    protected PropertyChangeSupport propertyChangeSupport;
    
    public Model(){ //Constructs and sets things
        propertyChangeSupport = new PropertyChangeSupport(this);
        file = new File("src/ivnqm2musicgenerator/SongList.txt");
        songArrayList = new ArrayList<>(1);
        readFile();
    }
    
    public MusicGeneratorSong findSongByName(String name) { //Finds the selected song by its title
        for(MusicGeneratorSong songObj : songArrayList) {
            if(songObj.title.equals(name)) {
                return songObj;
            }
        }
        return null;
    }
    
    public void playSong( MusicGeneratorSong selected, Model model ) { 
        //Actually quite proud of this
        //Couldn't get the UI to update while the song was playing,
        //So I had to implement multithreading to play the song while the UI updated
        selected.printInfo();
        Task<Void> task = new Task<Void>() {
                @Override 
                public Void call() throws Exception {
                    selected.play(model);
                    return null;
                }
            };
            new Thread(task).start();
    }
      
    public void generateNewSong( String givenTitle ){ //Generates a song with a provided title
        songArrayList.add( new GeneratedSong(givenTitle) );
    }
    
    public ArrayList getArrayList(){ //gets the ArrayList
        return this.songArrayList;
    }
    
    public void triggerChangeNote(Note selectedNote){ 
//The next 4 are some kind of voodoo magic that I don't fully understand, 
//but is needed for some convoluted comunication in the program
        firePropertyChange(selectedNote,true);
    }
    public void triggerChangeBack(Note selectedNote){
        firePropertyChange(selectedNote,false);
    } 
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(Note selectedNote, boolean onOff) { 
//This sends messages to the one in the Controller so my UI can update dynamically based on music note being played
        if( onOff ) {
            propertyChangeSupport.firePropertyChange("NoteID", 1, selectedNote.id);
        }
        else { 
            propertyChangeSupport.firePropertyChange("NoteID", 0, selectedNote.id);
        }
    }
    
    private void readFile(){ //It reads the file and puts the songs in an ArrayList
        try{
            Scanner input = new Scanner(file);
        
            String tokenTitle;
            String tokenMusic;
       
            while(input.hasNext()){        
                input.useDelimiter("-");
                tokenTitle = input.next();
                input.useDelimiter(";");
                if(input.hasNext()){
                    tokenMusic = input.next();
                    PreMadeSong test = new PreMadeSong(tokenTitle,tokenMusic);
                    songArrayList.add( test ); 
                    input.useDelimiter("\"");
                    input.next();
                }
            }    
        }catch( Exception e ) {
            System.out.println("Read File Messed up");
        } 
    }
}
