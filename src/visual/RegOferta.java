package visual;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class RegOferta extends JDialog {

    private static final long serialVersionUID = 1L;
    private Image imagenFondo;
    private static final Color COLOR_CAMPO = new Color(153, 255, 255);

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
    private RoundedTextField puestoTextField;
    private RoundedTextField salariotextField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
       try {
          RegOferta dialog = new RegOferta();
          dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
          dialog.setVisible(true);
       } catch (Exception e) {
          e.printStackTrace();
       }
    }

    /**
     * Create the dialog.
     */
    public RegOferta() {
       setIconImage(new ImageIcon(getClass().getResource("/imagenes/fondoRegOferta.png")).getImage());
       setTitle("REGISTRAR OFERTA");
       URL rutaImagen = getClass().getResource("/imagenes/fondoRegOferta.png");

       if (rutaImagen != null) {
          imagenFondo = new ImageIcon(rutaImagen).getImage();
       }

       getContentPane().setBackground(new Color(192, 192, 192));
       setBounds(100, 100, 450, 300);
       
       setTitle("LISTADO DE OFERTAS");
       setBounds(100, 100, 450, 300);
       Dimension dim = getToolkit().getScreenSize();

       setSize(1280, 720);
       setLocationRelativeTo(null);
       
       JLayeredPane layeredPane = new JLayeredPane();
       getContentPane().add(layeredPane, BorderLayout.CENTER);
       layeredPane.setLayout(null);

       panelFondo.setBounds(0, 0, 1280, 720);
       layeredPane.add(panelFondo, JLayeredPane.DEFAULT_LAYER);
       panelFondo.setLayout(null);

       JPanel panel = new JPanel();
       panel.setBackground(new Color(25, 25, 112));
       panel.setBounds(106, 48, 450, 635);
       panelFondo.add(panel);
       panel.setLayout(null);
       
       JEditorPane editorPane = new JEditorPane();
       editorPane.setBackground(new Color(173, 216, 230));
       editorPane.setBounds(26, 351, 395, 116);
       panel.add(editorPane);
       
       JEditorPane editorPane_1 = new JEditorPane();
       editorPane_1.setBackground(new Color(173, 216, 230));
       editorPane_1.setBounds(26, 498, 395, 116);
       panel.add(editorPane_1);
       
       JLabel lblPuesto = new JLabel("PUESTO:");
       lblPuesto.setForeground(Color.WHITE);
       lblPuesto.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblPuesto.setBounds(26, 11, 73, 19);
       panel.add(lblPuesto);
       
       puestoTextField = new RoundedTextField(20);
       puestoTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
       puestoTextField.setColumns(10);
       puestoTextField.setBackground(new Color(153, 255, 255));
       puestoTextField.setBounds(26, 40, 173, 28);
       panel.add(puestoTextField);
       
       JLabel lblSalario = new JLabel("SALARIO:");
       lblSalario.setForeground(Color.WHITE);
       lblSalario.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblSalario.setBounds(248, 11, 73, 19);
       panel.add(lblSalario);
       
       salariotextField = new RoundedTextField(20);
       salariotextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
       salariotextField.setColumns(10);
       salariotextField.setBackground(new Color(153, 255, 255));
       salariotextField.setBounds(248, 40, 173, 28);
       panel.add(salariotextField);
       
       JLabel lblModalidad = new JLabel("MODALIDAD:");
       lblModalidad.setForeground(Color.WHITE);
       lblModalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblModalidad.setBounds(26, 78, 101, 19);
       panel.add(lblModalidad);
       
       JComboBox modalidadComboBox = new JComboBox();
       modalidadComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
       modalidadComboBox.setModel(new DefaultComboBoxModel(new String[] {"Modalidad ", "Tiempo Completo", "Medio Tiempo", "Beca / Practicas", "Por Horas"}));
       modalidadComboBox.setBounds(26, 104, 173, 29);
       aplicarColorCombo(modalidadComboBox);
       panel.add(modalidadComboBox);
       
       JLabel lblDechaDeNacimiento = new JLabel("FECHA DE CIERRE:");
       lblDechaDeNacimiento.setForeground(Color.WHITE);
       lblDechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblDechaDeNacimiento.setBounds(248, 78, 226, 19);
       panel.add(lblDechaDeNacimiento);
       
       JSpinner fechaSpinner = new JSpinner();
       SpinnerDateModel modeloFechaCierre = new SpinnerDateModel();
       fechaSpinner.setModel(modeloFechaCierre);
       JSpinner.DateEditor editorFechaCierre = new JSpinner.DateEditor(fechaSpinner, "dd/MM/yyyy");
       fechaSpinner.setEditor(editorFechaCierre);
       fechaSpinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
       fechaSpinner.setBounds(250, 103, 171, 28);
       aplicarColorSpinner(fechaSpinner);
       panel.add(fechaSpinner);
       
       JLabel lblDescripcion = new JLabel("DESCRIPCION:");
       lblDescripcion.setForeground(Color.WHITE);
       lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblDescripcion.setBounds(26, 322, 117, 19);
       panel.add(lblDescripcion);
       
       JLabel lblRequisitos = new JLabel("REQUISITOS:");
       lblRequisitos.setForeground(Color.WHITE);
       lblRequisitos.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblRequisitos.setBounds(26, 469, 117, 19);
       panel.add(lblRequisitos);
       
       JLabel lblDechaDeNacimiento_1 = new JLabel("CANTIDAD DE VACANTES:");
       lblDechaDeNacimiento_1.setForeground(Color.WHITE);
       lblDechaDeNacimiento_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblDechaDeNacimiento_1.setBounds(248, 141, 226, 19);
       panel.add(lblDechaDeNacimiento_1);
       
       JSpinner fechaSpinner_1 = new JSpinner();
       fechaSpinner_1.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
       fechaSpinner_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
       fechaSpinner_1.setBounds(250, 166, 171, 28);
       aplicarColorSpinner(fechaSpinner_1);
       panel.add(fechaSpinner_1);
       
       JLabel lblTipoDeContraro = new JLabel("TIPO DE CONTRARO:");
       lblTipoDeContraro.setForeground(Color.WHITE);
       lblTipoDeContraro.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblTipoDeContraro.setBounds(26, 139, 139, 19);
       panel.add(lblTipoDeContraro);
       
       JComboBox tipoContratoComboBox = new JComboBox();
       tipoContratoComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
       tipoContratoComboBox.setModel(new DefaultComboBoxModel(new String[] {"Indefinido ", "Tiempo Determinado", "Obra o Servicio", "Temporal", "Pasantía o Aprendizaje"}));
       tipoContratoComboBox.setBounds(26, 165, 173, 29);
       aplicarColorCombo(tipoContratoComboBox);
       panel.add(tipoContratoComboBox);
       
       JLabel lblNewLabel_1 = new JLabel("Registrar    Oferta");
       lblNewLabel_1.setBounds(106, -14, 645, 72);
       panelFondo.add(lblNewLabel_1);
       lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 41));
       
       JPanel panel_1 = new JPanel();
       panel_1.setOpaque(false);
       panel_1.setBounds(566, 140, 450, 460);
       panelFondo.add(panel_1);
       panel_1.setLayout(null);

       JLabel lblIconoOferta = new JLabel();
       URL rutaIcono = getClass().getResource("/imagenes/iconoOfertaDeTrabajo.png");
       if (rutaIcono != null) {
          ImageIcon iconoOriginal = new ImageIcon(rutaIcono);
          Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                350, 350, Image.SCALE_SMOOTH);
          lblIconoOferta.setIcon(new ImageIcon(imagenEscalada));
       }
       lblIconoOferta.setOpaque(false);
       lblIconoOferta.setHorizontalAlignment(SwingConstants.CENTER);
       lblIconoOferta.setBounds(35, 10, 350, 350);
       panel_1.add(lblIconoOferta);
       
       RoundedButton btnCrearOferta = new RoundedButton("CREAR OFERTA", 20);
       btnCrearOferta.setForeground(Color.WHITE);
       btnCrearOferta.setFont(new Font("Tahoma", Font.BOLD, 20));
       btnCrearOferta.setBackground(new Color(255, 153, 0));
       btnCrearOferta.setBounds(10, 358, 208, 49);
       panel_1.add(btnCrearOferta);
       
       RoundedButton btnCancelar = new RoundedButton("CANCELAR", 20);
       btnCancelar.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent arg0) {
       		dispose();
       	}
       });
       btnCancelar.setForeground(Color.WHITE);
       btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
       btnCancelar.setBackground(new Color(255, 0, 51));
       btnCancelar.setBounds(273, 358, 177, 49);
       panel_1.add(btnCancelar);
       
       aplicarMascaraSalario(salariotextField);
       aplicarPlaceholder(salariotextField, "Ej: 25000.00");
       
       JLabel lblPais = new JLabel("PAIS:");
       lblPais.setForeground(Color.WHITE);
       lblPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblPais.setBounds(25, 206, 101, 19);
       panel.add(lblPais);
       
       JComboBox paisCombobox = new JComboBox();
       paisCombobox.setFont(new Font("Tahoma", Font.PLAIN, 15));
       paisCombobox.setBounds(25, 232, 151, 29);
       aplicarColorCombo(paisCombobox);
       panel.add(paisCombobox);
       
       JLabel lblDechaDeNacimiento_1_1 = new JLabel("EDAD:");
       lblDechaDeNacimiento_1_1.setForeground(Color.WHITE);
       lblDechaDeNacimiento_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblDechaDeNacimiento_1_1.setBounds(184, 206, 64, 19);
       panel.add(lblDechaDeNacimiento_1_1);
       
       JSpinner edadSpinner = new JSpinner();
       edadSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(18), Integer.valueOf(18), null, Integer.valueOf(1)));
       edadSpinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
       edadSpinner.setBounds(186, 232, 86, 28);
       aplicarColorSpinner(edadSpinner);
       panel.add(edadSpinner);
       
       JLabel lblSexo = new JLabel("SEXO:");
       lblSexo.setForeground(Color.WHITE);
       lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblSexo.setBounds(282, 206, 58, 19);
       panel.add(lblSexo);
       
       JComboBox paisCombobox_1 = new JComboBox();
       paisCombobox_1.setModel(new DefaultComboBoxModel(new String[] {"AMBOS", "FEMENINO", "MASCULINO"}));
       paisCombobox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
       paisCombobox_1.setBounds(282, 232, 139, 29);
       aplicarColorCombo(paisCombobox_1);
       panel.add(paisCombobox_1);
       
       JLabel lblProfesion = new JLabel("PROFESION:");
       lblProfesion.setForeground(Color.WHITE);
       lblProfesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblProfesion.setBounds(26, 271, 101, 19);
       panel.add(lblProfesion);
       
       RoundedTextField puestoTextField_1 = new RoundedTextField(20);
       puestoTextField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
       puestoTextField_1.setColumns(10);
       puestoTextField_1.setBackground(new Color(153, 255, 255));
       puestoTextField_1.setBounds(26, 289, 395, 28);
       panel.add(puestoTextField_1);
    }

    private void aplicarColorCombo(JComboBox<?> combo) {
        combo.setBackground(COLOR_CAMPO);
        combo.setOpaque(true);
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setBackground(COLOR_CAMPO);
                c.setForeground(Color.BLACK);
                return c;
            }
        });
        combo.setEditor(new BasicComboBoxEditor() {
            @Override
            public Component getEditorComponent() {
                Component c = super.getEditorComponent();
                c.setBackground(COLOR_CAMPO);
                return c;
            }
        });
    }

    private void aplicarColorSpinner(JSpinner spinner) {
        spinner.setBackground(COLOR_CAMPO);
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JFormattedTextField campoTexto = ((JSpinner.DefaultEditor) editor).getTextField();
            campoTexto.setBackground(COLOR_CAMPO);
            campoTexto.setOpaque(true);
        }
    }

    private void aplicarPlaceholder(JTextField campo, String textoEjemplo) {

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

    private void aplicarMascaraSalario(JTextField campo) {

        PlainDocument documento = (PlainDocument) campo.getDocument();

        documento.setDocumentFilter(new DocumentFilter() {

            private String formatear(String texto) {

                StringBuilder soloValidos = new StringBuilder();
                boolean puntoEncontrado = false;
                int decimales = 0;

                for (char c : texto.toCharArray()) {
                    if (Character.isDigit(c)) {
                        if (puntoEncontrado) {
                            if (decimales < 2) {
                                soloValidos.append(c);
                                decimales++;
                            }
                        } else {
                            soloValidos.append(c);
                        }
                    } else if (c == '.' && !puntoEncontrado) {
                        soloValidos.append(c);
                        puntoEncontrado = true;
                    }
                }

                return soloValidos.toString();
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String texto, AttributeSet atributos)
                    throws BadLocationException {
                replace(fb, offset, 0, texto, atributos);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String texto, AttributeSet atributos)
                    throws BadLocationException {

                String actual = fb.getDocument().getText(0, fb.getDocument().getLength());

                StringBuilder nuevoTexto = new StringBuilder(actual);
                nuevoTexto.replace(offset, offset + length, texto == null ? "" : texto);

                String textoFormateado = formatear(nuevoTexto.toString());

                fb.replace(0, fb.getDocument().getLength(), textoFormateado, atributos);
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                replace(fb, offset, length, "", null);
            }
        });
    }
}