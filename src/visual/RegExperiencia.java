package visual;

import java.awt.*;

import javax.swing.*;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegExperiencia extends JDialog {

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
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
       try {
          RegExperiencia dialog = new RegExperiencia();
          dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
          dialog.setVisible(true);
       } catch (Exception e) {
          e.printStackTrace();
       }
    }

    /**
     * Create the dialog.
     */
    public RegExperiencia() {
       setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoRegistrar.png")).getImage());
       setTitle("REGISTRAR EXPERIENCIA");
       URL rutaImagen = getClass().getResource("/imagenes/fondoRegOferta.png");

       if (rutaImagen != null) {
          imagenFondo = new ImageIcon(rutaImagen).getImage();
       }

       getContentPane().setBackground(new Color(192, 192, 192));
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

       JLabel lblInstitucion = new JLabel("INSTITUCION:");
       lblInstitucion.setForeground(Color.WHITE);
       lblInstitucion.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblInstitucion.setBounds(26, 27, 117, 19);
       panel.add(lblInstitucion);

       textField = new JTextField();
       textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
       textField.setColumns(10);
       textField.setBackground(new Color(153, 255, 255));
       textField.setBounds(26, 56, 395, 28);
       panel.add(textField);

       JLabel lblCargo = new JLabel("CARGO:");
       lblCargo.setForeground(Color.WHITE);
       lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblCargo.setBounds(26, 107, 73, 19);
       panel.add(lblCargo);

       textField_1 = new JTextField();
       textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
       textField_1.setColumns(10);
       textField_1.setBackground(new Color(153, 255, 255));
       textField_1.setBounds(26, 133, 173, 28);
       panel.add(textField_1);

       JLabel lblEspecialidad = new JLabel("ESPECIALIDAD:");
       lblEspecialidad.setForeground(Color.WHITE);
       lblEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblEspecialidad.setBounds(248, 107, 139, 19);
       panel.add(lblEspecialidad);

       textField_2 = new JTextField();
       textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
       textField_2.setColumns(10);
       textField_2.setBackground(new Color(153, 255, 255));
       textField_2.setBounds(248, 133, 173, 28);
       panel.add(textField_2);

       JLabel lblFechaInicio = new JLabel("FECHA DE INICIO:");
       lblFechaInicio.setForeground(Color.WHITE);
       lblFechaInicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblFechaInicio.setBounds(26, 168, 226, 19);
       panel.add(lblFechaInicio);

       JSpinner fechaSpinner = new JSpinner();
       SpinnerDateModel modeloFechaInicio = new SpinnerDateModel();
       fechaSpinner.setModel(modeloFechaInicio);
       JSpinner.DateEditor editorFechaInicio = new JSpinner.DateEditor(fechaSpinner, "dd/MM/yyyy");
       fechaSpinner.setEditor(editorFechaInicio);
       fechaSpinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
       fechaSpinner.setBackground(new Color(102, 255, 255));
       fechaSpinner.setBounds(26, 195, 173, 28);
       panel.add(fechaSpinner);

       JLabel lblFechaFinalizacion = new JLabel("FECHA DE FINALIZACION:");
       lblFechaFinalizacion.setForeground(Color.WHITE);
       lblFechaFinalizacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblFechaFinalizacion.setBounds(248, 168, 226, 19);
       panel.add(lblFechaFinalizacion);

       JSpinner fechaSpinner_1 = new JSpinner();
       SpinnerDateModel modeloFechaFin = new SpinnerDateModel();
       fechaSpinner_1.setModel(modeloFechaFin);
       JSpinner.DateEditor editorFechaFin = new JSpinner.DateEditor(fechaSpinner_1, "dd/MM/yyyy");
       fechaSpinner_1.setEditor(editorFechaFin);
       fechaSpinner_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
       fechaSpinner_1.setBackground(new Color(102, 255, 255));
       fechaSpinner_1.setBounds(248, 195, 173, 28);
       panel.add(fechaSpinner_1);

       JLabel lblFunciones = new JLabel("FUNCIONES REALIZADAS:");
       lblFunciones.setForeground(Color.WHITE);
       lblFunciones.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblFunciones.setBounds(26, 231, 226, 19);
       panel.add(lblFunciones);

       JEditorPane editorPane = new JEditorPane();
       editorPane.setBackground(new Color(173, 216, 230));
       editorPane.setBounds(26, 260, 395, 166);
       panel.add(editorPane);

       JLabel lblLogros = new JLabel("LOGROS OBTENIDOS:");
       lblLogros.setForeground(Color.WHITE);
       lblLogros.setFont(new Font("Tahoma", Font.PLAIN, 14));
       lblLogros.setBounds(26, 436, 226, 19);
       panel.add(lblLogros);

       JEditorPane editorPane_1 = new JEditorPane();
       editorPane_1.setBackground(new Color(173, 216, 230));
       editorPane_1.setBounds(26, 465, 395, 149);
       panel.add(editorPane_1);

       JLabel lblNewLabel_1 = new JLabel("Registrar    Experiencia");
       lblNewLabel_1.setBounds(106, -14, 645, 72);
       panelFondo.add(lblNewLabel_1);
       lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 41));

       JPanel panel_1 = new JPanel();
       panel_1.setOpaque(false);
       panel_1.setBounds(566, 140, 450, 460);
       panelFondo.add(panel_1);
       panel_1.setLayout(null);

       JLabel lblIconoExperiencia = new JLabel();
       URL rutaIcono = getClass().getResource("/imagenes/iconoProfesion.png");
       if (rutaIcono != null) {
          ImageIcon iconoOriginal = new ImageIcon(rutaIcono);
          Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                350, 350, Image.SCALE_SMOOTH);
          lblIconoExperiencia.setIcon(new ImageIcon(imagenEscalada));
       }
       lblIconoExperiencia.setOpaque(false);
       lblIconoExperiencia.setHorizontalAlignment(SwingConstants.CENTER);
       lblIconoExperiencia.setBounds(35, 10, 350, 350);
       panel_1.add(lblIconoExperiencia);

       JButton btnCrearExperiencia = new JButton("AGREGAR");
       btnCrearExperiencia.setForeground(Color.WHITE);
       btnCrearExperiencia.setFont(new Font("Tahoma", Font.BOLD, 20));
       btnCrearExperiencia.setBackground(new Color(25, 25, 112));
       btnCrearExperiencia.setBounds(10, 358, 208, 49);
       panel_1.add(btnCrearExperiencia);

       JButton btnCancelar = new JButton("CANCELAR");
       btnCancelar.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent arg0) {
       		dispose();
       	}
       });
       btnCancelar.setForeground(Color.WHITE);
       btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 20));
       btnCancelar.setBackground(new Color(25, 25, 112));
       btnCancelar.setBounds(273, 358, 177, 49);
       panel_1.add(btnCancelar);
    }
}