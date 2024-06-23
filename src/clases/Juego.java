package clases;
import java.util.ArrayList;


/**
 * La clase Juego representa el juego en sí mismo, incluyendo el tablero y las acciones de juego.
 */
public class Juego {
    public static final int NUM_JUGADORES = 2;
    public Jugador jugadores[];  //Array con los datos de los jugadores
    Tablero tablero;
    ArrayList<Jugada> jugadas;
    int turno = 0; //Turno de jugador, valdrá 0 o 1
    int aleatorio;

    /**
     * Constructor de la clase Juego.
     * Inicializa el tablero y el array de jugadores.
     */
    public Juego() {
        tablero = new Tablero();
        jugadores = new Jugador[NUM_JUGADORES];
        jugadas = new ArrayList<Jugada>();
    }

    /**
     * Establece un jugador en una posición específica del array de jugadores.
     * @param index Índice en el array de jugadores donde se establecerá el jugador.
     * @param jug El jugador que se establecerá en el array.
     */
    public void establecerJugador(int index, Jugador jug) {
        this.jugadores[index] = jug;
    }

    /**
     * Obtiene el jugador que tiene el turno actual.
     * @return El jugador que tiene el turno actual.
     */
    public Jugador obtenerJugadorTurno() {
        return jugadores[turno];
    }

    /**
     * Realiza una jugada en el tablero para un jugador específico en una columna dada.
     * @param jug El jugador que realiza la jugada.
     * @param col La columna en la que se colocará la ficha.
     * @return true si la jugada se pudo realizar correctamente, false si la columna está llena.
     */
    public boolean jugar(Jugador jug, int col) {
        return tablero.colocarFicha(jug,col);
    }

    /**
     * Realiza una jugada aleatoria para un jugador en el tablero. La usamos para la IA.
     * @param jug El jugador que realiza la jugada.
     * @return true si la jugada se pudo realizar correctamente, false si la columna está llena.
     */
    public boolean jugar(Jugador jug) {
        aleatorio = (int) Math.floor(Math.random() * tablero.COLS);
        return jugar(jug, aleatorio);
    }
}

