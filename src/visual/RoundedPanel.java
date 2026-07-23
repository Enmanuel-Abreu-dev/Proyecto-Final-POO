package visual;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private int radio;
    private Color colorFondo;
    private Color colorBorde;
    private boolean dibujarBorde;

    public RoundedPanel(int radio, Color colorFondo) {
        super();
        this.radio = radio;
        this.colorFondo = colorFondo;
        this.colorBorde = new Color(200, 200, 200);
        this.dibujarBorde = false;
        setOpaque(false); // clave: para que no pinte el fondo cuadrado por defecto
    }

    public RoundedPanel(int radio, Color colorFondo, Color colorBorde) {
        this(radio, colorFondo);
        this.colorBorde = colorBorde;
        this.dibujarBorde = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(colorFondo);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radio, radio);

        if (dibujarBorde) {
            g2.setColor(colorBorde);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radio, radio);
        }

        g2.dispose();
        super.paintComponent(g);
    }
}