/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2musicgenerator;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author ianvn
 */
public class GeneratedSong extends MusicGeneratorSong{
    
    static int numberOfGeneratedSongs = 0;
    
    public GeneratedSong(){ //COnstructor 1 - not really used anymore
        super("","");
        generateSong(null);
    }
    public GeneratedSong( String givenTitle ){ //Constructor 2
        super(givenTitle,"");
        generateSong(givenTitle);
    }
    
    @Override
    public void printInfo(){ //Prints the song name when played
        System.out.print("Generated Song: \n" + this.title + "\n");
    }
    
    public void generateSong(String givenTitle){ // Randomly generates a song and then saves it to the file
        //randomly make a song
        Random rand = new Random();

        int songLength = rand.nextInt(21) + 5;
        Note notes[] = new Note[songLength];
        
        for(int i = 0; i<songLength; i++){
            int randomNote = rand.nextInt(13) + 60;
            while(randomNote == 61 || randomNote == 63 || randomNote == 66 || randomNote == 68 || randomNote == 70){
                randomNote = rand.nextInt(13) + 60;
            }
            Note forNote = Note.getByCode(randomNote);
            notes[i] = forNote;
        }
        
        this.music = notes;
        String musicString = String.join(" ", "60", String.valueOf(notes[0].id)); 
        for(int k = 1;k<songLength;k++){
            musicString = String.join(" ", musicString, String.valueOf(notes[k].id));
        }
        musicString = String.join(";", musicString, ""); 
        numberOfGeneratedSongs++;
        if(givenTitle == ""){
            this.title = String.join("#", "\"GeneratedSong", (String.valueOf(numberOfGeneratedSongs)+"\""));
        } else {
            this.title = ("\""+givenTitle+"\"");
        }
        
        try {
        FileWriter fileWriter = new FileWriter("src/ivnqm2musicgenerator/SongList.txt",true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("%s-%s\n", this.title, musicString);
        printWriter.close();
        } catch( Exception e){
            System.out.println("File Writing Messed Up");
        }
    }
}
