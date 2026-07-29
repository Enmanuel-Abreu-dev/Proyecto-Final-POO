package visual;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import logico.SolicitudEmp;
import logico.Oferta;
import logico.Persona;
import logico.Universitario;
import logico.Tecnico;
import logico.Obrero;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMatcheo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panelDetalle;
	private JPanel panelPodio;

	private static final Color AZUL_PRINCIPAL = new Color(0x25, 0x63, 0xA6);
	private static final Color TEXTO_OSCURO = new Color(0x1F, 0x29, 0x37);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color VERDE = new Color(0x0F, 0x8B, 0x8D);
	private static final Color ROJO = new Color(0xC0, 0x5B, 0x5B);

	// Campos del panel de detalle que se actualizan al hacer clic
	private RoundedLabel fotoCandidatoLbl;
	private RoundedTextField nombreCandidatoTxt;
	private RoundedTextField tipoCandidatoTxt;
	private RoundedTextField profesionTxt;
	private RoundedTextField modalidadTxt;
	private RoundedTextField experienciaTxt;
	private RoundedTextField edadTxt;
	private RoundedTextField paisTxt;
	private RoundedTextField sexoTxt;
	private RoundedTextField dispViajarTxt;
	private RoundedTextField dispResidenciaTxt;

	private Oferta ofertaActual;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaMatcheo dialog = new VentanaMatcheo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaMatcheo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaMatcheo.class.getResource("/imagenes/iconoBuscarOferta.png")));
		setBounds(100, 100, 450, 300);
		setTitle("LISTADO DE CANDIDATOS");
	    setBounds(100, 100, 450, 300);
	    Dimension dim = getToolkit().getScreenSize();

	    setSize(1280, 720);
	    setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBackground(new Color(25, 25, 112));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JLayeredPane layeredPane = new JLayeredPane();
		contentPanel.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 339, 650, 334);
		layeredPane.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);

		JLabel lblNewLabel_1 = new JLabel("Mejores Candidatos");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 62));
		lblNewLabel_1.setBounds(24, -11, 761, 93);
		layeredPane.add(lblNewLabel_1);

		panelPodio = new JPanel();
		panelPodio.setBounds(0, 92, 650, 200);
		layeredPane.add(panelPodio);
		panelPodio.setLayout(new GridLayout(1, 0, 0, 0));

		panelDetalle = new JPanel();
		panelDetalle.setBackground(TARJETA_BLANCA);
		panelDetalle.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		panelDetalle.setLayout(null);
		panelDetalle.setPreferredSize(new Dimension(540, 520));

		JScrollPane scrollDetalle = new JScrollPane(panelDetalle);
		scrollDetalle.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollDetalle.setBounds(700, 92, 562, 551);
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
		nombreCandidatoTxt.setBounds(185, 30, 330, 40);
		panelDetalle.add(nombreCandidatoTxt);

		tipoCandidatoTxt = new RoundedTextField(20);
		tipoCandidatoTxt.setText("TIPO DE CANDIDATO");
		tipoCandidatoTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tipoCandidatoTxt.setEditable(false);
		tipoCandidatoTxt.setForeground(AZUL_PRINCIPAL);
		tipoCandidatoTxt.setBackground(new Color(204, 204, 204));
		tipoCandidatoTxt.setBounds(185, 80, 330, 34);
		panelDetalle.add(tipoCandidatoTxt);

		profesionTxt = new RoundedTextField(20);
		profesionTxt.setText("PROFESION");
		profesionTxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		profesionTxt.setEditable(false);
		profesionTxt.setBackground(new Color(204, 204, 204));
		profesionTxt.setBounds(185, 124, 330, 34);
		panelDetalle.add(profesionTxt);

		JLabel lblModalidadLbl = new JLabel("MODALIDAD:");
		lblModalidadLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModalidadLbl.setForeground(TEXTO_OSCURO);
		lblModalidadLbl.setBounds(20, 190, 230, 25);
		panelDetalle.add(lblModalidadLbl);

		modalidadTxt = new RoundedTextField(20);
		modalidadTxt.setText("MODALIDAD");
		modalidadTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		modalidadTxt.setEditable(false);
		modalidadTxt.setBackground(new Color(204, 204, 204));
		modalidadTxt.setBounds(20, 216, 230, 34);
		panelDetalle.add(modalidadTxt);

		JLabel lblExperienciaLbl = new JLabel("AÑOS DE EXPERIENCIA:");
		lblExperienciaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExperienciaLbl.setForeground(TEXTO_OSCURO);
		lblExperienciaLbl.setBounds(285, 190, 230, 25);
		panelDetalle.add(lblExperienciaLbl);

		experienciaTxt = new RoundedTextField(20);
		experienciaTxt.setText("EXPERIENCIA");
		experienciaTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		experienciaTxt.setEditable(false);
		experienciaTxt.setBackground(new Color(204, 204, 204));
		experienciaTxt.setBounds(285, 216, 230, 34);
		panelDetalle.add(experienciaTxt);

		JLabel lblEdadLbl = new JLabel("EDAD:");
		lblEdadLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEdadLbl.setForeground(TEXTO_OSCURO);
		lblEdadLbl.setBounds(20, 265, 230, 25);
		panelDetalle.add(lblEdadLbl);

		edadTxt = new RoundedTextField(20);
		edadTxt.setText("EDAD");
		edadTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		edadTxt.setEditable(false);
		edadTxt.setBackground(new Color(204, 204, 204));
		edadTxt.setBounds(20, 291, 230, 34);
		panelDetalle.add(edadTxt);

		JLabel lblPaisLbl = new JLabel("PAIS:");
		lblPaisLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPaisLbl.setForeground(TEXTO_OSCURO);
		lblPaisLbl.setBounds(285, 265, 230, 25);
		panelDetalle.add(lblPaisLbl);

		paisTxt = new RoundedTextField(20);
		paisTxt.setText("PAIS");
		paisTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paisTxt.setEditable(false);
		paisTxt.setBackground(new Color(204, 204, 204));
		paisTxt.setBounds(285, 291, 230, 34);
		panelDetalle.add(paisTxt);

		JLabel lblSexoLbl = new JLabel("SEXO:");
		lblSexoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSexoLbl.setForeground(TEXTO_OSCURO);
		lblSexoLbl.setBounds(20, 340, 230, 25);
		panelDetalle.add(lblSexoLbl);

		sexoTxt = new RoundedTextField(20);
		sexoTxt.setText("SEXO");
		sexoTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sexoTxt.setEditable(false);
		sexoTxt.setBackground(new Color(204, 204, 204));
		sexoTxt.setBounds(20, 366, 230, 34);
		panelDetalle.add(sexoTxt);

		JLabel lblDispViajarLbl = new JLabel("DISPONIBILIDAD DE VIAJE:");
		lblDispViajarLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDispViajarLbl.setForeground(TEXTO_OSCURO);
		lblDispViajarLbl.setBounds(285, 340, 230, 25);
		panelDetalle.add(lblDispViajarLbl);

		dispViajarTxt = new RoundedTextField(20);
		dispViajarTxt.setText("DISP. VIAJE");
		dispViajarTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dispViajarTxt.setEditable(false);
		dispViajarTxt.setBackground(new Color(204, 204, 204));
		dispViajarTxt.setBounds(285, 366, 230, 34);
		panelDetalle.add(dispViajarTxt);

		JLabel lblDispResidenciaLbl = new JLabel("DISPONIBILIDAD DE MUDANZA:");
		lblDispResidenciaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDispResidenciaLbl.setForeground(TEXTO_OSCURO);
		lblDispResidenciaLbl.setBounds(20, 415, 280, 25);
		panelDetalle.add(lblDispResidenciaLbl);

		dispResidenciaTxt = new RoundedTextField(20);
		dispResidenciaTxt.setText("DISP. MUDANZA");
		dispResidenciaTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dispResidenciaTxt.setEditable(false);
		dispResidenciaTxt.setBackground(new Color(204, 204, 204));
		dispResidenciaTxt.setBounds(20, 441, 230, 34);
		panelDetalle.add(dispResidenciaTxt);

		JLabel lblOtrasSolicitudes = new JLabel("OTRAS SOLICITUDES:");
		lblOtrasSolicitudes.setForeground(Color.WHITE);
		lblOtrasSolicitudes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblOtrasSolicitudes.setBounds(10, 310, 283, 19);
		layeredPane.add(lblOtrasSolicitudes);

		btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 16));
		btnSalir.setBackground(new Color(0, 153, 204));
		btnSalir.setBounds(1118, 22, 100, 36);
		layeredPane.add(btnSalir);

	}

	public void cargarPodio(java.util.ArrayList<SolicitudEmp> solicitudes,
			java.util.ArrayList<Float> porcentajes, Oferta oferta) {
		this.ofertaActual = oferta;
		panelPodio.removeAll();
		for (int i = 0; i < solicitudes.size() && i < 3; i++) {
			panelPodio.add(crearTarjetaPodio(solicitudes.get(i), porcentajes.get(i), oferta, i + 1));
		}
		panelPodio.revalidate();
		panelPodio.repaint();
	}

	private JPanel crearTarjetaPodio(SolicitudEmp sol, float porcentaje, Oferta oferta, int posicion) {

	    JPanel tarjeta = new JPanel();
	    tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
	    tarjeta.setBackground(Color.WHITE);
	    tarjeta.setBorder(new EmptyBorder(14, 10, 14, 10));

		Color acento;
		if (posicion == 1) {
			acento = new Color(0xE8, 0xB4, 0x1C);
		} else if (posicion == 2) {
			acento = new Color(0xA9, 0xB4, 0xBC);
		} else {
			acento = new Color(0xC4, 0x7A, 0x3E);
		}

		tarjeta.setBorder(BorderFactory.createCompoundBorder( new MatteBorder(0, 0, 4, 0, acento),
				new EmptyBorder(14, 10, 14, 10)));

		String nombreCompleto = sol.getPersona().getNombre() + " " + sol.getPersona().getApellido();

	    JLabel lblPosicion = new JLabel(posicion + "º lugar");
	    lblPosicion.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblPosicion.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JLabel lblNombre = new JLabel(nombreCompleto);
	    lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JLabel lblPorcentaje = new JLabel(Math.round(porcentaje) + "%");
	    lblPorcentaje.setFont(new Font("Tahoma", Font.BOLD, 18));
	    lblPorcentaje.setAlignmentX(Component.CENTER_ALIGNMENT);

	    tarjeta.add(lblPosicion);
	    tarjeta.add(lblNombre);
	    tarjeta.add(lblPorcentaje);

		tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tarjeta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarDetalle(sol, oferta);
			}
		});

	    return tarjeta;
	}

	public void mostrarDetalle(SolicitudEmp sol, Oferta oferta) {
		Persona p = sol.getPersona();

		String nivel;
		String profesion;
		if (p instanceof Universitario) {
			nivel = "Universitario";
			profesion = ((Universitario) p).getCarrera();
		} else if (p instanceof Tecnico) {
			nivel = "Tecnico";
			profesion = ((Tecnico) p).getEspecialidad();
		} else if (p instanceof Obrero) {
			nivel = "Obrero";
			profesion = ((Obrero) p).getProfesion();
		} else {
			nivel = "";
			profesion = "";
		}

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
		tipoCandidatoTxt.setText(nivel);

		profesionTxt.setText(profesion);
		profesionTxt.setForeground(
				oferta.getPuesto() != null && oferta.getPuesto().equalsIgnoreCase(nivel)
						? VERDE : ROJO);

		modalidadTxt.setText(sol.getModalidad());
		modalidadTxt.setForeground(
				sol.getModalidad() != null && sol.getModalidad().equalsIgnoreCase(oferta.getModalidad())
						? VERDE : ROJO);

		experienciaTxt.setText(p.calcularAniosExperiencia() + "");
		experienciaTxt.setForeground(
				p.calcularAniosExperiencia() >= oferta.getAniosExperiencia() ? VERDE : ROJO);

		edadTxt.setText(p.calcularEdad() + "");
		edadTxt.setForeground(p.calcularEdad() <= oferta.getEdad() ? VERDE : ROJO);

		paisTxt.setText(p.getPais());
		paisTxt.setForeground(
				p.getPais() != null && p.getPais().equalsIgnoreCase(oferta.getPais())
						? VERDE : ROJO);

		sexoTxt.setText(p.getSexo());
		sexoTxt.setForeground(
				p.getSexo() != null && p.getSexo().equalsIgnoreCase(oferta.getSexo())
						? VERDE : ROJO);

		dispViajarTxt.setText(p.isDispViajar() ? "SI" : "NO");
		dispViajarTxt.setForeground(p.isDispViajar() ? VERDE : ROJO);

		dispResidenciaTxt.setText(p.isDispResidencia() ? "SI" : "NO");
		dispResidenciaTxt.setForeground(p.isDispResidencia() ? VERDE : ROJO);
	}
}