/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breakingbadgame;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author roelcastano
 */
public class Bate extends Base {
    protected Animacion animBate; //animacion del carro
    private int posX;    //posicion en x.       
    private int posY;    //posicion en y.

    public Bate(int posX, int posY) {

        super(posX, posY);	//constructor

        //Se cargan las imágenes(cuadros) para la animación del malo
        Image paddle1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/minivan.png"));
        //Image bueno2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("car/1.gif"));
        //Image bueno3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("car/2.gif"));
        //Image bueno4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("car/3.gif"));
        //Image bueno5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("car/4.gif"));

        //Se crea una nueva animacion con la inizialicacion dada
        animBate = new Animacion();
        animBate.sumaCuadro(paddle1, 100);
        //animPaddle.sumaCuadro(bueno2, 100);
        //animBabe.sumaCuadro(bueno3, 100);
        //animBabe.sumaCuadro(bueno4, 100);
        //animBabe.sumaCuadro(bueno5, 100);
    }

    /**
     * Metodo de acceso que regresa el ancho del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del
     * icono.
     */
    public int getAncho() {
        return (new ImageIcon(animBate.getImagen())).getIconWidth();
    }

    /**
     * Metodo de acceso que regresa el alto del icono
     *
     * @return un objeto de la clase <code>ImageIcon</code> que es el alto del
     * icono.
     */
    public int getAlto() {
        return (new ImageIcon(animBate.getImagen())).getIconHeight();
    }

    /**
     * Metodo de acceso que regresa la imagen del icono
     *
     * @return un objeto de la clase <code>Image</code> que es la imagen del
     * icono.
     */
    public Image getImagen() {
        return (new ImageIcon(animBate.getImagen())).getImage();
    }
}
