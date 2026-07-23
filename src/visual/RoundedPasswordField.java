package visual;

import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

/**
 * JPasswordField con esquinas redondeadas.
 * Mismo patron visual que RoundedButton / RoundedPanel / RoundedField.
 */
public class RoundedPasswordField extends JPasswordField {

    private static final long serialVersionUID = 1L;

    private final int radio;

    public RoundedPasswordField() {
        this(20);
    }

    public RoundedPasswordField(int radio) {
        super();
        this.radio = radio;
        setOpaque(false);
        setBorder(new EmptyBorder(8, 14, 8, 14));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Sin borde adicional; el color de fondo redondeado ya delimita el campo.
    }

    @Override
    public boolean contains(int x, int y) {
        Shape forma = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radio, radio);
        return forma.contains(x, y);
    }
}