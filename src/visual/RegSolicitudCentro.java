package visual;

import java.awt.*;

import javax.swing.*;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logico.BolsaTrabajo;
import logico.Oferta;
import logico.SolicitudCentro;
import logico.Persona;
import logico.Institucion;

public class RegSolicitudCentro extends JDialog {

    private static final long serialVersionUID = 1L;
    private Image imagenFondo;
    private Oferta ofertaSeleccionada;
    private Persona personaSeleccionada;

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
                        this);
            }
        }
    };

    private RoundedTextField textFieldPersona;
    private RoundedTextField textFieldCentro;
    private JEditorPane editorPaneMensaje;
    private VentanaMatcheo ventanaOrigen;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            RegSolicitudCentro dialog = new RegSolicitudCentro(null, null,null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     * 
     * @param oferta  la oferta asociada a la solicitud (se usa para obtener el
     *                centro/empresa que la envia)
     * @param persona la persona seleccionada, sobre la cual se envia la
     *                solicitud
     */
    public RegSolicitudCentro(Oferta oferta, Persona persona, VentanaMatcheo ventanaOrigen) {
        this.ofertaSeleccionada = oferta;
        this.personaSeleccionada = persona;
        this.ventanaOrigen = ventanaOrigen;
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

        JLabel lblPersona = new JLabel("PERSONA SELECCIONADA:");
        lblPersona.setForeground(Color.WHITE);
        lblPersona.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPersona.setBounds(26, 27, 250, 19);
        panel.add(lblPersona);

        textFieldPersona = new RoundedTextField(20);
        textFieldPersona.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldPersona.setColumns(10);
        textFieldPersona.setBackground(new Color(153, 255, 255));
        textFieldPersona.setEditable(false);
        textFieldPersona.setBounds(26, 56, 395, 28);
        if (personaSeleccionada != null) {
            textFieldPersona.setText(personaSeleccionada.getNombre() + " " + personaSeleccionada.getApellido());
        }
        panel.add(textFieldPersona);

        JLabel lblCentro = new JLabel("CENTRO:");
        lblCentro.setForeground(Color.WHITE);
        lblCentro.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCentro.setBounds(26, 107, 173, 19);
        panel.add(lblCentro);

        textFieldCentro = new RoundedTextField(20);
        textFieldCentro.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldCentro.setColumns(10);
        textFieldCentro.setBackground(new Color(153, 255, 255));
        textFieldCentro.setEditable(false);
        textFieldCentro.setBounds(26, 133, 395, 28);
        if (ofertaSeleccionada != null && ofertaSeleccionada.getMyEmpresa() != null) {
            textFieldCentro.setText(ofertaSeleccionada.getMyEmpresa().getNombre());
        } else if (BolsaTrabajo.getInstance().getUsuarioActual() != null
                && BolsaTrabajo.getInstance().getUsuarioActual().getMyInstitucion() != null) {
            textFieldCentro.setText(BolsaTrabajo.getInstance().getUsuarioActual().getMyInstitucion().getNombre());
        }
        panel.add(textFieldCentro);

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
        URL rutaIcono = getClass().getResource("/imagenes/iconoSolicitudEmpl.png");
        if (rutaIcono != null) {
            ImageIcon iconoOriginal = new ImageIcon(rutaIcono);
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                    450, 350, Image.SCALE_SMOOTH);
            lblIconoSolicitud.setIcon(new ImageIcon(imagenEscalada));
        }
        lblIconoSolicitud.setOpaque(false);
        lblIconoSolicitud.setHorizontalAlignment(SwingConstants.CENTER);
        lblIconoSolicitud.setBounds(35, 10, 350, 350);
        panel_1.add(lblIconoSolicitud);

        RoundedButton btnEnviarSolicitud = new RoundedButton("ENVIAR SOLICITUD", 20);
        btnEnviarSolicitud.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (editorPaneMensaje.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No puedes crear una Solicitud dejando campos vacios.");
                    return;
                }

                Institucion centro = (ofertaSeleccionada != null) ? ofertaSeleccionada.getMyEmpresa(): (BolsaTrabajo.getInstance().getUsuarioActual() != null ? BolsaTrabajo.getInstance().getUsuarioActual().getMyInstitucion(): null);

                SolicitudCentro soli = new SolicitudCentro(BolsaTrabajo.getInstance().generarIdSolicitud(),
                        ofertaSeleccionada, centro, personaSeleccionada, editorPaneMensaje.getText());

                BolsaTrabajo.getInstance().registrarSolicitud(soli);
                personaSeleccionada.getSolicitudCentro().add(soli);
                BolsaTrabajo.getInstance().actulizarCantOferta(oferta);

                if (ventanaOrigen != null) {
                    ventanaOrigen.eliminarCandidato(personaSeleccionada);
                }

                JOptionPane.showMessageDialog(null, "Solicitud Registrada Exitosamente",
                        "Solicitud", JOptionPane.INFORMATION_MESSAGE);

                dispose();

            }
        });

        btnEnviarSolicitud.setForeground(Color.WHITE);
        btnEnviarSolicitud.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEnviarSolicitud.setBackground(new Color(255, 153, 0));
        btnEnviarSolicitud.setBounds(10, 358, 237, 49);
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
    }
}