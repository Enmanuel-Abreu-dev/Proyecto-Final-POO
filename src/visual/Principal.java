package visual;

import java.awt.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Principal extends JDialog {

    private static final long serialVersionUID = 1L;

    // Paleta del proyecto
    private static final Color AZUL_OSCURO    = new Color(22, 58, 95);
    private static final Color AZUL_PRINCIPAL = new Color(37, 99, 166);
    private static final Color TEXTO_OSCURO   = new Color(31, 41, 55);
    private static final Color BORDE_SUAVE    = new Color(226, 232, 240);

    // Referencia a la imagen que se va a dibujar como fondo
    private Image imagenFondo;

    // Panel con paintComponent sobreescrito: aqui es donde se "pinta" la imagen
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

    private RoundedTextField textField;
    private JComboBox comboBox;

    // ==========================================================
    // Datos de ejemplo del usuario logged in (solo para la demo
    // visual). Cambiando este booleano se puede ver como se
    // vería el panel tanto para una Persona como para una Empresa.
    // ==========================================================
    private final boolean esEmpresa = true; // true = Institucion logueada, false = Persona logueada
    private final String nombreMostrado = esEmpresa ? "Tecnología Global SRL" : "Juan Pérez";
    private final String rutaFotoRecurso = esEmpresa ? "/imagenes/logoEmpresaGenerico.png" : "/imagenes/fotoPersonaGenerico.png";
    private final String inicialesPlaceholder = esEmpresa ? "TG" : "JP";

    // Panel-botón de perfil y su panel desplegable (para poder alternar setVisible)
    private RoundedPanel panelPerfil;
    private RoundedPanel panelDesplegable;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Principal dialog = new Principal();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Principal() {
        setBounds(100, 100, 450, 300);
        Dimension dim = getToolkit().getScreenSize();
        setSize(dim.width, dim.height);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Cargar la imagen desde los recursos del proyecto (carpeta /imagenes/)
        URL rutaImagen = getClass().getResource("/imagenes/fondoPrincipal.png");
        if (rutaImagen != null) {
            imagenFondo = new ImageIcon(rutaImagen).getImage();
        }

        JLayeredPane layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane, BorderLayout.CENTER);
        layeredPane.setLayout(new BorderLayout(0, 0));

        // El panelFondo (con la imagen) ocupa todo el espacio disponible
        layeredPane.add(panelFondo, BorderLayout.CENTER);
        panelFondo.setLayout(null);

        // ==========================================================
        // Campo "Cargo o area" con icono de maletin a la izquierda
        // ==========================================================
        textField = new RoundedTextField(90);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField.setBackground(new Color(255, 255, 255));
        textField.setBounds(299, 282, 497, 80);
        textField.setBorder(new EmptyBorder(5, 55, 5, 12));
        panelFondo.add(textField);
        textField.setColumns(10);

        agregarIcono("/imagenes/iconoCargo.png", textField.getX(), textField.getY(), textField.getHeight());
        comboBox = new JComboBox();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
        comboBox.setBackground(new Color(255, 255, 255));
        comboBox.setBounds(739, 282, 497, 80);
        panelFondo.add(comboBox);

        agregarIcono("/imagenes/iconoLugar.png", comboBox.getX(), comboBox.getY(), comboBox.getHeight());
		
        RoundedPanel panel = new RoundedPanel(90, new Color(255, 255, 255));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(1102, 282, 497, 80);
        panelFondo.add(panel);
        panel.setLayout(null);

        RoundedButton btnBuscar = new RoundedButton("BUSCAR EMPLEOS", 80);
        btnBuscar.setBounds(167, 10, 302, 60);
        btnBuscar.setBackground(new Color(22, 58, 95));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnBuscar.setFocusPainted(false);

        URL rutaLupa = getClass().getResource("/imagenes/iconoLupa.png");
        if (rutaLupa != null) {
            ImageIcon lupa = new ImageIcon(rutaLupa);
            Image lupaEscalada = lupa.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnBuscar.setIcon(new ImageIcon(lupaEscalada));
            btnBuscar.setIconTextGap(10);
            btnBuscar.setHorizontalTextPosition(SwingConstants.RIGHT);
            btnBuscar.setVerticalTextPosition(SwingConstants.CENTER);
        }

        panel.add(btnBuscar);

        // ==========================================================
        // Panel-botón de perfil (foto/logo + nombre + "Ver Perfil"),
        // arriba a la derecha. Al hacer clic, alterna la visibilidad
        // de panelDesplegable (otro JPanel superpuesto, sin ventanas
        // ni menús aparte) para simular el flujo tipo app.
        // ==========================================================
        agregarPanelPerfil(dim.width);

        // A partir de aqui, cualquier boton/label/campo que quieras agregar
        // se añade con panelFondo.add(...) para que quede pintado ENCIMA
        // de la imagen de fondo.
    }

    private void agregarIcono(String rutaRecurso, int xComponente, int yComponente, int altoComponente) {
        URL ruta = getClass().getResource(rutaRecurso);
        if (ruta == null) {
            return; 
        }

        int tamanoIcono = 28;
        ImageIcon icono = new ImageIcon(ruta);
        Image imgEscalada = icono.getImage().getScaledInstance(tamanoIcono, tamanoIcono, Image.SCALE_SMOOTH);
        JLabel lblIcono = new JLabel(new ImageIcon(imgEscalada));
        lblIcono.setOpaque(false);
        lblIcono.setBounds(
                xComponente + 15,
                yComponente + (altoComponente - tamanoIcono) / 2,
                tamanoIcono,
                tamanoIcono
        );

        panelFondo.add(lblIcono);
        panelFondo.setComponentZOrder(lblIcono, 0);
    }

    /**
     * Construye el panel superior derecho: FOTO/LOGO | NOMBRE / "Ver Perfil".
     * El contenido (nombre, foto, iniciales) se arma a partir de
     * nombreMostrado / rutaFotoRecurso / inicialesPlaceholder, que varían
     * según si el usuario logueado es una Persona o una Institucion.
     * Es puramente visual: al hacer clic solo alterna setVisible(...)
     * de panelDesplegable, sin lógica de negocio ni ventanas aparte.
     */
    private void agregarPanelPerfil(int anchoVentana) {

        int anchoPanel = 300;
        int altoPanel = 70;
        int margen = 30;
        int xPanel = anchoVentana - anchoPanel - margen;
        int yPanel = 25;

        // --- Panel-botón (siempre visible) ---
        panelPerfil = new RoundedPanel(60, new Color(255, 255, 255));
        panelPerfil.setBackground(new Color(255, 255, 255));
        panelPerfil.setBounds(xPanel, yPanel, anchoPanel, altoPanel);
        panelPerfil.setLayout(null);
        panelPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelFondo.add(panelPerfil);
        panelFondo.setComponentZOrder(panelPerfil, 0);

        JLabel lblFoto = new JLabel(inicialesPlaceholder, SwingConstants.CENTER);
        lblFoto.setOpaque(true);
        lblFoto.setBackground(AZUL_PRINCIPAL);
        lblFoto.setForeground(Color.WHITE);
        lblFoto.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblFoto.setBounds(12, 12, 46, 46);
        lblFoto.setBorder(new LineBorder(AZUL_OSCURO, 1, true));
        panelPerfil.add(lblFoto);

        URL rutaFoto = getClass().getResource(rutaFotoRecurso);
        if (rutaFoto != null) {
            ImageIcon icono = new ImageIcon(rutaFoto);
            Image escalada = icono.getImage().getScaledInstance(46, 46, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(escalada));
            lblFoto.setText("");
        }

        JLabel lblNombre = new JLabel(nombreMostrado);
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNombre.setForeground(TEXTO_OSCURO);
        lblNombre.setBounds(70, 12, anchoPanel - 100, 22);
        panelPerfil.add(lblNombre);

        JLabel lblVerPerfil = new JLabel("Ver Perfil  ▾");
        lblVerPerfil.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblVerPerfil.setForeground(AZUL_PRINCIPAL);
        lblVerPerfil.setBounds(70, 34, anchoPanel - 100, 20);
        panelPerfil.add(lblVerPerfil);

        // --- Panel desplegable A PANTALLA COMPLETA (oculto al inicio) ---
        // Cubre todo panelFondo cuando se activa, como una "pantalla" nueva
        // dentro de la misma ventana. Vacío/de ejemplo por ahora; se deja
        // listo para que se rellene después con lo que necesites.
        panelDesplegable = new RoundedPanel(0, new Color(255, 255, 255));
        panelDesplegable.setBackground(new Color(255, 255, 255));
        panelDesplegable.setBounds(0, 0, anchoVentana, getToolkit().getScreenSize().height);
        panelDesplegable.setLayout(null);
        panelDesplegable.setVisible(false);
        panelFondo.add(panelDesplegable);
        panelFondo.setComponentZOrder(panelDesplegable, 0);
        // El botón de perfil debe quedar SIEMPRE por encima del panel a pantalla
        // completa, para poder volver a hacer clic y cerrarlo.
        panelFondo.setComponentZOrder(panelPerfil, 0);

        // Botón para cerrar el panel a pantalla completa y volver a la vista anterior
        RoundedButton btnCerrarPanel = new RoundedButton("✕  CERRAR", 40);
        btnCerrarPanel.setBounds(1700, 100, 160, 45);
        btnCerrarPanel.setBackground(AZUL_OSCURO);
        btnCerrarPanel.setForeground(Color.WHITE);
        btnCerrarPanel.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCerrarPanel.setFocusPainted(false);
        btnCerrarPanel.addActionListener(e -> panelDesplegable.setVisible(false));
        panelDesplegable.add(btnCerrarPanel);

        // --- Alternar visibilidad al hacer clic (setVisible, sin popups ni ventanas) ---
        MouseAdapter listenerPanelPerfil = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelDesplegable.setVisible(!panelDesplegable.isVisible());
                panelFondo.setComponentZOrder(panelDesplegable, panelDesplegable.isVisible() ? 0 : panelFondo.getComponentCount() - 1);
                panelFondo.setComponentZOrder(panelPerfil, 0);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                panelPerfil.setBackground(new Color(240, 244, 248));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                panelPerfil.setBackground(new Color(255, 255, 255));
            }
        };

        panelPerfil.addMouseListener(listenerPanelPerfil);
        lblFoto.addMouseListener(listenerPanelPerfil);
        lblNombre.addMouseListener(listenerPanelPerfil);
        lblVerPerfil.addMouseListener(listenerPanelPerfil);
    }
}