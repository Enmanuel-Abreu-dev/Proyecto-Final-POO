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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import logico.SolicitudEmp;
import logico.BolsaTrabajo;
import logico.Oferta;
import logico.Persona;


public class ListSolicitudesEmp extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Color AZUL_OSCURO = new Color(0x16, 0x3A, 0x5F);
	private static final Color AZUL_PRINCIPAL = new Color(0x25, 0x63, 0xA6);
	private static final Color VERDE_AZULADO = new Color(0x0F, 0x8B, 0x8D);
	private static final Color FONDO_GRIS = new Color(0xF4, 0xF6, 0xF8);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color TEXTO_OSCURO = new Color(0x1F, 0x29, 0x37);
	private static final Color ROJO = new Color(0xC0, 0x5B, 0x5B);

	private JPanel panelListado;

	private RoundedLabel logoEmpresaLbl;
	private RoundedTextField nombreEmpresa;
	private RoundedTextField puestoTxt;
	private RoundedTextField estadoTxt;
	private RoundedTextField fechaSolicitudTxt;
	private RoundedTextField modalidadTxt;
	private RoundedTextField rangoSalarialTxt;
	private RoundedTextField salarioOfertaTxt;
	private RoundedTextField tipoContratoTxt;
	private RoundedTextField ubicacionTxt;
	private JTextArea txtDescripcionOferta;

	private Oferta seleccionado = null;

	private final Persona candidato;

	public static void main(String[] args) {
		try {
			ListSolicitudesEmp dialog = new ListSolicitudesEmp(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListSolicitudesEmp(Persona candidato) {
		
		this.candidato = candidato;

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ListSolicitudesEmp.class.getResource("/imagenes/iconoBuscarOferta.png")));
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

		logoEmpresaLbl = new RoundedLabel(20);
		logoEmpresaLbl.setHorizontalAlignment(JLabel.CENTER);
		logoEmpresaLbl.setBackground(new Color(204, 204, 204));
		logoEmpresaLbl.setForeground(TEXTO_OSCURO);
		logoEmpresaLbl.setText("LOGO");
		logoEmpresaLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		logoEmpresaLbl.setBounds(20, 20, 150, 150);
		panelDetalle.add(logoEmpresaLbl);

		nombreEmpresa = new RoundedTextField(20);
		nombreEmpresa.setText("EMPRESA");
		nombreEmpresa.setFont(new Font("Tahoma", Font.BOLD, 18));
		nombreEmpresa.setEditable(false);
		nombreEmpresa.setBackground(new Color(204, 204, 204));
		nombreEmpresa.setBounds(190, 30, 420, 40);
		panelDetalle.add(nombreEmpresa);

		puestoTxt = new RoundedTextField(20);
		puestoTxt.setText("PUESTO SOLICITADO");
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

		JLabel lblModalidadLbl = new JLabel("MODALIDAD SOLICITADA:");
		lblModalidadLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModalidadLbl.setForeground(TEXTO_OSCURO);
		lblModalidadLbl.setBounds(330, 190, 260, 25);
		panelDetalle.add(lblModalidadLbl);

		modalidadTxt = new RoundedTextField(20);
		modalidadTxt.setText("MODALIDAD");
		modalidadTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		modalidadTxt.setEditable(false);
		modalidadTxt.setBackground(new Color(204, 204, 204));
		modalidadTxt.setBounds(330, 216, 280, 34);
		panelDetalle.add(modalidadTxt);

		JLabel lblRangoLbl = new JLabel("RANGO SALARIAL SOLICITADO:");
		lblRangoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRangoLbl.setForeground(TEXTO_OSCURO);
		lblRangoLbl.setBounds(20, 265, 280, 25);
		panelDetalle.add(lblRangoLbl);

		rangoSalarialTxt = new RoundedTextField(20);
		rangoSalarialTxt.setText("RANGO SALARIAL");
		rangoSalarialTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rangoSalarialTxt.setEditable(false);
		rangoSalarialTxt.setBackground(new Color(204, 204, 204));
		rangoSalarialTxt.setBounds(20, 291, 280, 34);
		panelDetalle.add(rangoSalarialTxt);

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

		JLabel lblContratoLbl = new JLabel("TIPO DE CONTRATO:");
		lblContratoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContratoLbl.setForeground(TEXTO_OSCURO);
		lblContratoLbl.setBounds(20, 340, 260, 25);
		panelDetalle.add(lblContratoLbl);

		tipoContratoTxt = new RoundedTextField(20);
		tipoContratoTxt.setText("TIPO DE CONTRATO");
		tipoContratoTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tipoContratoTxt.setEditable(false);
		tipoContratoTxt.setBackground(new Color(204, 204, 204));
		tipoContratoTxt.setBounds(20, 366, 280, 34);
		panelDetalle.add(tipoContratoTxt);

		JLabel lblUbicacionLbl = new JLabel("UBICACION:");
		lblUbicacionLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUbicacionLbl.setForeground(TEXTO_OSCURO);
		lblUbicacionLbl.setBounds(330, 340, 260, 25);
		panelDetalle.add(lblUbicacionLbl);

		ubicacionTxt = new RoundedTextField(20);
		ubicacionTxt.setText("UBICACION");
		ubicacionTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ubicacionTxt.setEditable(false);
		ubicacionTxt.setBackground(new Color(204, 204, 204));
		ubicacionTxt.setBounds(330, 366, 280, 34);
		panelDetalle.add(ubicacionTxt);

		JLabel lblDescripcionLbl = new JLabel("DESCRIPCION DE LA OFERTA:");
		lblDescripcionLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcionLbl.setForeground(TEXTO_OSCURO);
		lblDescripcionLbl.setBounds(20, 415, 350, 25);
		panelDetalle.add(lblDescripcionLbl);

		txtDescripcionOferta = new JTextArea();
		txtDescripcionOferta.setLineWrap(true);
		txtDescripcionOferta.setWrapStyleWord(true);
		txtDescripcionOferta.setEditable(false);
		txtDescripcionOferta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDescripcionOferta.setBackground(new Color(204, 204, 204));
		txtDescripcionOferta.setBounds(20, 445, 590, 250);
		panelDetalle.add(txtDescripcionOferta);

		JLabel lblNewLabel_1 = new JLabel("Mis Solicitudes");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 41));
		lblNewLabel_1.setBounds(43, 10, 385, 72);
		layeredPane.add(lblNewLabel_1);

		setTitle("MIS SOLICITUDES");
		setSize(1280, 720);
		setLocationRelativeTo(null);
		cargarSolicitud();
	}

	public void cargarSolicitud() {
		ArrayList<SolicitudEmp> solicitudes =
				BolsaTrabajo.getInstance().getUsuarioActual().getMyPersona().getSolicitudEmps();

		panelListado.removeAll();
		for (final SolicitudEmp s : solicitudes) {
			panelListado.add(crearTarjeta(s));
			panelListado.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		panelListado.revalidate();
		panelListado.repaint();

		if (!solicitudes.isEmpty()) {
			mostrarDetalle(solicitudes.get(0));
		}
	}

	private JPanel crearTarjeta(final SolicitudEmp s) {
		JPanel tarjeta = new JPanel();
		tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
		tarjeta.setBackground(TARJETA_BLANCA);
		tarjeta.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		tarjeta.setAlignmentX(Component.LEFT_ALIGNMENT);
		tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

		String puesto = (s.getOferta() != null) ? s.getOferta().getPuesto() : "PUESTO NO DISPONIBLE";
		String empresa = (s.getOferta() != null && s.getOferta().getMyEmpresa() != null)
				? s.getOferta().getMyEmpresa().getNombre() : "EMPRESA NO DISPONIBLE";

		JLabel lblPuesto = new JLabel(puesto);
		lblPuesto.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		lblPuesto.setForeground(AZUL_OSCURO);
		lblPuesto.setBorder(new EmptyBorder(8, 12, 2, 12));
		lblPuesto.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblEmpresa = new JLabel(empresa);
		lblEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmpresa.setForeground(AZUL_PRINCIPAL);
		lblEmpresa.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblEmpresa.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblEstado = new JLabel(estadoSolicitud(s.isEstado()));
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setForeground(s.isEstado() ? VERDE_AZULADO : ROJO);
		lblEstado.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblFecha = new JLabel(s.getFecha() != null ? s.getFecha().toString() : "");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setForeground(new Color(150, 150, 150));
		lblFecha.setBorder(new EmptyBorder(2, 12, 8, 12));
		lblFecha.setAlignmentX(Component.LEFT_ALIGNMENT);

		tarjeta.add(lblPuesto);
		tarjeta.add(lblEmpresa);
		tarjeta.add(lblEstado);
		tarjeta.add(lblFecha);

		MouseAdapter listenerTarjeta = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarDetalle(s);
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
		lblEmpresa.addMouseListener(listenerTarjeta);
		lblEstado.addMouseListener(listenerTarjeta);
		lblFecha.addMouseListener(listenerTarjeta);

		return tarjeta;
	}

	private void mostrarDetalle(SolicitudEmp s) {
		seleccionado = s.getOferta();

		String rutaImagen = null;
		if (s.getOferta() != null && s.getOferta().getMyEmpresa() != null)
			rutaImagen = s.getOferta().getMyEmpresa().getRutaImagen();

		if (rutaImagen != null && new File(rutaImagen).exists()) {
			ImageIcon logoOriginal = new ImageIcon(rutaImagen);
			Image logoEscalado = logoOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			logoEmpresaLbl.setIcon(new ImageIcon(logoEscalado));
			logoEmpresaLbl.setText("");
		} else {
			ImageIcon iconoEmpresaIcon = new ImageIcon(getClass().getResource("/imagenes/iconoEmpresa.png"));
			Image iconoEmpresaImg = iconoEmpresaIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			logoEmpresaLbl.setIcon(new ImageIcon(iconoEmpresaImg));
			logoEmpresaLbl.setText("");
		}

		if (s.getOferta() != null) {
			nombreEmpresa.setText(s.getOferta().getMyEmpresa() != null
					? s.getOferta().getMyEmpresa().getNombre() : "");
			puestoTxt.setText(s.getOferta().getPuesto());
			salarioOfertaTxt.setText(String.valueOf(s.getOferta().getSalario()));
			tipoContratoTxt.setText(s.getOferta().getTipoContrato());
			ubicacionTxt.setText(s.getOferta().getUbicacion());
			txtDescripcionOferta.setText(s.getOferta().getDescripcion());
		} else {
			nombreEmpresa.setText("");
			puestoTxt.setText("");
			salarioOfertaTxt.setText("");
			tipoContratoTxt.setText("");
			ubicacionTxt.setText("");
			txtDescripcionOferta.setText("");
		}

		estadoTxt.setText(estadoSolicitud(s.isEstado()));
		estadoTxt.setForeground(s.isEstado() ? VERDE_AZULADO : ROJO);

		fechaSolicitudTxt.setText(s.getFecha() != null ? s.getFecha().toString() : "");
		modalidadTxt.setText(s.getModalidad());
		rangoSalarialTxt.setText(s.getRangoSalarial());
	}

	private static String estadoSolicitud ( boolean estado )
	{
		if ( estado ) return "ACEPTADA";
		else return "PENDIENTE / RECHAZADA";
	}
}