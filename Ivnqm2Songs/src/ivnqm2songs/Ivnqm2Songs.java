/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2songs;

import static ivnqm2songs.Genre.*;

/**
 *
 * @author ianvn
 */
public class Ivnqm2Songs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Song song1 = new Song("My Heart Will Go On", "Celine Dion" );
        
        song1.setAlbum("Let's Talk About Love");
        song1.setReleaseYear(1997);
        song1.setGenre(POP);
        
        Song song2 = new Song("Hotel California","Eagles","Hotel California",1976,ROCK);
        //*Insert "The Big Lebowski" reference here*
        
        System.out.println("\n\nSong1: ");
        System.out.println("\nName: "+ song1.getName() +" \nArtist: " + song1.getArtist() + " \nAlbum: " + song1.getAlbum() + " \nRelease Year: " + song1.getReleaseYear() + " \nGenre: " + song1.getGenre() + " \nVersion: " + song1.getVersion() );
        
        System.out.println("\n\nSong2: ");
        System.out.println("\nName: "+ song2.getName() +" \nArtist: " + song2.getArtist() + " \nAlbum: " + song2.getAlbum() + " \nRelease Year: " + song2.getReleaseYear() + " \nGenre: " + song2.getGenre() + " \nVersion: " + song2.getVersion() );
        
        System.out.println("\nNumber of Songs: " + Song.numberOfSongs);
    }
    
}
