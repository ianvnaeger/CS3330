/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivnqm2musicgenerator;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ianvn
 */
public enum Note { //an enum used to restrict notes accepted in the program
    C(60), D(62), E(64), F(65), G(67), A(69), B(71), Ch(72); 
    //These were chosen because they are the first octive I learned to play on the Trumpet! 
    
    public int id;

    Note(int c){
         this.id=c;
    }

    static Map<Integer, Note>map = new HashMap<>();

      static {
         for (Note note : Note.values()) {
            map.put(note.id, note);
         }
      }

      public static Note getByCode(int code) { 
         return map.get(code);
      }
         
}
