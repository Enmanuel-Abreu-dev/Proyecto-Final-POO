package visual;

import java.awt.*;

import javax.swing.*;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import logico.Oferta;

public class RegSolicitudEmp extends JDialog {

    private static final long serialVersionUID = 1L;
    private Image imagenFondo;
    private Oferta ofertaSeleccionada;

    private final JPanel panelFondo = new JPanel() {

       private static final long serialVersionUID = 1L;

       @Override
       protected void paintComponent(Graphics g) {
          super.paintComponent(g);

          if (imagenFondo != null) {
             g.drawImage(
                   imagenFondo,
                   0,
                   0,
                   getWidth(),
                   getHeight(),
                   this
             );
          }
       }
    };

    private RoundedTextField textFieldOferta;
    private RoundedTextField textFieldRangoSalarial;
    private JComboBox comboBoxModalidad;
    private JEditorPane editorPaneMensaje;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
       try {
          RegSolicitudEmp dialog = new RegSolicitudEmp(null);
          dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
          dialog.setVisible(true);
       } catch (Exception e) {
          e.printStackTrace();
       }
    }

    /**
     * Create the dialog.
     * @param oferta la oferta ya seleccionada en ListOfertas, sobre la cual se postula
     */
    public RegSolicitudEmp(Oferta oferta) {
       this.ofertaSeleccionada = oferta;
       setIconImage(new ImageIcon(getClass().getResource("/imagenes/fondoRegOferta.png")).getImage());
       setTitle("REGISTRAR SOLICITUD");
       URL rutaImagen = getClass().getResource("/imagenes/fondoRegOferta.png");

       if (rutaImagen != null) {
          imagenFondo = new ImageIcon(rutaImagen).getImage();
       }

       getContentPane().setBackground(new Color(192, 192, 192));
       setBounds(100, 100, 450, 300);

       setSize(1280, 720);
       setLocationRelativeTo(null);

       JLayeredPane layeredPane = new JLayeredPane();
       getContentPane().add(layeredPane, BorderLayout.CENTER);
       layeredPane.setLayout(null);

       panelFondo.setBounds(0, 0, 1280, 720);
       layeredPane.add(panelFondo, JLayeredPane.DEFAULT_LAYER);
       panelFondo.setLayout(null);

       JPanel panel = new JPanel();
       panel.setBackground(new Color(25, 25, 112));
       panel.setBounds(106, 48, 450, 635);
       panelFondo.add(panel);
       panel.setLayout(null);

       editorPaneMensaje = new JEditorPane();
       editorPaneMensaje.setBackground(new Color(173, 216, 230));
       editorPaneMensaje.setBounds(26, 260, 395, 166);
       panel.add(editorPaneMensaje);

       JLabel lblOferta = new JLabel("OFERTA SELECCIONADA:");
       lblOferta.setForeground(Color.WHITE);
       lblOferta.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblOferta.setBounds(26, 27, 250, 19);
       panel.add(lblOferta);

       textFieldOferta = new RoundedTextField(20);
       textFieldOferta.setFont(new Font("Tahoma", Font.PLAIN, 14));
       textFieldOferta.setColumns(10);
       textFieldOferta.setBackground(new Color(153, 255, 255));
       textFieldOferta.setEditable(false);
       textFieldOferta.setBounds(26, 56, 395, 28);
       if (ofertaSeleccionada != null) {
          textFieldOferta.setText(ofertaSeleccionada.getPuesto() + " - " + ofertaSeleccionada.getMyEmpresa().getNombre());
       }
       panel.add(textFieldOferta);

       JLabel lblRangoSalarial = new JLabel("RANGO SALARIAL:");
       lblRangoSalarial.setForeground(Color.WHITE);
       lblRangoSalarial.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblRangoSalarial.setBounds(26, 107, 173, 19);
       panel.add(lblRangoSalarial);

       textFieldRangoSalarial = new RoundedTextField(20);
       textFieldRangoSalarial.setFont(new Font("Tahoma", Font.PLAIN, 14));
       textFieldRangoSalarial.setColumns(10);
       textFieldRangoSalarial.setBackground(new Color(153, 255, 255));
       textFieldRangoSalarial.setBounds(26, 133, 173, 28);
       panel.add(textFieldRangoSalarial);

       JLabel lblModalidad = new JLabel("MODALIDAD:");
       lblModalidad.setForeground(Color.WHITE);
       lblModalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblModalidad.setBounds(248, 107, 173, 19);
       panel.add(lblModalidad);

       comboBoxModalidad = new JComboBox();
       comboBoxModalidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
       comboBoxModalidad.setModel(new DefaultComboBoxModel(new String[] {"Modalidad ", "Tiempo Completo", "Medio Tiempo", "Beca / Practicas", "Por Horas"}));
       comboBoxModalidad.setBackground(new Color(102, 255, 255));
       comboBoxModalidad.setBounds(248, 133, 173, 29);
       panel.add(comboBoxModalidad);

       JLabel lblMensaje = new JLabel("MENSAJE:");
       lblMensaje.setForeground(Color.WHITE);
       lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblMensaje.setBounds(26, 231, 117, 19);
       panel.add(lblMensaje);

       JLabel lblNewLabel_1 = new JLabel("Registrar    Solicitud");
       lblNewLabel_1.setBounds(106, -14, 645, 72);
       panelFondo.add(lblNewLabel_1);
       lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 41));

       JPanel panel_1 = new JPanel();
       panel_1.setOpaque(false);
       panel_1.setBounds(566, 140, 450, 460);
       panelFondo.add(panel_1);
       panel_1.setLayout(null);

       JLabel lblIconoSolicitud = new JLabel();
       URL rutaIcono = getClass().getResource("/imagenes/iconoOfertaDeTrabajo.png");
       if (rutaIcono != null) {
          ImageIcon iconoOriginal = new ImageIcon(rutaIcono);
          Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                350, 350, Image.SCALE_SMOOTH);
          lblIconoSolicitud.setIcon(new ImageIcon(imagenEscalada));
       }
       lblIconoSolicitud.setOpaque(false);
       lblIconoSolicitud.setHorizontalAlignment(SwingConstants.CENTER);
       lblIconoSolicitud.setBounds(35, 10, 350, 350);
       panel_1.add(lblIconoSolicitud);

       RoundedButton btnEnviarSolicitud = new RoundedButton("ENVIAR SOLICITUD", 20);
       btnEnviarSolicitud.setForeground(Color.WHITE);
       btnEnviarSolicitud.setFont(new Font("Tahoma", Font.BOLD, 20));
       btnEnviarSolicitud.setBackground(new Color(255, 153, 0));
       btnEnviarSolicitud.setBounds(10, 358, 208, 49);
       panel_1.add(btnEnviarSolicitud);

       RoundedButton btnCancelar = new RoundedButton("CANCELAR", 20);
       btnCancelar.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent arg0) {
       		dispose();
       	}
       });
       btnCancelar.setForeground(Color.WHITE);
       btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
       btnCancelar.setBackground(new Color(255, 0, 51));
       btnCancelar.setBounds(273, 358, 177, 49);
       panel_1.add(btnCancelar);

       aplicarPlaceholder(textFieldRangoSalarial, "Ej: 25000-35000");
    }

    private void aplicarPlaceholder(JTextField campo, String textoEjemplo) {

        Color colorNormal = campo.getForeground();
        Color colorPlaceholder = Color.BLACK;

        campo.setText(textoEjemplo);
        campo.setForeground(colorPlaceholder);

        campo.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(textoEjemplo)) {
                    campo.setText("");
                    campo.setForeground(colorNormal);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText(textoEjemplo);
                    campo.setForeground(colorPlaceholder);
                }
            }
        });
    }
}