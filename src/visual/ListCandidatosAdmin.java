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
import logico.Experiencia;
import logico.Obrero;
import logico.Persona;
import logico.Tecnico;
import logico.Universitario;

public class ListCandidatosAdmin extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color AZUL_OSCURO = new Color(0x16, 0x3A, 0x5F);
	private static final Color AZUL_PRINCIPAL = new Color(0x25, 0x63, 0xA6);
	private static final Color VERDE_AZULADO = new Color(0x0F, 0x8B, 0x8D);
	private static final Color FONDO_GRIS = new Color(0xF4, 0xF6, 0xF8);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color TEXTO_OSCURO = new Color(0x1F, 0x29, 0x37);
	private static final Color ROJO = new Color(0xC0, 0x5B, 0x5B);

	private JPanel panelListado;

	private RoundedLabel fotoLbl;
	private RoundedTextField nombreTxt;
	private RoundedTextField tipoTxt;
	private RoundedTextField cedulaTxt;
	private RoundedTextField sexoTxt;
	private RoundedTextField paisTxt;
	private RoundedTextField telefonoTxt;
	private RoundedTextField correoTxt;
	private RoundedTextField dispViajarTxt;
	private RoundedTextField dispResidenciaTxt;
	private RoundedTextField estadoEmpleoTxt;
	private RoundedTextField datoEspecificoTxt1;
	private RoundedTextField datoEspecificoTxt2;
	private JLabel lblEspecifico1;
	private JLabel lblEspecifico2;
	private JTextArea txtExperiencia;

	public ListCandidatosAdmin() {
		this(1500, 900);
	}

	public ListCandidatosAdmin(int ancho, int alto) {
		setBackground(FONDO_GRIS);
		setLayout(null);
		setBounds(0, 0, ancho, alto);

		JLabel lblTitulo = new JLabel("Candidatos Registrados");
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
		panelDetalle.setPreferredSize(new Dimension(anchoDetalle, 820));

		JScrollPane scrollDetalle = new JScrollPane(panelDetalle);
		scrollDetalle.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollDetalle.setBounds(60 + anchoListado, 75, anchoDetalle, alto - 110);
		add(scrollDetalle);

		fotoLbl = new RoundedLabel(20);
		fotoLbl.setHorizontalAlignment(JLabel.CENTER);
		fotoLbl.setBackground(new Color(204, 204, 204));
		fotoLbl.setForeground(TEXTO_OSCURO);
		fotoLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		fotoLbl.setBounds(20, 20, 130, 130);
		panelDetalle.add(fotoLbl);

		nombreTxt = new RoundedTextField(20);
		nombreTxt.setFont(new Font("Tahoma", Font.BOLD, 18));
		nombreTxt.setEditable(false);
		nombreTxt.setBackground(new Color(204, 204, 204));
		nombreTxt.setBounds(170, 30, anchoDetalle - 210, 40);
		panelDetalle.add(nombreTxt);

		tipoTxt = new RoundedTextField(20);
		tipoTxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		tipoTxt.setEditable(false);
		tipoTxt.setForeground(AZUL_PRINCIPAL);
		tipoTxt.setBackground(new Color(204, 204, 204));
		tipoTxt.setBounds(170, 80, anchoDetalle - 210, 34);
		panelDetalle.add(tipoTxt);

		cedulaTxt = crearCampo(panelDetalle, "CEDULA:", 20, 175);
		sexoTxt = crearCampo(panelDetalle, "SEXO:", 330, 175);
		paisTxt = crearCampo(panelDetalle, "PAIS:", 20, 245);
		telefonoTxt = crearCampo(panelDetalle, "TELEFONO:", 330, 245);
		correoTxt = crearCampo(panelDetalle, "CORREO:", 20, 315);
		estadoEmpleoTxt = crearCampo(panelDetalle, "ESTADO LABORAL:", 330, 315);
		dispViajarTxt = crearCampo(panelDetalle, "DISPONIBILIDAD DE VIAJE:", 20, 385);
		dispResidenciaTxt = crearCampo(panelDetalle, "DISPONIBILIDAD DE MUDANZA:", 330, 385);

		lblEspecifico1 = new JLabel();
		lblEspecifico1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEspecifico1.setForeground(TEXTO_OSCURO);
		lblEspecifico1.setBounds(20, 455, 280, 25);
		panelDetalle.add(lblEspecifico1);

		datoEspecificoTxt1 = new RoundedTextField(20);
		datoEspecificoTxt1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		datoEspecificoTxt1.setEditable(false);
		datoEspecificoTxt1.setBackground(new Color(204, 204, 204));
		datoEspecificoTxt1.setBounds(20, 481, 280, 34);
		panelDetalle.add(datoEspecificoTxt1);

		lblEspecifico2 = new JLabel();
		lblEspecifico2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEspecifico2.setForeground(TEXTO_OSCURO);
		lblEspecifico2.setBounds(330, 455, 280, 25);
		panelDetalle.add(lblEspecifico2);

		datoEspecificoTxt2 = new RoundedTextField(20);
		datoEspecificoTxt2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		datoEspecificoTxt2.setEditable(false);
		datoEspecificoTxt2.setBackground(new Color(204, 204, 204));
		datoEspecificoTxt2.setBounds(330, 481, 280, 34);
		panelDetalle.add(datoEspecificoTxt2);

		JLabel lblExperienciaLbl = new JLabel("EXPERIENCIA LABORAL:");
		lblExperienciaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExperienciaLbl.setForeground(TEXTO_OSCURO);
		lblExperienciaLbl.setBounds(20, 555, 350, 25);
		panelDetalle.add(lblExperienciaLbl);

		txtExperiencia = new JTextArea();
		txtExperiencia.setLineWrap(true);
		txtExperiencia.setWrapStyleWord(true);
		txtExperiencia.setEditable(false);
		txtExperiencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtExperiencia.setBackground(new Color(204, 204, 204));
		txtExperiencia.setBounds(20, 581, anchoDetalle - 60, 210);
		panelDetalle.add(txtExperiencia);

		cargarCandidatos();
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

	public void cargarCandidatos() {
		ArrayList<Persona> personas = BolsaTrabajo.getInstance().getPersonas();

		panelListado.removeAll();
		for (final Persona p : personas) {
			panelListado.add(crearTarjeta(p));
			panelListado.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		panelListado.revalidate();
		panelListado.repaint();

		if (!personas.isEmpty()) {
			mostrarDetalle(personas.get(0));
		}
	}

	private JPanel crearTarjeta(final Persona p) {
		JPanel tarjeta = new JPanel();
		tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
		tarjeta.setBackground(TARJETA_BLANCA);
		tarjeta.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		tarjeta.setAlignmentX(Component.LEFT_ALIGNMENT);
		tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

		JLabel lblNombre = new JLabel(p.getNombre() + " " + p.getApellido());
		lblNombre.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		lblNombre.setForeground(AZUL_OSCURO);
		lblNombre.setBorder(new EmptyBorder(8, 12, 2, 12));
		lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblCedula = new JLabel("Cédula: " + p.getCedula());
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCedula.setForeground(new Color(100, 100, 100));
		lblCedula.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblCedula.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblTipo = new JLabel(obtenerTipo(p));
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipo.setForeground(AZUL_PRINCIPAL);
		lblTipo.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblTipo.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblEstado = new JLabel(p.isEmpleado() ? "EMPLEADO" : "DESEMPLEADO");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setForeground(p.isEmpleado() ? VERDE_AZULADO : ROJO);
		lblEstado.setBorder(new EmptyBorder(2, 12, 8, 12));
		lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);

		tarjeta.add(lblNombre);
		tarjeta.add(lblCedula);
		tarjeta.add(lblTipo);
		tarjeta.add(lblEstado);

		MouseAdapter listenerTarjeta = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarDetalle(p);
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
		lblCedula.addMouseListener(listenerTarjeta);
		lblTipo.addMouseListener(listenerTarjeta);
		lblEstado.addMouseListener(listenerTarjeta);

		return tarjeta;
	}

	private void mostrarDetalle(Persona p) {
		String rutaImagen = p.getRutaImagen();

		if (rutaImagen != null && new File(rutaImagen).exists()) {
			ImageIcon fotoOriginal = new ImageIcon(rutaImagen);
			Image fotoEscalada = fotoOriginal.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
			fotoLbl.setIcon(new ImageIcon(fotoEscalada));
			fotoLbl.setText("");
		} else {
			fotoLbl.setIcon(null);
			fotoLbl.setText(obtenerIniciales(p.getNombre() + " " + p.getApellido()));
		}

		nombreTxt.setText(p.getNombre() + " " + p.getApellido());
		tipoTxt.setText(obtenerTipo(p));
		cedulaTxt.setText(p.getCedula());
		sexoTxt.setText(p.getSexo());
		paisTxt.setText(p.getPais());
		telefonoTxt.setText(p.getTelefono());
		correoTxt.setText(p.getEmail());

		estadoEmpleoTxt.setText(p.isEmpleado() ? "EMPLEADO" : "DESEMPLEADO");
		estadoEmpleoTxt.setForeground(p.isEmpleado() ? VERDE_AZULADO : ROJO);

		dispViajarTxt.setText(p.isDispViajar() ? "SI" : "NO");
		dispResidenciaTxt.setText(p.isDispResidencia() ? "SI" : "NO");

		if (p instanceof Universitario) {
			Universitario u = (Universitario) p;
			lblEspecifico1.setText("CARRERA:");
			datoEspecificoTxt1.setText(u.getCarrera());
			lblEspecifico2.setText("UNIVERSIDAD:");
			datoEspecificoTxt2.setText(u.getUniversidad());
		} else if (p instanceof Tecnico) {
			Tecnico t = (Tecnico) p;
			lblEspecifico1.setText("ESPECIALIDAD:");
			datoEspecificoTxt1.setText(t.getEspecialidad());
			lblEspecifico2.setText("POLITECNICO:");
			datoEspecificoTxt2.setText(t.getPolitecnico());
		} else if (p instanceof Obrero) {
			Obrero o = (Obrero) p;
			lblEspecifico1.setText("PROFESION / OFICIO:");
			datoEspecificoTxt1.setText(o.getProfesion());
			lblEspecifico2.setText("");
			datoEspecificoTxt2.setText("");
		}

		String experiencias = "";
		for (Experiencia e : p.getExperiencia()) {
			experiencias += e.getCargo() + " - " + e.getInstitucion() + " (" + e.getFechaInicio() + " / "
					+ e.getFechaFinalizacion() + ")\n";
		}
		txtExperiencia.setText(experiencias);
	}

	private String obtenerTipo(Persona p) {
		if (p instanceof Universitario)
			return "UNIVERSITARIO";
		if (p instanceof Tecnico)
			return "TECNICO";
		if (p instanceof Obrero)
			return "OBRERO";
		return "";
	}

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
}