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

import logico.Institucion;
import logico.Oferta;
import logico.SolicitudEmp;
import logico.Persona;
import logico.Universitario;
import logico.Tecnico;
import logico.Obrero;
import logico.Experiencia;


public class ListSolicitudesAceptadas extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Color AZUL_OSCURO = new Color(22, 58, 95);
	private static final Color AZUL_PRINCIPAL = new Color(37, 99, 166);
	private static final Color VERDE_AZULADO = new Color(15, 139, 141);
	private static final Color FONDO_GRIS = new Color(244, 246, 248);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color TEXTO_OSCURO = new Color(31, 41, 55);
	private static final Color ROJO = new Color(255, 0, 0);

	private JPanel panelListado;

	private RoundedLabel fotoCandidatoLbl;
	private RoundedTextField nombreCandidatoTxt;
	private RoundedTextField cedulaTxt;
	private RoundedTextField tipoCandidatoTxt;
	private RoundedTextField edadTxt;
	private RoundedTextField paisTxt;
	private RoundedTextField telefonoTxt;
	private RoundedTextField emailTxt;
	private RoundedTextField dispViajarTxt;
	private RoundedTextField dispResidenciaTxt;
	private RoundedTextField estadoLaboralTxt;
	private JTextArea txtExperiencia;

	private final Institucion empresa;

	public static void main(String[] args) {
		try {
			ListSolicitudesAceptadas dialog = new ListSolicitudesAceptadas(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListSolicitudesAceptadas(Institucion empresa) {
		this.empresa = empresa;

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ListSolicitudesAceptadas.class.getResource("/imagenes/iconoBuscarOferta.png")));
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
		panelDetalle.setPreferredSize(new Dimension(630, 760));

		JScrollPane scrollDetalle = new JScrollPane(panelDetalle);
		scrollDetalle.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollDetalle.setBounds(603, 80, 663, 590);
		layeredPane.add(scrollDetalle);

		fotoCandidatoLbl = new RoundedLabel(20);
		fotoCandidatoLbl.setHorizontalAlignment(JLabel.CENTER);
		fotoCandidatoLbl.setBackground(new Color(204, 204, 204));
		fotoCandidatoLbl.setForeground(TEXTO_OSCURO);
		fotoCandidatoLbl.setText("FOTO");
		fotoCandidatoLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		fotoCandidatoLbl.setBounds(20, 20, 150, 150);
		panelDetalle.add(fotoCandidatoLbl);

		nombreCandidatoTxt = new RoundedTextField(20);
		nombreCandidatoTxt.setText("NOMBRE CANDIDATO");
		nombreCandidatoTxt.setFont(new Font("Tahoma", Font.BOLD, 18));
		nombreCandidatoTxt.setEditable(false);
		nombreCandidatoTxt.setBackground(new Color(204, 204, 204));
		nombreCandidatoTxt.setBounds(190, 30, 420, 40);
		panelDetalle.add(nombreCandidatoTxt);

		tipoCandidatoTxt = new RoundedTextField(20);
		tipoCandidatoTxt.setText("TIPO DE CANDIDATO");
		tipoCandidatoTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tipoCandidatoTxt.setEditable(false);
		tipoCandidatoTxt.setForeground(AZUL_PRINCIPAL);
		tipoCandidatoTxt.setBackground(new Color(204, 204, 204));
		tipoCandidatoTxt.setBounds(190, 80, 420, 34);
		panelDetalle.add(tipoCandidatoTxt);

		estadoLaboralTxt = new RoundedTextField(20);
		estadoLaboralTxt.setText("ESTADO LABORAL");
		estadoLaboralTxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		estadoLaboralTxt.setEditable(false);
		estadoLaboralTxt.setBackground(new Color(204, 204, 204));
		estadoLaboralTxt.setBounds(190, 124, 420, 34);
		panelDetalle.add(estadoLaboralTxt);

		JLabel lblCedulaLbl = new JLabel("CEDULA:");
		lblCedulaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCedulaLbl.setForeground(TEXTO_OSCURO);
		lblCedulaLbl.setBounds(20, 190, 260, 25);
		panelDetalle.add(lblCedulaLbl);

		cedulaTxt = new RoundedTextField(20);
		cedulaTxt.setText("CEDULA");
		cedulaTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cedulaTxt.setEditable(false);
		cedulaTxt.setBackground(new Color(204, 204, 204));
		cedulaTxt.setBounds(20, 216, 280, 34);
		panelDetalle.add(cedulaTxt);

		JLabel lblEdadLbl = new JLabel("EDAD:");
		lblEdadLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEdadLbl.setForeground(TEXTO_OSCURO);
		lblEdadLbl.setBounds(330, 190, 150, 25);
		panelDetalle.add(lblEdadLbl);

		edadTxt = new RoundedTextField(20);
		edadTxt.setText("EDAD");
		edadTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		edadTxt.setEditable(false);
		edadTxt.setBackground(new Color(204, 204, 204));
		edadTxt.setBounds(330, 216, 280, 34);
		panelDetalle.add(edadTxt);

		JLabel lblPaisLbl = new JLabel("PAIS:");
		lblPaisLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPaisLbl.setForeground(TEXTO_OSCURO);
		lblPaisLbl.setBounds(20, 265, 260, 25);
		panelDetalle.add(lblPaisLbl);

		paisTxt = new RoundedTextField(20);
		paisTxt.setText("PAIS");
		paisTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paisTxt.setEditable(false);
		paisTxt.setBackground(new Color(204, 204, 204));
		paisTxt.setBounds(20, 291, 280, 34);
		panelDetalle.add(paisTxt);

		JLabel lblTelefonoLbl = new JLabel("TELEFONO:");
		lblTelefonoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefonoLbl.setForeground(TEXTO_OSCURO);
		lblTelefonoLbl.setBounds(330, 265, 260, 25);
		panelDetalle.add(lblTelefonoLbl);

		telefonoTxt = new RoundedTextField(20);
		telefonoTxt.setText("TELEFONO");
		telefonoTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		telefonoTxt.setEditable(false);
		telefonoTxt.setBackground(new Color(204, 204, 204));
		telefonoTxt.setBounds(330, 291, 280, 34);
		panelDetalle.add(telefonoTxt);

		JLabel lblEmailLbl = new JLabel("CORREO ELECTRONICO:");
		lblEmailLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmailLbl.setForeground(TEXTO_OSCURO);
		lblEmailLbl.setBounds(20, 340, 300, 25);
		panelDetalle.add(lblEmailLbl);

		emailTxt = new RoundedTextField(20);
		emailTxt.setText("CORREO");
		emailTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailTxt.setEditable(false);
		emailTxt.setBackground(new Color(204, 204, 204));
		emailTxt.setBounds(20, 366, 590, 34);
		panelDetalle.add(emailTxt);

		JLabel lblDispViajarLbl = new JLabel("DISPONIBILIDAD DE VIAJE:");
		lblDispViajarLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDispViajarLbl.setForeground(TEXTO_OSCURO);
		lblDispViajarLbl.setBounds(20, 415, 260, 25);
		panelDetalle.add(lblDispViajarLbl);

		dispViajarTxt = new RoundedTextField(20);
		dispViajarTxt.setText("DISP. VIAJE");
		dispViajarTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dispViajarTxt.setEditable(false);
		dispViajarTxt.setBackground(new Color(204, 204, 204));
		dispViajarTxt.setBounds(20, 441, 280, 34);
		panelDetalle.add(dispViajarTxt);

		JLabel lblDispResidenciaLbl = new JLabel("DISPONIBILIDAD DE MUDANZA:");
		lblDispResidenciaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDispResidenciaLbl.setForeground(TEXTO_OSCURO);
		lblDispResidenciaLbl.setBounds(330, 415, 280, 25);
		panelDetalle.add(lblDispResidenciaLbl);

		dispResidenciaTxt = new RoundedTextField(20);
		dispResidenciaTxt.setText("DISP. MUDANZA");
		dispResidenciaTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dispResidenciaTxt.setEditable(false);
		dispResidenciaTxt.setBackground(new Color(204, 204, 204));
		dispResidenciaTxt.setBounds(330, 441, 280, 34);
		panelDetalle.add(dispResidenciaTxt);

		JLabel lblExperienciaLbl = new JLabel("EXPERIENCIA LABORAL:");
		lblExperienciaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExperienciaLbl.setForeground(TEXTO_OSCURO);
		lblExperienciaLbl.setBounds(20, 490, 350, 25);
		panelDetalle.add(lblExperienciaLbl);

		txtExperiencia = new JTextArea();
		txtExperiencia.setLineWrap(true);
		txtExperiencia.setWrapStyleWord(true);
		txtExperiencia.setEditable(false);
		txtExperiencia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtExperiencia.setBackground(new Color(204, 204, 204));
		txtExperiencia.setBounds(20, 520, 590, 200);
		panelDetalle.add(txtExperiencia);

		JLabel lblNewLabel_1 = new JLabel("Solicitudes Aceptadas");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 41));
		lblNewLabel_1.setBounds(43, 10, 598, 72);
		layeredPane.add(lblNewLabel_1);

		setTitle("SOLICITUDES ACEPTADAS");
		setSize(1280, 720);
		setLocationRelativeTo(null);
		cargarSolicitudesAceptadas();
	}

	public void cargarSolicitudesAceptadas() {
		ArrayList<SolicitudEmp> solicitudes = solicitudesAceptadas();

		panelListado.removeAll();
		for (final SolicitudEmp s : solicitudes) {
			panelListado.add(crearTarjeta(s));
			panelListado.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		panelListado.revalidate();
		panelListado.repaint();

		if (!solicitudes.isEmpty()) {
			mostrarDetalle(solicitudes.get(0).getPersona());
		}
	}

	private ArrayList<SolicitudEmp> solicitudesAceptadas() {
		ArrayList<SolicitudEmp> lista = new ArrayList<>();
		ArrayList<Oferta> ofertas = empresa.getMyOfertas();

		for (Oferta o : ofertas)
			for (SolicitudEmp s : o.getSolicitudEmps())
				if (s.isEstado())
					lista.add(s);
		return lista;
	}

	private JPanel crearTarjeta(final SolicitudEmp s) {
		final Persona p = s.getPersona();

		JPanel tarjeta = new JPanel();
		tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
		tarjeta.setBackground(TARJETA_BLANCA);
		tarjeta.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		tarjeta.setAlignmentX(Component.LEFT_ALIGNMENT);
		tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

		JLabel lblNombre = new JLabel(p.getNombre() + " " + p.getApellido());
		lblNombre.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		lblNombre.setForeground(AZUL_OSCURO);
		lblNombre.setBorder(new EmptyBorder(8, 12, 2, 12));
		lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblPuesto = new JLabel(s.getOferta().getPuesto());
		lblPuesto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPuesto.setForeground(AZUL_PRINCIPAL);
		lblPuesto.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblPuesto.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblFecha = new JLabel(s.getFecha().toString());
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setForeground(new Color(150, 150, 150));
		lblFecha.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblFecha.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblEstado = new JLabel("ACEPTADA");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setForeground(VERDE_AZULADO);
		lblEstado.setBorder(new EmptyBorder(2, 12, 8, 12));
		lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);

		tarjeta.add(lblNombre);
		tarjeta.add(lblPuesto);
		tarjeta.add(lblFecha);
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
		lblPuesto.addMouseListener(listenerTarjeta);
		lblFecha.addMouseListener(listenerTarjeta);
		lblEstado.addMouseListener(listenerTarjeta);

		return tarjeta;
	}

	private void mostrarDetalle(Persona p) {
		String rutaImagen = p.getRutaImagen();

		if (rutaImagen != null && new File(rutaImagen).exists()) {
			ImageIcon fotoOriginal = new ImageIcon(rutaImagen);
			Image fotoEscalada = fotoOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			fotoCandidatoLbl.setIcon(new ImageIcon(fotoEscalada));
			fotoCandidatoLbl.setText("");
		} else {
			ImageIcon iconoProfesionIcon = new ImageIcon(getClass().getResource("/imagenes/iconoProfesion.png"));
			Image iconoProfesionImg = iconoProfesionIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			fotoCandidatoLbl.setIcon(new ImageIcon(iconoProfesionImg));
			fotoCandidatoLbl.setText("");
		}

		nombreCandidatoTxt.setText(p.getNombre() + " " + p.getApellido());
		tipoCandidatoTxt.setText(tipoCandidato(p));

		estadoLaboralTxt.setText(p.isEmpleado() ? "EMPLEADO" : "DISPONIBLE");
		estadoLaboralTxt.setForeground(p.isEmpleado() ? ROJO : VERDE_AZULADO);

		cedulaTxt.setText(p.getCedula());
		edadTxt.setText("" + p.calcularEdad());
		paisTxt.setText(p.getPais());
		telefonoTxt.setText(p.getTelefono());
		emailTxt.setText(p.getEmail());

		dispViajarTxt.setText(p.isDispViajar() ? "SI" : "NO");
		dispViajarTxt.setForeground(p.isDispViajar() ? VERDE_AZULADO : ROJO);

		dispResidenciaTxt.setText(p.isDispResidencia() ? "SI" : "NO");
		dispResidenciaTxt.setForeground(p.isDispResidencia() ? VERDE_AZULADO : ROJO);

		String experiencias = "";
		for (Experiencia e : p.getExperiencia())
			experiencias += e.getCargo() + " - " + e.getInstitucion() + " (" + e.getFechaInicio() + " / "
					+ e.getFechaFinalizacion() + ")\n";
		txtExperiencia.setText(experiencias);
	}

	private static String tipoCandidato(Persona p) {
		if (p instanceof Universitario) return "Universitario";
		if (p instanceof Tecnico) return "Tecnico";
		if (p instanceof Obrero) return "Obrero";
		return "";
	}
}