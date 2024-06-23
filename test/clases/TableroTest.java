package clases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * La clase TableroTest contiene Test unitarios para la clase Tablero.
 */
public class TableroTest {

    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;

    /**
     * Configuración inicial para cada Test. Se ejecuta antes de cada Test.
     */
    @BeforeEach
    public void setUp() {
        tablero = new Tablero();
        jugador1 = new Jugador("Jugador 1", Color.RED);
        jugador2 = new Jugador("Jugador 2", Color.YELLOW);
    }

    /**
     * Test que verifica que el tablero se inicializa correctamente, es decir,
     * que todas las casillas están vacías.
     */
    @Test
    public void testInicializacionTablero() {
        for (int i = 0; i < Tablero.FILAS; i++) {
            for (int j = 0; j < Tablero.COLS; j++) {
                assertNull(tablero.cas[i][j].jugador, "La casilla debería estar vacía");
            }
        }
    }

    /**
     * Test que verifica que se puede colocar una ficha en una columna vacía.
     */
    @Test
    public void testColocarFicha() {
        assertTrue(tablero.colocarFicha(jugador1, 0), "Debería ser posible colocar la ficha " +
                "en la columna 0");
    }

    /**
     * Test que verifica que no se puede colocar una ficha en una columna llena.
     */
    @Test
    public void testColumnaLlena() {
        for (int i = 0; i < Tablero.FILAS; i++) {
            tablero.colocarFicha(jugador1, 0);
        }
        assertFalse(tablero.colocarFicha(jugador1, 0), "No debería ser posible colocar más " +
                "fichas en una columna llena");
    }

    /**
     * Test que verifica que el tablero está completo cuando todas las casillas están llenas.
     */
    @Test
    public void testTableroCompleto() {
        for (int col = 0; col < Tablero.COLS; col++) {
            for (int row = 0; row < Tablero.FILAS; row++) {
                tablero.colocarFicha(jugador1, col);
            }
        }
        assertTrue(tablero.estaCompleto(), "El tablero debería estar completo");
    }

    /**
     * Test que verifica la condición de cuatro en raya horizontal.
     */
    @Test
    public void testCuatroEnRayaHorizontal() {
        for (int col = 0; col < 4; col++) {
            tablero.colocarFicha(jugador1, col);
        }
        assertTrue(tablero.cuatroEnRaya(jugador1), "Debería haber cuatro en raya horizontal");
    }

    /**
     * Test que verifica la condición de cuatro en raya vertical.
     */
    @Test
    public void testCuatroEnRayaVertical() {
        for (int i = 0; i < 4; i++) {
            tablero.colocarFicha(jugador1, 0);
        }
        assertTrue(tablero.cuatroEnRaya(jugador1), "Debería haber cuatro en raya vertical");
    }

    /**
     * Test que verifica la condición de cuatro en raya diagonal.
     */
    @Test
    public void testCuatroEnRayaDiagonal() {
        tablero.colocarFicha(jugador1, 0);
        tablero.colocarFicha(jugador2, 1);
        tablero.colocarFicha(jugador1, 1);
        tablero.colocarFicha(jugador2, 2);
        tablero.colocarFicha(jugador2, 2);
        tablero.colocarFicha(jugador1, 2);
        tablero.colocarFicha(jugador2, 3);
        tablero.colocarFicha(jugador2, 3);
        tablero.colocarFicha(jugador2, 3);
        tablero.colocarFicha(jugador1, 3);

        assertTrue(tablero.cuatroEnRaya(jugador1), "Debería haber cuatro en raya en diagonal");
    }
}
