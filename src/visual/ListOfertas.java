package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.border.MatteBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Canvas;
import java.awt.Panel;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JLabel;
import java.awt.TextField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.BolsaTrabajo;
import logico.Oferta;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.util.ArrayList;
import java.awt.Toolkit;
import javax.swing.JTable;


public class ListOfertas extends JDialog {

	private static final long serialVersionUID = 1L;
	private RoundedTextField nombreEmpresa;
	private RoundedTextField cantDiasVigente;
	private RoundedTextField profesionTxt;
	private RoundedTextField txtHibridoPresencial;
	private RoundedTextField puestoTxt;
	private RoundedTextField salarioTxt;
	private JTextArea txtDescripcion;
	private JTextArea txtRequisitos;
	private JPanel panel;
	private Oferta seleccionado = null;
	private static Object[] row;
	private static DefaultTableModel model;
	private JComboBox ordenComboBox;
	private JComboBox experienciaComboBox;
	private JComboBox salarioComboBox;
	private Oferta ofertaSeleccionada;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListOfertas dialog = new ListOfertas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListOfertas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListOfertas.class.getResource("/imagenes/iconoBuscarOferta.png")));
		getContentPane().setBackground(new Color(0, 0, 102));
		{
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setBackground(new Color(0, 102, 102));
			getContentPane().add(layeredPane, BorderLayout.CENTER);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 154, 535, 529);
			layeredPane.add(scrollPane);

			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			
			//! Campos para la tabla
			String[] headersTodos = {"Empresa", "Puesto", "Salario", "Tipo Contrato", "Profesion"};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(headersTodos);

			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = table.getSelectedRow();
					if ( index >= 0 )
						mostrarDetalle(BolsaTrabajo.getInstance().getOfertas().get(index));
				}
			});

			table.setModel(model);
			scrollPane.setViewportView(table);

			ordenComboBox = new JComboBox();
			ordenComboBox.setModel(new DefaultComboBoxModel(new String[] {"Ordenar ", "Relevancia ", "Fecha ", "Salario"}));
			ordenComboBox.setFont(new Font("Tahoma", Font.ITALIC, 16));
			ordenComboBox.setBackground(new Color(255, 255, 255));
			ordenComboBox.setBounds(108, 98, 183, 42);
			layeredPane.add(ordenComboBox);

			experienciaComboBox = new JComboBox();
			experienciaComboBox.setModel(new DefaultComboBoxModel(new String[] {"Experiencia", "Sin Experiencia ", "1 año", "2 años ", "3-4 años", "5-10 años"}));
			experienciaComboBox.setFont(new Font("Tahoma", Font.ITALIC, 16));
			experienciaComboBox.setBackground(new Color(255, 255, 255));
			experienciaComboBox.setBounds(399, 98, 183, 42);
			layeredPane.add(experienciaComboBox);

			salarioComboBox = new JComboBox();
			salarioComboBox.setModel(new DefaultComboBoxModel(new String[] {"Salario", "Menos de $15,000", "$15,000 - $25,000", "$25,000 - $35,000", "$35,000 - $50,000", "$50,000 - $70,000", "$70,000 - $100,000", "Más de $100,000"}));
			salarioComboBox.setFont(new Font("Tahoma", Font.ITALIC, 16));
			salarioComboBox.setBackground(new Color(255, 255, 255));
			salarioComboBox.setBounds(690, 98, 183, 42);
			layeredPane.add(salarioComboBox);

			JButton btnNewButton = new JButton("SALIR");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnNewButton.setForeground(new Color(255, 255, 255));
			btnNewButton.setBackground(new Color(0, 153, 204));
			btnNewButton.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 20));
			btnNewButton.setBounds(0, 32, 115, 36);
			layeredPane.add(btnNewButton);

			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(240, 248, 255));
			panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			panel_1.setLayout(null);
			panel_1.setPreferredSize(new Dimension(645, 700));

			JScrollPane scrollDetalle = new JScrollPane(panel_1);
			scrollDetalle.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			scrollDetalle.setBounds(603, 154, 663, 529);
			layeredPane.add(scrollDetalle);

			ImageIcon iconoEmpresaIcon = new ImageIcon(getClass().getResource("/imagenes/iconoEmpresa.png"));
			Image iconoEmpresaImg = iconoEmpresaIcon.getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH);
			JLabel panel_2 = new JLabel(new ImageIcon(iconoEmpresaImg));
			panel_2.setBounds(10, 22, 27, 27);
			panel_1.add(panel_2);

			ImageIcon iconoFechaIcon = new ImageIcon(getClass().getResource("/imagenes/iconoFecha.png"));
			Image iconoFechaImg = iconoFechaIcon.getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH);
			JLabel panel_2_1 = new JLabel(new ImageIcon(iconoFechaImg));
			panel_2_1.setBounds(10, 65, 27, 27);
			panel_1.add(panel_2_1);

			ImageIcon iconoModalidadIcon = new ImageIcon(getClass().getResource("/imagenes/iconoModalidad.png"));
			Image iconoModalidadImg = iconoModalidadIcon.getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH);
			JLabel panel_2_1_1 = new JLabel(new ImageIcon(iconoModalidadImg));
			panel_2_1_1.setBounds(331, 22, 27, 27);
			panel_1.add(panel_2_1_1);

			ImageIcon iconoPuestoIcon = new ImageIcon(getClass().getResource("/imagenes/iconoPuesto.png"));
			Image iconoPuestoImg = iconoPuestoIcon.getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH);
			JLabel panel_2_1_2 = new JLabel(new ImageIcon(iconoPuestoImg));
			panel_2_1_2.setBounds(331, 65, 27, 27);
			panel_1.add(panel_2_1_2);

			ImageIcon iconoRegistrarIcon = new ImageIcon(getClass().getResource("/imagenes/iconoProfesion.png"));
			Image iconoRegistrarImg = iconoRegistrarIcon.getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH);
			JLabel panel_2_1_3 = new JLabel(new ImageIcon(iconoRegistrarImg));
			panel_2_1_3.setBounds(10, 114, 27, 27);
			panel_1.add(panel_2_1_3);

			ImageIcon iconoSalarioIcon = new ImageIcon(getClass().getResource("/imagenes/iconoSalario.png"));
			Image iconoSalarioImg = iconoSalarioIcon.getImage().getScaledInstance(27, 27, Image.SCALE_SMOOTH);
			JLabel panel_2_1_4 = new JLabel(new ImageIcon(iconoSalarioImg));
			panel_2_1_4.setBounds(331, 114, 27, 27);
			panel_1.add(panel_2_1_4);

			nombreEmpresa = new RoundedTextField(20);
			nombreEmpresa.setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 16));
			nombreEmpresa.setText("EMPRESA");
			nombreEmpresa.setEditable(false);
			nombreEmpresa.setBackground(new Color(204, 204, 204));
			nombreEmpresa.setBounds(47, 22, 274, 27);
			panel_1.add(nombreEmpresa);
			nombreEmpresa.setColumns(10);

			cantDiasVigente = new RoundedTextField(20);
			cantDiasVigente.setText("HACE X DIAS");
			cantDiasVigente.setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 16));
			cantDiasVigente.setEditable(false);
			cantDiasVigente.setColumns(10);
			cantDiasVigente.setBackground(new Color(204, 204, 204));
			cantDiasVigente.setBounds(47, 65, 274, 27);
			panel_1.add(cantDiasVigente);

			profesionTxt = new RoundedTextField(20);
			profesionTxt.setText("PROFESION");
			profesionTxt.setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 16));
			profesionTxt.setEditable(false);
			profesionTxt.setColumns(10);
			profesionTxt.setBackground(new Color(204, 204, 204));
			profesionTxt.setBounds(47, 114, 274, 27);
			panel_1.add(profesionTxt);

			txtHibridoPresencial = new RoundedTextField(20);
			txtHibridoPresencial.setText("MODALIDAD");
			txtHibridoPresencial.setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 16));
			txtHibridoPresencial.setEditable(false);
			txtHibridoPresencial.setColumns(10);
			txtHibridoPresencial.setBackground(new Color(204, 204, 204));
			txtHibridoPresencial.setBounds(368, 22, 274, 27);
			panel_1.add(txtHibridoPresencial);

			puestoTxt = new RoundedTextField(20);
			puestoTxt.setText("PUESTO");
			puestoTxt.setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 16));
			puestoTxt.setEditable(false);
			puestoTxt.setColumns(10);
			puestoTxt.setBackground(new Color(204, 204, 204));
			puestoTxt.setBounds(368, 65, 274, 27);
			panel_1.add(puestoTxt);

			salarioTxt = new RoundedTextField(20);
			salarioTxt.setText("SALARIO");
			salarioTxt.setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 16));
			salarioTxt.setEditable(false);
			salarioTxt.setColumns(10);
			salarioTxt.setBackground(new Color(204, 204, 204));
			salarioTxt.setBounds(367, 114, 274, 27);
			panel_1.add(salarioTxt);

			JLabel lblTituloDescripcion = new JLabel("DESCRIPCIÓN");
			lblTituloDescripcion.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 18));
			lblTituloDescripcion.setBounds(10, 160, 300, 25);
			panel_1.add(lblTituloDescripcion);

			txtDescripcion = new JTextArea();
			txtDescripcion.setText("Aquí va la descripción completa de la oferta...");
			txtDescripcion.setLineWrap(true);
			txtDescripcion.setWrapStyleWord(true);
			txtDescripcion.setEditable(false);
			txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtDescripcion.setBackground(new Color(204, 204, 204));
			txtDescripcion.setBounds(10, 190, 620, 200);
			panel_1.add(txtDescripcion);

			JLabel lblTituloRequisitos = new JLabel("REQUISITOS");
			lblTituloRequisitos.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 18));
			lblTituloRequisitos.setBounds(10, 410, 300, 25);
			panel_1.add(lblTituloRequisitos);

			txtRequisitos = new JTextArea();
			txtRequisitos.setText("- Requisito 1\n- Requisito 2\n- Requisito 3");
			txtRequisitos.setLineWrap(true);
			txtRequisitos.setWrapStyleWord(true);
			txtRequisitos.setEditable(false);
			txtRequisitos.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtRequisitos.setBackground(new Color(204, 204, 204));
			txtRequisitos.setBounds(10, 440, 620, 200);
			panel_1.add(txtRequisitos);
			
			JButton btnPostularme = new JButton("POSTULARME");
			btnPostularme.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (ofertaSeleccionada != null) {
						RegSolicitudEmp dialogSolicitud = new RegSolicitudEmp(ofertaSeleccionada);
						dialogSolicitud.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialogSolicitud.setVisible(true);
					}
				}
			});
			btnPostularme.setForeground(Color.WHITE);
			btnPostularme.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 20));
			btnPostularme.setBackground(new Color(0, 153, 204));
			btnPostularme.setBounds(981, 98, 173, 42);
			layeredPane.add(btnPostularme);
		}
		setTitle("LISTADO DE OFERTAS");
		setBounds(100, 100, 450, 300);
		Dimension dim = getToolkit().getScreenSize();

		setSize(1280, 720);
		setLocationRelativeTo(null);
		cargarOferta();
	}

	private void mostrarDetalle(Oferta o) {
		ofertaSeleccionada = o;
		nombreEmpresa.setText(o.getMyEmpresa().getNombre());
		cantDiasVigente.setText(o.getFechaPublicacion().toString());
		profesionTxt.setText(o.getProfesion());
		txtHibridoPresencial.setText(o.getModalidad());
		puestoTxt.setText(o.getPuesto());
		salarioTxt.setText(String.valueOf(o.getSalario()));
		txtDescripcion.setText(o.getDescripcion());
		txtRequisitos.setText(o.getRequisitos());
	}

	public static void cargarOferta()
	{
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];

		for ( Oferta o : BolsaTrabajo.getInstance().getOfertas() )
		{
			row[0] = o.getMyEmpresa().getNombre();
			row[1] = o.getPuesto();
			row[2] = o.getSalario();
			row[3] = o.getTipoContrato();
			row[4] = o.getProfesion();
			model.addRow(row);
		}
	}
}