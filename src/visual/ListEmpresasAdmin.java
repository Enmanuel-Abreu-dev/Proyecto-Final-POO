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
import logico.Institucion;
import logico.Oferta;

public class ListEmpresasAdmin extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color AZUL_OSCURO = new Color(0x16, 0x3A, 0x5F);
	private static final Color AZUL_PRINCIPAL = new Color(0x25, 0x63, 0xA6);
	private static final Color VERDE_AZULADO = new Color(0x0F, 0x8B, 0x8D);
	private static final Color FONDO_GRIS = new Color(0xF4, 0xF6, 0xF8);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color TEXTO_OSCURO = new Color(0x1F, 0x29, 0x37);

	private JPanel panelListado;

	private RoundedLabel logoEmpresaLbl;
	private RoundedTextField nombreTxt;
	private RoundedTextField sectorTxt;
	private RoundedTextField rncTxt;
	private RoundedTextField registroSocialTxt;
	private RoundedTextField paisTxt;
	private RoundedTextField telefonoTxt;
	private RoundedTextField correoTxt;
	private RoundedTextField cantEmpleadoTxt;
	private RoundedTextField direccionTxt;
	private RoundedTextField cantOfertasTxt;
	private RoundedTextField cantSolicitudesTxt;
	private JTextArea txtOfertas;

	public ListEmpresasAdmin() {
		this(1500, 900);
	}

	public ListEmpresasAdmin(int ancho, int alto) {
		setBackground(FONDO_GRIS);
		setLayout(null);
		setBounds(0, 0, ancho, alto);

		JLabel lblTitulo = new JLabel("Empresas Registradas");
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
		panelDetalle.setPreferredSize(new Dimension(anchoDetalle, 780));

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

		nombreTxt = new RoundedTextField(20);
		nombreTxt.setFont(new Font("Tahoma", Font.BOLD, 18));
		nombreTxt.setEditable(false);
		nombreTxt.setBackground(new Color(204, 204, 204));
		nombreTxt.setBounds(170, 30, anchoDetalle - 210, 40);
		panelDetalle.add(nombreTxt);

		sectorTxt = new RoundedTextField(20);
		sectorTxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		sectorTxt.setEditable(false);
		sectorTxt.setForeground(AZUL_PRINCIPAL);
		sectorTxt.setBackground(new Color(204, 204, 204));
		sectorTxt.setBounds(170, 80, anchoDetalle - 210, 34);
		panelDetalle.add(sectorTxt);

		rncTxt = crearCampo(panelDetalle, "RNC:", 20, 175);
		registroSocialTxt = crearCampo(panelDetalle, "REGISTRO SOCIAL:", 330, 175);
		paisTxt = crearCampo(panelDetalle, "PAIS:", 20, 245);
		telefonoTxt = crearCampo(panelDetalle, "TELEFONO:", 330, 245);
		correoTxt = crearCampo(panelDetalle, "CORREO:", 20, 315);
		cantEmpleadoTxt = crearCampo(panelDetalle, "CANTIDAD DE EMPLEADOS:", 330, 315);

		JLabel lblDireccionLbl = new JLabel("DIRECCION:");
		lblDireccionLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDireccionLbl.setForeground(TEXTO_OSCURO);
		lblDireccionLbl.setBounds(20, 385, 350, 25);
		panelDetalle.add(lblDireccionLbl);

		direccionTxt = new RoundedTextField(20);
		direccionTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		direccionTxt.setEditable(false);
		direccionTxt.setBackground(new Color(204, 204, 204));
		direccionTxt.setBounds(20, 411, anchoDetalle - 60, 34);
		panelDetalle.add(direccionTxt);

		cantOfertasTxt = crearCampo(panelDetalle, "OFERTAS PUBLICADAS:", 20, 465);
		cantSolicitudesTxt = crearCampo(panelDetalle, "SOLICITUDES RECIBIDAS:", 330, 465);

		JLabel lblOfertasLbl = new JLabel("OFERTAS DE LA EMPRESA:");
		lblOfertasLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOfertasLbl.setForeground(TEXTO_OSCURO);
		lblOfertasLbl.setBounds(20, 535, 350, 25);
		panelDetalle.add(lblOfertasLbl);

		txtOfertas = new JTextArea();
		txtOfertas.setLineWrap(true);
		txtOfertas.setWrapStyleWord(true);
		txtOfertas.setEditable(false);
		txtOfertas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtOfertas.setBackground(new Color(204, 204, 204));
		txtOfertas.setBounds(20, 561, anchoDetalle - 60, 200);
		panelDetalle.add(txtOfertas);

		cargarEmpresas();
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

	public void cargarEmpresas() {
		ArrayList<Institucion> instituciones = BolsaTrabajo.getInstance().getInstituciones();

		panelListado.removeAll();
		for (final Institucion i : instituciones) {
			panelListado.add(crearTarjeta(i));
			panelListado.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		panelListado.revalidate();
		panelListado.repaint();

		if (!instituciones.isEmpty()) {
			mostrarDetalle(instituciones.get(0));
		}
	}

	private JPanel crearTarjeta(final Institucion i) {
		JPanel tarjeta = new JPanel();
		tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
		tarjeta.setBackground(TARJETA_BLANCA);
		tarjeta.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		tarjeta.setAlignmentX(Component.LEFT_ALIGNMENT);
		tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

		JLabel lblNombre = new JLabel(i.getNombre());
		lblNombre.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		lblNombre.setForeground(AZUL_OSCURO);
		lblNombre.setBorder(new EmptyBorder(8, 12, 2, 12));
		lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblRnc = new JLabel("RNC: " + i.getRNC());
		lblRnc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRnc.setForeground(new Color(100, 100, 100));
		lblRnc.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblRnc.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblPais = new JLabel(i.getPais());
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPais.setForeground(AZUL_PRINCIPAL);
		lblPais.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblPais.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblSector = new JLabel(i.isPrivado() ? "SECTOR PRIVADO" : "SECTOR PUBLICO");
		lblSector.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSector.setForeground(VERDE_AZULADO);
		lblSector.setBorder(new EmptyBorder(2, 12, 8, 12));
		lblSector.setAlignmentX(Component.LEFT_ALIGNMENT);

		tarjeta.add(lblNombre);
		tarjeta.add(lblRnc);
		tarjeta.add(lblPais);
		tarjeta.add(lblSector);

		MouseAdapter listenerTarjeta = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarDetalle(i);
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
		lblNombre.addMouseListener(listenerTarjeta);
		lblRnc.addMouseListener(listenerTarjeta);
		lblPais.addMouseListener(listenerTarjeta);
		lblSector.addMouseListener(listenerTarjeta);

		return tarjeta;
	}

	private void mostrarDetalle(Institucion i) {
		String rutaImagen = i.getRutaImagen();

		if (rutaImagen != null && new File(rutaImagen).exists()) {
			ImageIcon logoOriginal = new ImageIcon(rutaImagen);
			Image logoEscalado = logoOriginal.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
			logoEmpresaLbl.setIcon(new ImageIcon(logoEscalado));
			logoEmpresaLbl.setText("");
		} else {
			logoEmpresaLbl.setIcon(null);
			logoEmpresaLbl.setText(obtenerIniciales(i.getNombre()));
		}

		nombreTxt.setText(i.getNombre());
		sectorTxt.setText(i.isPrivado() ? "SECTOR PRIVADO" : "SECTOR PUBLICO");
		rncTxt.setText(i.getRNC());
		registroSocialTxt.setText(i.getRegistroSocial());
		paisTxt.setText(i.getPais());
		telefonoTxt.setText(i.getTelefono());
		correoTxt.setText(i.getEmail());
		cantEmpleadoTxt.setText("" + i.getCantEmpleado());
		direccionTxt.setText(i.getDireccion());
		cantOfertasTxt.setText("" + i.getMyOfertas().size());
		cantSolicitudesTxt.setText("" + i.getMySolicitudes().size());

		String ofertas = "";
		for (Oferta o : i.getMyOfertas()) {
			ofertas += o.getPuesto() + " - " + (o.isEstado() ? "ABIERTA" : "CERRADA") + "\n";
		}
		txtOfertas.setText(ofertas);
	}

	private String obtenerIniciales(String nombreCompleto) {
		if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
			return "EM";
		}
		String[] partes = nombreCompleto.trim().split("\\s+");
		if (partes.length == 1) {
			return partes[0].substring(0, Math.min(2, partes[0].length())).toUpperCase();
		}
		return ("" + partes[0].charAt(0) + partes[1].charAt(0)).toUpperCase();
	}
}