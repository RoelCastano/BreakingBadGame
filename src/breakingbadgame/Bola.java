/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breakingbadgame;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;



public class Bola extends Base {

   private int xdir;
   private int ydir;
   private Animacion animBola;
   protected int posX;
   protected int posY;

   //protected String ball = "../images/ball.png";
   
   
    /**
     * Metodo constructor usado para crear el objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     */

   public Bola(int posX, int posY) {
     super(posX, posY);
     xdir = 1;
     ydir = -1;
     Image bola = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("../images/ball.png"));
     
//ImageIcon ii = new ImageIcon(this.getClass().getResource(ball));
//anim = ii.getImage();

//     width = image.getWidth(null);
//     heigth = image.getHeight(null);
     
             //Se crea la animación
        animBola = new Animacion();
        animBola.sumaCuadro(bola, 100);

     resetState();
    }


//    public void move()
//    {
//      x += xdir;
//      y += ydir;
//
//      if (x == 0) {
//        setXDir(1);
//      }
//
//      if (x == BALL_RIGHT) {
//        setXDir(-1);
//      }
//
//      if (y == 0) {
//        setYDir(1);
//      }
//    }

    public void resetState() 
    {
      posX = 230;
      posY = 355;
    }

    /**
     * Metodo de acceso que regresa el ancho del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del
     * icono.
     */
    public int getAncho() {
        return (new ImageIcon(animBola.getImagen())).getIconWidth();
    }

    /**
     * Metodo de acceso que regresa el alto del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el alto del
     * icono.
     */
    public int getAlto() {
        return (new ImageIcon(animBola.getImagen()).getIconHeight());
    }



    /**
     * Metodo de acceso que regresa la imagen del icono
     *
     * @return un objeto de la clase <code>Image</code> que es la imagen del
     * icono.
     */
    public Image getImagen() {
        return (new ImageIcon(animBola.getImagen())).getImage();
    }
    

}