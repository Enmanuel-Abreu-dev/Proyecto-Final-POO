package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import logico.BolsaTrabajo;

public class PrincipalAdmin extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final Color AZUL_PRINCIPAL = new Color(37, 99, 166);
	private static final Color FONDO_GRIS = new Color(244, 246, 248);
	private static final Color ROJO = new Color(255, 0, 0);

	private RoundedButton btnInicio;
	private RoundedButton btnEmpresas;
	private RoundedButton btnCandidatos;
	private RoundedButton btnOfertas;
	private RoundedButton btnSolicitudes;

	private RoundedPanel panelContenido;
	private DashboardAdmin panelDashboard;
	private ListEmpresasAdmin panelEmpresas;
	private ListCandidatosAdmin panelCandidatos;
	private ListOfertasAdmin panelOfertas;
	private ListSolicitudesAdmin panelSolicitudes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PrincipalAdmin frame = new PrincipalAdmin();
			frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public PrincipalAdmin() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				BolsaTrabajo.guardarDatos();
				System.exit(0);
			}
		});

		setBounds(100, 100, 450, 300);
		Dimension dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		JLayeredPane layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new BorderLayout(0, 0));

		RoundedPanel panelFondo = new RoundedPanel(0, FONDO_GRIS);
		panelFondo.setBackground(FONDO_GRIS);
		layeredPane.add(panelFondo, BorderLayout.CENTER);
		panelFondo.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 102));
		panel_1.setBounds(0, 0, dim.width, 105);
		panelFondo.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblTitulo = new JLabel("PANEL DE ADMINISTRACIÓN");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(40, 32, 600, 40);
		panel_1.add(lblTitulo);

		RoundedButton btnCerrarSesion = new RoundedButton("CERRAR SESIÓN", 40);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BolsaTrabajo.guardarDatos();
				dispose();
				LoginUsuario myUser = new LoginUsuario();
				myUser.setVisible(true);
				myUser.setModal(true);
			}
		});
		btnCerrarSesion.setBounds(dim.width - 220, 30, 160, 45);
		btnCerrarSesion.setBackground(ROJO);
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCerrarSesion.setFocusPainted(false);
		panel_1.add(btnCerrarSesion);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(153, 255, 255));
		panel_2.setBounds(0, 105, 220, dim.height);
		panelFondo.add(panel_2);
		panel_2.setLayout(null);

		int xContenido = 220;
		int yContenido = 105;
		int anchoContenido = dim.width - xContenido;
		int altoContenido = dim.height - yContenido;

		panelContenido = new RoundedPanel(0, FONDO_GRIS);
		panelContenido.setBackground(FONDO_GRIS);
		panelContenido.setBounds(xContenido, yContenido, anchoContenido, altoContenido);
		panelContenido.setLayout(null);
		panelFondo.add(panelContenido);

		panelDashboard = new DashboardAdmin(anchoContenido, altoContenido);
		panelContenido.add(panelDashboard);

		panelEmpresas = new ListEmpresasAdmin(anchoContenido, altoContenido);
		panelEmpresas.setVisible(false);
		panelContenido.add(panelEmpresas);

		panelCandidatos = new ListCandidatosAdmin(anchoContenido, altoContenido);
		panelCandidatos.setVisible(false);
		panelContenido.add(panelCandidatos);

		panelOfertas = new ListOfertasAdmin(anchoContenido, altoContenido);
		panelOfertas.setVisible(false);
		panelContenido.add(panelOfertas);

		panelSolicitudes = new ListSolicitudesAdmin(anchoContenido, altoContenido);
		panelSolicitudes.setVisible(false);
		panelContenido.add(panelSolicitudes);

		btnInicio = new RoundedButton("INICIO / KPI's", 40);
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarSeccion(panelDashboard, btnInicio);
			}
		});
		btnInicio.setForeground(Color.WHITE);
		btnInicio.setFont(new Font("Tw Cen MT", Font.PLAIN, 26));
		btnInicio.setBackground(new Color(0, 0, 102));
		btnInicio.setBounds(10, 37, 200, 128);
		panel_2.add(btnInicio);

		btnEmpresas = new RoundedButton("EMPRESAS", 40);
		btnEmpresas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEmpresas.cargarEmpresas();
				mostrarSeccion(panelEmpresas, btnEmpresas);
			}
		});
		btnEmpresas.setForeground(Color.WHITE);
		btnEmpresas.setFont(new Font("Tw Cen MT", Font.PLAIN, 22));
		btnEmpresas.setBackground(new Color(0, 0, 102));
		btnEmpresas.setBounds(10, 202, 200, 128);
		panel_2.add(btnEmpresas);

		btnCandidatos = new RoundedButton("CANDIDATOS", 40);
		btnCandidatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCandidatos.cargarCandidatos();
				mostrarSeccion(panelCandidatos, btnCandidatos);
			}
		});
		btnCandidatos.setForeground(Color.WHITE);
		btnCandidatos.setFont(new Font("Tw Cen MT", Font.PLAIN, 22));
		btnCandidatos.setBackground(new Color(0, 0, 102));
		btnCandidatos.setBounds(10, 367, 200, 128);
		panel_2.add(btnCandidatos);

		btnOfertas = new RoundedButton("OFERTAS", 40);
		btnOfertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelOfertas.cargarOfertas();
				mostrarSeccion(panelOfertas, btnOfertas);
			}
		});
		btnOfertas.setForeground(Color.WHITE);
		btnOfertas.setFont(new Font("Tw Cen MT", Font.PLAIN, 26));
		btnOfertas.setBackground(new Color(0, 0, 102));
		btnOfertas.setBounds(10, 532, 200, 128);
		panel_2.add(btnOfertas);

		btnSolicitudes = new RoundedButton("SOLICITUDES", 40);
		btnSolicitudes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSolicitudes.cargarSolicitudes();
				mostrarSeccion(panelSolicitudes, btnSolicitudes);
			}
		});
		btnSolicitudes.setForeground(Color.WHITE);
		btnSolicitudes.setFont(new Font("Tw Cen MT", Font.PLAIN, 22));
		btnSolicitudes.setBackground(new Color(0, 0, 102));
		btnSolicitudes.setBounds(10, 697, 200, 128);
		panel_2.add(btnSolicitudes);

		marcarBotonActivo(btnInicio);
	}

	private void mostrarSeccion(JPanel seccion, RoundedButton boton) {
		panelDashboard.setVisible(false);
		panelEmpresas.setVisible(false);
		panelCandidatos.setVisible(false);
		panelOfertas.setVisible(false);
		panelSolicitudes.setVisible(false);

		seccion.setVisible(true);
		marcarBotonActivo(boton);
	}

	private void marcarBotonActivo(RoundedButton activo) {
		RoundedButton[] botones = { btnInicio, btnEmpresas, btnCandidatos, btnOfertas, btnSolicitudes };
		for (RoundedButton b : botones) {
			b.setBackground(b == activo ? AZUL_PRINCIPAL : new Color(0, 0, 102));
		}
	}
}