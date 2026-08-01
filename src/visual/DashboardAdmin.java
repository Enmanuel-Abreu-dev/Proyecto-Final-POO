package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logico.BolsaTrabajo;
import logico.Oferta;

public class DashboardAdmin extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color AZUL_OSCURO = new Color(22, 58, 95);
	private static final Color AZUL_PRINCIPAL = new Color(37, 99, 166);
	private static final Color VERDE_AZULADO = new Color(15, 139, 141);
	private static final Color FONDO_GRIS = new Color(244, 246, 248);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color TEXTO_OSCURO = new Color(31, 41, 55);
	private static final Color ROJO = new Color(255, 0, 0);

	private RoundedButton btnGraphModalidadOferta;
	private RoundedButton btnGraphOfertasPorExp;
	private RoundedButton btnGraphOfertasPorSalario;
	private RoundedButton btnGraphOfertasRelizadasEmpresa;
	private RoundedButton btnGraphPersonasEmpleadas;
	private RoundedButton btnGraphPersonasTecUni;
	private RoundedButton btnGraphSolicitudesEmpAceptadas;

	public DashboardAdmin() {
		this(1500, 900);
	}

	public DashboardAdmin(int ancho, int alto) {
		setBackground(FONDO_GRIS);
		setLayout(null);
		setBounds(0, 0, ancho, alto);

		JLabel lblTitulo = new JLabel("Resumen General de la Plataforma");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblTitulo.setForeground(TEXTO_OSCURO);
		lblTitulo.setBounds(40, 30, ancho - 80, 34);
		add(lblTitulo);

		JLabel lblSubtitulo = new JLabel("Vista consolidada de empresas, candidatos, ofertas y solicitudes.");
		lblSubtitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSubtitulo.setForeground(new Color(120, 128, 138));
		lblSubtitulo.setBounds(40, 68, ancho - 80, 22);
		add(lblSubtitulo);

		int anchoTarjeta = (ancho - 80 - 60) / 4;

		agregarTarjetaResumen("EMPRESAS REGISTRADAS", "" + BolsaTrabajo.getInstance().getInstituciones().size(),
				AZUL_OSCURO, 40, 105, anchoTarjeta);
		agregarTarjetaResumen("CANDIDATOS REGISTRADOS", "" + BolsaTrabajo.getInstance().getPersonas().size(),
				AZUL_PRINCIPAL, 60 + anchoTarjeta, 105, anchoTarjeta);
		agregarTarjetaResumen("OFERTAS ACTIVAS", "" + contarOfertasActivas(), VERDE_AZULADO,
				80 + anchoTarjeta * 2, 105, anchoTarjeta);
		agregarTarjetaResumen("SOLICITUDES TOTALES", "" + BolsaTrabajo.getInstance().getSolicitudes().size(),
				ROJO, 100 + anchoTarjeta * 3, 105, anchoTarjeta);

		JLabel lblTituloGraficos = new JLabel("Estadísticas Generales");
		lblTituloGraficos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTituloGraficos.setForeground(TEXTO_OSCURO);
		lblTituloGraficos.setBounds(40, 250, ancho - 80, 28);
		add(lblTituloGraficos);

		int anchoBoton = ancho - 80;

		btnGraphModalidadOferta = new RoundedButton("OFERTAS POR MODALIDAD", 20);
		btnGraphModalidadOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialogGrafico = new JDialog();
				dialogGrafico.setTitle("Ofertas por Modalidad");
				dialogGrafico.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialogGrafico.getContentPane().setLayout(new BorderLayout());
				dialogGrafico.getContentPane().add(new GraphModalidadOferta(), BorderLayout.CENTER);
				dialogGrafico.setSize(700, 500);
				dialogGrafico.setLocationRelativeTo(DashboardAdmin.this);
				dialogGrafico.setVisible(true);
			}
		});
		btnGraphModalidadOferta.setForeground(Color.WHITE);
		btnGraphModalidadOferta.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGraphModalidadOferta.setBackground(AZUL_OSCURO);
		btnGraphModalidadOferta.setFocusPainted(false);
		btnGraphModalidadOferta.setBounds(76, 397, 635, 65);
		add(btnGraphModalidadOferta);

		btnGraphOfertasPorExp = new RoundedButton("OFERTAS POR AÑOS DE EXPERIENCIA", 20);
		btnGraphOfertasPorExp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialogGrafico = new JDialog();
				dialogGrafico.setTitle("Ofertas por Años de Experiencia");
				dialogGrafico.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialogGrafico.getContentPane().setLayout(new BorderLayout());
				dialogGrafico.getContentPane().add(new GraphOfertasPorExp(), BorderLayout.CENTER);
				dialogGrafico.setSize(700, 500);
				dialogGrafico.setLocationRelativeTo(DashboardAdmin.this);
				dialogGrafico.setVisible(true);
			}
		});
		btnGraphOfertasPorExp.setForeground(Color.WHITE);
		btnGraphOfertasPorExp.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGraphOfertasPorExp.setBackground(AZUL_OSCURO);
		btnGraphOfertasPorExp.setFocusPainted(false);
		btnGraphOfertasPorExp.setBounds(787, 301, 635, 65);
		add(btnGraphOfertasPorExp);

		btnGraphOfertasPorSalario = new RoundedButton("OFERTAS POR SALARIO", 20);
		btnGraphOfertasPorSalario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialogGrafico = new JDialog();
				dialogGrafico.setTitle("Ofertas por Salario");
				dialogGrafico.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialogGrafico.getContentPane().setLayout(new BorderLayout());
				dialogGrafico.getContentPane().add(new GraphOfertasPorSalario(), BorderLayout.CENTER);
				dialogGrafico.setSize(700, 500);
				dialogGrafico.setLocationRelativeTo(DashboardAdmin.this);
				dialogGrafico.setVisible(true);
			}
		});
		btnGraphOfertasPorSalario.setForeground(Color.WHITE);
		btnGraphOfertasPorSalario.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGraphOfertasPorSalario.setBackground(AZUL_OSCURO);
		btnGraphOfertasPorSalario.setFocusPainted(false);
		btnGraphOfertasPorSalario.setBounds(76, 301, 635, 65);
		add(btnGraphOfertasPorSalario);

		btnGraphOfertasRelizadasEmpresa = new RoundedButton("OFERTAS POR TAMAÑO DE EMPRESA", 20);
		btnGraphOfertasRelizadasEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialogGrafico = new JDialog();
				dialogGrafico.setTitle("Ofertas por Tamaño de Empresa");
				dialogGrafico.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialogGrafico.getContentPane().setLayout(new BorderLayout());
				dialogGrafico.getContentPane().add(new GraphOfertasRelizadasEmpresa(), BorderLayout.CENTER);
				dialogGrafico.setSize(700, 500);
				dialogGrafico.setLocationRelativeTo(DashboardAdmin.this);
				dialogGrafico.setVisible(true);
			}
		});
		btnGraphOfertasRelizadasEmpresa.setForeground(Color.WHITE);
		btnGraphOfertasRelizadasEmpresa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGraphOfertasRelizadasEmpresa.setBackground(AZUL_OSCURO);
		btnGraphOfertasRelizadasEmpresa.setFocusPainted(false);
		btnGraphOfertasRelizadasEmpresa.setBounds(787, 397, 635, 65);
		add(btnGraphOfertasRelizadasEmpresa);

		btnGraphPersonasEmpleadas = new RoundedButton("PERSONAS EMPLEADAS", 20);
		btnGraphPersonasEmpleadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialogGrafico = new JDialog();
				dialogGrafico.setTitle("Personas Empleadas");
				dialogGrafico.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialogGrafico.getContentPane().setLayout(new BorderLayout());
				dialogGrafico.getContentPane().add(new GraphPersonasEmpleadas(), BorderLayout.CENTER);
				dialogGrafico.setSize(700, 500);
				dialogGrafico.setLocationRelativeTo(DashboardAdmin.this);
				dialogGrafico.setVisible(true);
			}
		});
		btnGraphPersonasEmpleadas.setForeground(Color.WHITE);
		btnGraphPersonasEmpleadas.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGraphPersonasEmpleadas.setBackground(AZUL_OSCURO);
		btnGraphPersonasEmpleadas.setFocusPainted(false);
		btnGraphPersonasEmpleadas.setBounds(76, 494, 635, 65);
		add(btnGraphPersonasEmpleadas);

		btnGraphPersonasTecUni = new RoundedButton("PERSONAS TECNICOS & UNIVERSITARIAS", 20);
		btnGraphPersonasTecUni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialogGrafico = new JDialog();
				dialogGrafico.setTitle("Personas Técnicos & Universitarias");
				dialogGrafico.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialogGrafico.getContentPane().setLayout(new BorderLayout());
				dialogGrafico.getContentPane().add(new GraphPersonasTecUni(), BorderLayout.CENTER);
				dialogGrafico.setSize(700, 500);
				dialogGrafico.setLocationRelativeTo(DashboardAdmin.this);
				dialogGrafico.setVisible(true);
			}
		});
		btnGraphPersonasTecUni.setForeground(Color.WHITE);
		btnGraphPersonasTecUni.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGraphPersonasTecUni.setBackground(AZUL_OSCURO);
		btnGraphPersonasTecUni.setFocusPainted(false);
		btnGraphPersonasTecUni.setBounds(787, 494, 635, 65);
		add(btnGraphPersonasTecUni);

		btnGraphSolicitudesEmpAceptadas = new RoundedButton("SOLICITUDES DE EMPLEO", 20);
		btnGraphSolicitudesEmpAceptadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialogGrafico = new JDialog();
				dialogGrafico.setTitle("Solicitudes de Empleo");
				dialogGrafico.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialogGrafico.getContentPane().setLayout(new BorderLayout());
				dialogGrafico.getContentPane().add(new GraphSolicitudesEmpAceptadas(), BorderLayout.CENTER);
				dialogGrafico.setSize(700, 500);
				dialogGrafico.setLocationRelativeTo(DashboardAdmin.this);
				dialogGrafico.setVisible(true);
			}
		});
		btnGraphSolicitudesEmpAceptadas.setForeground(Color.WHITE);
		btnGraphSolicitudesEmpAceptadas.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGraphSolicitudesEmpAceptadas.setBackground(AZUL_OSCURO);
		btnGraphSolicitudesEmpAceptadas.setFocusPainted(false);
		btnGraphSolicitudesEmpAceptadas.setBounds(76, 590, 635, 65);
		add(btnGraphSolicitudesEmpAceptadas);
	}

	private int contarOfertasActivas() {
		int total = 0;
		for (Oferta o : BolsaTrabajo.getInstance().getOfertas())
			if (o.isEstado())
				total++;
		return total;
	}

	private void agregarTarjetaResumen(String etiqueta, String valor, Color color, int x, int y, int ancho) {
		RoundedPanel tarjeta = new RoundedPanel(25, TARJETA_BLANCA, new Color(225, 228, 232));
		tarjeta.setBackground(TARJETA_BLANCA);
		tarjeta.setBounds(x, y, ancho, 110);
		tarjeta.setLayout(null);
		add(tarjeta);

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
}