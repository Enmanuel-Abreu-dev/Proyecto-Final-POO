package visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import logico.BolsaTrabajo;
import logico.EstadoSolicutud;
import logico.Persona;
import logico.Solicitud;
import logico.SolicitudCentro;
import logico.SolicitudEmp;

public class ListSolicitudesAdmin extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color AZUL_OSCURO = new Color(0x16, 0x3A, 0x5F);
	private static final Color AZUL_PRINCIPAL = new Color(0x25, 0x63, 0xA6);
	private static final Color FONDO_GRIS = new Color(0xF4, 0xF6, 0xF8);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color TEXTO_OSCURO = new Color(0x1F, 0x29, 0x37);
	private static final Color VERDE_AZULADO = new Color(0x0F, 0x8B, 0x8D);
	private static final Color ROJO = new Color(0xC0, 0x5B, 0x5B);
	private static final Color NARANJA = new Color(0xE0, 0x8E, 0x45);

	private JPanel panelListado;

	private RoundedTextField candidatoTxt;
	private RoundedTextField tipoSolicitudTxt;
	private RoundedTextField relacionadoTxt;
	private RoundedTextField fechaTxt;
	private RoundedTextField estadoTxt;
	private JTextArea txtInformacionAdicional;

	public ListSolicitudesAdmin() {
		this(1500, 900);
	}

	public ListSolicitudesAdmin(int ancho, int alto) {
		setBackground(FONDO_GRIS);
		setLayout(null);
		setBounds(0, 0, ancho, alto);

		JLabel lblTitulo = new JLabel("Solicitudes Registradas");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblTitulo.setForeground(TEXTO_OSCURO);
		lblTitulo.setBounds(40, 25, ancho - 80, 34);
		add(lblTitulo);

		int anchoListado = (int) ((ancho - 100) * 0.42);
		int anchoDetalle = ancho - 100 - anchoListado;

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(40, 75, anchoListado, alto - 110);
		add(scrollPane);

		panelListado = new JPanel();
		panelListado.setBackground(FONDO_GRIS);
		scrollPane.setViewportView(panelListado);
		panelListado.setLayout(new BoxLayout(panelListado, BoxLayout.Y_AXIS));

		JPanel panelDetalle = new JPanel();
		panelDetalle.setBackground(TARJETA_BLANCA);
		panelDetalle.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		panelDetalle.setLayout(null);
		panelDetalle.setPreferredSize(new Dimension(anchoDetalle, 620));

		JScrollPane scrollDetalle = new JScrollPane(panelDetalle);
		scrollDetalle.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollDetalle.setBounds(60 + anchoListado, 75, anchoDetalle, alto - 110);
		add(scrollDetalle);

		candidatoTxt = crearCampoAncho(panelDetalle, "CANDIDATO:", 20, 20, anchoDetalle - 60);
		tipoSolicitudTxt = crearCampo(panelDetalle, "TIPO DE SOLICITUD:", 20, 95);
		estadoTxt = crearCampo(panelDetalle, "ESTADO:", 330, 95);
		relacionadoTxt = crearCampoAncho(panelDetalle, "RELACIONADO CON:", 20, 165, anchoDetalle - 60);
		fechaTxt = crearCampo(panelDetalle, "FECHA:", 20, 240);

		JLabel lblInfoLbl = new JLabel("INFORMACION ADICIONAL:");
		lblInfoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInfoLbl.setForeground(TEXTO_OSCURO);
		lblInfoLbl.setBounds(20, 310, 350, 25);
		panelDetalle.add(lblInfoLbl);

		txtInformacionAdicional = new JTextArea();
		txtInformacionAdicional.setLineWrap(true);
		txtInformacionAdicional.setWrapStyleWord(true);
		txtInformacionAdicional.setEditable(false);
		txtInformacionAdicional.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtInformacionAdicional.setBackground(new Color(204, 204, 204));
		txtInformacionAdicional.setBounds(20, 336, anchoDetalle - 60, 250);
		panelDetalle.add(txtInformacionAdicional);

		cargarSolicitudes();
	}

	private RoundedTextField crearCampo(JPanel contenedor, String etiqueta, int x, int y) {
		return crearCampoAncho(contenedor, etiqueta, x, y, 280);
	}

	private RoundedTextField crearCampoAncho(JPanel contenedor, String etiqueta, int x, int y, int ancho) {
		JLabel lbl = new JLabel(etiqueta);
		lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl.setForeground(TEXTO_OSCURO);
		lbl.setBounds(x, y, ancho, 25);
		contenedor.add(lbl);

		RoundedTextField campo = new RoundedTextField(20);
		campo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		campo.setEditable(false);
		campo.setBackground(new Color(204, 204, 204));
		campo.setBounds(x, y + 26, ancho, 34);
		contenedor.add(campo);

		return campo;
	}

	public void cargarSolicitudes() {
		ArrayList<Solicitud> solicitudes = BolsaTrabajo.getInstance().getSolicitudes();

		panelListado.removeAll();
		for (final Solicitud s : solicitudes) {
			panelListado.add(crearTarjeta(s));
			panelListado.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		panelListado.revalidate();
		panelListado.repaint();

		if (!solicitudes.isEmpty()) {
			mostrarDetalle(solicitudes.get(0));
		}
	}

	private JPanel crearTarjeta(final Solicitud s) {
		JPanel tarjeta = new JPanel();
		tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
		tarjeta.setBackground(TARJETA_BLANCA);
		tarjeta.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		tarjeta.setAlignmentX(Component.LEFT_ALIGNMENT);
		tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

		JLabel lblCandidato = new JLabel(obtenerCandidato(s));
		lblCandidato.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		lblCandidato.setForeground(AZUL_OSCURO);
		lblCandidato.setBorder(new EmptyBorder(8, 12, 2, 12));
		lblCandidato.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblTipo = new JLabel((s instanceof SolicitudEmp) ? "SOLICITUD DE EMPLEO" : "SOLICITUD A EMPRESA");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipo.setForeground(AZUL_PRINCIPAL);
		lblTipo.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblTipo.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblFecha = new JLabel(s.getFecha().toString());
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setForeground(new Color(150, 150, 150));
		lblFecha.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblFecha.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblEstado = new JLabel
		(
			s.getEstado() == EstadoSolicutud.ACEPTADA ? "ACEPTADA" :
			s.getEstado() == EstadoSolicutud.RECHAZADA ? "RECHAZADA" :
			s.getEstado() == EstadoSolicutud.PENDIENTE ? "PENDIENTE" :
			"PENDIENTE"
		);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setForeground(
				s.getEstado() == EstadoSolicutud.ACEPTADA ? VERDE_AZULADO :
				s.getEstado() == EstadoSolicutud.RECHAZADA ? ROJO :
				s.getEstado() == EstadoSolicutud.PENDIENTE ? NARANJA :
				NARANJA
			);
		lblEstado.setBorder(new EmptyBorder(2, 12, 8, 12));
		lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);

		tarjeta.add(lblCandidato);
		tarjeta.add(lblTipo);
		tarjeta.add(lblFecha);
		tarjeta.add(lblEstado);

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
		lblCandidato.addMouseListener(listenerTarjeta);
		lblTipo.addMouseListener(listenerTarjeta);
		lblFecha.addMouseListener(listenerTarjeta);
		lblEstado.addMouseListener(listenerTarjeta);

		return tarjeta;
	}

	private void mostrarDetalle(Solicitud s) {
		candidatoTxt.setText(obtenerCandidato(s));
		tipoSolicitudTxt.setText((s instanceof SolicitudEmp) ? "SOLICITUD DE EMPLEO" : "SOLICITUD A EMPRESA");

		estadoTxt.setText
		(
			s.getEstado() == EstadoSolicutud.ACEPTADA ? "ACEPTADA" :
			s.getEstado() == EstadoSolicutud.RECHAZADA ? "RECHAZADA" :
			s.getEstado() == EstadoSolicutud.PENDIENTE ? "PENDIENTE" :
			"PENDIENTE"
		);
		estadoTxt.setForeground(
				s.getEstado() == EstadoSolicutud.ACEPTADA ? VERDE_AZULADO :
				s.getEstado() == EstadoSolicutud.RECHAZADA ? ROJO :
				s.getEstado() == EstadoSolicutud.PENDIENTE ? NARANJA :
				NARANJA
			);

		fechaTxt.setText(s.getFecha().toString());

		if (s instanceof SolicitudEmp) {
			SolicitudEmp se = (SolicitudEmp) s;
			relacionadoTxt.setText(se.getOferta().getPuesto() + " - " + se.getOferta().getMyEmpresa().getNombre());
			txtInformacionAdicional
					.setText("RANGO SALARIAL: " + se.getRangoSalarial() + "\nMODALIDAD: " + se.getModalidad());
		} else if (s instanceof SolicitudCentro) {
			SolicitudCentro sc = (SolicitudCentro) s;
			relacionadoTxt.setText(sc.getCentro().getNombre());
			txtInformacionAdicional.setText("MENSAJE:\n" + sc.getMensaje());
		}
	}

	private String obtenerCandidato(Solicitud s) {
		Persona p = null;

		if (s instanceof SolicitudEmp)
			p = ((SolicitudEmp) s).getPersona();
		else if (s instanceof SolicitudCentro)
			p = ((SolicitudCentro) s).getPersona();

		return (p != null) ? (p.getNombre() + " " + p.getApellido()) : "";
	}
}