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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import logico.BolsaTrabajo;
import logico.Oferta;


public class ListOfertas extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Color AZUL_OSCURO = new Color(22, 58, 95);
	private static final Color AZUL_PRINCIPAL = new Color(37, 99, 166);
	private static final Color VERDE_AZULADO = new Color(15, 139, 141);
	private static final Color FONDO_GRIS = new Color(244, 246, 248);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color TEXTO_OSCURO = new Color(31, 41, 55);
	private static final Color ROJO = new Color(255, 0, 0);

	private JPanel panelListado;

	private RoundedLabel logoEmpresaLbl;
	private RoundedTextField nombreEmpresa;
	private RoundedTextField puestoTxt;
	private RoundedTextField estadoTxt;
	private RoundedTextField profesionTxt;
	private RoundedTextField salarioTxt;
	private RoundedTextField txtHibridoPresencial;
	private RoundedTextField tipoContratoTxt;
	private RoundedTextField cantDiasVigente;
	private RoundedTextField fechaCierreTxt;
	private RoundedTextField cantVacanteTxt;
	private RoundedTextField ubicacionTxt;
	private JTextArea txtDescripcion;
	private JTextArea txtRequisitos;

	private JComboBox experienciaComboBox;
	private JComboBox salarioComboBox;
	private Oferta ofertaSeleccionada;

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

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(FONDO_GRIS);
		getContentPane().add(layeredPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(24, 154, 535, 516);
		layeredPane.add(scrollPane);

		panelListado = new JPanel();
		panelListado.setBackground(FONDO_GRIS);
		scrollPane.setViewportView(panelListado);
		panelListado.setLayout(new BoxLayout(panelListado, BoxLayout.Y_AXIS));

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

		JPanel panelDetalle = new JPanel();
		panelDetalle.setBackground(TARJETA_BLANCA);
		panelDetalle.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		panelDetalle.setLayout(null);
		panelDetalle.setPreferredSize(new Dimension(630, 900));

		JScrollPane scrollDetalle = new JScrollPane(panelDetalle);
		scrollDetalle.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollDetalle.setBounds(603, 154, 663, 516);
		layeredPane.add(scrollDetalle);

		logoEmpresaLbl = new RoundedLabel(20);
		logoEmpresaLbl.setHorizontalAlignment(JLabel.CENTER);
		logoEmpresaLbl.setBackground(new Color(204, 204, 204));
		logoEmpresaLbl.setForeground(TEXTO_OSCURO);
		logoEmpresaLbl.setText("LOGO");
		logoEmpresaLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		logoEmpresaLbl.setBounds(20, 20, 150, 150);
		panelDetalle.add(logoEmpresaLbl);

		nombreEmpresa = new RoundedTextField(20);
		nombreEmpresa.setText("EMPRESA");
		nombreEmpresa.setFont(new Font("Tahoma", Font.BOLD, 18));
		nombreEmpresa.setEditable(false);
		nombreEmpresa.setBackground(new Color(204, 204, 204));
		nombreEmpresa.setBounds(190, 30, 420, 40);
		panelDetalle.add(nombreEmpresa);

		puestoTxt = new RoundedTextField(20);
		puestoTxt.setText("PUESTO");
		puestoTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		puestoTxt.setEditable(false);
		puestoTxt.setForeground(AZUL_PRINCIPAL);
		puestoTxt.setBackground(new Color(204, 204, 204));
		puestoTxt.setBounds(190, 80, 420, 34);
		panelDetalle.add(puestoTxt);

		estadoTxt = new RoundedTextField(20);
		estadoTxt.setText("ESTADO");
		estadoTxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		estadoTxt.setEditable(false);
		estadoTxt.setBackground(new Color(204, 204, 204));
		estadoTxt.setBounds(190, 124, 420, 34);
		panelDetalle.add(estadoTxt);

		JLabel lblProfesionLbl = new JLabel("PROFESION:");
		lblProfesionLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProfesionLbl.setForeground(TEXTO_OSCURO);
		lblProfesionLbl.setBounds(20, 190, 260, 25);
		panelDetalle.add(lblProfesionLbl);

		profesionTxt = new RoundedTextField(20);
		profesionTxt.setText("PROFESION");
		profesionTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		profesionTxt.setEditable(false);
		profesionTxt.setBackground(new Color(204, 204, 204));
		profesionTxt.setBounds(20, 216, 280, 34);
		panelDetalle.add(profesionTxt);

		JLabel lblSalarioLbl = new JLabel("SALARIO:");
		lblSalarioLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSalarioLbl.setForeground(TEXTO_OSCURO);
		lblSalarioLbl.setBounds(330, 190, 150, 25);
		panelDetalle.add(lblSalarioLbl);

		salarioTxt = new RoundedTextField(20);
		salarioTxt.setText("SALARIO");
		salarioTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		salarioTxt.setEditable(false);
		salarioTxt.setBackground(new Color(204, 204, 204));
		salarioTxt.setBounds(330, 216, 280, 34);
		panelDetalle.add(salarioTxt);

		JLabel lblModalidadLbl = new JLabel("MODALIDAD:");
		lblModalidadLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModalidadLbl.setForeground(TEXTO_OSCURO);
		lblModalidadLbl.setBounds(20, 265, 260, 25);
		panelDetalle.add(lblModalidadLbl);

		txtHibridoPresencial = new RoundedTextField(20);
		txtHibridoPresencial.setText("MODALIDAD");
		txtHibridoPresencial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHibridoPresencial.setEditable(false);
		txtHibridoPresencial.setBackground(new Color(204, 204, 204));
		txtHibridoPresencial.setBounds(20, 291, 280, 34);
		panelDetalle.add(txtHibridoPresencial);

		JLabel lblContratoLbl = new JLabel("TIPO DE CONTRATO:");
		lblContratoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContratoLbl.setForeground(TEXTO_OSCURO);
		lblContratoLbl.setBounds(330, 265, 260, 25);
		panelDetalle.add(lblContratoLbl);

		tipoContratoTxt = new RoundedTextField(20);
		tipoContratoTxt.setText("TIPO DE CONTRATO");
		tipoContratoTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tipoContratoTxt.setEditable(false);
		tipoContratoTxt.setBackground(new Color(204, 204, 204));
		tipoContratoTxt.setBounds(330, 291, 280, 34);
		panelDetalle.add(tipoContratoTxt);

		JLabel lblPublicacionLbl = new JLabel("FECHA DE PUBLICACION:");
		lblPublicacionLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPublicacionLbl.setForeground(TEXTO_OSCURO);
		lblPublicacionLbl.setBounds(20, 340, 260, 25);
		panelDetalle.add(lblPublicacionLbl);

		cantDiasVigente = new RoundedTextField(20);
		cantDiasVigente.setText("FECHA");
		cantDiasVigente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cantDiasVigente.setEditable(false);
		cantDiasVigente.setBackground(new Color(204, 204, 204));
		cantDiasVigente.setBounds(20, 366, 280, 34);
		panelDetalle.add(cantDiasVigente);

		JLabel lblCierreLbl = new JLabel("FECHA DE CIERRE:");
		lblCierreLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCierreLbl.setForeground(TEXTO_OSCURO);
		lblCierreLbl.setBounds(330, 340, 260, 25);
		panelDetalle.add(lblCierreLbl);

		fechaCierreTxt = new RoundedTextField(20);
		fechaCierreTxt.setText("FECHA");
		fechaCierreTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fechaCierreTxt.setEditable(false);
		fechaCierreTxt.setBackground(new Color(204, 204, 204));
		fechaCierreTxt.setBounds(330, 366, 280, 34);
		panelDetalle.add(fechaCierreTxt);

		JLabel lblVacantesLbl = new JLabel("VACANTES:");
		lblVacantesLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVacantesLbl.setForeground(TEXTO_OSCURO);
		lblVacantesLbl.setBounds(20, 415, 260, 25);
		panelDetalle.add(lblVacantesLbl);

		cantVacanteTxt = new RoundedTextField(20);
		cantVacanteTxt.setText("VACANTES");
		cantVacanteTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cantVacanteTxt.setEditable(false);
		cantVacanteTxt.setBackground(new Color(204, 204, 204));
		cantVacanteTxt.setBounds(20, 441, 280, 34);
		panelDetalle.add(cantVacanteTxt);

		JLabel lblUbicacionLbl = new JLabel("UBICACION:");
		lblUbicacionLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUbicacionLbl.setForeground(TEXTO_OSCURO);
		lblUbicacionLbl.setBounds(330, 415, 260, 25);
		panelDetalle.add(lblUbicacionLbl);

		ubicacionTxt = new RoundedTextField(20);
		ubicacionTxt.setText("UBICACION");
		ubicacionTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ubicacionTxt.setEditable(false);
		ubicacionTxt.setBackground(new Color(204, 204, 204));
		ubicacionTxt.setBounds(330, 441, 280, 34);
		panelDetalle.add(ubicacionTxt);

		JLabel lblTituloDescripcion = new JLabel("DESCRIPCION:");
		lblTituloDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTituloDescripcion.setForeground(TEXTO_OSCURO);
		lblTituloDescripcion.setBounds(20, 490, 350, 25);
		panelDetalle.add(lblTituloDescripcion);

		txtDescripcion = new JTextArea();
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		txtDescripcion.setEditable(false);
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDescripcion.setBackground(new Color(204, 204, 204));
		txtDescripcion.setBounds(20, 520, 590, 150);
		panelDetalle.add(txtDescripcion);

		JLabel lblTituloRequisitos = new JLabel("REQUISITOS:");
		lblTituloRequisitos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTituloRequisitos.setForeground(TEXTO_OSCURO);
		lblTituloRequisitos.setBounds(20, 685, 350, 25);
		panelDetalle.add(lblTituloRequisitos);

		txtRequisitos = new JTextArea();
		txtRequisitos.setLineWrap(true);
		txtRequisitos.setWrapStyleWord(true);
		txtRequisitos.setEditable(false);
		txtRequisitos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtRequisitos.setBackground(new Color(204, 204, 204));
		txtRequisitos.setBounds(20, 715, 590, 150);
		panelDetalle.add(txtRequisitos);

		JButton btnPostularme = new JButton("POSTULARME");
		btnPostularme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ofertaSeleccionada != null) {
					RegSolicitudEmp dialogSolicitud = new RegSolicitudEmp(ofertaSeleccionada);
					dialogSolicitud.setModal(true);
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

		JLabel lblNewLabel_1 = new JLabel("Ofertas Disponibles");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 41));
		lblNewLabel_1.setBounds(133, 16, 500, 72);
		layeredPane.add(lblNewLabel_1);

		setTitle("LISTADO DE OFERTAS");
		setSize(1280, 720);
		setLocationRelativeTo(null);
		cargarOferta();
	}

	public void cargarOferta() {
		ArrayList<Oferta> ofertas = BolsaTrabajo.getInstance().getOfertas();

		panelListado.removeAll();
		for (final Oferta o : ofertas) {
			if ( o.isEstado() )
			{
				panelListado.add(crearTarjeta(o));
				panelListado.add(Box.createRigidArea(new Dimension(0, 10)));
			}
		}
		panelListado.revalidate();
		panelListado.repaint();

		if (!ofertas.isEmpty()) {
			mostrarDetalle(ofertas.get(0));
		}
	}

	private JPanel crearTarjeta(final Oferta o) {
		JPanel tarjeta = new JPanel();
		tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
		tarjeta.setBackground(TARJETA_BLANCA);
		tarjeta.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		tarjeta.setAlignmentX(Component.LEFT_ALIGNMENT);
		tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

		JLabel lblPuesto = new JLabel(o.getPuesto());
		lblPuesto.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		lblPuesto.setForeground(AZUL_OSCURO);
		lblPuesto.setBorder(new EmptyBorder(8, 12, 2, 12));
		lblPuesto.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblEmpresa = new JLabel(o.getMyEmpresa().getNombre());
		lblEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmpresa.setForeground(AZUL_PRINCIPAL);
		lblEmpresa.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblEmpresa.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblModalidad = new JLabel(o.getModalidad() + "  -  " + o.getTipoContrato());
		lblModalidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblModalidad.setForeground(new Color(100, 100, 100));
		lblModalidad.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblModalidad.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblEstado = new JLabel(estadoOferta(o.isEstado()));
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setForeground(o.isEstado() ? VERDE_AZULADO : ROJO);
		lblEstado.setBorder(new EmptyBorder(2, 12, 8, 12));
		lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);

		tarjeta.add(lblPuesto);
		tarjeta.add(lblEmpresa);
		tarjeta.add(lblModalidad);
		tarjeta.add(lblEstado);

		MouseAdapter listenerTarjeta = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarDetalle(o);
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
		lblPuesto.addMouseListener(listenerTarjeta);
		lblEmpresa.addMouseListener(listenerTarjeta);
		lblModalidad.addMouseListener(listenerTarjeta);
		lblEstado.addMouseListener(listenerTarjeta);

		return tarjeta;
	}

	private void mostrarDetalle(Oferta o) {
		ofertaSeleccionada = o;

		String rutaImagen = o.getMyEmpresa().getRutaImagen();

		if (rutaImagen != null && new File(rutaImagen).exists()) {
			ImageIcon logoOriginal = new ImageIcon(rutaImagen);
			Image logoEscalado = logoOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			logoEmpresaLbl.setIcon(new ImageIcon(logoEscalado));
			logoEmpresaLbl.setText("");
		} else {
			ImageIcon iconoEmpresaIcon = new ImageIcon(getClass().getResource("/imagenes/iconoEmpresa.png"));
			Image iconoEmpresaImg = iconoEmpresaIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			logoEmpresaLbl.setIcon(new ImageIcon(iconoEmpresaImg));
			logoEmpresaLbl.setText("");
		}

		nombreEmpresa.setText(o.getMyEmpresa().getNombre());
		puestoTxt.setText(o.getPuesto());

		estadoTxt.setText(estadoOferta(o.isEstado()));
		estadoTxt.setForeground(o.isEstado() ? VERDE_AZULADO : ROJO);

		profesionTxt.setText(o.getProfesion());
		salarioTxt.setText(String.valueOf(o.getSalario()));
		txtHibridoPresencial.setText(o.getModalidad());
		tipoContratoTxt.setText(o.getTipoContrato());
		cantDiasVigente.setText(o.getFechaPublicacion().toString());
		fechaCierreTxt.setText(o.getFechaFinalizacion().toString());
		cantVacanteTxt.setText("" + o.getCantVacante());
		ubicacionTxt.setText(o.getUbicacion());
		txtDescripcion.setText(o.getDescripcion());
		txtRequisitos.setText(o.getRequisitos());
	}

	private static String estadoOferta ( boolean estado )
	{
		if ( estado ) return "ABIERTA";
		else return "CERRADA";
	}
}