package visual;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

	private static final Color VERDE = new Color(0x0F, 0x8B, 0x8D);
	private static final Color ROJO = new Color(0xC0, 0x5B, 0x5B);

	// Labels del panel de detalle que se actualizan al hacer clic
	private JLabel lblNombreCandidato;
	private JLabel lblApellidoCandidato;
	private JLabel lblProfesion;
	private JLabel lblPais;
	private JLabel lblModalidadDelEmpleado;
	private JLabel lblCantYearsExp;
	private JLabel lblXAos;
	private JLabel lblPaisDelEmpleado;
	private JLabel lblSexoDelEmpleado;
	private JLabel lblCantidad;
	private JLabel lblCantidad_1;
	private JLabel lblProfesionDelEmpleado;

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
		panelDetalle.setBackground(Color.WHITE);
		panelDetalle.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelDetalle.setBounds(700, 92, 462, 551);
		layeredPane.add(panelDetalle);
		panelDetalle.setLayout(null);
		
		lblNombreCandidato = new JLabel("NOMBRE  NOMBRE");
		lblNombreCandidato.setForeground(new Color(0, 0, 0));
		lblNombreCandidato.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblNombreCandidato.setBounds(159, 36, 238, 19);
		panelDetalle.add(lblNombreCandidato);
		
		
		ImageIcon iconoEmpresaIcon = new ImageIcon(getClass().getResource("/imagenes/iconoProfesion.png"));
		Image iconoEmpresaImg = iconoEmpresaIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		JLabel panel_2 = new JLabel(new ImageIcon(iconoEmpresaImg));
		panel_2.setBounds(41, 25, 99, 90);
		panelDetalle.add(panel_2);
		
		lblApellidoCandidato = new JLabel("APELLIDO  APELLIDO");
		lblApellidoCandidato.setForeground(Color.BLACK);
		lblApellidoCandidato.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblApellidoCandidato.setBounds(159, 57, 307, 19);
		panelDetalle.add(lblApellidoCandidato);
		
		lblProfesion = new JLabel("nivel (Univ, Obrero, Tecnico)");
		lblProfesion.setForeground(Color.BLACK);
		lblProfesion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProfesion.setBounds(159, 80, 196, 19);
		panelDetalle.add(lblProfesion);
		
		lblPais = new JLabel("Pais");
		lblPais.setForeground(Color.BLACK);
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPais.setBounds(159, 96, 93, 19);
		panelDetalle.add(lblPais);
		
		JLabel lblNombreNombre = new JLabel("MODALIDAD:");
		lblNombreNombre.setForeground(Color.BLACK);
		lblNombreNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNombreNombre.setBounds(53, 193, 103, 19);
		panelDetalle.add(lblNombreNombre);
		
		lblModalidadDelEmpleado = new JLabel("Modalidad del empleado");
		lblModalidadDelEmpleado.setForeground(Color.BLACK);
		lblModalidadDelEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblModalidadDelEmpleado.setBounds(158, 193, 176, 19);
		panelDetalle.add(lblModalidadDelEmpleado);
		
		JLabel lblModalidad = new JLabel("AÑOS DE EXPERIENCIA:");
		lblModalidad.setForeground(Color.BLACK);
		lblModalidad.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblModalidad.setBounds(52, 230, 182, 19);
		panelDetalle.add(lblModalidad);
		
		lblCantYearsExp = new JLabel("cantidad");
		lblCantYearsExp.setForeground(Color.BLACK);
		lblCantYearsExp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCantYearsExp.setBounds(233, 230, 64, 19);
		panelDetalle.add(lblCantYearsExp);
		
		JLabel lblEdad = new JLabel("EDAD:");
		lblEdad.setForeground(Color.BLACK);
		lblEdad.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblEdad.setBounds(53, 266, 54, 19);
		panelDetalle.add(lblEdad);
		
		lblXAos = new JLabel("x años");
		lblXAos.setForeground(Color.BLACK);
		lblXAos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblXAos.setBounds(110, 266, 196, 19);
		panelDetalle.add(lblXAos);
		
		JLabel lblPais_1 = new JLabel("PAIS:");
		lblPais_1.setForeground(Color.BLACK);
		lblPais_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPais_1.setBounds(53, 301, 48, 19);
		panelDetalle.add(lblPais_1);
		
		lblPaisDelEmpleado = new JLabel("Pais del empleado");
		lblPaisDelEmpleado.setForeground(Color.BLACK);
		lblPaisDelEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPaisDelEmpleado.setBounds(110, 301, 196, 19);
		panelDetalle.add(lblPaisDelEmpleado);
		
		JLabel lblPais_1_1 = new JLabel("SEXO:");
		lblPais_1_1.setForeground(Color.BLACK);
		lblPais_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPais_1_1.setBounds(53, 339, 54, 19);
		panelDetalle.add(lblPais_1_1);
		
		lblSexoDelEmpleado = new JLabel("Sexo del empleado");
		lblSexoDelEmpleado.setForeground(Color.BLACK);
		lblSexoDelEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSexoDelEmpleado.setBounds(110, 339, 196, 19);
		panelDetalle.add(lblSexoDelEmpleado);
		
		JLabel lblDisponibilidadParaViajar = new JLabel("DISPONIBILIDAD PARA VIAJAR:");
		lblDisponibilidadParaViajar.setForeground(Color.BLACK);
		lblDisponibilidadParaViajar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDisponibilidadParaViajar.setBounds(52, 375, 237, 19);
		panelDetalle.add(lblDisponibilidadParaViajar);
		
		lblCantidad = new JLabel("Si / No  ");
		lblCantidad.setForeground(Color.BLACK);
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCantidad.setBounds(298, 375, 79, 19);
		panelDetalle.add(lblCantidad);
		
		JLabel lblDisponibilidadDeResidir = new JLabel("DISPONIBILIDAD DE RESIDIR:");
		lblDisponibilidadDeResidir.setForeground(Color.BLACK);
		lblDisponibilidadDeResidir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDisponibilidadDeResidir.setBounds(53, 411, 237, 19);
		panelDetalle.add(lblDisponibilidadDeResidir);
		
		lblCantidad_1 = new JLabel("Si / No  ");
		lblCantidad_1.setForeground(Color.BLACK);
		lblCantidad_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCantidad_1.setBounds(288, 411, 79, 19);
		panelDetalle.add(lblCantidad_1);
		
		JLabel lblProfesion_1 = new JLabel("PROFESION:");
		lblProfesion_1.setForeground(Color.BLACK);
		lblProfesion_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblProfesion_1.setBounds(54, 156, 103, 19);
		panelDetalle.add(lblProfesion_1);
		
		lblProfesionDelEmpleado = new JLabel("Profesion del Empleado");
		lblProfesionDelEmpleado.setForeground(Color.BLACK);
		lblProfesionDelEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProfesionDelEmpleado.setBounds(149, 156, 176, 19);
		panelDetalle.add(lblProfesionDelEmpleado);
		
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

	/**
	 * Llena el podio con las solicitudes recibidas para la oferta dada.
	 * @param solicitudes lista de solicitudes YA ordenadas de mayor a menor % (viene de tu logica de matcheo)
	 * @param porcentajes porcentaje de cada solicitud, en el mismo orden que solicitudes
	 * @param oferta la oferta contra la que se esta comparando
	 */
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

	/**
	 * Actualiza el panel de detalle con los datos del candidato (sacados de la
	 * SolicitudEmp / Persona) comparados contra la oferta. Verde si coincide,
	 * rojo si no.
	 */
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

		lblNombreCandidato.setText(p.getNombre());
		lblApellidoCandidato.setText(p.getApellido());
		lblProfesion.setText(nivel);
		lblPais.setText(p.getPais());

		lblModalidadDelEmpleado.setText(sol.getModalidad());
		lblModalidadDelEmpleado.setForeground(
				sol.getModalidad() != null && sol.getModalidad().equalsIgnoreCase(oferta.getModalidad())
						? VERDE : ROJO);

		lblCantYearsExp.setText(p.getExperiencia().size() + "");

		lblXAos.setText(p.getFechaNacim() != null ? p.getFechaNacim().toString() : "");

		lblPaisDelEmpleado.setText(p.getPais());
		lblPaisDelEmpleado.setForeground(
				p.getPais() != null && p.getPais().equalsIgnoreCase(oferta.getPais())
						? VERDE : ROJO);

		lblSexoDelEmpleado.setText(p.getSexo());
		lblSexoDelEmpleado.setForeground(
				p.getSexo() != null && p.getSexo().equalsIgnoreCase(oferta.getSexo())
						? VERDE : ROJO);

		lblCantidad.setText(p.isDispViajar() ? "Si" : "No");
		lblCantidad.setForeground(p.isDispViajar() ? VERDE : ROJO);

		lblCantidad_1.setText(p.isDispResidencia() ? "Si" : "No");
		lblCantidad_1.setForeground(p.isDispResidencia() ? VERDE : ROJO);

		lblProfesionDelEmpleado.setText(profesion);
		lblProfesionDelEmpleado.setForeground(
				oferta.getPuesto() != null && oferta.getPuesto().equalsIgnoreCase(nivel)
						? VERDE : ROJO);
	}
}