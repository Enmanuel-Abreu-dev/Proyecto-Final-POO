package visual;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import logico.SolicitudCentro;
import logico.BolsaTrabajo;
import logico.EstadoSolicutud;
import logico.Oferta;
import logico.Persona;

public class ListSolicitudesRecibidasCentro extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Color AZUL_OSCURO = new Color(22, 58, 95);
	private static final Color AZUL_PRINCIPAL = new Color(37, 99, 166);
	private static final Color VERDE_AZULADO = new Color(15, 139, 141);
	private static final Color FONDO_GRIS = new Color(244, 246, 248);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color TEXTO_OSCURO = new Color(31, 41, 55);
	private static final Color ROJO = new Color(255, 0, 0);
	private static final Color NARANJA = new Color(0xE0, 0x8E, 0x45);

	private JPanel panelListado;

	private RoundedLabel logoCentroLbl;
	private RoundedTextField nombreCentro;
	private RoundedTextField puestoTxt;
	private RoundedTextField estadoTxt;
	private RoundedTextField fechaSolicitudTxt;
	private RoundedTextField tipoContratoTxt;
	private RoundedTextField ubicacionTxt;
	private RoundedTextField salarioOfertaTxt;
	private JTextArea txtMensaje;

	private Oferta seleccionado = null;
	private SolicitudCentro soliActual;

	private final Persona candidato;

	public static void main(String[] args) {
		try {
			ListSolicitudesRecibidasCentro dialog = new ListSolicitudesRecibidasCentro(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListSolicitudesRecibidasCentro(Persona candidato) {

		this.candidato = candidato;

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ListSolicitudesRecibidasCentro.class.getResource("/imagenes/iconoBuscarOferta.png")));
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
		btnSalir.setBounds(1150, 24, 100, 36);
		layeredPane.add(btnSalir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(24, 154, 535, 516);
		layeredPane.add(scrollPane);

		panelListado = new JPanel();
		panelListado.setBackground(FONDO_GRIS);
		scrollPane.setViewportView(panelListado);
		panelListado.setLayout(new BoxLayout(panelListado, BoxLayout.Y_AXIS));

		JPanel panelDetalle = new JPanel();
		panelDetalle.setBackground(TARJETA_BLANCA);
		panelDetalle.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		panelDetalle.setLayout(null);
		panelDetalle.setPreferredSize(new Dimension(630, 760));

		JScrollPane scrollDetalle = new JScrollPane(panelDetalle);
		scrollDetalle.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollDetalle.setBounds(603, 154, 663, 516);
		layeredPane.add(scrollDetalle);

		logoCentroLbl = new RoundedLabel(20);
		logoCentroLbl.setHorizontalAlignment(JLabel.CENTER);
		logoCentroLbl.setBackground(new Color(204, 204, 204));
		logoCentroLbl.setForeground(TEXTO_OSCURO);
		logoCentroLbl.setText("LOGO");
		logoCentroLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		logoCentroLbl.setBounds(20, 20, 150, 150);
		panelDetalle.add(logoCentroLbl);

		nombreCentro = new RoundedTextField(20);
		nombreCentro.setText("EMPRESA");
		nombreCentro.setFont(new Font("Tahoma", Font.BOLD, 18));
		nombreCentro.setEditable(false);
		nombreCentro.setBackground(new Color(204, 204, 204));
		nombreCentro.setBounds(190, 30, 420, 40);
		panelDetalle.add(nombreCentro);

		puestoTxt = new RoundedTextField(20);
		puestoTxt.setText("PUESTO ASOCIADO");
		puestoTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		puestoTxt.setEditable(false);
		puestoTxt.setForeground(AZUL_PRINCIPAL);
		puestoTxt.setBackground(new Color(204, 204, 204));
		puestoTxt.setBounds(190, 80, 420, 34);
		panelDetalle.add(puestoTxt);

		estadoTxt = new RoundedTextField(20);
		estadoTxt.setText("ESTADO");
		estadoTxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		estadoTxt.setEditable(false);
		estadoTxt.setBackground(new Color(204, 204, 204));
		estadoTxt.setBounds(190, 124, 420, 34);
		panelDetalle.add(estadoTxt);

		JLabel lblFechaLbl = new JLabel("FECHA DE SOLICITUD:");
		lblFechaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaLbl.setForeground(TEXTO_OSCURO);
		lblFechaLbl.setBounds(20, 190, 260, 25);
		panelDetalle.add(lblFechaLbl);

		fechaSolicitudTxt = new RoundedTextField(20);
		fechaSolicitudTxt.setText("FECHA");
		fechaSolicitudTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fechaSolicitudTxt.setEditable(false);
		fechaSolicitudTxt.setBackground(new Color(204, 204, 204));
		fechaSolicitudTxt.setBounds(20, 216, 280, 34);
		panelDetalle.add(fechaSolicitudTxt);

		JLabel lblTipoContratoLbl = new JLabel("TIPO DE CONTRATO:");
		lblTipoContratoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoContratoLbl.setForeground(TEXTO_OSCURO);
		lblTipoContratoLbl.setBounds(330, 190, 260, 25);
		panelDetalle.add(lblTipoContratoLbl);

		tipoContratoTxt = new RoundedTextField(20);
		tipoContratoTxt.setText("TIPO DE CONTRATO");
		tipoContratoTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tipoContratoTxt.setEditable(false);
		tipoContratoTxt.setBackground(new Color(204, 204, 204));
		tipoContratoTxt.setBounds(330, 216, 280, 34);
		panelDetalle.add(tipoContratoTxt);

		JLabel lblUbicacionLbl = new JLabel("UBICACION:");
		lblUbicacionLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUbicacionLbl.setForeground(TEXTO_OSCURO);
		lblUbicacionLbl.setBounds(20, 265, 260, 25);
		panelDetalle.add(lblUbicacionLbl);

		ubicacionTxt = new RoundedTextField(20);
		ubicacionTxt.setText("UBICACION");
		ubicacionTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ubicacionTxt.setEditable(false);
		ubicacionTxt.setBackground(new Color(204, 204, 204));
		ubicacionTxt.setBounds(20, 291, 280, 34);
		panelDetalle.add(ubicacionTxt);

		JLabel lblSalarioLbl = new JLabel("SALARIO DE LA OFERTA:");
		lblSalarioLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSalarioLbl.setForeground(TEXTO_OSCURO);
		lblSalarioLbl.setBounds(330, 265, 260, 25);
		panelDetalle.add(lblSalarioLbl);

		salarioOfertaTxt = new RoundedTextField(20);
		salarioOfertaTxt.setText("SALARIO");
		salarioOfertaTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		salarioOfertaTxt.setEditable(false);
		salarioOfertaTxt.setBackground(new Color(204, 204, 204));
		salarioOfertaTxt.setBounds(330, 291, 280, 34);
		panelDetalle.add(salarioOfertaTxt);

		JLabel lblMensajeLbl = new JLabel("MENSAJE DE LA EMPRESA:");
		lblMensajeLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMensajeLbl.setForeground(TEXTO_OSCURO);
		lblMensajeLbl.setBounds(20, 340, 350, 25);
		panelDetalle.add(lblMensajeLbl);

		txtMensaje = new JTextArea();
		txtMensaje.setLineWrap(true);
		txtMensaje.setWrapStyleWord(true);
		txtMensaje.setEditable(false);
		txtMensaje.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMensaje.setBackground(new Color(204, 204, 204));
		txtMensaje.setBounds(20, 370, 590, 240);
		panelDetalle.add(txtMensaje);

		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( soliActual != null )
				{
					soliActual.setEstado(EstadoSolicutud.ACEPTADA);
					JOptionPane.showMessageDialog(null, "La solicitud de " + soliActual.getCentro().getNombre() + " ha sido aceptada",
							"Solicitud Aceptada", JOptionPane.INFORMATION_MESSAGE);

					soliActual.getPersona().setEmpleado(true);
					cargarSolicitud();
					soliActual = null;
				}
			}
		});
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBackground(VERDE_AZULADO);
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAceptar.setBounds(20, 620, 280, 42);
		panelDetalle.add(btnAceptar);

		JButton btnRechazar = new JButton("RECHAZAR");
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( soliActual != null )
				{
					soliActual.setEstado(EstadoSolicutud.RECHAZADA);
					JOptionPane.showMessageDialog(null, "La solicitud de " + soliActual.getCentro().getNombre() + " ha sido rechazada",
							"Solicitud Rechazada", JOptionPane.INFORMATION_MESSAGE);

					cargarSolicitud();
					soliActual = null;
				}
			}
		});
		btnRechazar.setForeground(Color.WHITE);
		btnRechazar.setBackground(ROJO);
		btnRechazar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRechazar.setBounds(330, 620, 280, 42);
		panelDetalle.add(btnRechazar);

		JLabel lblNewLabel_1 = new JLabel("Solicitudes de Empresas");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 34));
		lblNewLabel_1.setBounds(43, 10, 545, 72);
		layeredPane.add(lblNewLabel_1);

		setTitle("SOLICITUDES DE EMPRESAS");
		setSize(1280, 720);
		setLocationRelativeTo(null);
		cargarSolicitud();
	}

	public void cargarSolicitud() {
		ArrayList<SolicitudCentro> solicitudes =
				BolsaTrabajo.getInstance().getUsuarioActual().getMyPersona().getSolicitudCentro();

		panelListado.removeAll();
		for (final SolicitudCentro s : solicitudes) {
			panelListado.add(crearTarjeta(s));
			panelListado.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		panelListado.revalidate();
		panelListado.repaint();

		if (!solicitudes.isEmpty()) {
			mostrarDetalle(solicitudes.get(0));
		}
	}

	private JPanel crearTarjeta(final SolicitudCentro s) {
		JPanel tarjeta = new JPanel();
		tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
		tarjeta.setBackground(TARJETA_BLANCA);
		tarjeta.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		tarjeta.setAlignmentX(Component.LEFT_ALIGNMENT);
		tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

		String centroNombre = (s.getCentro() != null) ? s.getCentro().getNombre() : "EMPRESA NO DISPONIBLE";
		String puesto = (s.getOferta() != null) ? s.getOferta().getPuesto() : "SIN OFERTA ASOCIADA";

		JLabel lblCentro = new JLabel(centroNombre);
		lblCentro.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		lblCentro.setForeground(AZUL_OSCURO);
		lblCentro.setBorder(new EmptyBorder(8, 12, 2, 12));
		lblCentro.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblPuesto = new JLabel(puesto);
		lblPuesto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPuesto.setForeground(AZUL_PRINCIPAL);
		lblPuesto.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblPuesto.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblEstado = new JLabel(estadoSolicitud(s.getEstado()));
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setForeground(
				s.getEstado() == EstadoSolicutud.ACEPTADA ? VERDE_AZULADO :
						s.getEstado() == EstadoSolicutud.RECHAZADA ? ROJO :
						s.getEstado() == EstadoSolicutud.PENDIENTE ? NARANJA :
						NARANJA
		);
		lblEstado.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblFecha = new JLabel(s.getFecha() != null ? s.getFecha().toString() : "");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setForeground(new Color(150, 150, 150));
		lblFecha.setBorder(new EmptyBorder(2, 12, 8, 12));
		lblFecha.setAlignmentX(Component.LEFT_ALIGNMENT);

		tarjeta.add(lblCentro);
		tarjeta.add(lblPuesto);
		tarjeta.add(lblEstado);
		tarjeta.add(lblFecha);

		MouseAdapter listenerTarjeta = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarDetalle(s);
				soliActual = s;
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
		lblCentro.addMouseListener(listenerTarjeta);
		lblPuesto.addMouseListener(listenerTarjeta);
		lblEstado.addMouseListener(listenerTarjeta);
		lblFecha.addMouseListener(listenerTarjeta);

		return tarjeta;
	}

	private void mostrarDetalle(SolicitudCentro s) {
		seleccionado = s.getOferta();

		String rutaImagen = (s.getCentro() != null) ? s.getCentro().getRutaImagen() : null;

		if (rutaImagen != null && new File(rutaImagen).exists()) {
			ImageIcon logoOriginal = new ImageIcon(rutaImagen);
			Image logoEscalado = logoOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			logoCentroLbl.setIcon(new ImageIcon(logoEscalado));
			logoCentroLbl.setText("");
		} else {
			ImageIcon iconoEmpresaIcon = new ImageIcon(getClass().getResource("/imagenes/iconoEmpresa.png"));
			Image iconoEmpresaImg = iconoEmpresaIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			logoCentroLbl.setIcon(new ImageIcon(iconoEmpresaImg));
			logoCentroLbl.setText("");
		}

		nombreCentro.setText(s.getCentro() != null ? s.getCentro().getNombre() : "");

		if (s.getOferta() != null) {
			puestoTxt.setText(s.getOferta().getPuesto());
			salarioOfertaTxt.setText(String.valueOf(s.getOferta().getSalario()));
			tipoContratoTxt.setText(s.getOferta().getTipoContrato());
			ubicacionTxt.setText(s.getOferta().getUbicacion());
		} else {
			puestoTxt.setText("SIN OFERTA ASOCIADA");
			salarioOfertaTxt.setText("");
			tipoContratoTxt.setText("");
			ubicacionTxt.setText("");
		}

		estadoTxt.setText(estadoSolicitud(s.getEstado()));
		estadoTxt.setForeground(
				s.getEstado() == EstadoSolicutud.ACEPTADA ? VERDE_AZULADO :
						s.getEstado() == EstadoSolicutud.RECHAZADA ? ROJO :
						s.getEstado() == EstadoSolicutud.PENDIENTE ? NARANJA :
						NARANJA
		);

		fechaSolicitudTxt.setText(s.getFecha() != null ? s.getFecha().toString() : "");
		txtMensaje.setText(s.getMensaje());
	}

	private static String estadoSolicitud ( EstadoSolicutud e )
	{
		if ( e == EstadoSolicutud.ACEPTADA ) return "ACEPTADA";
		if ( e == EstadoSolicutud.PENDIENTE ) return "PENDIENTE";
		if ( e == EstadoSolicutud.RECHAZADA ) return "RECHAZADA";

		return "Vacio";
	}
}