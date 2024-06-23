package clases;

import java.awt.*;

/**
 * La clase Jugador representa un jugador en el juego.
 */
public class Jugador {
    public String nombre;
    public Color colores;

    /**
     * Constructor de la clase Jugador.
     * @param nombre El nombre del jugador.
     * @param color El color asociado al jugador.
     */
    public Jugador(String nombre, Color color) {
        this.nombre = nombre;
        this.colores = color;
    }

}
