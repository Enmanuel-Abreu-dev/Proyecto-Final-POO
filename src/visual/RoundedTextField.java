package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RoundedTextField extends JTextField {

    private static final long serialVersionUID = 1L;
    private int radio;
    private Color colorBorde;

    public RoundedTextField(int radio) {
        super();
        this.radio = radio;
        this.colorBorde = new Color(180, 180, 180); // gris claro por defecto
        setOpaque(false);
        setBorder(new EmptyBorder(5, 12, 5, 12)); // espacio interno (padding)
    }

    public RoundedTextField(int radio, Color colorBorde) {
        this(radio);
        this.colorBorde = colorBorde;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radio, radio);
        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(hasFocus() ? new Color(37, 99, 166) : colorBorde); // azul principal #2563A6 al enfocar
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radio, radio);
        g2.dispose();
    }
}
