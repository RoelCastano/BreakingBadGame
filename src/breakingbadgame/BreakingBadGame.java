/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breakingbadgame;

/**
 *
 * @author roelcastano
 */
public class BreakingBadGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrameBreakingBadGame juego = new JFrameBreakingBadGame(); //Crea una variable de JFrameProjectX 
        juego.setVisible(true); //Hace que se vea el jframe
        juego.setSize(900, 700); //Ajusta el tamaño inicial del jframe        
    }
    
}
