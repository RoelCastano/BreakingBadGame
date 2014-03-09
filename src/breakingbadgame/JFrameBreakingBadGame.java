/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbadgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import static java.lang.Math.floor;
import java.util.LinkedList;
import javax.swing.JFrame;

/**
 *
 * @author roelcastano
 */
public class JFrameBreakingBadGame extends JFrame implements Runnable, KeyListener {

    private long tiempoActual; //Variable que guarda el tiempo para la animacion.
    boolean pausa;
    boolean gameOver;
    private Image dbImage;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico
    private Bate bate;
    private int direccionBate; // Direccion del Bate
    boolean instrucciones;
    private Meth meth;
    private Meth methEx;
    private Image background;			// Imagen de fondo
    private int numMeths;
    private Bola bola;  //creacion del objeto bola
    private LinkedList meths;
    private int methsPorLinea;
    int velBola;
    int vidas;

    public JFrameBreakingBadGame() {
        setTitle("Breaking Bad Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Carga los clips de sonido
        //coin = new SoundClip("sounds/smw_coin.wav");
        //whip = new SoundClip("sounds/whip-whoosh-02.wav");
        init();
        start();

    }

    public void init() {
        setSize(640, 700);
        pausa = false;
        vidas = 3;
        velBola = 5;
        int posX = (int) (getWidth() / 2 - 30);    // posicion en x del carro en medio del JFrame
        int posY = (int) (getHeight() - 60);    // posicion en y del carro
        background = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/background.jpg"));
        bate = new Bate(posX, posY);
        direccionBate = 0;
        instrucciones = false;
        numMeths = 108;
        meths = new LinkedList();
        methEx = new Meth(0, 0);
        methsPorLinea = getWidth() / (methEx.getAncho() + 10);
        for (int i = 0; i < numMeths + 1; i++) {
            int a = i % methsPorLinea;
            int posMethX = ((methEx.getAncho() + 5) * (a)) + (5 * a) + 20;
            int posMethY = ((int) (i / methsPorLinea)) * 40 + 40; //(int) (30 * floor((95 * i) / 950)) + 80 + (int) floor((95 * i) / 950) * 20;
            meth = new Meth(posMethX, posMethY);
            meths.add(meth);
        }
        bola = new Bola(getWidth() / 2, getHeight() - 30, velBola, velBola);
        //Pinta el fondo del Applet de color amarillo		
        setBackground(Color.white);
        addKeyListener(this);
    }

    /**
     * Metodo <I>start</I> sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se crea e inicializa el hilo para la animacion este metodo
     * es llamado despues del init o cuando el usuario visita otra pagina y
     * luego regresa a la pagina en donde esta este <code>Applet</code>
     *
     */
    public void start() {
        // Declaras un hilo
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();
    }

    /**
     * Metodo stop sobrescrito de la clase Applet. En este metodo se pueden
     * tomar acciones para cuando se termina de usar el Applet. Usualmente
     * cuando el usuario sale de la pagina en donde esta este Applet.
     */
    public void stop() {

    }

    /**
     * Metodo destroy sobrescrito de la clase Applet. En este metodo se toman
     * las acciones necesarias para cuando el Applet ya no va a ser usado.
     * Usualmente cuando el usuario cierra el navegador.
     */
    public void destroy() {

    }

    /**
     * Metodo <I>run</I> sobrescrito de la clase <code>Thread</code>.<P>
     * En este metodo se ejecuta el hilo, es un ciclo indefinido donde se
     * incrementa la posicion en x o y dependiendo de la direccion, finalmente
     * se repinta el <code>Applet</code> y luego manda a dormir el hilo.
     *
     */
    public void run() {
        //Guarda el tiempo actual del sistema

        tiempoActual = System.currentTimeMillis();
        /*vidas>0 && */
        while (true) {
            if (vidas > 0) {
                if (!pausa) {
                    actualiza();
                    checaColision();
                }

            } else {
                gameOver = true;
            }

            repaint();    // Se actualiza el <code>Applet</code> repintando el contenido.

            try {
                // El thread se duerme.
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }
        }

    }

    /**
     * Metodo usado para actualizar la posicion de objetos elefante y popo.
     *
     */
    public void actualiza() {
        switch (direccionBate) {
            case 3: {
                bate.setPosX(bate.getPosX() - 5);
                break;    //se mueve hacia abajo izquierda
            }
            case 4: {
                bate.setPosX(bate.getPosX() + 5);
                break;    //se mueve hacia arriba izquierda	
            }
        }
        bola.setPosX(bola.getPosX() + bola.getVelocidadX());
        bola.setPosY(bola.getPosY() + bola.getVelocidadY());

        long tiempoTranscurrido = System.currentTimeMillis() - tiempoActual;

        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;
        //Actualiza la animación en base al tiempo transcurrido
        if (direccionBate != 0) {
            bate.animBate.actualiza(tiempoTranscurrido);  //cuando el carro no se mueve, no se anima
        }
    }

    /**
     * Metodo usado para checar las colisiones del objeto elefante y popo con
     * las orillas del <code>Applet</code>.
     */
    public void checaColision() {
        //Colision del bueno con el Applet 
        if (bate.getPosX() + bate.getAncho() > getWidth()) {
            bate.setPosX(getWidth() - bate.getAncho());
        }
        if (bate.getPosX() < 0) {
            bate.setPosX(0);
        }
        //Colision de la bola con el JFrame
        if (bola.getPosX() <= 0) {
            bola.setVelocidadX(velBola);
        }
        if (bola.getPosX() >= getWidth() - 30) {
            bola.setVelocidadX(-velBola);
        }
        if (bola.getPosY() <= 20) {
            bola.setVelocidadY(velBola);
        }
        if (bola.getPosY() > getHeight()) {
            vidas--;
            bola.setPosX(getWidth() / 2);
            bola.setPosY(getHeight() / 10 * 8);
            bola.setVelocidadX(0);
            bola.setVelocidadY(-velBola);
        }
        //Colision del bate con la bola

        if (bate.intersecta(bola)) {

            int bateLPos = bate.getPosX();/*(int)bate.getPerimetro().getMinX();*/

            int bolaLPos = bola.getPosX();/*(int)bola.getPerimetro().getMinX();*/

            int zona = bate.getAncho() / 5;

            int first = bateLPos + (zona);
            int second = bateLPos + (zona * 2);
            int third = bateLPos + (zona * 3);
            int fourth = bateLPos + (zona * 4);

            if (bolaLPos < first) {
                bola.setVelocidadX(-velBola);
                bola.setVelocidadY(-velBola);
            }

            if (bolaLPos >= first && bolaLPos < second) {
                bola.setVelocidadX(-velBola);
                if (bola.getVelocidadY() > 0) {
                    bola.setVelocidadY(-velBola);
                } else {
                    bola.setVelocidadY(velBola);
                }
            }

            if (bolaLPos >= second && bolaLPos < third) {
                bola.setVelocidadX(0);
                bola.setVelocidadY(-velBola);
            }

            if (bolaLPos >= third && bolaLPos < fourth) {
                bola.setVelocidadX(velBola);
                if (bola.getVelocidadY() > 0) {
                    bola.setVelocidadY(-velBola);
                } else {
                    bola.setVelocidadY(velBola);
                }
            }

            if (bolaLPos > fourth) {
                bola.setVelocidadX(velBola);
                bola.setVelocidadY(-velBola);
            }
        }
        //bola con meths
        for (int i = 0; i < numMeths; i++) {
            meth = (Meth) meths.get(i);
            if (bola.intersecta(meth)) {

                int bolaLeft = (int) bola.getPosX();
                int bolaHeight = (int) bola.getAlto();
                int bolaWidth = (int) bola.getAncho();
                int bolaTop = (int) bola.getPosY();

                Point pointRight
                        = new Point(bolaLeft + bolaWidth + velBola, bolaTop);
                Point pointLeft = new Point(bolaLeft - velBola, bolaTop);
                Point pointTop = new Point(bolaLeft, bolaTop - velBola);
                Point pointBottom
                        = new Point(bolaLeft, bolaTop + bolaHeight + velBola);

                if (!meth.isDestroyed()) {
                    if (meth.getPerimetro().contains(pointRight)) {
                        bola.setVelocidadX(-velBola);
                    } else if (meth.getPerimetro().contains(pointLeft)) {
                        bola.setVelocidadX(velBola);
                    }

                    if (meth.getPerimetro().contains(pointTop)) {
                        bola.setVelocidadY(velBola);
                    } else if (meth.getPerimetro().contains(pointBottom)) {
                        bola.setVelocidadY(-velBola);
                    }

                    meth.setDestroyed(true);
                }
                meth.setDestroyed(true);
            }
        }
    }

    /**
     * Metodo <I>update</I> sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo lo que hace es actualizar el contenedor
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint(Graphics g) {
        // Inicializan el DoubleBuffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // Actualiza la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Actualiza el Foreground.
        dbg.setColor(getForeground());
        paint1(dbg);

        // Dibuja la imagen actualizada
        g.drawImage(dbImage, 0, 0, this);
    }

    /**
     * Metodo <I>keyPressed</I> sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar cualquier la
     * tecla.
     *
     * @param e es el <code>evento</code> generado al presionar las teclas.
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {    //Presiono flecha izquierda
            direccionBate = 3;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {    //Presiono flecha derecha
            direccionBate = 4;
        }
        if (e.getKeyCode() == KeyEvent.VK_I) {
            instrucciones = !instrucciones;
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            pausa = !pausa;
        }

    }

    /**
     * Metodo <I>keyTyped</I> sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al presionar una tecla que
     * no es de accion.
     *
     * @param e es el <code>evento</code> que se genera en al presionar las
     * teclas.
     */
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Metodo <I>keyReleased</I> sobrescrito de la interface
     * <code>KeyListener</code>.<P>
     * En este metodo maneja el evento que se genera al soltar la tecla
     * presionada.
     *
     * @param e es el <code>evento</code> que se genera en al soltar las teclas.
     */
    public void keyReleased(KeyEvent e) {
        direccionBate = 0;
    }

    /**
     * Metodo <I>paint</I> sobrescrito de la clase <code>Applet</code>, heredado
     * de la clase Container.<P>
     * En este metodo se dibuja la imagen con la posicion actualizada, ademas
     * que cuando la imagen es cargada te despliega una advertencia.
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint1(Graphics g) {
        g.setFont(new Font("Helvetica", Font.PLAIN, 20));	// plain font size 20
        g.setColor(Color.white);							// black font

        g.setColor(Color.black);							// black font
        if (bate != null) {
            g.drawImage(background, 0, -120, this);
            g.drawImage(bate.getImagen(), bate.getPosX(), bate.getPosY(), this);
            g.drawImage(bola.getImagen(), bola.getPosX(), bola.getPosY(), this);
            for (int i = 0; i < numMeths; i++) {
                meth = (Meth) meths.get(i);
                if (!meth.isDestroyed()) {
                    g.drawImage(meth.getImagen(), meth.getPosX(),
                            meth.getPosY(), meth.getAncho(),
                            meth.getAlto(), this);
                }
            }
            g.drawString("Vidas: " + vidas, 40, getHeight() - 20);
            if (instrucciones) {
                g.drawString("INSTRUCCIONES       ", getWidth() / 2 - 80, getHeight() / 2);
                g.drawString("P - Pausar/Jugar", getWidth() / 2 - 80, getHeight() / 2 + 20);
                g.drawString("I - Instrucciones", getWidth() / 2 - 80, getHeight() / 2 + 40);
                g.drawString("< - Moverse a la Izquierda", getWidth() / 2 - 80, getHeight() / 2 + 60);
                g.drawString("> - Moverse a la Derecha", getWidth() / 2 - 80, getHeight() / 2 + 80);
            }
            if (pausa) {
                g.drawString("PAUSA", bate.getPosX() + bate.getAncho(), bate.getPosY());
            }
            if (gameOver) {
                g.drawString("GAME OVER", getWidth() / 2 - 40, getHeight() / 2);
            }
        } else {
            //Da un mensaje mientras se carga el dibujo	
            g.drawString("No se cargo la imagen..", 20, 20);
        }

    }

}
