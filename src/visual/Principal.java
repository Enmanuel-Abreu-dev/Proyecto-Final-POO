package visual;

import logico.BolsaTrabajo;
import logico.Usuario;
import logico.Institucion;
import logico.Persona;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JDialog {

    private static final long serialVersionUID = 1L;

    private static final Color AZUL_OSCURO = new Color(22, 58, 95);
    private static final Color AZUL_PRINCIPAL = new Color(37, 99, 166);
    private static final Color VERDE_AZULADO = new Color(15, 139, 141);
    private static final Color FONDO_GRIS = new Color(244, 246, 248);
    private static final Color TARJETA_BLANCA = Color.WHITE;
    private static final Color TEXTO_OSCURO = new Color(31, 41, 55);

    private Image imagenFondo;

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
    private RoundedPanel panelPerfil;
    private RoundedPanel panelDesplegable;
    private RoundedPanel panelContenido;
    private RoundedPanel panelInicio;
    private RoundedPanel panelPerfilUsuario;

    private final Usuario usuarioActual;
    private final boolean esEmpresa;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Principal dialog = new Principal(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     * @param usuario el usuario que inicio sesion (empresa o candidato)
     */
    public Principal(Usuario usuario) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				saveData();
			}
		});
		this.usuarioActual = usuario;
        this.esEmpresa = (usuarioActual != null && usuarioActual.getMyInstitucion() != null);
        
        setBounds(100, 100, 450, 300);
        Dimension dim = getToolkit().getScreenSize();
        setSize(dim.width, dim.height);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        URL rutaImagen = getClass().getResource("/imagenes/fondoPrincipal.png");
        if (rutaImagen != null) {
            imagenFondo = new ImageIcon(rutaImagen).getImage();
        }

        JLayeredPane layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane, BorderLayout.CENTER);
        layeredPane.setLayout(new BorderLayout(0, 0));

        layeredPane.add(panelFondo, BorderLayout.CENTER);
        panelFondo.setLayout(null);

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

        String nombreMostrado;
        String inicialesPlaceholder;
        String rutaImagenPerfil = null;

        if (esEmpresa) {
            Institucion miInstitucion = usuarioActual.getMyInstitucion();
            nombreMostrado = miInstitucion.getNombre();
            inicialesPlaceholder = obtenerIniciales(miInstitucion.getNombre());
            rutaImagenPerfil = miInstitucion.getRutaImagen();
        } else if (usuarioActual != null && usuarioActual.getMyPersona() != null) {
            Persona miPersona = usuarioActual.getMyPersona();
            nombreMostrado = miPersona.getNombre() + " " + miPersona.getApellido();
            inicialesPlaceholder = obtenerIniciales(miPersona.getNombre() + " " + miPersona.getApellido());
            rutaImagenPerfil = miPersona.getRutaImagen();
        } else {
            nombreMostrado = "Usuario";
            inicialesPlaceholder = "US";
        }

        int anchoPanelPerfil = 300;
        int altoPanelPerfil = 70;
        int margenPanelPerfil = 30;
        int xPanelPerfil = dim.width - anchoPanelPerfil - margenPanelPerfil;
        int yPanelPerfil = 25;

        panelPerfil = new RoundedPanel(60, new Color(255, 255, 255));
        panelPerfil.setBackground(new Color(255, 255, 255));
        panelPerfil.setBounds(xPanelPerfil, yPanelPerfil, anchoPanelPerfil, altoPanelPerfil);
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

        if (rutaImagenPerfil != null && new File(rutaImagenPerfil).exists()) {
            ImageIcon icono = new ImageIcon(rutaImagenPerfil);
            Image escalada = icono.getImage().getScaledInstance(46, 46, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(escalada));
            lblFoto.setText("");
        }

        panelPerfil.add(lblFoto);

        JLabel lblNombre = new JLabel(nombreMostrado);
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNombre.setForeground(TEXTO_OSCURO);
        lblNombre.setBounds(70, 12, anchoPanelPerfil - 100, 22);
        panelPerfil.add(lblNombre);

        JLabel lblVerPerfil = new JLabel("Ver Perfil");
        lblVerPerfil.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblVerPerfil.setForeground(AZUL_PRINCIPAL);
        lblVerPerfil.setBounds(70, 34, anchoPanelPerfil - 100, 20);
        panelPerfil.add(lblVerPerfil);


        panelDesplegable = new RoundedPanel(0, new Color(255, 255, 255));
        panelDesplegable.setBackground(new Color(255, 255, 255));
        panelDesplegable.setBounds(0, 0, dim.width, dim.height);
        panelDesplegable.setLayout(null);
        panelDesplegable.setVisible(false);
        panelFondo.add(panelDesplegable);
        panelFondo.setComponentZOrder(panelDesplegable, 0);
        panelFondo.setComponentZOrder(panelPerfil, 0);

        RoundedButton btnCerrarPanel = new RoundedButton("CERRAR", 40);
        btnCerrarPanel.setText("CERRAR");
        btnCerrarPanel.setBounds(1400, 40, 160, 45);
        btnCerrarPanel.setBackground(new Color(255, 0, 0));
        btnCerrarPanel.setForeground(Color.WHITE);
        btnCerrarPanel.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCerrarPanel.setFocusPainted(false);
        btnCerrarPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelDesplegable.setVisible(false);
            }
        });
        panelDesplegable.add(btnCerrarPanel);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0, 0, 102));
        panel_1.setBounds(0, 0, dim.width, 105);
        panelDesplegable.add(panel_1);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(153, 255, 255));
        panel_2.setBounds(0, 105, 220, dim.height);
        panelDesplegable.add(panel_2);
        panel_2.setLayout(null);

        int xContenido = 220;
        int yContenido = 105;
        int anchoContenido = dim.width - xContenido;
        int altoContenido = dim.height - yContenido;

        panelContenido = new RoundedPanel(0, FONDO_GRIS);
        panelContenido.setBackground(FONDO_GRIS);
        panelContenido.setBounds(xContenido, yContenido, anchoContenido, altoContenido);
        panelContenido.setLayout(null);
        panelDesplegable.add(panelContenido);

        panelInicio = new RoundedPanel(0, FONDO_GRIS);
        panelInicio.setBackground(FONDO_GRIS);
        panelInicio.setBounds(0, 0, anchoContenido, altoContenido);
        panelInicio.setLayout(null);
        panelContenido.add(panelInicio);

        JLabel lblBienvenida = new JLabel("Bienvenido/a, " + nombreMostrado);
        lblBienvenida.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblBienvenida.setForeground(TEXTO_OSCURO);
        lblBienvenida.setBounds(40, 40, anchoContenido - 80, 40);
        panelInicio.add(lblBienvenida);

        JLabel lblSubBienvenida = new JLabel(esEmpresa
                ? "Este es el resumen de tu actividad como empresa."
                : "Este es el resumen de tu actividad como candidato.");
        lblSubBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSubBienvenida.setForeground(new Color(120, 128, 138));
        lblSubBienvenida.setBounds(40, 85, anchoContenido - 80, 25);
        panelInicio.add(lblSubBienvenida);

        int anchoTarjetaResumen = (anchoContenido - 100) / 3;

        if (esEmpresa) {
            agregarTarjetaResumen(panelInicio, "OFERTAS ACTIVAS", "5", VERDE_AZULADO, 40, 140, anchoTarjetaResumen);
            agregarTarjetaResumen(panelInicio, "SOLICITUDES PENDIENTES", "12", AZUL_PRINCIPAL, 60 + anchoTarjetaResumen, 140, anchoTarjetaResumen);
            agregarTarjetaResumen(panelInicio, "CANDIDATOS NUEVOS", "8", AZUL_OSCURO, 80 + anchoTarjetaResumen * 2, 140, anchoTarjetaResumen);
        } else {
            agregarTarjetaResumen(panelInicio, "SOLICITUDES ENVIADAS", "3", VERDE_AZULADO, 40, 140, anchoTarjetaResumen);
            agregarTarjetaResumen(panelInicio, "EN PROCESO", "1", AZUL_PRINCIPAL, 60 + anchoTarjetaResumen, 140, anchoTarjetaResumen);
            agregarTarjetaResumen(panelInicio, "OFERTAS RECOMENDADAS", "6", AZUL_OSCURO, 80 + anchoTarjetaResumen * 2, 140, anchoTarjetaResumen);
        }

        RoundedPanel tarjetaAccesos = new RoundedPanel(30, TARJETA_BLANCA, new Color(225, 228, 232));
        tarjetaAccesos.setBackground(TARJETA_BLANCA);
        tarjetaAccesos.setBounds(40, 290, anchoContenido - 80, 200);
        tarjetaAccesos.setLayout(null);
        panelInicio.add(tarjetaAccesos);

        JLabel lblTituloAccesos = new JLabel(esEmpresa ? "ACCESOS RAPIDOS" : "RECOMENDACIONES PARA TI");
        lblTituloAccesos.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTituloAccesos.setForeground(TEXTO_OSCURO);
        lblTituloAccesos.setBounds(25, 20, 400, 25);
        tarjetaAccesos.add(lblTituloAccesos);

        JLabel lblTextoAccesos = new JLabel("<html>" + (esEmpresa
                ? "Utiliza el menu lateral para publicar nuevas ofertas, revisar<br>las solicitudes recibidas o consultar el listado de candidatos."
                : "Explora las ofertas disponibles desde el buscador de arriba<br>y revisa el estado de tus solicitudes en cualquier momento.")
                + "</html>");
        lblTextoAccesos.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTextoAccesos.setForeground(new Color(90, 98, 108));
        lblTextoAccesos.setBounds(25, 55, anchoContenido - 130, 100);
        tarjetaAccesos.add(lblTextoAccesos);

        panelPerfilUsuario = new RoundedPanel(0, FONDO_GRIS);
        panelPerfilUsuario.setBackground(FONDO_GRIS);
        panelPerfilUsuario.setBounds(0, 0, anchoContenido, altoContenido);
        panelPerfilUsuario.setLayout(null);
        panelPerfilUsuario.setVisible(false);
        panelContenido.add(panelPerfilUsuario);

        RoundedPanel tarjetaEncabezado = new RoundedPanel(30, TARJETA_BLANCA, new Color(225, 228, 232));
        tarjetaEncabezado.setBackground(TARJETA_BLANCA);
        tarjetaEncabezado.setBounds(40, 40, anchoContenido - 80, 190);
        tarjetaEncabezado.setLayout(null);
        panelPerfilUsuario.add(tarjetaEncabezado);

        RoundedLabel lblLogoPerfil = new RoundedLabel(20);
        lblLogoPerfil.setHorizontalAlignment(JLabel.CENTER);
        lblLogoPerfil.setBackground(AZUL_PRINCIPAL);
        lblLogoPerfil.setForeground(Color.WHITE);
        lblLogoPerfil.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblLogoPerfil.setBounds(30, 30, 130, 130);

        if (rutaImagenPerfil != null && new File(rutaImagenPerfil).exists()) {
            ImageIcon icono = new ImageIcon(rutaImagenPerfil);
            Image escalada = icono.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
            lblLogoPerfil.setIcon(new ImageIcon(escalada));
        } else {
            lblLogoPerfil.setText(inicialesPlaceholder);
        }

        tarjetaEncabezado.add(lblLogoPerfil);

        JLabel lblNombrePerfil = new JLabel(nombreMostrado);
        lblNombrePerfil.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblNombrePerfil.setForeground(TEXTO_OSCURO);
        lblNombrePerfil.setBounds(185, 32, 500, 34);
        tarjetaEncabezado.add(lblNombrePerfil);

        JLabel lblSubtituloPerfil = new JLabel(esEmpresa
                ? (usuarioActual.getMyInstitucion().isPrivado() ? "SECTOR PRIVADO" : "SECTOR PUBLICO")
                : "CANDIDATO");
        lblSubtituloPerfil.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSubtituloPerfil.setForeground(AZUL_PRINCIPAL);
        lblSubtituloPerfil.setBounds(185, 68, 500, 22);
        tarjetaEncabezado.add(lblSubtituloPerfil);

        JLabel lblEstadoPerfil = new JLabel("CUENTA VERIFICADA");
        lblEstadoPerfil.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblEstadoPerfil.setForeground(VERDE_AZULADO);
        lblEstadoPerfil.setBounds(185, 96, 300, 20);
        tarjetaEncabezado.add(lblEstadoPerfil);

        RoundedButton btnEditarPerfil = new RoundedButton("EDITAR PERFIL", 30);
        btnEditarPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (esEmpresa) {
                    RegEmpresa editEmpresa = new RegEmpresa(usuarioActual.getMyInstitucion());
                    editEmpresa.setVisible(true);
                    editEmpresa.setModal(true);
                } else {
                    RegPersona editPersona = new RegPersona(usuarioActual.getMyPersona());
                    editPersona.setVisible(true);
                    editPersona.setModal(true);
                }
            }
        });
        btnEditarPerfil.setBackground(AZUL_OSCURO);
        btnEditarPerfil.setForeground(Color.WHITE);
        btnEditarPerfil.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnEditarPerfil.setBounds(185, 130, 180, 40);
        tarjetaEncabezado.add(btnEditarPerfil);

        RoundedPanel tarjetaDatos = new RoundedPanel(30, TARJETA_BLANCA, new Color(225, 228, 232));
        tarjetaDatos.setBackground(TARJETA_BLANCA);
        tarjetaDatos.setBounds(40, 250, (anchoContenido - 100) / 2, 300);
        tarjetaDatos.setLayout(null);
        panelPerfilUsuario.add(tarjetaDatos);

        JLabel lblTituloDatos = new JLabel(esEmpresa ? "INFORMACION DE LA EMPRESA" : "INFORMACION PERSONAL");
        lblTituloDatos.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTituloDatos.setForeground(TEXTO_OSCURO);
        lblTituloDatos.setBounds(25, 20, 350, 25);
        tarjetaDatos.add(lblTituloDatos);

        if (esEmpresa) {
            Institucion miInstitucion = usuarioActual.getMyInstitucion();
            agregarFilaPerfil(tarjetaDatos, "RNC:", miInstitucion.getRNC(), 60);
            agregarFilaPerfil(tarjetaDatos, "PAIS:", miInstitucion.getPais(), 105);
            agregarFilaPerfil(tarjetaDatos, "DIRECCION:", miInstitucion.getDireccion(), 150);
            agregarFilaPerfil(tarjetaDatos, "TELEFONO:", miInstitucion.getTelefono(), 195);
            agregarFilaPerfil(tarjetaDatos, "CORREO:", miInstitucion.getEmail(), 240);
        } else {
            Persona miPersona = usuarioActual.getMyPersona();
            agregarFilaPerfil(tarjetaDatos, "CEDULA:", miPersona.getCedula(), 60);
            agregarFilaPerfil(tarjetaDatos, "PAIS:", miPersona.getPais(), 105);
            agregarFilaPerfil(tarjetaDatos, "DIRECCION:", miPersona.getDireccion(), 150);
            agregarFilaPerfil(tarjetaDatos, "TELEFONO:", miPersona.getTelefono(), 195);
            agregarFilaPerfil(tarjetaDatos, "CORREO:", miPersona.getEmail(), 240);
        }

        RoundedPanel tarjetaResumenPerfil = new RoundedPanel(30, TARJETA_BLANCA, new Color(225, 228, 232));
        tarjetaResumenPerfil.setBackground(TARJETA_BLANCA);
        tarjetaResumenPerfil.setBounds(60 + (anchoContenido - 100) / 2, 250, (anchoContenido - 100) / 2, 300);
        tarjetaResumenPerfil.setLayout(null);
        panelPerfilUsuario.add(tarjetaResumenPerfil);

        JLabel lblTituloResumenPerfil = new JLabel("RESUMEN");
        lblTituloResumenPerfil.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTituloResumenPerfil.setForeground(TEXTO_OSCURO);
        lblTituloResumenPerfil.setBounds(25, 20, 350, 25);
        tarjetaResumenPerfil.add(lblTituloResumenPerfil);

        if (esEmpresa) {
            Institucion miInstitucion = usuarioActual.getMyInstitucion();
            agregarFilaPerfil(tarjetaResumenPerfil, "CANTIDAD DE EMPLEADOS:", String.valueOf(miInstitucion.getCantEmpleado()), 60);
            agregarFilaPerfil(tarjetaResumenPerfil, "OFERTAS ACTIVAS:", "5", 105);
            agregarFilaPerfil(tarjetaResumenPerfil, "SOLICITUDES RECIBIDAS:", "38", 150);
        } else {
            Persona miPersona = usuarioActual.getMyPersona();
            agregarFilaPerfil(tarjetaResumenPerfil, "DISPONIBLE PARA VIAJAR:", miPersona.isDispViajar() ? "SI" : "NO", 60);
            agregarFilaPerfil(tarjetaResumenPerfil, "DISPONIBLE PARA MUDARSE:", miPersona.isDispResidencia() ? "SI" : "NO", 105);
            agregarFilaPerfil(tarjetaResumenPerfil, "SITUACION LABORAL:", miPersona.isEmpleado() ? "EMPLEADO" : "DESEMPLEADO", 150);
        }

        RoundedButton btnCerrarSesion = new RoundedButton("CERRAR SESION", 30);
        btnCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginUsuario myUser = new LoginUsuario();
                myUser.setVisible(true);
                myUser.setModal(true);
            }
        });
        btnCerrarSesion.setBackground(new Color(255, 0, 0));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCerrarSesion.setBounds(25, 240, 220, 40);
        tarjetaResumenPerfil.add(btnCerrarSesion);

        // ---------------- Menu lateral: opciones segun el rol ----------------
        if (esEmpresa) {
            RoundedButton btnPublicarOferta = new RoundedButton("Publicar Oferta", 40);
            btnPublicarOferta.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panelInicio.setVisible(false);
                    panelPerfilUsuario.setVisible(false);
                    RegOferta newOferta = new RegOferta();
                    newOferta.setVisible(true);
                    newOferta.setModal(true);
                    panelInicio.setVisible(true);
                }
            });
            btnPublicarOferta.setForeground(Color.WHITE);
            btnPublicarOferta.setBackground(new Color(0, 0, 102));
            btnPublicarOferta.setFont(new Font("Tw Cen MT", Font.PLAIN, 26));
            btnPublicarOferta.setBounds(10, 37, 200, 128);
            panel_2.add(btnPublicarOferta);

            RoundedButton btnVerOfertas = new RoundedButton("Ver Mis Ofertas", 40);
            btnVerOfertas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panelInicio.setVisible(false);
                    panelPerfilUsuario.setVisible(false);
                    ListOfertasEmpresa myOfertas = new ListOfertasEmpresa(usuarioActual.getMyInstitucion());
                    myOfertas.setVisible(true);
                    myOfertas.setModal(true);
                    panelInicio.setVisible(true);
                }
            });
            btnVerOfertas.setForeground(Color.WHITE);
            btnVerOfertas.setFont(new Font("Tw Cen MT", Font.PLAIN, 22));
            btnVerOfertas.setBackground(new Color(0, 0, 102));
            btnVerOfertas.setBounds(10, 202, 200, 128);
            panel_2.add(btnVerOfertas);

            RoundedButton btnSolicitudesRecibidas = new RoundedButton("Solicitudes Recibidas", 40);
            btnSolicitudesRecibidas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panelInicio.setVisible(false);
                    panelPerfilUsuario.setVisible(false);
                    ListSolicitudesRecibidas mySolicitudes = new ListSolicitudesRecibidas(usuarioActual.getMyInstitucion());
                    mySolicitudes.setVisible(true);
                    mySolicitudes.setModal(true);
                    panelInicio.setVisible(true);
                }
            });
            btnSolicitudesRecibidas.setForeground(Color.WHITE);
            btnSolicitudesRecibidas.setFont(new Font("Tw Cen MT", Font.PLAIN, 19));
            btnSolicitudesRecibidas.setBackground(new Color(0, 0, 102));
            btnSolicitudesRecibidas.setBounds(10, 367, 200, 128);
            panel_2.add(btnSolicitudesRecibidas);

            RoundedButton btnCandidatos = new RoundedButton("Candidatos", 40);
            btnCandidatos.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panelInicio.setVisible(false);
                    panelPerfilUsuario.setVisible(false);
                    ListCandidatos myCandidatos = new ListCandidatos(usuarioActual.getMyInstitucion());
                    myCandidatos.setVisible(true);
                    myCandidatos.setModal(true);
                    panelInicio.setVisible(true);
                }
            });
            btnCandidatos.setForeground(Color.WHITE);
            btnCandidatos.setFont(new Font("Tw Cen MT", Font.PLAIN, 22));
            btnCandidatos.setBackground(new Color(0, 0, 102));
            btnCandidatos.setBounds(10, 532, 200, 128);
            panel_2.add(btnCandidatos);
        } else {
            RoundedButton btnBuscarOfertas = new RoundedButton("Buscar Ofertas", 40);
            btnBuscarOfertas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panelInicio.setVisible(false);
                    panelPerfilUsuario.setVisible(false);
                    ListOfertas myOfertas = new ListOfertas();
                    myOfertas.setVisible(true);
                    myOfertas.setModal(true);
                    panelInicio.setVisible(true);
                }
            });
            btnBuscarOfertas.setForeground(Color.WHITE);
            btnBuscarOfertas.setBackground(new Color(0, 0, 102));
            btnBuscarOfertas.setFont(new Font("Tw Cen MT", Font.PLAIN, 26));
            btnBuscarOfertas.setBounds(10, 37, 200, 128);
            panel_2.add(btnBuscarOfertas);

            RoundedButton btnMisSolicitudes = new RoundedButton("Mis Solicitudes", 40);
            btnMisSolicitudes.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    panelInicio.setVisible(false);
                    panelPerfilUsuario.setVisible(false);
                    ListSolicitudesEmp mySolicitudes = new ListSolicitudesEmp(usuarioActual.getMyPersona());
                    mySolicitudes.setVisible(true);
                    mySolicitudes.setModal(true);
                    panelInicio.setVisible(true);
                }
            });
            btnMisSolicitudes.setForeground(Color.WHITE);
            btnMisSolicitudes.setFont(new Font("Tw Cen MT", Font.PLAIN, 22));
            btnMisSolicitudes.setBackground(new Color(0, 0, 102));
            btnMisSolicitudes.setBounds(10, 202, 200, 128);
            panel_2.add(btnMisSolicitudes);
        }

        RoundedButton btnVerMiPerfil = new RoundedButton("Ver Mi Perfil", 40);
        btnVerMiPerfil.setText("Ver Mi Perfil");
        btnVerMiPerfil.setForeground(Color.WHITE);
        btnVerMiPerfil.setFont(new Font("Tw Cen MT", Font.PLAIN, 22));
        btnVerMiPerfil.setBackground(new Color(0, 0, 102));
        btnVerMiPerfil.setBounds(10, 697, 200, 128);
        btnVerMiPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelInicio.setVisible(false);
                panelPerfilUsuario.setVisible(true);
            }
        });
        panel_2.add(btnVerMiPerfil);

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

        // A partir de aqui, cualquier boton/label/campo que quieras agregar
        // se añade con panelFondo.add(...) para que quede pintado ENCIMA
        // de la imagen de fondo.
    }

    /**
     * Metodo de apoyo puramente visual: dibuja una tarjeta pequeña de
     * resumen (numero grande + etiqueta) como las del panel de Inicio.
     */
    private void agregarTarjetaResumen(JPanel contenedor, String etiqueta, String valor, Color color, int x, int y, int ancho) {
        RoundedPanel tarjeta = new RoundedPanel(25, TARJETA_BLANCA, new Color(225, 228, 232));
        tarjeta.setBackground(TARJETA_BLANCA);
        tarjeta.setBounds(x, y, ancho, 110);
        tarjeta.setLayout(null);
        contenedor.add(tarjeta);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Tahoma", Font.BOLD, 32));
        lblValor.setForeground(color);
        lblValor.setBounds(20, 15, ancho - 40, 45);
        tarjeta.add(lblValor);

        JLabel lblEtiqueta = new JLabel(etiqueta);
        lblEtiqueta.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblEtiqueta.setForeground(new Color(120, 128, 138));
        lblEtiqueta.setBounds(20, 65, ancho - 40, 30);
        tarjeta.add(lblEtiqueta);
    }

    /**
     * Metodo de apoyo puramente visual: dibuja una fila "ETIQUETA: valor"
     * dentro de una tarjeta de perfil, siguiendo el mismo estilo en todas.
     */
    private void agregarFilaPerfil(JPanel contenedor, String etiqueta, String valor, int y) {
        JLabel lblEtiqueta = new JLabel(etiqueta);
        lblEtiqueta.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblEtiqueta.setForeground(new Color(120, 128, 138));
        lblEtiqueta.setBounds(25, y, 260, 20);
        contenedor.add(lblEtiqueta);

        JLabel lblValor = new JLabel(valor != null ? valor : "");
        lblValor.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblValor.setForeground(TEXTO_OSCURO);
        lblValor.setBounds(25, y + 20, 320, 22);
        contenedor.add(lblValor);
    }

    /**
     * Metodo de apoyo puramente visual: obtiene las iniciales de un
     * nombre para usarlas como placeholder de foto/logo.
     */
    private String obtenerIniciales(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            return "US";
        }
        String[] partes = nombreCompleto.trim().split("\\s+");
        if (partes.length == 1) {
            return partes[0].substring(0, Math.min(2, partes[0].length())).toUpperCase();
        }
        return ("" + partes[0].charAt(0) + partes[1].charAt(0)).toUpperCase();
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
    
    public void saveData() {
    	try {
    		ObjectOutputStream io = new ObjectOutputStream(new FileOutputStream("save.bin"));
    		io.writeObject(BolsaTrabajo.getInstance());
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}