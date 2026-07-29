package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

// --- Nuevos imports para cargar la foto (explorador NATIVO del sistema operativo) ---
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logico.Persona;
import logico.Universitario;
import logico.Tecnico;
import logico.Obrero;
import logico.Usuario;
import logico.BolsaTrabajo;

public class RegPersona extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final int RADIO = 20;

	private Image imagenFondo;

	private final JPanel panelFondo = new JPanel() {

		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			if (imagenFondo != null) {
				g.drawImage(
						imagenFondo,
						0,
						0,
						getWidth(),
						getHeight(),
						this
				);
			}
		}
	};

	private RoundedTextField nombreField;
	private RoundedTextField apellidoField;
	private RoundedTextField cedulaField;
	private RoundedTextField telefonoField;
	private RoundedTextField textField_4;
	private RoundedTextField textField_5;
	private JComboBox paisComboBox;
	private JSpinner fechaSpinner;
	private JComboBox paisComboBox_1;
	private JComboBox paisComboBox_1_1;
	private JComboBox paisComboBox_1_1_1;
	private RoundedButton registrarBtn;

	private JRadioButton rbUniversitario;
	private JRadioButton rbTecnico;
	private JRadioButton rbObrero;

	private JPanel pnUniversitario;
	private RoundedTextField carreraField;
	private RoundedTextField universidadField;

	private JPanel pnTecnico;
	private RoundedTextField especialidadField;
	private RoundedTextField politecnicoField;

	private JPanel pnObrero;
	private RoundedTextField profesionField;

	// --- Cuadro de foto del candidato ---
	private JLabel lblFoto;
	private String rutaFotoSeleccionada;

	private Persona myPersona = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			RegPersona dialog = new RegPersona(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegPersona(Persona persona) {

		myPersona = persona;

		setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoRegistrar.png")).getImage());

		if (myPersona == null) {
			setTitle("REGISTRAR NUEVA PERSONA");
		} else {
			setTitle("MODIFICAR PERSONA");
		}

		setBounds(100, 100, 450, 300);

		Dimension dim = getToolkit().getScreenSize();

		setSize(dim.width, dim.height - 38);
		setLocationRelativeTo(null);

		URL rutaImagenFondo = getClass().getResource("/imagenes/fondoRegPersona.png");

		if (rutaImagenFondo != null) {
			imagenFondo = new ImageIcon(rutaImagenFondo).getImage();
		}

		JLayeredPane layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(null);

		panelFondo.setBounds(0, 0, dim.width, dim.height - 38);
		layeredPane.add(panelFondo, JLayeredPane.DEFAULT_LAYER);
		panelFondo.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 741, 1150);
		panelFondo.add(panel);
		panel.setLayout(null);

		registrarBtn = new RoundedButton("REGISTRAR", RADIO);
		if (myPersona != null) {
			registrarBtn.setText("MODIFICAR");
		}
		System.out.println(BolsaTrabajo.getInstance().getUsuarios().size());
		System.out.println(BolsaTrabajo.getInstance().getUsuarios().get(2).getNombre());
		registrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// --- Validacion basica ---
				if (nombreField.getText().trim().isEmpty() || cedulaField.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"El nombre y la cedula no pueden estar vacios.",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (myPersona == null) {

					// --- Registrar nueva persona ---
					String identificador = BolsaTrabajo.getInstance().generarIdPersona();
					String cedula = cedulaField.getText();
					String nombre = nombreField.getText();
					String apellido = apellidoField.getText();
					String email = textField_5.getText();
					String direccion = textField_4.getText();
					String telefono = telefonoField.getText();
					String rutaImagen = rutaFotoSeleccionada;
					String pais = (String) paisComboBox.getSelectedItem();
					LocalDate fechaNacim = ((Date) fechaSpinner.getValue())
							.toInstant()
							.atZone(ZoneId.systemDefault())
							.toLocalDate();
					boolean dispViajar = "SI".equals(paisComboBox_1.getSelectedItem());
					boolean dispResidencia = "SI".equals(paisComboBox_1_1.getSelectedItem());
					boolean empleado = "SI".equals(paisComboBox_1_1_1.getSelectedItem());

					Persona nuevo = null;

					if (rbUniversitario.isSelected()) {
						nuevo = new Universitario(identificador, cedula, nombre, apellido, email, direccion, null,
								telefono, pais, rutaImagen, fechaNacim, dispViajar, dispResidencia, carreraField.getText(), universidadField.getText());
					}
					if (rbTecnico.isSelected()) {
						nuevo = new Tecnico(identificador, cedula, nombre, apellido, email, direccion, null,
								telefono, pais, rutaImagen, fechaNacim, dispViajar, dispResidencia, especialidadField.getText(), politecnicoField.getText());
					}
					if (rbObrero.isSelected()) {
						nuevo = new Obrero(identificador, cedula, nombre, apellido, email, direccion, null,
								telefono, pais, rutaImagen, fechaNacim, dispViajar, dispResidencia,
								profesionField.getText());
					}
					BolsaTrabajo.getInstance().registrarPersona(nuevo);
					Usuario user = BolsaTrabajo.getInstance().crearUsuario(email, null, nuevo);
					BolsaTrabajo.getInstance().registrarUsuario(user);
					BolsaTrabajo.guardarDatos();

					System.out.println(BolsaTrabajo.getInstance().getUsuarios().size());

					JOptionPane.showMessageDialog(null, "Candidato Registrado Exitosamente",
							"Registro", JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(null, "Nombre Usuario: " + user.getNombre() + "   " + "Contraseña: " + user.getPassword(),"Credenciales de Usuario", JOptionPane.INFORMATION_MESSAGE);

				} else {

					// --- Modificar persona existente ---
					myPersona.setNombre(nombreField.getText());
					myPersona.setApellido(apellidoField.getText());
					myPersona.setTelefono(telefonoField.getText());
					myPersona.setDireccion(textField_4.getText());
					myPersona.setEmail(textField_5.getText());
					myPersona.setPais((String) paisComboBox.getSelectedItem());
					myPersona.setFechaNacim(
							((Date) fechaSpinner.getValue())
									.toInstant()
									.atZone(ZoneId.systemDefault())
									.toLocalDate()
					);
					myPersona.setDispViajar("SI".equals(paisComboBox_1.getSelectedItem()));
					myPersona.setDispResidencia("SI".equals(paisComboBox_1_1.getSelectedItem()));
					myPersona.setEmpleado("SI".equals(paisComboBox_1_1_1.getSelectedItem()));

					if (myPersona instanceof Universitario) {
						((Universitario) myPersona).setCarrera(carreraField.getText());
						((Universitario) myPersona).setUniversidad(universidadField.getText());
					} else if (myPersona instanceof Tecnico) {
						((Tecnico) myPersona).setEspecialidad(especialidadField.getText());
						((Tecnico) myPersona).setPolitecnico(politecnicoField.getText());
					} else if (myPersona instanceof Obrero) {
						((Obrero) myPersona).setProfesion(profesionField.getText());
					}
					BolsaTrabajo.getInstance().modificarPersona(myPersona);

					JOptionPane.showMessageDialog(null, "Candidato Modificado Exitosamente",
							"Modificacion", JOptionPane.INFORMATION_MESSAGE);
				}

				dispose();
			}
		});
		registrarBtn.setBackground(new Color(255, 153, 51));
		registrarBtn.setForeground(new Color(255, 255, 255));
		registrarBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		registrarBtn.setBounds(140, 900, 166, 49);
		panel.add(registrarBtn);

		RoundedButton cancelarBtn = new RoundedButton("CANCELAR", RADIO);
		cancelarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelarBtn.setBackground(new Color(255, 0, 0));
		cancelarBtn.setForeground(new Color(255, 255, 255));
		cancelarBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		cancelarBtn.setBounds(425, 900, 166, 49);
		panel.add(cancelarBtn);

		JLabel lblNombresYApellidos = new JLabel("NOMBRE(S):");
		lblNombresYApellidos.setForeground(Color.WHITE);
		lblNombresYApellidos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombresYApellidos.setBounds(42, 86, 226, 19);
		panel.add(lblNombresYApellidos);

		nombreField = new RoundedTextField(RADIO);
		nombreField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nombreField.setColumns(10);
		nombreField.setBackground(new Color(153, 255, 255));
		nombreField.setBounds(42, 115, 334, 42);
		panel.add(nombreField);

		JLabel lblApellidos = new JLabel("APELLIDO(S):");
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblApellidos.setBounds(42, 184, 226, 19);
		panel.add(lblApellidos);

		apellidoField = new RoundedTextField(RADIO);
		apellidoField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		apellidoField.setColumns(10);
		apellidoField.setBackground(new Color(153, 255, 255));
		apellidoField.setBounds(42, 213, 334, 42);
		panel.add(apellidoField);

		// --- Cuadro de foto del candidato (a la derecha, junto a apellido) ---
		JLabel lblFotoTitulo = new JLabel("FOTO:");
		lblFotoTitulo.setForeground(Color.WHITE);
		lblFotoTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFotoTitulo.setBounds(484, 56, 120, 19);
		panel.add(lblFotoTitulo);

		lblFoto = new JLabel();
		lblFoto.setText("CARGAR");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setOpaque(true);
		lblFoto.setBackground(new Color(102, 255, 255));
		lblFoto.setForeground(new Color(0, 0, 51));
		lblFoto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFoto.setBounds(484, 90, 200, 185);
		lblFoto.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblFoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( myPersona == null )
				{
					if ( nombreField.getText().trim().isEmpty() && cedulaField.getText().trim().isEmpty() )
					{
						JOptionPane.showMessageDialog(null, "Error debe ingresar un Nombre y una Cedula", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						rutaFotoSeleccionada = BolsaTrabajo.getInstance().buscarImagen(nombreField.getText(), cedulaField.getText());
						cargarFoto();
					}
				}
				else
				{
                    String rutaNueva = BolsaTrabajo.getInstance().buscarImagen(nombreField.getText(), cedulaField.getText());
                    if ( rutaNueva != null)
                    {
                        rutaFotoSeleccionada = rutaNueva;
                        cargarFoto();
                    }
                }
			}
		});
		panel.add(lblFoto);

		JLabel lblNombres = new JLabel("CEDULA:");
		lblNombres.setForeground(Color.WHITE);
		lblNombres.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombres.setBounds(329, 285, 226, 19);
		panel.add(lblNombres);

		cedulaField = new RoundedTextField(RADIO);
		cedulaField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cedulaField.setColumns(10);
		cedulaField.setBackground(new Color(153, 255, 255));
		cedulaField.setBounds(329, 314, 317, 42);
		panel.add(cedulaField);

		JLabel lblTelefono = new JLabel("TELEFONO:");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTelefono.setBounds(401, 391, 200, 19);
		panel.add(lblTelefono);

		telefonoField = new RoundedTextField(RADIO);
		telefonoField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		telefonoField.setColumns(10);
		telefonoField.setBackground(new Color(153, 255, 255));
		telefonoField.setBounds(398, 420, 248, 42);
		panel.add(telefonoField);

		JLabel lblDireccion = new JLabel("PROVINCIA - ESTADO:");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDireccion.setBounds(42, 285, 226, 19);
		panel.add(lblDireccion);

		textField_4 = new RoundedTextField(RADIO);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_4.setColumns(10);
		textField_4.setBackground(new Color(153, 255, 255));
		textField_4.setBounds(42, 314, 264, 42);
		panel.add(textField_4);

		JLabel lblPais = new JLabel("PAIS:");
		lblPais.setForeground(Color.WHITE);
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPais.setBounds(425, 498, 166, 19);
		panel.add(lblPais);

		paisComboBox = new JComboBox();
		paisComboBox.setModel(new DefaultComboBoxModel(new String[] {"Republica Dominicana", "Estados Unidos"}));
		paisComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paisComboBox.setBackground(new Color(102, 255, 255));
		paisComboBox.setBounds(425, 527, 259, 42);
		panel.add(paisComboBox);

		fechaSpinner = new JSpinner();
		fechaSpinner.setBackground(new Color(102, 255, 255));
		fechaSpinner.setFont(new Font("Tahoma", Font.PLAIN, 20));
		SpinnerDateModel modeloFecha = new SpinnerDateModel();
		fechaSpinner.setModel(modeloFecha);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(fechaSpinner, "dd/MM/yyyy");
		fechaSpinner.setEditor(editor);
		fechaSpinner.setBounds(217, 527, 173, 42);
		panel.add(fechaSpinner);

		JLabel lblDechaDeNacimiento = new JLabel("NACIMIENTO:");
		lblDechaDeNacimiento.setForeground(Color.WHITE);
		lblDechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDechaDeNacimiento.setBounds(217, 502, 226, 19);
		panel.add(lblDechaDeNacimiento);

		JLabel lblCorreoElectronico = new JLabel("CORREO ELECTRONICO:");
		lblCorreoElectronico.setForeground(Color.WHITE);
		lblCorreoElectronico.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCorreoElectronico.setBounds(42, 391, 226, 19);
		panel.add(lblCorreoElectronico);

		textField_5 = new RoundedTextField(RADIO);
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_5.setColumns(10);
		textField_5.setBackground(new Color(153, 255, 255));
		textField_5.setBounds(42, 420, 334, 42);
		panel.add(textField_5);

		JLabel lblpuedeViajar = new JLabel("DISP. DE VIAJE:");
		lblpuedeViajar.setForeground(Color.WHITE);
		lblpuedeViajar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblpuedeViajar.setBounds(42, 498, 271, 19);
		panel.add(lblpuedeViajar);

		paisComboBox_1 = new JComboBox();
		paisComboBox_1.setModel(new DefaultComboBoxModel(new String[] {"SI", "NO"}));
		paisComboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paisComboBox_1.setBackground(new Color(102, 255, 255));
		paisComboBox_1.setBounds(42, 527, 135, 42);
		panel.add(paisComboBox_1);

		JLabel lblpuedeMudarse = new JLabel("¿PUEDE MUDARSE?");
		lblpuedeMudarse.setForeground(Color.WHITE);
		lblpuedeMudarse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblpuedeMudarse.setBounds(42, 595, 166, 19);
		panel.add(lblpuedeMudarse);

		paisComboBox_1_1 = new JComboBox();
		paisComboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"SI", "NO"}));
		paisComboBox_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paisComboBox_1_1.setBackground(new Color(102, 255, 255));
		paisComboBox_1_1.setBounds(42, 624, 190, 42);
		panel.add(paisComboBox_1_1);

		JLabel lblestaEmpleado = new JLabel("¿ESTA EMPLEADO?");
		lblestaEmpleado.setForeground(Color.WHITE);
		lblestaEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblestaEmpleado.setBounds(277, 595, 166, 19);
		panel.add(lblestaEmpleado);

		paisComboBox_1_1_1 = new JComboBox();
		paisComboBox_1_1_1.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		paisComboBox_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paisComboBox_1_1_1.setBackground(new Color(102, 255, 255));
		paisComboBox_1_1_1.setBounds(277, 624, 190, 42);
		panel.add(paisComboBox_1_1_1);

		// --- Seleccion de tipo de candidato (mismo patron que RegQueso) ---

		JLabel lblTipoCandidato = new JLabel("TIPO DE CANDIDATO:");
		lblTipoCandidato.setForeground(Color.WHITE);
		lblTipoCandidato.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTipoCandidato.setBounds(42, 690, 271, 19);
		panel.add(lblTipoCandidato);

		rbUniversitario = new JRadioButton("UNIVERSITARIO");
		rbUniversitario.setForeground(Color.WHITE);
		rbUniversitario.setBackground(new Color(0, 0, 51));
		rbUniversitario.setFont(new Font("Tahoma", Font.PLAIN, 16));

		rbTecnico = new JRadioButton("TECNICO");
		rbTecnico.setForeground(Color.WHITE);
		rbTecnico.setBackground(new Color(0, 0, 51));
		rbTecnico.setFont(new Font("Tahoma", Font.PLAIN, 16));

		rbObrero = new JRadioButton("OBRERO");
		rbObrero.setForeground(Color.WHITE);
		rbObrero.setBackground(new Color(0, 0, 51));
		rbObrero.setFont(new Font("Tahoma", Font.PLAIN, 16));

		ButtonGroup btnGrupoTipo = new ButtonGroup();
		btnGrupoTipo.add(rbUniversitario);
		btnGrupoTipo.add(rbTecnico);
		btnGrupoTipo.add(rbObrero);

		rbUniversitario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rbUniversitario.isSelected()) {
					pnUniversitario.setVisible(true);
					pnTecnico.setVisible(false);
					pnObrero.setVisible(false);
				}
			}
		});
		rbUniversitario.setBounds(42, 719, 190, 20);
		panel.add(rbUniversitario);

		rbTecnico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rbTecnico.isSelected()) {
					pnTecnico.setVisible(true);
					pnUniversitario.setVisible(false);
					pnObrero.setVisible(false);
				}
			}
		});
		rbTecnico.setBounds(260, 719, 150, 20);
		panel.add(rbTecnico);

		rbObrero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rbObrero.isSelected()) {
					pnObrero.setVisible(true);
					pnUniversitario.setVisible(false);
					pnTecnico.setVisible(false);
				}
			}
		});
		rbObrero.setBounds(440, 719, 150, 20);
		panel.add(rbObrero);

		pnUniversitario = new JPanel();
		pnUniversitario.setBackground(new Color(0, 0, 51));
		pnUniversitario.setBounds(42, 755, 640, 100);
		panel.add(pnUniversitario);
		pnUniversitario.setLayout(null);

		JLabel lblCarrera = new JLabel("CARRERA:");
		lblCarrera.setForeground(Color.WHITE);
		lblCarrera.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCarrera.setBounds(0, 10, 150, 19);
		pnUniversitario.add(lblCarrera);

		carreraField = new RoundedTextField(RADIO);
		carreraField.setBackground(new Color(153, 255, 255));
		carreraField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		carreraField.setBounds(0, 35, 280, 38);
		pnUniversitario.add(carreraField);

		JLabel lblUniversidad = new JLabel("UNIVERSIDAD:");
		lblUniversidad.setForeground(Color.WHITE);
		lblUniversidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUniversidad.setBounds(320, 10, 150, 19);
		pnUniversitario.add(lblUniversidad);

		universidadField = new RoundedTextField(RADIO);
		universidadField.setBackground(new Color(153, 255, 255));
		universidadField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		universidadField.setBounds(320, 35, 280, 38);
		pnUniversitario.add(universidadField);

		pnTecnico = new JPanel();
		pnTecnico.setBackground(new Color(0, 0, 51));
		pnTecnico.setBounds(42, 755, 640, 100);
		panel.add(pnTecnico);
		pnTecnico.setLayout(null);

		JLabel lblEspecialidad = new JLabel("ESPECIALIDAD:");
		lblEspecialidad.setForeground(Color.WHITE);
		lblEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEspecialidad.setBounds(0, 10, 150, 19);
		pnTecnico.add(lblEspecialidad);

		especialidadField = new RoundedTextField(RADIO);
		especialidadField.setBackground(new Color(153, 255, 255));
		especialidadField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		especialidadField.setBounds(0, 35, 280, 38);
		pnTecnico.add(especialidadField);

		JLabel lblPolitecnico = new JLabel("POLITECNICO:");
		lblPolitecnico.setForeground(Color.WHITE);
		lblPolitecnico.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPolitecnico.setBounds(320, 10, 150, 19);
		pnTecnico.add(lblPolitecnico);

		politecnicoField = new RoundedTextField(RADIO);
		politecnicoField.setBackground(new Color(153, 255, 255));
		politecnicoField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		politecnicoField.setBounds(320, 35, 280, 38);
		pnTecnico.add(politecnicoField);

		pnObrero = new JPanel();
		pnObrero.setBackground(new Color(0, 0, 51));
		pnObrero.setBounds(42, 755, 640, 100);
		panel.add(pnObrero);
		pnObrero.setLayout(null);

		JLabel lblProfesion = new JLabel("PROFESION U OFICIO:");
		lblProfesion.setForeground(Color.WHITE);
		lblProfesion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProfesion.setBounds(0, 10, 200, 19);
		pnObrero.add(lblProfesion);

		profesionField = new RoundedTextField(RADIO);
		profesionField.setBackground(new Color(153, 255, 255));
		profesionField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		profesionField.setBounds(0, 35, 280, 38);
		pnObrero.add(profesionField);

		aplicarMascaraCedula(cedulaField);
		aplicarMascaraTelefono(telefonoField);
		aplicarPlaceholder(textField_5, "ejemplo@correo.com");

		inicializarVentana();
		cargarDatos();
	}

	private void cargarFoto() {
		ImageIcon icon = new ImageIcon(rutaFotoSeleccionada);
		Image escalada = icon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH);

		lblFoto.setIcon(new ImageIcon(escalada));
		lblFoto.setText("");
		
	}

	private void inicializarVentana() {
		rbUniversitario.setSelected(true);
		pnUniversitario.setVisible(true);
		pnTecnico.setVisible(false);
		pnObrero.setVisible(false);
	}

	private void cargarDatos() {
		if (myPersona != null) {

			nombreField.setText(myPersona.getNombre());
			apellidoField.setText(myPersona.getApellido());
			cedulaField.setText(myPersona.getCedula());
			cedulaField.setEditable(false);
			cedulaField.setBackground(new Color(192, 192, 192));
			telefonoField.setText(myPersona.getTelefono());
			textField_4.setText(myPersona.getDireccion());
			textField_5.setText(myPersona.getEmail());
			textField_5.setForeground(Color.BLACK);
			paisComboBox.setSelectedItem(myPersona.getPais());

			if (myPersona.getFechaNacim() != null) {
				Date fecha = Date.from(
						myPersona.getFechaNacim()
								.atStartOfDay(ZoneId.systemDefault())
								.toInstant()
				);
				fechaSpinner.setValue(fecha);
			}

			paisComboBox_1.setSelectedItem(myPersona.isDispViajar() ? "SI" : "NO");
			paisComboBox_1_1.setSelectedItem(myPersona.isDispResidencia() ? "SI" : "NO");
			paisComboBox_1_1_1.setSelectedItem(myPersona.isEmpleado() ? "SI" : "NO");

			if (myPersona instanceof Universitario) {
				rbUniversitario.setSelected(true);
				carreraField.setText(((Universitario) myPersona).getCarrera());
				universidadField.setText(((Universitario) myPersona).getUniversidad());
				pnUniversitario.setVisible(true);
				pnTecnico.setVisible(false);
				pnObrero.setVisible(false);
			}
			if (myPersona instanceof Tecnico) {
				rbTecnico.setSelected(true);
				especialidadField.setText(((Tecnico) myPersona).getEspecialidad());
				politecnicoField.setText(((Tecnico) myPersona).getPolitecnico());
				pnTecnico.setVisible(true);
				pnUniversitario.setVisible(false);
				pnObrero.setVisible(false);
			}
			if (myPersona instanceof Obrero) {
				rbObrero.setSelected(true);
				profesionField.setText(((Obrero) myPersona).getProfesion());
				pnObrero.setVisible(true);
				pnUniversitario.setVisible(false);
				pnTecnico.setVisible(false);
			}

			rbUniversitario.setEnabled(false);
			rbTecnico.setEnabled(false);
			rbObrero.setEnabled(false);
		}
	}

	private void aplicarMascaraCedula(JTextField campo) {
		aplicarMascaraNumerica(campo, 11, 3, 10);
	}

	private void aplicarMascaraTelefono(JTextField campo) {
		aplicarMascaraNumerica(campo, 10, 3, 6);
	}

	private void aplicarMascaraNumerica(
			JTextField campo,
			int maxDigitos,
			int... posicionesGuion) {

		PlainDocument documento = (PlainDocument) campo.getDocument();

		documento.setDocumentFilter(new DocumentFilter() {

			private String formatear(String texto) {

				String digitos = texto.replaceAll("\\D", "");

				if (digitos.length() > maxDigitos) {
					digitos = digitos.substring(0, maxDigitos);
				}

				StringBuilder resultado = new StringBuilder(digitos);
				int guionesAgregados = 0;

				for (int posicion : posicionesGuion) {

					if (digitos.length() > posicion) {
						resultado.insert(
								posicion + guionesAgregados,
								"-"
						);

						guionesAgregados++;
					}
				}

				return resultado.toString();
			}

			@Override
			public void insertString(
					FilterBypass fb,
					int offset,
					String texto,
					AttributeSet atributos)
					throws BadLocationException {

				replace(
						fb,
						offset,
						0,
						texto,
						atributos
				);
			}

			@Override
			public void replace(
					FilterBypass fb,
					int offset,
					int length,
					String texto,
					AttributeSet atributos)
					throws BadLocationException {

				String actual = fb.getDocument().getText(
						0,
						fb.getDocument().getLength()
				);

				StringBuilder nuevoTexto = new StringBuilder(actual);

				nuevoTexto.replace(
						offset,
						offset + length,
						texto == null ? "" : texto
				);

				String textoFormateado =
						formatear(nuevoTexto.toString());

				fb.replace(
						0,
						fb.getDocument().getLength(),
						textoFormateado,
						atributos
				);
			}

			@Override
			public void remove(
					FilterBypass fb,
					int offset,
					int length)
					throws BadLocationException {

				replace(
						fb,
						offset,
						length,
						"",
						null
				);
			}
		});
	}

	private void aplicarPlaceholder(
			JTextField campo,
			String textoEjemplo) {

		Color colorNormal = campo.getForeground();
		Color colorPlaceholder = Color.BLACK;

		campo.setText(textoEjemplo);
		campo.setForeground(colorPlaceholder);

		campo.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {

				if (campo.getText().equals(textoEjemplo)) {
					campo.setText("");
					campo.setForeground(colorNormal);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {

				if (campo.getText().isEmpty()) {
					campo.setText(textoEjemplo);
					campo.setForeground(colorPlaceholder);
				}
			}
		});
	}
}