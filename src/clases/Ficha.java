package clases;

import java.awt.*;

/**
 * La clase Ficha representa una ficha en el juego, con una posición y un color.
 */
class Ficha {

    public static final int dimension = 74;
    int x;
    int y;
    Color color;

    /**
     * Constructor para la clase Ficha.
     *
     * @param a La posición X de la ficha.
     * @param b La posición Y de la ficha.
     * @param c El color de la ficha.
     */
    public Ficha(int a, int b, Color c) {
        setX(a);
        setY(b);
        setColor(c);
    }

    /**
     * Establece la posición X de la ficha.
     *
     * @param x La nueva posición X.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Establece la posición Y de la ficha.
     *
     * @param y La nueva posición Y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Establece el color de la ficha.
     *
     * @param color El nuevo color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Dibuja la ficha en el contexto gráfico proporcionado.
     *
     * @param g El contexto gráfico en el que dibujar la ficha.
     */
    public void dib(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, dimension, dimension);
    }
}

