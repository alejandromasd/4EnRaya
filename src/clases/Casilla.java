package clases;

/**
 * La clase Casilla representa una casilla en el tablero de juego donde un jugador puede colocar una ficha.
 */
public class Casilla {
    public Jugador jugador; //Jugador que ha colocado la ficha en la casilla correspondiente

    /**
     * Constructor por defecto para la clase Casilla.
     */
    public Casilla(){}

    /**
     * Método para colocar una ficha en la casilla.
     * Si la casilla está vacía, asigna el jugador a la casilla y devuelve true.
     * Si la casilla ya está ocupada, devuelve false.
     *
     * @param jug El jugador que intenta colocar la ficha.
     * @return true si la ficha se coloca con éxito, false si la casilla ya está ocupada.
     */
    public boolean colocarFicha(Jugador jug) {
        if(jugador == null) {
            this.jugador = jug;
            return true; //Devolvemos true si la casilla esta vacía
        }
        else {
            return false;
        }
    }
}
