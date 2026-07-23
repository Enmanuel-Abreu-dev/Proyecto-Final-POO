package visual;

import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

/**
 * JLabel con esquinas redondeadas. Recorta tanto el fondo (color solido)
 * como el icono/foto que se le asigne con setIcon(...), para que la
 * imagen tambien salga con bordes redondeados.
 * Mismo patron visual que RoundedButton / RoundedPanel / RoundedTextField.
 */
public class RoundedLabel extends JLabel {

    private static final long serialVersionUID = 1L;

    private final int radio;

    public RoundedLabel() {
        this(20);
    }

    public RoundedLabel(int radio) {
        super();
        this.radio = radio;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Recorta todo lo que se pinte (fondo + icono) a la forma redondeada
        Shape forma = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radio, radio);
        g2.setClip(forma);

        // Fondo (color de "CARGAR" o del cuadro vacio)
        if (getBackground() != null) {
            g2.setColor(getBackground());
            g2.fill(forma);
        }

        // Pinta el icono/foto y el texto ya recortados por el clip
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Sin borde adicional; si quieres un contorno, se puede dibujar aqui
        // con g2.draw(forma) usando un Color y Stroke especificos.
    }

    @Override
    public boolean contains(int x, int y) {
        Shape forma = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radio, radio);
        return forma.contains(x, y);
    }
}