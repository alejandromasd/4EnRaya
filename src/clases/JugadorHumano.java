package clases;

import java.awt.*;

/**
 * La clase JugadorHumano representa un jugador humano en el juego.
 * Extiende la clase Jugador y permite establecer un nombre y un color personalizados para el jugador humano.
 */
public class JugadorHumano extends Jugador {

    /**
     * Constructor de la clase JugadorHumano.
     *
     * @param nombre El nombre del jugador humano.
     * @param color  El color asociado al jugador humano.
     */
    public JugadorHumano(String nombre, Color color) {
        super(nombre, color);
    }
}
