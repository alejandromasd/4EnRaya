package clases;

import java.sql.*;
import java.util.Vector;

/**
 * La clase Ranking gestiona la conexión a una base de datos SQLite para
 * manejar un ranking de jugadores, incluyendo la creación de la tabla,
 * inserción y actualización de jugadores, y la obtención de datos del ranking.
 */
public class Ranking {
    public static void main(String[] args) {
        Connection conexion = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:ranking.db");
            System.out.println("Conexión bbdd realizada correctamente");

            stmt = conexion.createStatement();
            String sql = "CREATE TABLE RANKING " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " NOMBRE           TEXT    NOT NULL, " +
                    " PARTIDAS_JUGADAS   INT     NOT NULL, " +
                    " VICTORIAS           INT     NOT NULL, " +
                    " DERROTAS         INT     NOT NULL, " +
                    " PUNTUACION_TOTAL          INT     NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            conexion.close();
        } catch (Exception e) {
            System.err.println("La tabla ya esta creada");
            System.exit(0);
        }
        System.out.println("Tabla creada correctamente");
    }

    /**
     * Inserta un nuevo jugador en la base de datos si no existe.
     *
     * @param nombreJugador El nombre del jugador a insertar.
     */
    public static void insertarJugador(String nombreJugador) {
        Connection conexion = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:ranking.db");
            conexion.setAutoCommit(false);
            System.out.println("Conexión bbdd realizada correctamente");

            stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM RANKING WHERE NOMBRE='" + nombreJugador + "';");

            if (!rs.next()) { //Si el jugador no existe en la base de datos lo inserto
                String sql = "INSERT INTO RANKING (NOMBRE, PARTIDAS_JUGADAS, VICTORIAS, DERROTAS, PUNTUACION_TOTAL) " +
                        "VALUES ('" + nombreJugador + "', 0, 0, 0, 0);";
                stmt.executeUpdate(sql);
                conexion.commit();
                System.out.println("Jugador insertado correctamente");
            } else {
                System.out.println("El jugador ya existe en la base de datos");
            }

            rs.close();
            stmt.close();
            conexion.close();
        } catch (Exception e) {
            System.err.println("Error al insertar jugador en la base de datos" + e);
            System.exit(0);
        }
    }

    /**
     * Actualiza los datos de un jugador después de una partida.
     *
     * @param nombreJugador El nombre del jugador a actualizar.
     * @param win           Indica si el jugador ganó la partida.
     */
    public static void actualizarJugador(String nombreJugador, boolean win) {
        Connection conexion = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:ranking.db");
            conexion.setAutoCommit(false);
            System.out.println("Conexión bbdd realizada correctamente");

            stmt = conexion.createStatement();
            String sql = "UPDATE RANKING set PARTIDAS_JUGADAS = PARTIDAS_JUGADAS + 1, " +
                    (win ? "VICTORIAS = VICTORIAS + 1, PUNTUACION_TOTAL = PUNTUACION_TOTAL + 10 " :
                            "DERROTAS = DERROTAS + 1, PUNTUACION_TOTAL = PUNTUACION_TOTAL - 5 ") +
                    "where NOMBRE = '" + nombreJugador + "';";
            stmt.executeUpdate(sql);
            conexion.commit();

            stmt.close();
            conexion.close();
        } catch (Exception e) {
            System.err.println("Error al obtener ganador " + e);
            System.exit(0);
        }
        System.out.println("Jugador actualizado correctamente");
    }

    /**
     * Obtiene los datos del ranking desde la base de datos.
     *
     * @return Un vector de vectores que contiene los datos del ranking.
     */
    public static Vector<Vector<Object>> obtenerDatosRanking() {
        Connection conexion = null;
        Statement stmt = null;
        Vector<Vector<Object>> datos = new Vector<>();

        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:ranking.db");
            stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NOMBRE, PARTIDAS_JUGADAS, VICTORIAS, " +
                    "DERROTAS, PUNTUACION_TOTAL FROM RANKING ORDER BY PUNTUACION_TOTAL DESC;");

            ResultSetMetaData rsmd = rs.getMetaData();
            int numeroColumnas = rsmd.getColumnCount();

            //Creo los datos de la tabla
            while (rs.next()) {
                Vector<Object> vector = new Vector<>();
                for (int i = 1; i <= numeroColumnas; i++) {
                    vector.add(rs.getObject(i));
                }
                datos.add(vector);
            }
            rs.close();
            stmt.close();
            conexion.close();
        } catch (Exception e) {
            System.err.println("Error al mostrar datos de la tabla" + e);
            System.exit(0);
        }

        return datos;
    }


}
