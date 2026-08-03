package visual;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import logico.SolicitudEmp;
import logico.Oferta;
import logico.Persona;
import logico.Universitario;
import logico.Tecnico;
import logico.BolsaTrabajo;
import logico.Coincidencia;
import logico.Obrero;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMatcheo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panelDetalle;
	private JPanel panelPodio;
	private JPanel panelListado;

	private static final Color AZUL_PRINCIPAL = new Color(37, 99, 166);
	private static final Color VERDE = new Color(15, 139, 141);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color TEXTO_OSCURO = new Color(31, 41, 55);
	private static final Color ROJO = new Color(255, 0, 0);

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
	private Persona personaActual;
	private ArrayList<Coincidencia> listaCoincidencias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaMatcheo dialog = new VentanaMatcheo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaMatcheo(Oferta oferta) {
		this.ofertaActual = oferta;
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

		JPanel panelOtras = new JPanel();
		scrollPane.setViewportView(panelOtras);
		panelListado = new JPanel(); 
		panelListado.setLayout(new BoxLayout(panelListado, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(panelListado);

		JLabel lblNewLabel_1 = new JLabel("Mejores Candidatos");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 62));
		lblNewLabel_1.setBounds(24, -11, 761, 93);
		layeredPane.add(lblNewLabel_1);

		panelPodio = new JPanel();
		panelPodio.setBounds(0, 92, 650, 200);
		layeredPane.add(panelPodio);
		panelPodio.setLayout(new GridLayout(1, 3, 3, 3));

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
		lblModalidadLbl.setBounds(20, 180, 230, 25);
		panelDetalle.add(lblModalidadLbl);

		modalidadTxt = new RoundedTextField(20);
		modalidadTxt.setText("MODALIDAD");
		modalidadTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		modalidadTxt.setEditable(false);
		modalidadTxt.setBackground(new Color(204, 204, 204));
		modalidadTxt.setBounds(20, 206, 230, 34);
		panelDetalle.add(modalidadTxt);

		JLabel lblExperienciaLbl = new JLabel("AÑOS DE EXPERIENCIA:");
		lblExperienciaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExperienciaLbl.setForeground(TEXTO_OSCURO);
		lblExperienciaLbl.setBounds(285, 180, 230, 25);
		panelDetalle.add(lblExperienciaLbl);

		experienciaTxt = new RoundedTextField(20);
		experienciaTxt.setText("EXPERIENCIA");
		experienciaTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		experienciaTxt.setEditable(false);
		experienciaTxt.setBackground(new Color(204, 204, 204));
		experienciaTxt.setBounds(285, 206, 230, 34);
		panelDetalle.add(experienciaTxt);

		JLabel lblEdadLbl = new JLabel("EDAD:");
		lblEdadLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEdadLbl.setForeground(TEXTO_OSCURO);
		lblEdadLbl.setBounds(20, 250, 230, 25);
		panelDetalle.add(lblEdadLbl);

		edadTxt = new RoundedTextField(20);
		edadTxt.setText("EDAD");
		edadTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		edadTxt.setEditable(false);
		edadTxt.setBackground(new Color(204, 204, 204));
		edadTxt.setBounds(20, 276, 230, 34);
		panelDetalle.add(edadTxt);

		JLabel lblPaisLbl = new JLabel("PAIS:");
		lblPaisLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPaisLbl.setForeground(TEXTO_OSCURO);
		lblPaisLbl.setBounds(285, 250, 230, 25);
		panelDetalle.add(lblPaisLbl);

		paisTxt = new RoundedTextField(20);
		paisTxt.setText("PAIS");
		paisTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paisTxt.setEditable(false);
		paisTxt.setBackground(new Color(204, 204, 204));
		paisTxt.setBounds(285, 276, 230, 34);
		panelDetalle.add(paisTxt);

		JLabel lblSexoLbl = new JLabel("SEXO:");
		lblSexoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSexoLbl.setForeground(TEXTO_OSCURO);
		lblSexoLbl.setBounds(20, 320, 230, 25);
		panelDetalle.add(lblSexoLbl);

		sexoTxt = new RoundedTextField(20);
		sexoTxt.setText("SEXO");
		sexoTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sexoTxt.setEditable(false);
		sexoTxt.setBackground(new Color(204, 204, 204));
		sexoTxt.setBounds(20, 346, 230, 34);
		panelDetalle.add(sexoTxt);

		JLabel lblDispViajarLbl = new JLabel("DISPONIBILIDAD DE VIAJE:");
		lblDispViajarLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDispViajarLbl.setForeground(TEXTO_OSCURO);
		lblDispViajarLbl.setBounds(285, 320, 230, 25);
		panelDetalle.add(lblDispViajarLbl);

		dispViajarTxt = new RoundedTextField(20);
		dispViajarTxt.setText("DISP. VIAJE");
		dispViajarTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dispViajarTxt.setEditable(false);
		dispViajarTxt.setBackground(new Color(204, 204, 204));
		dispViajarTxt.setBounds(285, 346, 230, 34);
		panelDetalle.add(dispViajarTxt);

		JLabel lblDispResidenciaLbl = new JLabel("DISPONIBILIDAD DE MUDANZA:");
		lblDispResidenciaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDispResidenciaLbl.setForeground(TEXTO_OSCURO);
		lblDispResidenciaLbl.setBounds(20, 390, 280, 25);
		panelDetalle.add(lblDispResidenciaLbl);

		dispResidenciaTxt = new RoundedTextField(20);
		dispResidenciaTxt.setText("DISP. MUDANZA");
		dispResidenciaTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dispResidenciaTxt.setEditable(false);
		dispResidenciaTxt.setBackground(new Color(204, 204, 204));
		dispResidenciaTxt.setBounds(20, 416, 230, 34);
		panelDetalle.add(dispResidenciaTxt);
		
		RoundedButton btnNewButton = new RoundedButton("New button", 30);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( personaActual != null )
				{
					RegSolicitudCentro solicitudCentro = new RegSolicitudCentro(oferta, personaActual, VentanaMatcheo.this);
					solicitudCentro.setModal(true);
					solicitudCentro.setVisible(true);
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(50, 205, 50));
		btnNewButton.setText("ACEPTAR");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnNewButton.setBounds(64, 472, 181, 49);
		panelDetalle.add(btnNewButton);
		
		RoundedButton rndbtnRechazar = new RoundedButton("New button", 30);
		rndbtnRechazar.setBackground(new Color(255, 69, 0));
		rndbtnRechazar.setForeground(new Color(255, 255, 255));
		rndbtnRechazar.setText("RECHAZAR");
		rndbtnRechazar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		rndbtnRechazar.setBounds(309, 472, 181, 49);
		panelDetalle.add(rndbtnRechazar);

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

		try {
			listaCoincidencias = BolsaTrabajo.getInstance().calcularCoincidencia(ofertaActual.getPuesto());
			cargarPodio(listaCoincidencias);	
    		cargarListado(listaCoincidencias);
		} catch (NullPointerException npe) {
			System.out.println("Error");
		}
	}

	public void cargarPodio(ArrayList<Coincidencia> coincidencia) {
		panelPodio.removeAll();

		int cantidadPodio = Math.min(3, coincidencia.size());
		for (int i = 0; i < cantidadPodio; i++) {
			Coincidencia c = coincidencia.get(i);
			panelPodio.add(crearTarjetaPodio(c.getPersona(), c.getPorcentaje(), i + 1));
		}

		panelPodio.revalidate();
		panelPodio.repaint();
	}

	public void cargarListado(ArrayList<Coincidencia> coincidencia) {
		panelListado.removeAll();

		for (int i = 3; i < coincidencia.size(); i++) {
			Coincidencia c = coincidencia.get(i);
			panelListado.add(crearTarjetaListado(c));
		}

		panelListado.revalidate();
		panelListado.repaint();
	}

	private JPanel crearTarjetaPodio(Persona p, float porcentaje, int posicion) {

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

		String nombreCompleto = p.getNombre() + " " + p.getApellido();

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
				mostrarDetalle(p, ofertaActual);
				personaActual = p;
			}
		});

	    return tarjeta;
	}

	private JPanel crearTarjetaListado(final Coincidencia c) {
		final Persona p = c.getPersona();

		JPanel tarjeta = new JPanel();
		tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
		tarjeta.setBackground(TARJETA_BLANCA);
		tarjeta.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		tarjeta.setAlignmentX(Component.LEFT_ALIGNMENT);
		tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

		JLabel lblCandidato = new JLabel(p.getNombre() + " " + p.getApellido());
		lblCandidato.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCandidato.setForeground(AZUL_PRINCIPAL);
		lblCandidato.setBorder(new EmptyBorder(8, 12, 2, 12));
		lblCandidato.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblPuesto = new JLabel(ofertaActual.getPuesto());
		lblPuesto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPuesto.setForeground(TEXTO_OSCURO);
		lblPuesto.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblPuesto.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblPorcentaje = new JLabel("Compatibilidad: " + Math.round(c.getPorcentaje()) + "%");
		lblPorcentaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPorcentaje.setForeground(new Color(150, 150, 150));
		lblPorcentaje.setBorder(new EmptyBorder(2, 12, 8, 12));
		lblPorcentaje.setAlignmentX(Component.LEFT_ALIGNMENT);

		tarjeta.add(lblCandidato);
		tarjeta.add(lblPuesto);
		tarjeta.add(lblPorcentaje);

		MouseAdapter listenerTarjeta = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarDetalle(p, ofertaActual);
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
		lblPuesto.addMouseListener(listenerTarjeta);
		lblPorcentaje.addMouseListener(listenerTarjeta);

		return tarjeta;
	}

	public void mostrarDetalle(Persona p, Oferta oferta) {

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

	public void eliminarCandidato(Persona p) {
		if (listaCoincidencias == null || p == null) return;

		listaCoincidencias.removeIf(c -> c.getPersona() == p);

		cargarPodio(listaCoincidencias);
		cargarListado(listaCoincidencias);

		if (p.equals(personaActual)) {
			personaActual = null;
		}
	}
}