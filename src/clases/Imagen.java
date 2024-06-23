package clases;

import java.awt.*;
import java.net.URL;

/**
 * La clase Imagen extiende Panel y se utiliza para cargar y mostrar una imagen
 * desde un recurso proporcionado.
 */
public class Imagen extends Panel {
    Image foto;

    /**
     * Constructor de la clase Imagen.
     *
     * @param s La ruta del recurso de la imagen a cargar.
     */
    public Imagen(String s) {
        URL url1 = getClass().getResource(s);
        foto = Toolkit.getDefaultToolkit().getImage(url1);
    }

    /**
     * Sobrescribe el método paint para dibujar la imagen en el panel.
     *
     * @param g El contexto gráfico en el que dibujar la imagen.
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(foto, 0, 0, this);
    }
}
