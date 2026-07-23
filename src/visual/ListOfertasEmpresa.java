package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import logico.Oferta;
import logico.Institucion;

public class ListOfertasEmpresa extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Color AZUL_OSCURO = new Color(0x16, 0x3A, 0x5F);
	private static final Color AZUL_PRINCIPAL = new Color(0x25, 0x63, 0xA6);
	private static final Color VERDE_AZULADO = new Color(0x0F, 0x8B, 0x8D);
	private static final Color FONDO_GRIS = new Color(0xF4, 0xF6, 0xF8);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color TEXTO_OSCURO = new Color(0x1F, 0x29, 0x37);
	private static final Color ROJO = new Color(0xC0, 0x5B, 0x5B);

	private JPanel panelListado;
	private RoundedTextField puestoTxt;
	private RoundedTextField salarioTxt;
	private RoundedTextField modalidadTxt;
	private RoundedTextField estadoTxt;
	private RoundedTextField fechaPublicacionTxt;
	private RoundedTextField fechaCierreTxt;
	private RoundedTextField cantVacanteTxt;
	private RoundedTextField cantSolicitudesTxt;
	private JTextArea txtDescripcion;
	private JTextArea txtRequisitos;
	private JButton btnVerCandidatos;

	private final Institucion empresa;
	private Oferta ofertaSeleccionada;

	/**
	 * Lanza la ventana de forma independiente (para pruebas visuales).
	 */
	public static void main(String[] args) {
		try {
			ListOfertasEmpresa dialog = new ListOfertasEmpresa(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListOfertasEmpresa(Institucion empresa) {
		this.empresa = empresa;

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ListOfertasEmpresa.class.getResource("/imagenes/iconoBuscarOferta.png")));
		getContentPane().setBackground(new Color(0, 0, 102));

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(FONDO_GRIS);
		getContentPane().add(layeredPane, BorderLayout.CENTER);

		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(new Color(0, 153, 204));
		btnSalir.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 16));
		btnSalir.setBounds(1133, 24, 100, 36);
		layeredPane.add(btnSalir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 80, 535, 590);
		layeredPane.add(scrollPane);

		panelListado = new JPanel();
		panelListado.setBackground(FONDO_GRIS);
		scrollPane.setViewportView(panelListado);
		panelListado.setLayout(new BoxLayout(panelListado, BoxLayout.Y_AXIS));

		JPanel panelDetalle = new JPanel();
		panelDetalle.setBackground(TARJETA_BLANCA);
		panelDetalle.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		panelDetalle.setLayout(null);
		panelDetalle.setPreferredSize(new Dimension(645, 780));

		JScrollPane scrollDetalle = new JScrollPane(panelDetalle);
		scrollDetalle.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollDetalle.setBounds(603, 80, 663, 590);
		layeredPane.add(scrollDetalle);

		JLabel lblPuestoLbl = new JLabel("PUESTO:");
		lblPuestoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPuestoLbl.setForeground(TEXTO_OSCURO);
		lblPuestoLbl.setBounds(20, 20, 260, 25);
		panelDetalle.add(lblPuestoLbl);

		puestoTxt = new RoundedTextField(20);
		puestoTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		puestoTxt.setEditable(false);
		puestoTxt.setBackground(new Color(204, 204, 204));
		puestoTxt.setBounds(20, 46, 590, 34);
		panelDetalle.add(puestoTxt);

		JLabel lblEstadoLbl = new JLabel("ESTADO:");
		lblEstadoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEstadoLbl.setForeground(TEXTO_OSCURO);
		lblEstadoLbl.setBounds(20, 95, 150, 25);
		panelDetalle.add(lblEstadoLbl);

		estadoTxt = new RoundedTextField(20);
		estadoTxt.setFont(new Font("Tahoma", Font.BOLD, 16));
		estadoTxt.setEditable(false);
		estadoTxt.setBackground(new Color(204, 204, 204));
		estadoTxt.setBounds(20, 121, 280, 34);
		panelDetalle.add(estadoTxt);

		JLabel lblSalarioLbl = new JLabel("SALARIO:");
		lblSalarioLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSalarioLbl.setForeground(TEXTO_OSCURO);
		lblSalarioLbl.setBounds(330, 95, 150, 25);
		panelDetalle.add(lblSalarioLbl);

		salarioTxt = new RoundedTextField(20);
		salarioTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		salarioTxt.setEditable(false);
		salarioTxt.setBackground(new Color(204, 204, 204));
		salarioTxt.setBounds(330, 121, 280, 34);
		panelDetalle.add(salarioTxt);

		JLabel lblModalidadLbl = new JLabel("MODALIDAD:");
		lblModalidadLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModalidadLbl.setForeground(TEXTO_OSCURO);
		lblModalidadLbl.setBounds(20, 170, 260, 25);
		panelDetalle.add(lblModalidadLbl);

		modalidadTxt = new RoundedTextField(20);
		modalidadTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		modalidadTxt.setEditable(false);
		modalidadTxt.setBackground(new Color(204, 204, 204));
		modalidadTxt.setBounds(20, 196, 280, 34);
		panelDetalle.add(modalidadTxt);

		JLabel lblVacantesLbl = new JLabel("VACANTES:");
		lblVacantesLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVacantesLbl.setForeground(TEXTO_OSCURO);
		lblVacantesLbl.setBounds(330, 170, 260, 25);
		panelDetalle.add(lblVacantesLbl);

		cantVacanteTxt = new RoundedTextField(20);
		cantVacanteTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cantVacanteTxt.setEditable(false);
		cantVacanteTxt.setBackground(new Color(204, 204, 204));
		cantVacanteTxt.setBounds(330, 196, 280, 34);
		panelDetalle.add(cantVacanteTxt);

		JLabel lblPublicacionLbl = new JLabel("FECHA PUBLICACION:");
		lblPublicacionLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPublicacionLbl.setForeground(TEXTO_OSCURO);
		lblPublicacionLbl.setBounds(20, 245, 260, 25);
		panelDetalle.add(lblPublicacionLbl);

		fechaPublicacionTxt = new RoundedTextField(20);
		fechaPublicacionTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fechaPublicacionTxt.setEditable(false);
		fechaPublicacionTxt.setBackground(new Color(204, 204, 204));
		fechaPublicacionTxt.setBounds(20, 271, 280, 34);
		panelDetalle.add(fechaPublicacionTxt);

		JLabel lblCierreLbl = new JLabel("FECHA CIERRE:");
		lblCierreLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCierreLbl.setForeground(TEXTO_OSCURO);
		lblCierreLbl.setBounds(330, 245, 260, 25);
		panelDetalle.add(lblCierreLbl);

		fechaCierreTxt = new RoundedTextField(20);
		fechaCierreTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fechaCierreTxt.setEditable(false);
		fechaCierreTxt.setBackground(new Color(204, 204, 204));
		fechaCierreTxt.setBounds(330, 271, 280, 34);
		panelDetalle.add(fechaCierreTxt);

		JLabel lblSolicitudesLbl = new JLabel("SOLICITUDES RECIBIDAS:");
		lblSolicitudesLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSolicitudesLbl.setForeground(TEXTO_OSCURO);
		lblSolicitudesLbl.setBounds(20, 320, 260, 25);
		panelDetalle.add(lblSolicitudesLbl);

		cantSolicitudesTxt = new RoundedTextField(20);
		cantSolicitudesTxt.setFont(new Font("Tahoma", Font.BOLD, 16));
		cantSolicitudesTxt.setForeground(VERDE_AZULADO);
		cantSolicitudesTxt.setEditable(false);
		cantSolicitudesTxt.setBackground(new Color(204, 204, 204));
		cantSolicitudesTxt.setBounds(20, 346, 280, 34);
		panelDetalle.add(cantSolicitudesTxt);

		btnVerCandidatos = new JButton("VER CANDIDATOS");
		btnVerCandidatos.setForeground(Color.WHITE);
		btnVerCandidatos.setBackground(AZUL_PRINCIPAL);
		btnVerCandidatos.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 14));
		btnVerCandidatos.setBounds(330, 346, 280, 34);
		btnVerCandidatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VentanaMatcheo match = new VentanaMatcheo();
				match.setVisible(true);
				match.setModal(true);
			}
		});
		panelDetalle.add(btnVerCandidatos);

		JLabel lblDescripcionLbl = new JLabel("DESCRIPCION:");
		lblDescripcionLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcionLbl.setForeground(TEXTO_OSCURO);
		lblDescripcionLbl.setBounds(20, 395, 260, 25);
		panelDetalle.add(lblDescripcionLbl);

		txtDescripcion = new JTextArea();
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		txtDescripcion.setEditable(false);
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDescripcion.setBackground(new Color(204, 204, 204));
		txtDescripcion.setBounds(20, 425, 590, 150);
		panelDetalle.add(txtDescripcion);

		JLabel lblRequisitosLbl = new JLabel("REQUISITOS:");
		lblRequisitosLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRequisitosLbl.setForeground(TEXTO_OSCURO);
		lblRequisitosLbl.setBounds(20, 590, 260, 25);
		panelDetalle.add(lblRequisitosLbl);

		txtRequisitos = new JTextArea();
		txtRequisitos.setLineWrap(true);
		txtRequisitos.setWrapStyleWord(true);
		txtRequisitos.setEditable(false);
		txtRequisitos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRequisitos.setBackground(new Color(204, 204, 204));
		txtRequisitos.setBounds(20, 620, 590, 150);
		panelDetalle.add(txtRequisitos);
		
		JLabel lblNewLabel_1 = new JLabel("Mis Ofertas Publicadas");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 41));
		lblNewLabel_1.setBounds(35, 10, 582, 72);
		layeredPane.add(lblNewLabel_1);

		setTitle("MIS OFERTAS");
		setSize(1280, 720);
		setLocationRelativeTo(null);
	}

	public void cargarOfertas(ArrayList<Oferta> ofertas) {
		panelListado.removeAll();
		for (final Oferta o : ofertas) {
			panelListado.add(crearTarjeta(o));
			panelListado.add(javax.swing.Box.createRigidArea(new Dimension(0, 10)));
		}
		panelListado.revalidate();
		panelListado.repaint();

		if (!ofertas.isEmpty()) {
			mostrarDetalle(ofertas.get(0));
		}
	}

	private JPanel crearTarjeta(final Oferta o) {
		JPanel tarjeta = new JPanel();
		tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
		tarjeta.setBackground(TARJETA_BLANCA);
		tarjeta.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		tarjeta.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
		tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

		JLabel lblPuesto = new JLabel(o.getPuesto());
		lblPuesto.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		lblPuesto.setForeground(TEXTO_OSCURO);
		lblPuesto.setBorder(new EmptyBorder(8, 12, 2, 12));
		lblPuesto.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

		JLabel lblVacantes = new JLabel(o.getCantVacante() + " vacante(s) - " + o.getModalidad());
		lblVacantes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVacantes.setForeground(AZUL_PRINCIPAL);
		lblVacantes.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblVacantes.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

		JLabel lblEstado = new JLabel(o.isEstado() ? "ABIERTA" : "CERRADA");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setForeground(o.isEstado() ? VERDE_AZULADO : ROJO);
		lblEstado.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblEstado.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

		JLabel lblFecha = new JLabel(o.getFechaPublicacion() != null ? o.getFechaPublicacion().toString() : "");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setForeground(new Color(150, 150, 150));
		lblFecha.setBorder(new EmptyBorder(2, 12, 8, 12));
		lblFecha.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

		tarjeta.add(lblPuesto);
		tarjeta.add(lblVacantes);
		tarjeta.add(lblEstado);
		tarjeta.add(lblFecha);

		MouseAdapter listenerTarjeta = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarDetalle(o);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				tarjeta.setBackground(new Color(245, 245, 245));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				tarjeta.setBackground(TARJETA_BLANCA);
			}
		};
		tarjeta.addMouseListener(listenerTarjeta);
		tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblPuesto.addMouseListener(listenerTarjeta);
		lblVacantes.addMouseListener(listenerTarjeta);
		lblEstado.addMouseListener(listenerTarjeta);
		lblFecha.addMouseListener(listenerTarjeta);

		return tarjeta;
	}

	private void mostrarDetalle(Oferta o) {
		ofertaSeleccionada = o;

		puestoTxt.setText(o.getPuesto());
		salarioTxt.setText(String.valueOf(o.getSalario()));
		modalidadTxt.setText(o.getModalidad());
		cantVacanteTxt.setText(String.valueOf(o.getCantVacante()));

		estadoTxt.setText(o.isEstado() ? "ABIERTA" : "CERRADA");
		estadoTxt.setForeground(o.isEstado() ? VERDE_AZULADO : ROJO);

		fechaPublicacionTxt.setText(o.getFechaPublicacion() != null ? o.getFechaPublicacion().toString() : "");
		fechaCierreTxt.setText(o.getFechaFinalizacion() != null ? o.getFechaFinalizacion().toString() : "");

		cantSolicitudesTxt.setText(o.getSolicitudEmps() != null
				? String.valueOf(o.getSolicitudEmps().size()) : "0");

		txtDescripcion.setText(o.getDescripcion());
		txtRequisitos.setText(o.getRequisitos());
	}
}