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
    private int velocidadY;
    private int velocidadX;

     //protected String ball = "../images/ball.png";
    /**
     * Metodo constructor usado para crear el objeto
     *
     * @param posX es la <code>posicion en x</code> del objeto.
     * @param posY es la <code>posicion en y</code> del objeto.
     */
    public Bola(int posX, int posY, int velX, int velY) {
        super(posX, posY);
        velocidadX = velX;
        velocidadY = velY;
        Image bola = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("../images/pizza.png"));

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
    public void resetState() {
        posX = 230;
        posY = 355;
    }

    /**
     * Metodo de acceso que regresa la velocidad en y del objeto
     *
     * @return velocidadX es la <code>velocidad en x</code> del objeto.
     */
    public int getVelocidadX() {
        return velocidadX;
    }

    /**
     * Metodo modificador usado para cambiar la velocidad en x del objeto
     *
     * @param t es la <code>velocidad en X</code> del objeto.
     */
    public void setVelocidadX(int t) {
        velocidadX = t;
    }

    /**
     * Metodo de acceso que regresa la velocidad en y del objeto
     *
     * @return velocidadY es la <code>velocidad en y</code> del objeto.
     */
    public int getVelocidadY() {
        return velocidadY;
    }

    /**
     * Metodo modificador usado para cambiar la velocidad en y del objeto
     *
     * @param t es la <code>velocidad en Y</code> del objeto.
     */
    public void setVelocidadY(int t) {
        velocidadY = t;
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
