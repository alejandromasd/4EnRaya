package clases;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;

/**
 * La clase Boton representa un botón con una imagen como contenido.
 * Extiende la clase Button de AWT y permite cargar una imagen desde un archivo y mostrarla en el botón.
 */
public class Boton extends Button {
    private BufferedImage imagen = null;

    /**
     * Constructor de la clase Boton.
     *
     * @param s La ruta de la imagen a cargar para el botón.
     */
    public Boton(String s) {
        super();
        URL url = getClass().getResource(s);
        if (url != null) {
            try {
                imagen = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sobrescribe el método paint para dibujar la imagen en el botón.
     *
     * @param g El contexto gráfico en el que se dibuja la imagen.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (imagen != null) {
            // Escalamos la imagen
            Image scaledImage = imagen.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            // Y la dibujamos
            g.drawImage(scaledImage, 0, 0, this);
        }
    }
}
