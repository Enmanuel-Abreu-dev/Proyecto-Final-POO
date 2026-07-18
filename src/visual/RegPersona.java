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

public class RegPersona extends JDialog {

	private static final long serialVersionUID = 1L;

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

	private JTextField nombreField;
	private JTextField apellidoField;
	private JTextField cedulaField;
	private JTextField telefonoField;
	private JTextField textField_4;
	private JTextField textField_5;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			RegPersona dialog = new RegPersona();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegPersona() {
		setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoRegistrar.png")).getImage());
		setTitle("REGISTRAR NUEVA PERSONA");

		setBounds(100, 100, 450, 300);

		Dimension dim = getToolkit().getScreenSize();

		setSize(dim.width, dim.height - 38);
		setLocationRelativeTo(null);

		URL rutaImagen = getClass().getResource("/imagenes/fondoRegPersona.png");

		if (rutaImagen != null) {
			imagenFondo = new ImageIcon(rutaImagen).getImage();
		}

		JLayeredPane layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(null);

		panelFondo.setBounds(0, 0, dim.width, dim.height - 38);
		layeredPane.add(panelFondo, JLayeredPane.DEFAULT_LAYER);
		panelFondo.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 741, 1000);
		panelFondo.add(panel);
		panel.setLayout(null);

		JButton registrarBtn = new JButton("REGISTRAR");
		registrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		registrarBtn.setBackground(new Color(255, 153, 51));
		registrarBtn.setForeground(new Color(255, 255, 255));
		registrarBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		registrarBtn.setBounds(140, 800, 166, 49);
		panel.add(registrarBtn);

		JButton cancelarBtn = new JButton("CANCELAR");
		cancelarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelarBtn.setBackground(new Color(255, 0, 0));
		cancelarBtn.setForeground(new Color(255, 255, 255));
		cancelarBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		cancelarBtn.setBounds(425, 800, 166, 49);
		panel.add(cancelarBtn);

		JLabel lblNombresYApellidos = new JLabel("NOMBRE(S):");
		lblNombresYApellidos.setForeground(Color.WHITE);
		lblNombresYApellidos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombresYApellidos.setBounds(42, 86, 226, 19);
		panel.add(lblNombresYApellidos);

		nombreField = new JTextField();
		nombreField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nombreField.setColumns(10);
		nombreField.setBackground(new Color(153, 255, 255));
		nombreField.setBounds(42, 115, 271, 42);
		panel.add(nombreField);

		JLabel lblApellidos = new JLabel("APELLIDO(S):");
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblApellidos.setBounds(408, 86, 226, 19);
		panel.add(lblApellidos);

		apellidoField = new JTextField();
		apellidoField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		apellidoField.setColumns(10);
		apellidoField.setBackground(new Color(153, 255, 255));
		apellidoField.setBounds(408, 115, 271, 42);
		panel.add(apellidoField);

		JLabel lblNombres = new JLabel("CEDULA:");
		lblNombres.setForeground(Color.WHITE);
		lblNombres.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombres.setBounds(42, 187, 226, 19);
		panel.add(lblNombres);

		cedulaField = new JTextField();
		cedulaField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cedulaField.setColumns(10);
		cedulaField.setBackground(new Color(153, 255, 255));
		cedulaField.setBounds(42, 216, 271, 42);
		panel.add(cedulaField);

		JLabel lblTelefono = new JLabel("TELEFONO:");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTelefono.setBounds(408, 187, 226, 19);
		panel.add(lblTelefono);

		telefonoField = new JTextField();
		telefonoField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		telefonoField.setColumns(10);
		telefonoField.setBackground(new Color(153, 255, 255));
		telefonoField.setBounds(408, 216, 271, 42);
		panel.add(telefonoField);

		JLabel lblDireccion = new JLabel("DIRECCION:");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDireccion.setBounds(42, 285, 226, 19);
		panel.add(lblDireccion);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_4.setColumns(10);
		textField_4.setBackground(new Color(153, 255, 255));
		textField_4.setBounds(42, 314, 271, 42);
		panel.add(textField_4);

		JLabel lblPais = new JLabel("PAIS:");
		lblPais.setForeground(Color.WHITE);
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPais.setBounds(408, 285, 166, 19);
		panel.add(lblPais);

		JComboBox paisComboBox = new JComboBox();
		paisComboBox.setModel(new DefaultComboBoxModel(new String[] {"Republica Dominicana", "Estados Unidos"}));
		paisComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paisComboBox.setBackground(new Color(102, 255, 255));
		paisComboBox.setBounds(408, 314, 271, 42);
		panel.add(paisComboBox);

		JSpinner fechaSpinner = new JSpinner();
		fechaSpinner.setBackground(new Color(102, 255, 255));
		fechaSpinner.setFont(new Font("Tahoma", Font.PLAIN, 20));
		SpinnerDateModel modeloFecha = new SpinnerDateModel();
		fechaSpinner.setModel(modeloFecha);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(fechaSpinner, "dd/MM/yyyy");
		fechaSpinner.setEditor(editor);
		fechaSpinner.setBounds(408, 527, 271, 42);
		panel.add(fechaSpinner);

		JLabel lblDechaDeNacimiento = new JLabel("FECHA DE NACIMIENTO:");
		lblDechaDeNacimiento.setForeground(Color.WHITE);
		lblDechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDechaDeNacimiento.setBounds(408, 502, 226, 19);
		panel.add(lblDechaDeNacimiento);

		JLabel lblCorreoElectronico = new JLabel("CORREO ELECTRONICO:");
		lblCorreoElectronico.setForeground(Color.WHITE);
		lblCorreoElectronico.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCorreoElectronico.setBounds(42, 391, 226, 19);
		panel.add(lblCorreoElectronico);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_5.setColumns(10);
		textField_5.setBackground(new Color(153, 255, 255));
		textField_5.setBounds(42, 420, 334, 42);
		panel.add(textField_5);

		JLabel lblpuedeViajar = new JLabel("DISPONIBILIDAD DE VIAJE:");
		lblpuedeViajar.setForeground(Color.WHITE);
		lblpuedeViajar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblpuedeViajar.setBounds(42, 498, 271, 19);
		panel.add(lblpuedeViajar);

		JComboBox paisComboBox_1 = new JComboBox();
		paisComboBox_1.setModel(new DefaultComboBoxModel(new String[] {"SI", "NO"}));
		paisComboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paisComboBox_1.setBackground(new Color(102, 255, 255));
		paisComboBox_1.setBounds(42, 527, 244, 42);
		panel.add(paisComboBox_1);

		JLabel lblpuedeMudarse = new JLabel("¿PUEDE MUDARSE?");
		lblpuedeMudarse.setForeground(Color.WHITE);
		lblpuedeMudarse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblpuedeMudarse.setBounds(42, 595, 166, 19);
		panel.add(lblpuedeMudarse);

		JComboBox paisComboBox_1_1 = new JComboBox();
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

		JComboBox paisComboBox_1_1_1 = new JComboBox();
		paisComboBox_1_1_1.setModel(new DefaultComboBoxModel(new String[] {"NO", "SI"}));
		paisComboBox_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paisComboBox_1_1_1.setBackground(new Color(102, 255, 255));
		paisComboBox_1_1_1.setBounds(277, 624, 190, 42);
		panel.add(paisComboBox_1_1_1);

		JLabel lblContrasea = new JLabel("CONTRASEÑA:");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblContrasea.setBounds(420, 379, 166, 42);
		panel.add(lblContrasea);

		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(102, 255, 255));
		passwordField.setBounds(420, 420, 259, 42);
		panel.add(passwordField);

		aplicarMascaraCedula(cedulaField);
		aplicarMascaraTelefono(telefonoField);
		aplicarPlaceholder(textField_5, "ejemplo@correo.com");
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