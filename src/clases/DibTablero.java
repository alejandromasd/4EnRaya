package clases;

import java.awt.*;
import java.util.ArrayList;

/**
 * La clase DibTablero representa el tablero de juego visualmente.
 * Extiende la clase Imagen para mostrar una imagen de fondo y dibuja las fichas en el tablero.
 */
public class DibTablero extends Imagen {
    ArrayList<Ficha> ficha = new ArrayList<>();

    /**
     * Constructor de la clase DibTablero.
     * Crea un tablero con una imagen de fondo y establece el tamaño.
     */
    public DibTablero() {
        super("/imagen/tablero.png");
        setLayout(null);
        setSize(630, 540);
    }


    /**
     * Sobrescribe el método paint para dibujar las fichas en el tablero.
     * @param g El contexto gráfico en el que se dibujan las fichas.
     */
    public void paint(Graphics g) {
        super.paint(g);
        for (Ficha kk: ficha) {
            kk.dib(g);
        }
    }
}
