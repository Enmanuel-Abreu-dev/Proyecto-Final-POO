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
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import logico.BolsaTrabajo;
import logico.Persona;
import logico.Experiencia;


public class ListExperiencia extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Color AZUL_OSCURO = new Color(22, 58, 95);
	private static final Color AZUL_PRINCIPAL = new Color(37, 99, 166);
	private static final Color VERDE_AZULADO = new Color(15, 139, 141);
	private static final Color FONDO_GRIS = new Color(244, 246, 248);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color TEXTO_OSCURO = new Color(31, 41, 55);
	private static final Color ROJO = new Color(255, 0, 0);

	private JPanel panelListado;

	private RoundedLabel iconoExperienciaLbl;
	private RoundedTextField institucionTxt;
	private RoundedTextField cargoTxt;
	private RoundedTextField especialidadTxt;
	private RoundedTextField fechaInicioTxt;
	private RoundedTextField fechaFinTxt;
	private RoundedTextField aniosExperienciaTxt;
	private RoundedTextField estadoTxt;
	private JTextArea txtDetalle;

	private Experiencia seleccionado = null;

	private final Persona candidato;

	public static void main(String[] args) {
		try {
			ListExperiencia dialog = new ListExperiencia(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListExperiencia(Persona candidato) {
		this.candidato = candidato;

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ListExperiencia.class.getResource("/imagenes/iconoBuscarOferta.png")));
		getContentPane().setBackground(new Color(0, 0, 102));

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(FONDO_GRIS);
		getContentPane().add(layeredPane, BorderLayout.CENTER);

		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(new Color(0, 153, 204));
		btnSalir.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 16));
		btnSalir.setBounds(1150, 24, 100, 36);
		layeredPane.add(btnSalir);

		JButton btnAgregar = new JButton("AGREGAR EXPERIENCIA");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegExperiencia dialogExperiencia = new RegExperiencia();
				dialogExperiencia.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialogExperiencia.setModal(true);
				dialogExperiencia.setVisible(true);
				cargarExperiencia();
			}
		});
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setBackground(AZUL_PRINCIPAL);
		btnAgregar.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		btnAgregar.setBounds(24, 105, 280, 38);
		layeredPane.add(btnAgregar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(24, 154, 535, 516);
		layeredPane.add(scrollPane);

		panelListado = new JPanel();
		panelListado.setBackground(FONDO_GRIS);
		scrollPane.setViewportView(panelListado);
		panelListado.setLayout(new BoxLayout(panelListado, BoxLayout.Y_AXIS));

		JPanel panelDetalle = new JPanel();
		panelDetalle.setBackground(TARJETA_BLANCA);
		panelDetalle.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		panelDetalle.setLayout(null);
		panelDetalle.setPreferredSize(new Dimension(630, 620));

		JScrollPane scrollDetalle = new JScrollPane(panelDetalle);
		scrollDetalle.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollDetalle.setBounds(603, 154, 663, 516);
		layeredPane.add(scrollDetalle);

		ImageIcon iconoProfesionIcon = new ImageIcon(getClass().getResource("/imagenes/iconoProfesion.png"));
		Image iconoProfesionImg = iconoProfesionIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

		iconoExperienciaLbl = new RoundedLabel(20);
		iconoExperienciaLbl.setHorizontalAlignment(JLabel.CENTER);
		iconoExperienciaLbl.setBackground(new Color(204, 204, 204));
		iconoExperienciaLbl.setForeground(TEXTO_OSCURO);
		iconoExperienciaLbl.setIcon(new ImageIcon(iconoProfesionImg));
		iconoExperienciaLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		iconoExperienciaLbl.setBounds(20, 20, 150, 150);
		panelDetalle.add(iconoExperienciaLbl);

		institucionTxt = new RoundedTextField(20);
		institucionTxt.setText("INSTITUCION");
		institucionTxt.setFont(new Font("Tahoma", Font.BOLD, 18));
		institucionTxt.setEditable(false);
		institucionTxt.setBackground(new Color(204, 204, 204));
		institucionTxt.setBounds(190, 30, 420, 40);
		panelDetalle.add(institucionTxt);

		cargoTxt = new RoundedTextField(20);
		cargoTxt.setText("CARGO");
		cargoTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cargoTxt.setEditable(false);
		cargoTxt.setForeground(AZUL_PRINCIPAL);
		cargoTxt.setBackground(new Color(204, 204, 204));
		cargoTxt.setBounds(190, 80, 420, 34);
		panelDetalle.add(cargoTxt);

		estadoTxt = new RoundedTextField(20);
		estadoTxt.setText("ESTADO");
		estadoTxt.setFont(new Font("Tahoma", Font.BOLD, 14));
		estadoTxt.setEditable(false);
		estadoTxt.setBackground(new Color(204, 204, 204));
		estadoTxt.setBounds(190, 124, 420, 34);
		panelDetalle.add(estadoTxt);

		JLabel lblEspecialidadLbl = new JLabel("ESPECIALIDAD:");
		lblEspecialidadLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEspecialidadLbl.setForeground(TEXTO_OSCURO);
		lblEspecialidadLbl.setBounds(20, 190, 260, 25);
		panelDetalle.add(lblEspecialidadLbl);

		especialidadTxt = new RoundedTextField(20);
		especialidadTxt.setText("ESPECIALIDAD");
		especialidadTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		especialidadTxt.setEditable(false);
		especialidadTxt.setBackground(new Color(204, 204, 204));
		especialidadTxt.setBounds(20, 216, 280, 34);
		panelDetalle.add(especialidadTxt);

		JLabel lblAniosLbl = new JLabel("AÑOS DE EXPERIENCIA:");
		lblAniosLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAniosLbl.setForeground(TEXTO_OSCURO);
		lblAniosLbl.setBounds(330, 190, 260, 25);
		panelDetalle.add(lblAniosLbl);

		aniosExperienciaTxt = new RoundedTextField(20);
		aniosExperienciaTxt.setText("AÑOS");
		aniosExperienciaTxt.setFont(new Font("Tahoma", Font.BOLD, 16));
		aniosExperienciaTxt.setForeground(VERDE_AZULADO);
		aniosExperienciaTxt.setEditable(false);
		aniosExperienciaTxt.setBackground(new Color(204, 204, 204));
		aniosExperienciaTxt.setBounds(330, 216, 280, 34);
		panelDetalle.add(aniosExperienciaTxt);

		JLabel lblInicioLbl = new JLabel("FECHA DE INICIO:");
		lblInicioLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInicioLbl.setForeground(TEXTO_OSCURO);
		lblInicioLbl.setBounds(20, 265, 260, 25);
		panelDetalle.add(lblInicioLbl);

		fechaInicioTxt = new RoundedTextField(20);
		fechaInicioTxt.setText("FECHA");
		fechaInicioTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fechaInicioTxt.setEditable(false);
		fechaInicioTxt.setBackground(new Color(204, 204, 204));
		fechaInicioTxt.setBounds(20, 291, 280, 34);
		panelDetalle.add(fechaInicioTxt);

		JLabel lblFinLbl = new JLabel("FECHA DE FINALIZACION:");
		lblFinLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFinLbl.setForeground(TEXTO_OSCURO);
		lblFinLbl.setBounds(330, 265, 260, 25);
		panelDetalle.add(lblFinLbl);

		fechaFinTxt = new RoundedTextField(20);
		fechaFinTxt.setText("FECHA");
		fechaFinTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fechaFinTxt.setEditable(false);
		fechaFinTxt.setBackground(new Color(204, 204, 204));
		fechaFinTxt.setBounds(330, 291, 280, 34);
		panelDetalle.add(fechaFinTxt);

		JLabel lblDetalleLbl = new JLabel("DETALLE DE LA EXPERIENCIA:");
		lblDetalleLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDetalleLbl.setForeground(TEXTO_OSCURO);
		lblDetalleLbl.setBounds(20, 340, 350, 25);
		panelDetalle.add(lblDetalleLbl);

		txtDetalle = new JTextArea();
		txtDetalle.setLineWrap(true);
		txtDetalle.setWrapStyleWord(true);
		txtDetalle.setEditable(false);
		txtDetalle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDetalle.setBackground(new Color(204, 204, 204));
		txtDetalle.setBounds(20, 370, 590, 200);
		panelDetalle.add(txtDetalle);

		JLabel lblNewLabel_1 = new JLabel("Experiencia Laboral");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 41));
		lblNewLabel_1.setBounds(43, 10, 500, 72);
		layeredPane.add(lblNewLabel_1);

		setTitle("EXPERIENCIA LABORAL");
		setSize(1280, 720);
		setLocationRelativeTo(null);
		cargarExperiencia();
	}

	public void cargarExperiencia() {
		ArrayList<Experiencia> experiencias =
				BolsaTrabajo.getInstance().getUsuarioActual().getMyPersona().getExperiencia();

		panelListado.removeAll();
		for (final Experiencia e : experiencias) {
			panelListado.add(crearTarjeta(e));
			panelListado.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		panelListado.revalidate();
		panelListado.repaint();

		if (!experiencias.isEmpty()) {
			mostrarDetalle(experiencias.get(0));
		}
	}

	private JPanel crearTarjeta(final Experiencia e) {
		JPanel tarjeta = new JPanel();
		tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
		tarjeta.setBackground(TARJETA_BLANCA);
		tarjeta.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		tarjeta.setAlignmentX(Component.LEFT_ALIGNMENT);
		tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

		JLabel lblCargo = new JLabel(e.getCargo());
		lblCargo.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		lblCargo.setForeground(AZUL_OSCURO);
		lblCargo.setBorder(new EmptyBorder(8, 12, 2, 12));
		lblCargo.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblInstitucion = new JLabel(e.getInstitucion());
		lblInstitucion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInstitucion.setForeground(AZUL_PRINCIPAL);
		lblInstitucion.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblInstitucion.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblAnios = new JLabel(e.getAniosDeExperiencia() + " año(s)");
		lblAnios.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAnios.setForeground(e.getAniosDeExperiencia() > 0 ? VERDE_AZULADO : ROJO);
		lblAnios.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblAnios.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel lblFecha = new JLabel(e.getFechaInicio() + " / " + e.getFechaFinalizacion());
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setForeground(new Color(150, 150, 150));
		lblFecha.setBorder(new EmptyBorder(2, 12, 8, 12));
		lblFecha.setAlignmentX(Component.LEFT_ALIGNMENT);

		tarjeta.add(lblCargo);
		tarjeta.add(lblInstitucion);
		tarjeta.add(lblAnios);
		tarjeta.add(lblFecha);

		MouseAdapter listenerTarjeta = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				mostrarDetalle(e);
			}
			@Override
			public void mouseEntered(MouseEvent evt) {
				tarjeta.setBackground(new Color(245, 245, 245));
			}
			@Override
			public void mouseExited(MouseEvent evt) {
				tarjeta.setBackground(TARJETA_BLANCA);
			}
		};
		tarjeta.addMouseListener(listenerTarjeta);
		tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblCargo.addMouseListener(listenerTarjeta);
		lblInstitucion.addMouseListener(listenerTarjeta);
		lblAnios.addMouseListener(listenerTarjeta);
		lblFecha.addMouseListener(listenerTarjeta);

		return tarjeta;
	}

	private void mostrarDetalle(Experiencia e) {
		seleccionado = e;

		institucionTxt.setText(e.getInstitucion());
		cargoTxt.setText(e.getCargo());

		estadoTxt.setText(estadoExperiencia(e));
		estadoTxt.setForeground(e.getAniosDeExperiencia() > 0 ? VERDE_AZULADO : ROJO);

		especialidadTxt.setText(e.getEspecialidad());
		aniosExperienciaTxt.setText("" + e.getAniosDeExperiencia());
		fechaInicioTxt.setText(e.getFechaInicio().toString());
		fechaFinTxt.setText(e.getFechaFinalizacion().toString());

		txtDetalle.setText(e.getCargo() + " - " + e.getEspecialidad() + "\n"
				+ e.getInstitucion() + "\n"
				+ e.getFechaInicio() + " / " + e.getFechaFinalizacion() + "\n"
				+ e.getAniosDeExperiencia() + " año(s) de experiencia");
	}

	private static String estadoExperiencia ( Experiencia e )
	{
		if ( e.getAniosDeExperiencia() > 0 ) return "EXPERIENCIA COMPROBADA";
		else return "MENOS DE UN AÑO";
	}
}