package visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import logico.BolsaTrabajo;
import logico.Oferta;

public class ListOfertasAdmin extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color AZUL_OSCURO = new Color(0x16, 0x3A, 0x5F);
	private static final Color AZUL_PRINCIPAL = new Color(0x25, 0x63, 0xA6);
	private static final Color FONDO_GRIS = new Color(0xF4, 0xF6, 0xF8);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color TEXTO_OSCURO = new Color(0x1F, 0x29, 0x37);
	private static final Color VERDE_AZULADO = new Color(0x0F, 0x8B, 0x8D);
	private static final Color ROJO = new Color(0xC0, 0x5B, 0x5B);

	private JPanel panelListado;

	private RoundedLabel logoEmpresaLbl;
	private RoundedTextField puestoTxt;
	private RoundedTextField empresaTxt;
	private RoundedTextField estadoTxt;
	private RoundedTextField profesionTxt;
	private RoundedTextField salarioTxt;
	private RoundedTextField modalidadTxt;
	private RoundedTextField tipoContratoTxt;
	private RoundedTextField fechaPublicacionTxt;
	private RoundedTextField fechaCierreTxt;
	private RoundedTextField cantVacanteTxt;
	private RoundedTextField ubicacionTxt;
	private RoundedTextField cantSolicitudesTxt;
	private JTextArea txtDescripcion;
	private JTextArea txtRequisitos;

	public ListOfertasAdmin() {
		this(1500, 900);
	}

	public ListOfertasAdmin(int ancho, int alto) {
		setBackground(FONDO_GRIS);
		setLayout(null);
		setBounds(0, 0, ancho, alto);

		JLabel lblTitulo = new JLabel("Ofertas Publicadas");
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
		panelDetalle.setPreferredSize(new Dimension(anchoDetalle, 900));

		JScrollPane scrollDetalle = new JScrollPane(panelDetalle);
		scrollDetalle.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollDetalle.setBounds(60 + anchoListado, 75, anchoDetalle, alto - 110);
		add(scrollDetalle);

		logoEmpresaLbl = new RoundedLabel(20);
		logoEmpresaLbl.setHorizontalAlignment(JLabel.CENTER);
		logoEmpresaLbl.setBackground(new Color(204, 204, 204));
		logoEmpresaLbl.setForeground(TEXTO_OSCURO);
		logoEmpresaLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		logoEmpresaLbl.setBounds(20, 20, 130, 130);
		panelDetalle.add(logoEmpresaLbl);

		empresaTxt = new RoundedTextField(20);
		empresaTxt.setFont(new Font("Tahoma", Font.BOLD, 18));
		empresaTxt.setEditable(false);
		empresaTxt.setBackground(new Color(204, 204, 204));
		empresaTxt.setBounds(170, 30, anchoDetalle - 210, 40);
		panelDetalle.add(empresaTxt);

		puestoTxt = new RoundedTextField(20);
		puestoTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		puestoTxt.setEditable(false);
		puestoTxt.setForeground(AZUL_PRINCIPAL);
		puestoTxt.setBackground(new Color(204, 204, 204));
		puestoTxt.setBounds(170, 80, anchoDetalle - 210, 34);
		panelDetalle.add(puestoTxt);

		estadoTxt = new RoundedTextField(20);
		estadoTxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		estadoTxt.setEditable(false);
		estadoTxt.setBackground(new Color(204, 204, 204));
		estadoTxt.setBounds(170, 124, anchoDetalle - 210, 34);
		panelDetalle.add(estadoTxt);

		profesionTxt = crearCampo(panelDetalle, "PROFESION:", 20, 190);
		salarioTxt = crearCampo(panelDetalle, "SALARIO:", 330, 190);
		modalidadTxt = crearCampo(panelDetalle, "MODALIDAD:", 20, 260);
		tipoContratoTxt = crearCampo(panelDetalle, "TIPO DE CONTRATO:", 330, 260);
		fechaPublicacionTxt = crearCampo(panelDetalle, "FECHA DE PUBLICACION:", 20, 330);
		fechaCierreTxt = crearCampo(panelDetalle, "FECHA DE CIERRE:", 330, 330);
		cantVacanteTxt = crearCampo(panelDetalle, "VACANTES:", 20, 400);
		ubicacionTxt = crearCampo(panelDetalle, "UBICACION:", 330, 400);
		cantSolicitudesTxt = crearCampo(panelDetalle, "SOLICITUDES RECIBIDAS:", 20, 470);

		JLabel lblTituloDescripcion = new JLabel("DESCRIPCION:");
		lblTituloDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTituloDescripcion.setForeground(TEXTO_OSCURO);
		lblTituloDescripcion.setBounds(20, 540, 350, 25);
		panelDetalle.add(lblTituloDescripcion);

		txtDescripcion = new JTextArea();
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		txtDescripcion.setEditable(false);
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDescripcion.setBackground(new Color(204, 204, 204));
		txtDescripcion.setBounds(20, 566, anchoDetalle - 60, 140);
		panelDetalle.add(txtDescripcion);

		JLabel lblTituloRequisitos = new JLabel("REQUISITOS:");
		lblTituloRequisitos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTituloRequisitos.setForeground(TEXTO_OSCURO);
		lblTituloRequisitos.setBounds(20, 720, 350, 25);
		panelDetalle.add(lblTituloRequisitos);

		txtRequisitos = new JTextArea();
		txtRequisitos.setLineWrap(true);
		txtRequisitos.setWrapStyleWord(true);
		txtRequisitos.setEditable(false);
		txtRequisitos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRequisitos.setBackground(new Color(204, 204, 204));
		txtRequisitos.setBounds(20, 746, anchoDetalle - 60, 140);
		panelDetalle.add(txtRequisitos);

		cargarOfertas();
	}

	private RoundedTextField crearCampo(JPanel contenedor, String etiqueta, int x, int y) {
		JLabel lbl = new JLabel(etiqueta);
		lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl.setForeground(TEXTO_OSCURO);
		lbl.setBounds(x, y, 280, 25);
		contenedor.add(lbl);

		RoundedTextField campo = new RoundedTextField(20);
		campo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		campo.setEditable(false);
		campo.setBackground(new Color(204, 204, 204));
		campo.setBounds(x, y + 26, 280, 34);
		contenedor.add(campo);

		return campo;
	}

	public void cargarOfertas() {
		ArrayList<Oferta> ofertas = BolsaTrabajo.getInstance().getOfertas();

		panelListado.removeAll();
		for (final Oferta o : ofertas) {
			panelListado.add(crearTarjeta(o));
			panelListado.add(Box.createRigidArea(new Dimension(0, 10)));
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
		tarjeta.setAlignmentX(Component.LEFT_ALIGNMENT);
		tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

		JLabel lblPuesto = new JLabel(o.getPuesto());
		lblPuesto.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		lblPuesto.setForeground(AZUL_OSCURO);
		lblPuesto.setBorder(new EmptyBorder(8, 12, 2, 12));
		lblPuesto.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblEmpresa = new JLabel(o.getMyEmpresa().getNombre());
		lblEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmpresa.setForeground(AZUL_PRINCIPAL);
		lblEmpresa.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblEmpresa.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblModalidad = new JLabel(o.getModalidad() + "  -  " + o.getTipoContrato());
		lblModalidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblModalidad.setForeground(new Color(100, 100, 100));
		lblModalidad.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblModalidad.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblEstado = new JLabel(o.isEstado() ? "ABIERTA" : "CERRADA");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setForeground(o.isEstado() ? VERDE_AZULADO : ROJO);
		lblEstado.setBorder(new EmptyBorder(2, 12, 8, 12));
		lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);

		tarjeta.add(lblPuesto);
		tarjeta.add(lblEmpresa);
		tarjeta.add(lblModalidad);
		tarjeta.add(lblEstado);

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
		lblEmpresa.addMouseListener(listenerTarjeta);
		lblModalidad.addMouseListener(listenerTarjeta);
		lblEstado.addMouseListener(listenerTarjeta);

		return tarjeta;
	}

	private void mostrarDetalle(Oferta o) {
		String rutaImagen = o.getMyEmpresa().getRutaImagen();

		if (rutaImagen != null && new File(rutaImagen).exists()) {
			ImageIcon logoOriginal = new ImageIcon(rutaImagen);
			Image logoEscalado = logoOriginal.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
			logoEmpresaLbl.setIcon(new ImageIcon(logoEscalado));
			logoEmpresaLbl.setText("");
		} else {
			logoEmpresaLbl.setIcon(null);
			logoEmpresaLbl.setText("LOGO");
		}

		empresaTxt.setText(o.getMyEmpresa().getNombre());
		puestoTxt.setText(o.getPuesto());

		estadoTxt.setText(o.isEstado() ? "ABIERTA" : "CERRADA");
		estadoTxt.setForeground(o.isEstado() ? VERDE_AZULADO : ROJO);

		profesionTxt.setText(o.getProfesion());
		salarioTxt.setText(String.valueOf(o.getSalario()));
		modalidadTxt.setText(o.getModalidad());
		tipoContratoTxt.setText(o.getTipoContrato());
		fechaPublicacionTxt.setText(o.getFechaPublicacion().toString());
		fechaCierreTxt.setText(o.getFechaFinalizacion().toString());
		cantVacanteTxt.setText("" + o.getCantVacante());
		ubicacionTxt.setText(o.getUbicacion());
		cantSolicitudesTxt.setText("" + o.getSolicitudEmps().size());
		txtDescripcion.setText(o.getDescripcion());
		txtRequisitos.setText(o.getRequisitos());
	}
}