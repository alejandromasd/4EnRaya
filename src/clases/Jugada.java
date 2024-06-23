package clases;

/**
 * La clase Jugada representa una jugada realizada por un jugador en una columna específica
 * del tablero de juego.
 */
public class Jugada {
    Jugador jugador;
    int col;

    /**
     * Constructor para la clase Jugada.
     *
     * @param jug El jugador que realiza la jugada.
     * @param col La columna en la que se realiza la jugada.
     */
    public Jugada(Jugador jug, int col) {
        this.jugador = jug;
        this.col = col;
    }
}

