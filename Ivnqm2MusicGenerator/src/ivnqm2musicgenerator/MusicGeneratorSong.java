/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2musicgenerator;

import java.util.Scanner;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author ianvn
 */
abstract class MusicGeneratorSong implements Song {
    String title = "";
    Note[] music;
        
    MusicGeneratorSong( String title, String music ){ //Constructs the song and puts music in readable format
        this.title = title;
        
        Scanner input = new Scanner(music);
        Note notes[] = new Note[music.length()];
        int i = 0;
        while(input.hasNext()) {
            Note loopNote = Note.getByCode(Integer.parseInt(input.next()));
            notes[i] = loopNote;
            i++;
        }
        input.close();
        
        this.music = notes;
    }
    
    @Override
    public void play(Model model){ //Plays the song using Midi Magic
        try{
            Synthesizer midiSynth = MidiSystem.getSynthesizer(); 
            midiSynth.open();
            //get and load default instrument and channel lists
            Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
            MidiChannel[] mChannels = midiSynth.getChannels();
            midiSynth.loadInstrument(instr[0]);//load an instrument
            for(int i=0;i<music.length;i++){
                if(music[i] != null){
                    //System.out.println(music[i].getId());            
                    mChannels[0].noteOn(music[i].id, 300);
                    model.triggerChangeNote(music[i]);
                    try { Thread.sleep(500); // wait time in milliseconds to control duration
                    } catch( InterruptedException e ) { }
                    model.triggerChangeBack(music[i]);
                    mChannels[0].noteOff(music[i].id);
                }
            }
        } catch (MidiUnavailableException e) {
            System.out.println("Music Generator Song Messed up");
        }
    }
    abstract public void printInfo(); //This will list Title and if it was premade or generated
}
