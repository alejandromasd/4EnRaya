package clases;


/**
 * La clase Tablero representa el tablero de juego 4 en Raya.
 * Contiene métodos para colocar fichas, verificar si el tablero está completo y detectar si hay un ganador.
 */
public class Tablero {

    public static final int FILAS = 6, COLS = 7;
    public int[] alturaColumna = new int[COLS]; //Almaceno las alturas de las diferentes columnas
    public Casilla[][] cas; //Matriz que representa todas las casillas del tablero

    /**
     * Constructor de la clase Tablero.
     * Crea un tablero en su estado inicial con todas las casillas vacías.
     */
    public Tablero() {
        cas = new Casilla[FILAS][COLS];
        for (int i = FILAS; i >= 0; i--) {
            for (int j = 0; j < COLS; j++) { //Creo array de casillas cas con las dimesiones del tablero (COLSXFILAS)
                if (i != FILAS) { //No cuento las filas ya que empiezo en el 0 y acabamos por tanto en FILAS -1
                    cas[i][j] = new Casilla();
                }
            }
        }
        //Creo e inicio el array alturaColumnas
        for (int i = 0; i < COLS; i++) {
            alturaColumna[i] = 0;


        }
    }

    /**
     * Coloca una ficha en una columna específica del tablero.
     *
     * @param jug El jugador que realiza la jugada.
     * @param col La columna en la que se colocará la ficha.
     * @return true si la ficha se colocó correctamente, false si la columna está llena.
     */
    public boolean colocarFicha(Jugador jug, int col) {
        int[] fila = new int[COLS];
        int i;
        if (col >= 0 && col < COLS) {
            for (i = FILAS - 1; i >= 0; i--) {

                if (cas[i][col].jugador != null) {
                    fila[col]++; //Sumo por cada casilla ocupada en la columna que recibe la función
                }

            }

            this.alturaColumna = fila;
            //Compruebo si es posible colocar una ficha
            if (this.alturaColumna[col] < FILAS) {

                return cas[this.alturaColumna[col]][col].colocarFicha(jug);
            } else {
                return false;
            }

        }
        return false;
    }

    /**
     * Verifica si el tablero está completo, es decir, no hay casillas vacías.
     *
     * @return true si el tablero está completo, false de lo contrario.
     */
    public boolean estaCompleto() {

        //n = Numero de casillas vacias
        int n = FILAS * COLS;

        for (int i = 0; i < FILAS; i++) {

            for (int j = 0; j < COLS; j++) {

                if (cas[i][j].jugador != null) {

                    n--; //Resto -1 por cada casilla que esta vacía

                }

            }

        }

        if (n == 0) {

            return true; //Devuelvo true si no queda ninguna casilla vacía

        } else {

            return false; //Y false si queda alguna vacía

        }

    }

    /**
     * Verifica si hay cuatro fichas del mismo jugador consecutivas en línea, lo que indica que hay un ganador.
     *
     * @param jug El jugador cuyas fichas se están buscando.
     * @return true si hay cuatro fichas en línea del mismo jugador, false de lo contrario.
     */
    public boolean cuatroEnRaya(Jugador jug) {
        boolean ganador = false;

        //Compruebo si hay 4 en Raya  en horizontal

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (cas[i][j].jugador == jug && cas[i][j + 1].jugador == jug && cas[i][j + 2].jugador == jug
                        && cas[i][j + 3].jugador == jug) {
                    ganador = true;
                }
            }
        }

        //Compruebo si hay 4 en Raya vertical

        for (int i = 0; i < FILAS - 3; i++) {
            for (int j = 0; j < COLS; j++) {
                if (cas[i][j].jugador == jug && cas[i + 1][j].jugador == jug && cas[i + 2][j].jugador == jug
                        && cas[i + 3][j].jugador == jug) {
                    ganador = true;
                }
            }
        }

        //Compruebo si hay 4 en Raya en diagonal de izquierda a derecha

        for (int i = 0; i < FILAS - 3; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (i + 3 < FILAS && j + 3 < COLS) {
                    if (cas[i][j].jugador == jug && cas[i + 1][j + 1].jugador == jug && cas[i + 2][j + 2].jugador == jug
                            && cas[i + 3][j + 3].jugador == jug) {
                        ganador = true;
                    }
                }
            }
        }

        //Compruebo si hay 4 en raya en diagonal derecha a izquierda
        for (int i = 0; i < FILAS - 3; i++) {

            for (int j = COLS - 1; j > 2; j--) {

                if (cas[i][j].jugador == jug && cas[i + 1][j - 1].jugador == jug && cas[i + 2][j - 2].jugador
                        == jug && cas[i + 3][j - 3].jugador == jug) {

                    ganador = true;

                }

            }

        }

        return ganador;


    }

}
