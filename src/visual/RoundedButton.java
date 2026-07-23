package visual;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    private static final long serialVersionUID = 1L;
    private int radio;

    public RoundedButton(String texto, int radio) {
        super(texto);
        this.radio = radio;
        setContentAreaFilled(false); // evita que Swing pinte el fondo cuadrado
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // opcional: dibuja un borde sutil en vez de nada
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground().darker());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radio, radio);
        g2.dispose();
    }
}