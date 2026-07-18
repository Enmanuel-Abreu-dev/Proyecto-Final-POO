package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;
import javax.swing.JPasswordField;

// --- Nuevos imports agregados SOLO para dar formato visual a los campos ---
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegEmpresa extends JDialog {

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
    private JTextField postalField;
    private JTextField telefonoField;
    private JTextField correoField;
    private JTextField direccionField;
    private JTextField razonSocialField;
    private JTextField registroSocialField;
    private JSpinner cantVacantesSpinner;
    private JSpinner cantTrabsSpinner;
    private JComboBox sectorComboBox;
    private JComboBox paisComboBox;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        try {
            RegEmpresa dialog = new RegEmpresa();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public RegEmpresa() {
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoRegistrar.png")).getImage());
        setTitle("REGISTRAR NUEVA EMPRESA");

        setBounds(100, 100, 450, 300);

        Dimension dim = getToolkit().getScreenSize();

        setSize(dim.width, dim.height - 38);
        setLocationRelativeTo(null);

        URL rutaImagen =
                getClass().getResource("/imagenes/fondoRegEmpresa.png");

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

        JButton registrarBtn = new JButton("CREAR CUENTA");
        registrarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });

        registrarBtn.setBackground(new Color(255, 153, 51));
        registrarBtn.setForeground(new Color(255, 255, 255));
        registrarBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        registrarBtn.setBounds(125, 800, 199, 49);
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
        cancelarBtn.setBounds(449, 800, 166, 49);
        panel.add(cancelarBtn);

        JLabel lblNewLabel = new JLabel("NOMBRE DE EMPRESA:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(44, 82, 226, 19);
        panel.add(lblNewLabel);

        nombreField = new JTextField();
        nombreField.setBackground(new Color(153, 255, 255));
        nombreField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        nombreField.setBounds(44, 111, 332, 42);
        panel.add(nombreField);
        nombreField.setColumns(10);

        JLabel lblTelefono = new JLabel("TELEFONO:");
        lblTelefono.setForeground(Color.WHITE);
        lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTelefono.setBounds(438, 82, 166, 19);
        panel.add(lblTelefono);

        JLabel lblCorreoElectronico = new JLabel("CORREO ELECTRONICO:");
        lblCorreoElectronico.setForeground(Color.WHITE);
        lblCorreoElectronico.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCorreoElectronico.setBounds(44, 187, 201, 20);
        panel.add(lblCorreoElectronico);

        JLabel lblContrasea = new JLabel("CONTRASEÑA:");
        lblContrasea.setForeground(Color.WHITE);
        lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblContrasea.setBounds(438, 176, 166, 42);
        panel.add(lblContrasea);

        JLabel lblNombreDeEmpresa = new JLabel("DIRECCION:");
        lblNombreDeEmpresa.setForeground(Color.WHITE);
        lblNombreDeEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNombreDeEmpresa.setBounds(44, 287, 201, 29);
        panel.add(lblNombreDeEmpresa);

        JLabel lblRazonSocial = new JLabel("RAZON SOCIAL:");
        lblRazonSocial.setForeground(Color.WHITE);
        lblRazonSocial.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblRazonSocial.setBounds(438, 287, 166, 29);
        panel.add(lblRazonSocial);

        JLabel lblCodigoPostal = new JLabel("CODIGO POSTAL:");
        lblCodigoPostal.setForeground(Color.WHITE);
        lblCodigoPostal.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCodigoPostal.setBounds(44, 390, 166, 19);
        panel.add(lblCodigoPostal);

        postalField = new JTextField();
        postalField.setBackground(new Color(102, 255, 255));
        postalField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        postalField.setColumns(10);
        postalField.setBounds(44, 419, 182, 42);
        panel.add(postalField);

        paisComboBox = new JComboBox();
        paisComboBox.setBackground(new Color(102, 255, 255));
        paisComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        paisComboBox.setModel(new DefaultComboBoxModel(new String[] {
                "REPUBLICA DOMINICANA",
                "ESTADOS UNIDOS"
        }));
        paisComboBox.setBounds(462, 419, 235, 42);
        panel.add(paisComboBox);

        JLabel lblRegistroSocial = new JLabel("REGISTRO SOCIAL:");
        lblRegistroSocial.setForeground(Color.WHITE);
        lblRegistroSocial.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblRegistroSocial.setBounds(255, 390, 166, 19);
        panel.add(lblRegistroSocial);

        JLabel lblPais = new JLabel("PAIS:");
        lblPais.setForeground(Color.WHITE);
        lblPais.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPais.setBounds(462, 390, 166, 19);
        panel.add(lblPais);

        telefonoField = new JTextField();
        telefonoField.setBackground(new Color(102, 255, 255));
        telefonoField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        telefonoField.setColumns(10);
        telefonoField.setBounds(438, 111, 259, 42);
        panel.add(telefonoField);

        correoField = new JTextField();
        correoField.setBackground(new Color(102, 255, 255));
        correoField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        correoField.setColumns(10);
        correoField.setBounds(44, 217, 332, 42);
        panel.add(correoField);

        direccionField = new JTextField();
        direccionField.setBackground(new Color(102, 255, 255));
        direccionField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        direccionField.setColumns(10);
        direccionField.setBounds(44, 320, 332, 42);
        panel.add(direccionField);

        razonSocialField = new JTextField();
        razonSocialField.setBackground(new Color(102, 255, 255));
        razonSocialField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        razonSocialField.setColumns(10);
        razonSocialField.setBounds(438, 320, 259, 42);
        panel.add(razonSocialField);

        registroSocialField = new JTextField();
        registroSocialField.setBackground(new Color(102, 255, 255));
        registroSocialField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        registroSocialField.setColumns(10);
        registroSocialField.setBounds(255, 419, 174, 42);
        panel.add(registroSocialField);

        JLabel lblSector = new JLabel("SECTOR:");
        lblSector.setForeground(Color.WHITE);
        lblSector.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSector.setBounds(44, 491, 166, 19);
        panel.add(lblSector);

        sectorComboBox = new JComboBox();
        sectorComboBox.setBackground(new Color(102, 255, 255));
        sectorComboBox.setModel(new DefaultComboBoxModel(new String[] {
                "OTROS",
                "PUBLICO",
                "PRIVADO"
        }));
        sectorComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        sectorComboBox.setBounds(44, 520, 280, 42);
        panel.add(sectorComboBox);

        JLabel lblCantidadDeTrabajadores = new JLabel("CANTIDAD DE TRABAJADORES:");
        lblCantidadDeTrabajadores.setForeground(Color.WHITE);
        lblCantidadDeTrabajadores.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCantidadDeTrabajadores.setBounds(388, 497, 262, 19);
        panel.add(lblCantidadDeTrabajadores);

        cantTrabsSpinner = new JSpinner();
        cantTrabsSpinner.setBackground(new Color(102, 255, 255));
        cantTrabsSpinner.setModel(new SpinnerNumberModel(
                Integer.valueOf(1),
                Integer.valueOf(1),
                null,
                Integer.valueOf(1)
        ));
        cantTrabsSpinner.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cantTrabsSpinner.setBounds(392, 523, 305, 42);
        panel.add(cantTrabsSpinner);

        JLabel lblCantidadDeVacantes = new JLabel("CANTIDAD DE VACANTES:");
        lblCantidadDeVacantes.setForeground(Color.WHITE);
        lblCantidadDeVacantes.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCantidadDeVacantes.setBounds(44, 592, 262, 19);
        panel.add(lblCantidadDeVacantes);

        cantVacantesSpinner = new JSpinner();
        cantVacantesSpinner.setBackground(new Color(102, 255, 255));
        cantVacantesSpinner.setModel(new SpinnerNumberModel(
                Integer.valueOf(0),
                Integer.valueOf(0),
                null,
                Integer.valueOf(1)
        ));
        cantVacantesSpinner.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cantVacantesSpinner.setBounds(44, 618, 305, 42);
        panel.add(cantVacantesSpinner);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(102, 255, 255));
        passwordField.setBounds(438, 217, 259, 42);
        panel.add(passwordField);

        aplicarMascaraTelefono(telefonoField);
        aplicarMascaraRNC(registroSocialField);
        aplicarSoloDigitos(postalField, 5);
        aplicarPlaceholder(correoField, "ejemplo@correo.com");
    }

    private void aplicarMascaraTelefono(JTextField campo) {
        aplicarMascaraNumerica(campo, 10, 3, 6);
    }

    private void aplicarMascaraRNC(JTextField campo) {
        aplicarMascaraNumerica(campo, 9, 3, 8);
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

    private void aplicarSoloDigitos(
            JTextField campo,
            int maxDigitos) {

        PlainDocument documento =
                (PlainDocument) campo.getDocument();

        documento.setDocumentFilter(new DocumentFilter() {

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

                if (texto == null) {
                    texto = "";
                }

                String digitos = texto.replaceAll("\\D", "");

                int cantidadActual =
                        fb.getDocument().getLength() - length;

                int espacioDisponible =
                        maxDigitos - cantidadActual;

                if (espacioDisponible <= 0) {
                    return;
                }

                if (digitos.length() > espacioDisponible) {
                    digitos = digitos.substring(
                            0,
                            espacioDisponible
                    );
                }

                fb.replace(
                        offset,
                        length,
                        digitos,
                        atributos
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