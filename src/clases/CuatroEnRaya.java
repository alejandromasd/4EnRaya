package clases;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * La clase CuatroEnRaya representa el juego de Cuatro en Raya. Esta clase
 * extiende de Frame y proporciona la interfaz gráfica de usuario para jugar
 * al juego. Permite a los jugadores iniciar una nueva partida, seleccionar
 * jugadores humanos o IA, jugar turnos, y muestra los resultados del juego.
 */
public class CuatroEnRaya extends Frame {

    /**
     * Método principal para iniciar el juego. Crea una instancia de la clase
     * CuatroEnRaya, lo que inicia el juego.
     */
    public static void main(String[] args) {

        CuatroEnRaya ventana = new CuatroEnRaya();

    }

    Juego game;
    Jugador player1, player2;
    public boolean comprobar;
    int numCol;
    int change;
    Clip fichaJugador, musicaPrincipal, victoria, gameOver, clickMenu, errorSonido, musicaEmpate;

    /**
     * Constructor de la clase CuatroEnRaya. Inicializa la interfaz gráfica
     * de usuario y carga los archivos de audio necesarios.
     */
    public CuatroEnRaya() {
        try {
            musicaPrincipal = AudioSystem.getClip();
            fichaJugador = AudioSystem.getClip();
            victoria = AudioSystem.getClip();
            gameOver = AudioSystem.getClip();
            clickMenu = AudioSystem.getClip();
            errorSonido = AudioSystem.getClip();
            musicaEmpate = AudioSystem.getClip();

            cargarAudio(musicaPrincipal, "/audio/inicio.wav");
            cargarAudio(fichaJugador, "/audio/ficha.wav");
            cargarAudio(victoria, "/audio/win.wav");
            cargarAudio(errorSonido, "/audio/error.wav");
            cargarAudio(clickMenu, "/audio/clickMenu.wav");
            cargarAudio(gameOver, "/audio/gameOver.wav");
            cargarAudio(musicaEmpate, "/audio/empate.wav");

        } catch (Exception e) {
            System.out.println("Error al cargar el audio" + e);
        }

        setSize(800, 585);
        setResizable(false);
        setTitle("4 EN RAYA - Alejandro Mas Diego 2ºDAM");
        setBackground(Color.DARK_GRAY);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        //Creamos la barra de menu y añadimos items
        MenuBar bar = new MenuBar();
        Menu partida = new Menu("Partida");
        Menu ayuda = new Menu("Ayuda");

        Panel principal = new Panel();
        musicaPrincipal.loop(5);

        //Opciones del menu partida

        MenuItem partida1 = new MenuItem("Empezar", new MenuShortcut(KeyEvent.VK_N));
        ActionListener Empezar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickMenu.setFramePosition(0);
                clickMenu.start();
                principal.setVisible(false);
                Panel draft = new Panel();
                draft.setLayout(null);
                draft.setBackground(Color.WHITE);
                draft.setBounds(0, 45, 800, 540);
                add(draft);

                Imagen fondodraft = new Imagen("/imagen/seleccion.png");
                fondodraft.setLayout(null);
                fondodraft.setBounds(0, 0, 800, 540);
                draft.add(fondodraft);


                Boton iniciar = new Boton("/imagen/nuevaPartida.png");
                iniciar.setBounds(550, 10, 250, 100);
                fondodraft.add(iniciar);

                Boton atras = new Boton("/imagen/volver.png");
                atras.setBounds(0, 6, 250, 100);
                fondodraft.add(atras);

                //accion atras
                atras.addActionListener((ActionEvent z) -> {
                    clickMenu.setFramePosition(0);
                    clickMenu.start();
                    principal.setVisible(true);
                    draft.setVisible(false);

                });

                //Listas jugadores
                List lista1 = new List();
                List lista2 = new List();

                lista1.setBounds(120, 200, 100, 60);
                lista1.setFont(new Font("Arial", Font.BOLD, 14));
                lista1.select(0);
                lista1.setForeground(Color.BLUE);
                lista1.setBackground(Color.YELLOW);

                lista2.setBounds(600, 200, 100, 60);
                lista2.setFont(new Font("Arial", Font.BOLD, 14));
                lista2.select(0);
                lista2.setForeground(Color.BLUE);
                lista2.setBackground(Color.YELLOW);

                lista1.add("Jugador 1");
                lista1.add("IA");
                lista2.add("Jugador 2");
                lista2.add("IA");

                fondodraft.add(lista1);
                fondodraft.add(lista2);


                TextField campo1 = new TextField("");
                campo1.setBounds(120, 270, 100, 20);
                campo1.setVisible(true);
                campo1.setFont(new Font("Arial", Font.BOLD, 14));
                campo1.setForeground(Color.BLUE);
                campo1.setBackground(Color.YELLOW);
                fondodraft.add(campo1);

                TextField campo2 = new TextField("");
                campo2.setBounds(600, 270, 100, 20);
                campo2.setVisible(true);
                campo2.setFont(new Font("Arial", Font.BOLD, 14));
                campo2.setForeground(Color.BLUE);
                campo2.setBackground(Color.YELLOW);
                fondodraft.add(campo2);

                Imagen fichaRoja = new Imagen("/imagen/fichaRoja.png");
                fichaRoja.setBounds(10, 280, 390, 250);
                fondodraft.add(fichaRoja);
                fichaRoja.setVisible(true);

                Imagen fichaAmarilla = new Imagen("/imagen/fichaAmarilla.png");
                fichaAmarilla.setBounds(515, 280, 390, 250);
                fondodraft.add(fichaAmarilla);

                //Evento para activar/desactivar introducir nombre de jugador
                lista1.addItemListener(new java.awt.event.ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (lista1.getSelectedItem().equals("Jugador 1")) {
                            campo1.setVisible(true);
                            campo1.setText("");
                            campo1.setEnabled(true);
                        } else {
                            campo1.setText("IA");
                            campo1.setEnabled(false);
                        }
                    }

                });

                lista2.addItemListener(new java.awt.event.ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (lista2.getSelectedItem().equals("Jugador 2")) {
                            campo2.setVisible(true);
                            campo2.setText("");
                            campo2.setEnabled(true);
                        } else {
                            campo2.setText("IA");
                            campo2.setEnabled(false);
                        }
                    }
                });

                //Botón NuevaPartida
                iniciar.addActionListener((ActionEvent z) -> {
                    if (lista1.getSelectedItem().equals("IA") && lista2.getSelectedItem().equals("IA")) {
                        Dialog error = new Dialog(CuatroEnRaya.this, "");
                        error.setSize(453, 325);
                        error.setResizable(false);
                        error.setTitle("ERROR");
                        error.setBackground(Color.BLACK);
                        error.setLocationRelativeTo(null);
                        error.setLayout(null);
                        error.setVisible(true);
                        errorSonido.setFramePosition(0);
                        errorSonido.start();


                        Imagen imagenError = new Imagen("/imagen/imagenError.png");
                        imagenError.setBounds(0, 85, 453, 240);
                        error.add(imagenError);

                        Label fallo = new Label("¡No se puede 2 IAs!");
                        fallo.setBounds(130, 30, 400, 40);
                        fallo.setFont(new Font("TimesRoman", Font.BOLD, 20));
                        fallo.setForeground(Color.red);
                        fallo.setVisible(true);
                        error.add(fallo);
                        errorSonido.setFramePosition(0);
                        errorSonido.start();


                        error.addWindowListener(new WindowAdapter() {
                            public void windowClosing(WindowEvent we) {
                                error.setVisible(false);

                            }
                        });
                    } else if (campo1.getText().trim().equals("") || campo2.getText().trim().equals("")) {
                        Dialog error2 = new Dialog(CuatroEnRaya.this, "titulo");
                        error2.setSize(453, 325);
                        error2.setResizable(false);
                        error2.setTitle("ERROR");
                        error2.setBackground(Color.BLACK);
                        error2.setLocationRelativeTo(null);
                        error2.setLayout(null);
                        error2.setVisible(true);

                        //¿Sonido?

                        Imagen imagenError2 = new Imagen("/imagen/imagenError.png");
                        imagenError2.setBounds(0, 85, 453, 240);
                        error2.add(imagenError2);

                        Label fallo2 = new Label("¡Rellena ambos nombres!");
                        fallo2.setBounds(105, 30, 400, 40);
                        fallo2.setVisible(true);
                        fallo2.setFont(new Font("TimesRoman", Font.BOLD, 20));
                        fallo2.setForeground(Color.red);
                        error2.add(fallo2);
                        errorSonido.setFramePosition(0);
                        errorSonido.start();


                        error2.addWindowListener(new WindowAdapter() {
                            public void windowClosing(WindowEvent we) {
                                error2.setVisible(false);

                            }
                        });
                    } else if (campo1.getText().equals(campo2.getText())) {
                        Dialog error3 = new Dialog(CuatroEnRaya.this, "titulo");
                        error3.setSize(453, 325);
                        error3.setResizable(false);
                        error3.setTitle("ERROR");
                        error3.setBackground(Color.BLACK);
                        error3.setLocationRelativeTo(null);
                        error3.setLayout(null);
                        error3.setVisible(true);

                        Imagen imagenError3 = new Imagen("/imagen/imagenError.png");
                        imagenError3.setBounds(0, 85, 453, 240);
                        error3.add(imagenError3);

                        Label fallo3 = new Label("Ambos jugadores no pueden llamarse igual");
                        fallo3.setBounds(20, 20, 420, 40);
                        fallo3.setFont(new Font("TimesRoman", Font.BOLD, 20));
                        fallo3.setForeground(Color.red);
                        fallo3.setVisible(true);
                        error3.add(fallo3);
                        errorSonido.setFramePosition(0);
                        errorSonido.start();

                        error3.addWindowListener(new WindowAdapter() {
                            public void windowClosing(WindowEvent we) {
                                error3.setVisible(false);


                            }
                        });


                    } else {
                        clickMenu.setFramePosition(0);
                        clickMenu.start();
                        game = new Juego();

                        if (lista1.getSelectedItem().equals("Jugador 1")) {
                            player1 = new JugadorHumano(campo1.getText(), Color.RED);
                            Ranking.insertarJugador(player1.nombre);
                        } else {
                            player1 = new JugadorIA(Color.RED);
                        }

                        if (lista2.getSelectedItem().equals("Jugador 2")) {
                            player2 = new JugadorHumano(campo2.getText(), Color.YELLOW);
                            Ranking.insertarJugador(player2.nombre);
                        } else {
                            player2 = new JugadorIA(Color.YELLOW);
                        }

                        game.establecerJugador(0, player1);
                        game.establecerJugador(1, player2);

                        System.out.println(game.jugadores[0].nombre);
                        System.out.println(game.jugadores[1].nombre);

                        draft.setVisible(false);

                        Panel partida = new Panel();
                        partida.setLayout(null);
                        partida.setBackground(Color.BLACK);
                        partida.setBounds(0, 45, 800, 540);
                        add(partida);

                        Imagen juegofondo = new Imagen("/imagen/lateral.png");
                        juegofondo.setLayout(null);
                        juegofondo.setBounds(0, 100, 170, 460);
                        partida.add(juegofondo);

                        DibTablero tabla = new DibTablero(); //actualizar el tablero
                        tabla.setLocation(170, 0);
                        tabla.setVisible(true);
                        tabla.setEnabled(true);
                        partida.add(tabla);

                        Panel izq = new Panel();
                        izq.setLayout(null);
                        izq.setBackground(Color.BLACK);
                        izq.setBounds(0, 0, 170, 100);
                        partida.add(izq);

                        Label turnode = new Label("Turno de ");
                        turnode.setBounds(40, 15, 120, 20);
                        turnode.setVisible(true);
                        turnode.setFont(new Font("TimesRoman", Font.BOLD, 20));
                        turnode.setForeground(Color.white);
                        izq.add(turnode);

                        Label turnonombre = new Label("");
                        turnonombre.setBounds(55, 40, 120, 30);
                        turnonombre.setFont(new Font("TimesRoman", Font.BOLD, 20));
                        turnonombre.setForeground(Color.white);
                        turnonombre.setVisible(true);
                        izq.add(turnonombre);

                        // Ventana Victoria
                        Dialog winner = new Dialog(CuatroEnRaya.this, "VICTORIA"); // aparece si alguien gana
                        winner.setSize(453, 325);
                        winner.setResizable(false);
                        winner.setTitle("¡HAS GANADO!");
                        winner.setBackground(Color.DARK_GRAY);
                        winner.setLocationRelativeTo(null);
                        winner.setLayout(null);
                        Imagen win = new Imagen("/imagen/win.png");
                        win.setBounds(0, 25, 453, 300);
                        winner.add(win);

                        //Creo un label temporal para almacenar el nombre del jugador
                        Label labelTemporal = new Label("");
                        labelTemporal.setBounds(50, 20, 120, 20);
                        labelTemporal.setVisible(true);
                        winner.add(labelTemporal);
                        winner.addWindowListener(new WindowAdapter() {
                            public void windowClosing(WindowEvent we) {
                                winner.setVisible(false);
                                partida.setVisible(false);
                                draft.setVisible(true);
                                musicaPrincipal.loop(5);
                            }
                        });

                        //Si gana la IA
                        Dialog winnerIA = new Dialog(CuatroEnRaya.this, "titulo");
                        winnerIA.setSize(453, 325);
                        winnerIA.setResizable(false);
                        winnerIA.setTitle("¡Has perdido! Inténtalo de nuevo.");
                        winnerIA.setBackground(Color.DARK_GRAY);
                        winnerIA.setLocationRelativeTo(null);
                        winnerIA.setLayout(null);
                        Imagen winia = new Imagen("/imagen/gameover.png");
                        winia.setBounds(0, 25, 453, 300);
                        winnerIA.add(winia);
                        winnerIA.setVisible(false);

                        winnerIA.addWindowListener(new WindowAdapter() {
                            public void windowClosing(WindowEvent we) {
                                winnerIA.setVisible(false);
                                partida.setVisible(false);
                                draft.setVisible(true);
                                musicaPrincipal.loop(5);
                            }
                        });

                        //Si empatan los jugadores
                        Dialog nowinner = new Dialog(CuatroEnRaya.this, "EMPATE");
                        nowinner.setSize(453, 325);
                        nowinner.setResizable(false);
                        nowinner.setTitle("FIN");
                        nowinner.setBackground(Color.DARK_GRAY);
                        nowinner.setLocationRelativeTo(null);
                        nowinner.setLayout(null);
                        Imagen empate = new Imagen("/imagen/empate.png");
                        empate.setBounds(0, 25, 453, 300);
                        nowinner.add(empate);
                        nowinner.setVisible(false);
                        nowinner.addWindowListener(new WindowAdapter() {
                            public void windowClosing(WindowEvent we) {
                                nowinner.setVisible(false);
                                partida.setVisible(false);
                                draft.setVisible(true);
                                musicaPrincipal.loop(5);
                            }
                        });

                        //Si la columna esta completa
                        Dialog completa = new Dialog(CuatroEnRaya.this, "COMPLETA");
                        completa.setSize(453, 325);
                        completa.setResizable(false);
                        completa.setBackground(Color.WHITE);
                        completa.setLocationRelativeTo(null);
                        completa.setLayout(null);
                        completa.setVisible(false);

                        Imagen columnaCompleta = new Imagen("/imagen/completa.png");
                        columnaCompleta.setBounds(0, 25, 453, 300);
                        completa.add(columnaCompleta);

                        completa.addWindowListener(new WindowAdapter() {
                            public void windowClosing(WindowEvent we) {
                                completa.setVisible(false);
                            }
                        });

                        //Ventana antes de empezar a jugar

                        Dialog inicio = new Dialog(CuatroEnRaya.this, "Pulsa para comenzar");
                        inicio.setSize(453, 325);
                        inicio.setResizable(false);
                        inicio.setBackground(Color.RED);
                        inicio.setLocationRelativeTo(null);
                        inicio.setLayout(null);
                        inicio.setVisible(true);

                        Imagen iniciofondo = new Imagen("/imagen/inicio.png");
                        iniciofondo.setBounds(0, 25, 453, 300);
                        inicio.add(iniciofondo);

                        Label start = new Label("Pulsa para comenzar");
                        start.setBounds(166, 60, 120, 20);
                        start.setVisible(true);
                        iniciofondo.add(start);

                        Button start1 = new Button("Empezar");
                        start1.setBounds(166, 100, 120, 20);
                        start1.setBackground(Color.RED);
                        start1.setForeground(Color.WHITE);
                        iniciofondo.add(start1);
                        tabla.setEnabled(false);

                        start1.addActionListener((ActionEvent) -> {
                            clickMenu.setFramePosition(0);
                            clickMenu.start();
                            tabla.setEnabled(true);
                            inicio.setVisible(false);
                            game.turno = (int) (Math.random() * 2);
                            turnonombre.setText(game.obtenerJugadorTurno().nombre);

                            if (game.obtenerJugadorTurno() instanceof JugadorIA) {
                                do {
                                    comprobar = game.jugar(game.obtenerJugadorTurno());
                                } while (comprobar == false);

                                tabla.ficha.add(new Ficha(8 + game.aleatorio * 90, 458 -
                                        game.tablero.alturaColumna[game.aleatorio] * 90,
                                        game.obtenerJugadorTurno().colores));
                                //Pinto la ficha en el tablero
                                tabla.paint(tabla.getGraphics());

                                if (game.turno == 1) {
                                    change = 0;
                                } else {
                                    change = 1;
                                }
                                //Cambio turno y muestro nombre
                                game.turno = change;
                                turnonombre.setText(game.obtenerJugadorTurno().nombre);
                            }


                        });

                        tabla.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent click) {
                                if (click.getButton() == MouseEvent.BUTTON1) {
                                    fichaJugador.setFramePosition(0); //Para resetear el sonido de la ficha
                                    fichaJugador.start();
                                    if (click.getX() <= 90) {
                                        if (game.jugar(game.obtenerJugadorTurno(), 0) == true) {
                                            comprobar = true;
                                            numCol = 0;
                                        } else {
                                            comprobar = false;
                                            completa.setVisible(true);
                                        }
                                    } else if (click.getX() > 90 && click.getX() <= 180) {
                                        if (game.jugar(game.obtenerJugadorTurno(), 1) == true) {
                                            comprobar = true;
                                            numCol = 1;
                                        } else {
                                            comprobar = false;
                                            completa.setVisible(true);
                                        }
                                    } else if (click.getX() > 180 && click.getX() <= 270) {
                                        if (game.jugar(game.obtenerJugadorTurno(), 2) == true) {
                                            comprobar = true;
                                            numCol = 2;
                                        } else {
                                            comprobar = false;
                                            completa.setVisible(true);
                                        }
                                    } else if (click.getX() > 270 && click.getX() <= 360) {
                                        if (game.jugar(game.obtenerJugadorTurno(), 3) == true) {
                                            comprobar = true;
                                            numCol = 3;
                                        } else {
                                            comprobar = false;
                                            completa.setVisible(true);
                                        }
                                    } else if (click.getX() > 360 && click.getX() <= 450) {
                                        if (game.jugar(game.obtenerJugadorTurno(), 4) == true) {
                                            comprobar = true;
                                            numCol = 4;
                                        } else {
                                            comprobar = false;
                                            completa.setVisible(true);
                                        }
                                    } else if (click.getX() > 450 && click.getX() <= 540) {
                                        if (game.jugar(game.obtenerJugadorTurno(), 5) == true) {
                                            comprobar = true;
                                            numCol = 5;
                                        } else {
                                            comprobar = false;
                                            completa.setVisible(true);
                                        }
                                    } else {
                                        if (game.jugar(game.obtenerJugadorTurno(), 6) == true) {
                                            comprobar = true;
                                            numCol = 6;
                                        } else {
                                            comprobar = false;
                                            completa.setVisible(true);
                                        }
                                    }

                                    if (comprobar == true) {
                                        tabla.ficha.add(new Ficha(8 + numCol * 90, 458 -
                                                game.tablero.alturaColumna[numCol] * 90,
                                                game.obtenerJugadorTurno().colores));
                                        //Pinto la ficha jugada en el tablero
                                        tabla.paint(tabla.getGraphics());

                                        if (game.tablero.cuatroEnRaya(game.obtenerJugadorTurno()) == false &&
                                                game.tablero.estaCompleto() == false) { //La partida continua

                                            if (game.turno == 1) {
                                                change = 0;
                                            } else {
                                                change = 1;
                                            }
                                            game.turno = change;
                                            turnonombre.setText(game.obtenerJugadorTurno().nombre);
                                        }
                                        //La partida se acaba
                                        else {
                                            if (game.tablero.cuatroEnRaya(game.obtenerJugadorTurno()) == true) {
                                                winner.setVisible(true);
                                                tabla.setEnabled(false);
                                                System.out.println("Ha ganado! " + game.obtenerJugadorTurno().nombre);
                                                Ranking.actualizarJugador(game.obtenerJugadorTurno().nombre, true);

                                                Jugador perdedor = game.obtenerJugadorTurno() == game.jugadores[0] ? game.jugadores[1] : game.jugadores[0];
                                                Ranking.actualizarJugador(perdedor.nombre, false);
                                                musicaPrincipal.stop();
                                                victoria.setFramePosition(0);
                                                victoria.start();

                                            }
                                            //Empate
                                            else {
                                                nowinner.setVisible(true);
                                                tabla.setEnabled(false);
                                                musicaPrincipal.stop();
                                                musicaEmpate.setFramePosition(0);
                                                musicaEmpate.start();

                                            }
                                        }
                                    }

                                    if (game.obtenerJugadorTurno() instanceof JugadorIA) {
                                        do {
                                            comprobar = game.jugar(game.obtenerJugadorTurno());

                                        } while (comprobar == false);

                                        tabla.ficha.add(new Ficha(8 + game.aleatorio * 90,
                                                458 - game.tablero.alturaColumna[game.aleatorio] * 90,
                                                game.obtenerJugadorTurno().colores));
                                        tabla.paint(tabla.getGraphics());


                                        if (game.tablero.cuatroEnRaya(game.obtenerJugadorTurno()) == false &&
                                                game.tablero.estaCompleto() == false) {
                                            //Cambio turno

                                            if (game.turno == 1) {
                                                change = 0;
                                            } else {
                                                change = 1;
                                            }
                                            game.turno = change;
                                            turnonombre.setText(game.obtenerJugadorTurno().nombre);
                                        } else {
                                            if (game.tablero.cuatroEnRaya(game.obtenerJugadorTurno()) == true) {
                                                //gana la IA
                                                winnerIA.setVisible(true);
                                                labelTemporal.setText(game.obtenerJugadorTurno().nombre);
                                                tabla.setEnabled(false);
                                                musicaPrincipal.stop();
                                                gameOver.setFramePosition(0);
                                                gameOver.start();
                                            } else {
                                                nowinner.setVisible(true);
                                                tabla.setEnabled(false);

                                            }
                                        }

                                    }
                                }
                            }

                        });
                    }
                });


            }

        };
        partida1.addActionListener(Empezar);


        //Menu Ranking
        MenuItem partida2 = new MenuItem("Ranking", new MenuShortcut(KeyEvent.VK_M));
        ActionListener Ranking = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickMenu.setFramePosition(0);
                clickMenu.start();
                try {
                    Vector<String> nombreColumnas = new Vector<>();
                    nombreColumnas.add("NOMBRE");
                    nombreColumnas.add("PARTIDAS JUGADAS");
                    nombreColumnas.add("VICTORIAS");
                    nombreColumnas.add("DERROTAS");
                    nombreColumnas.add("PUNTUACION TOTAL");

                    Ranking ranking = new Ranking();
                    Vector<Vector<Object>> datos = ranking.obtenerDatosRanking();

                    //Creo una tabla por defecto con todos los campos no editables
                    DefaultTableModel tablaPorDefecto = new DefaultTableModel(datos, nombreColumnas) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };

                    JTable tabla = new JTable(tablaPorDefecto);
                    tabla.setFont(new Font("Serif", Font.PLAIN, 20));
                    tabla.setRowHeight(30);
                    tabla.getTableHeader().setBackground(Color.BLUE);
                    tabla.getTableHeader().setForeground(Color.WHITE);

                    JFrame ventanaRanking = new JFrame("RANKING 4 EN RAYA");
                    ventanaRanking.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


                    JLabel labelBuscar = new JLabel("Buscar jugador:");
                    JTextField inputBuscar = new JTextField(15);
                    inputBuscar.addKeyListener(new KeyAdapter() {
                        public void keyReleased(KeyEvent e) {
                            String val = inputBuscar.getText();
                            TableRowSorter<TableModel> trs = new TableRowSorter<>(tabla.getModel());
                            tabla.setRowSorter(trs);
                            trs.setRowFilter(RowFilter.regexFilter("(?i)" + val));
                        }
                    });

                    JPanel panelBusqueda = new JPanel();
                    panelBusqueda.add(labelBuscar);
                    panelBusqueda.add(inputBuscar);

                    //Añado el panel
                    ventanaRanking.add(panelBusqueda, BorderLayout.NORTH);

                    //Añado la tabla
                    ventanaRanking.add(new JScrollPane(tabla));
                    ventanaRanking.setSize(800, 500);
                    ventanaRanking.setLocationRelativeTo(null);
                    ventanaRanking.setVisible(true);
                } catch (Exception ex) {
                    System.err.println("Error al acceder a la base de datos" + ex);
                    System.exit(0);
                }
            }
        };
        partida2.addActionListener(Ranking);

        //Menú salir

        MenuItem partida3 = new MenuItem("Salir", new MenuShortcut(KeyEvent.VK_W));
        ActionListener Salir = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        partida3.addActionListener(Salir);

        //Ayuda

        MenuItem help1 = new MenuItem("Reglas", new MenuShortcut(KeyEvent.VK_A));
        ActionListener ayuda1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialog ayuda1 = new Dialog(CuatroEnRaya.this, "Reglas");
                ayuda1.setSize(915, 600);
                ayuda1.setResizable(false);
                ayuda1.setTitle("¿CÓMO JUGAR?");
                ayuda1.setBackground(Color.BLACK);
                ayuda1.setLocationRelativeTo(null);
                ayuda1.setLayout(null);
                ayuda1.setVisible(true);

                ayuda1.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        ayuda1.setVisible(false);
                    }
                });


                Imagen fondoReglas = new Imagen("/imagen/reglas.png");
                fondoReglas.setLayout(null);
                fondoReglas.setBounds(0, 0, 1000, 600);
                ayuda1.add(fondoReglas);


            }
        };
        help1.addActionListener(ayuda1);


        MenuItem help2 = new MenuItem("Acerca de", new MenuShortcut(KeyEvent.VK_B));
        ActionListener ayuda2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialog ayuda2 = new Dialog(CuatroEnRaya.this, "Acerca de");
                ayuda2.setSize(453, 270);
                ayuda2.setResizable(false);
                ayuda2.setTitle("Acerca de");
                ayuda2.setBackground(Color.BLACK);
                ayuda2.setLocationRelativeTo(null);
                ayuda2.setLayout(null);
                ayuda2.setVisible(true);

                ayuda2.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        ayuda2.setVisible(false);
                    }
                });

                Imagen fondoAcercade = new Imagen("/imagen/acercade.png");
                fondoAcercade.setLayout(null);
                fondoAcercade.setBounds(0, 20, 500, 700);
                ayuda2.add(fondoAcercade);


            }
        };
        help2.addActionListener(ayuda2);

        //Items del submenu
        partida.add(partida1);
        partida.addSeparator();
        partida.add(partida2);
        partida.addSeparator();
        partida.add(partida3);


        ayuda.add(help1);
        ayuda.addSeparator();
        ayuda.add(help2);


        bar.add(partida);
        bar.add(ayuda);
        //Creo el menú
        setMenuBar(bar);

        principal.setLayout(null);
        //principal.setBackground(Color.RED);
        principal.setBounds(0, 45, 800, 540);
        add(principal);

        //Fondo
        Imagen fondo = new Imagen("/imagen/principal.png");
        fondo.setSize(800, 540);
        fondo.setLocation(0, 0);
        fondo.setVisible(true);
        principal.add(fondo);


        //Botones MenuPrincipal

        Boton comenzar = new Boton("/imagen/comenzar.png");
        comenzar.setBounds(350, 300, 100, 50);
        fondo.add(comenzar);
        comenzar.addActionListener(Empezar);


        Boton ranking = new Boton("/imagen/ranking.png");
        ranking.setBounds(350, 338, 100, 77);
        fondo.add(ranking);
        ranking.addActionListener(Ranking);


        Boton salir = new Boton("/imagen/salir.png");
        salir.setBounds(350, 407, 100, 50);
        fondo.add(salir);
        salir.addActionListener(Salir);


    }

    /**
     * Carga un archivo de audio en un objeto Clip.
     *
     * @param clip El objeto Clip donde se cargará el audio.
     * @param ruta La ruta del archivo de audio.
     */
    public void cargarAudio(Clip clip, String ruta) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(ruta));
            clip.open(audioInputStream);
        } catch (Exception ex) {
            System.err.println("Error al cargar el audio " + ex);
        }
    }


}
