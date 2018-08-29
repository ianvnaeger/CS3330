/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2musicgenerator;

/**
 *
 * @author ianvn
 */
public class PreMadeSong extends MusicGeneratorSong{
    //This is rather simple because it pretty much just uses stuff from MusicGeneratorSong
    //But I kept it in case I wanted to expand on it in the future
    
    PreMadeSong( String title, String music){ 
        super(title,music);
    }
    
    @Override
    public void printInfo(){
        System.out.print("Premade Song: \n\"" + this.title + "\"\n");
    }
}
