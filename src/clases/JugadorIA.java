package clases;

import java.awt.*;

/**
 * La clase JugadorIA representa un jugador controlado por la IA en el juego.
 * Extiende la clase Jugador y establece un nombre predeterminado para la IA.
 */
public class JugadorIA extends Jugador {

    /**
     * Constructor de la clase JugadorIA.
     * @param color El color asociado al jugador controlado por la IA.
     */
    public JugadorIA(Color color) {
        super("IA",color);
    }
}
