package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.SolicitudEmp;
import logico.Institucion;
import logico.Oferta;
import logico.Persona;


public class ListSolicitudesEmp extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Color AZUL_OSCURO = new Color(0x16, 0x3A, 0x5F);
	private static final Color AZUL_PRINCIPAL = new Color(0x25, 0x63, 0xA6);
	private static final Color VERDE_AZULADO = new Color(0x0F, 0x8B, 0x8D);
	private static final Color FONDO_GRIS = new Color(0xF4, 0xF6, 0xF8);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color TEXTO_OSCURO = new Color(0x1F, 0x29, 0x37);

	private JPanel panelListado;
	private RoundedTextField nombreEmpresa;
	private RoundedTextField puestoTxt;
	private RoundedTextField fechaSolicitudTxt;
	private RoundedTextField estadoTxt;
	private RoundedTextField rangoSalarialTxt;
	private RoundedTextField modalidadTxt;
	private JTextArea txtDescripcionOferta;

	private Oferta seleccionado = null;
	private static Object[] row;
	private static DefaultTableModel model;

	private final Persona candidato;

	public static void main(String[] args) {
		try {
			ListSolicitudesEmp dialog = new ListSolicitudesEmp(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListSolicitudesEmp(Persona candidato) {
		this.candidato = candidato;
		this.cargarSolicitudes(this.candidato.getSolicitudEmps());

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ListSolicitudesEmp.class.getResource("/imagenes/iconoBuscarOferta.png")));
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 80, 535, 590);
		layeredPane.add(scrollPane);

		panelListado = new JPanel();
		panelListado.setBackground(FONDO_GRIS);
		scrollPane.setViewportView(panelListado);
		panelListado.setLayout(new BoxLayout(panelListado, BoxLayout.Y_AXIS));

		JPanel panelDetalle = new JPanel();
		panelDetalle.setBackground(TARJETA_BLANCA);
		panelDetalle.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		panelDetalle.setLayout(null);
		panelDetalle.setPreferredSize(new Dimension(645, 700));

		JScrollPane scrollDetalle = new JScrollPane(panelDetalle);
		scrollDetalle.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollDetalle.setBounds(603, 80, 663, 590);
		layeredPane.add(scrollDetalle);

		JLabel lblEmpresaLbl = new JLabel("EMPRESA:");
		lblEmpresaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmpresaLbl.setForeground(TEXTO_OSCURO);
		lblEmpresaLbl.setBounds(20, 20, 150, 25);
		panelDetalle.add(lblEmpresaLbl);

		nombreEmpresa = new RoundedTextField(20);
		nombreEmpresa.setText("EMPRESA");
		nombreEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nombreEmpresa.setEditable(false);
		nombreEmpresa.setBackground(new Color(204, 204, 204));
		nombreEmpresa.setBounds(20, 46, 590, 34);
		panelDetalle.add(nombreEmpresa);

		JLabel lblPuestoLbl = new JLabel("PUESTO SOLICITADO:");
		lblPuestoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPuestoLbl.setForeground(TEXTO_OSCURO);
		lblPuestoLbl.setBounds(20, 95, 260, 25);
		panelDetalle.add(lblPuestoLbl);

		puestoTxt = new RoundedTextField(20);
		puestoTxt.setText("PUESTO");
		puestoTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		puestoTxt.setEditable(false);
		puestoTxt.setBackground(new Color(204, 204, 204));
		puestoTxt.setBounds(20, 121, 590, 34);
		panelDetalle.add(puestoTxt);

		JLabel lblFechaLbl = new JLabel("FECHA DE SOLICITUD:");
		lblFechaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaLbl.setForeground(TEXTO_OSCURO);
		lblFechaLbl.setBounds(20, 170, 260, 25);
		panelDetalle.add(lblFechaLbl);

		fechaSolicitudTxt = new RoundedTextField(20);
		fechaSolicitudTxt.setText("FECHA");
		fechaSolicitudTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fechaSolicitudTxt.setEditable(false);
		fechaSolicitudTxt.setBackground(new Color(204, 204, 204));
		fechaSolicitudTxt.setBounds(20, 196, 280, 34);
		panelDetalle.add(fechaSolicitudTxt);

		JLabel lblEstadoLbl = new JLabel("ESTADO:");
		lblEstadoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEstadoLbl.setForeground(TEXTO_OSCURO);
		lblEstadoLbl.setBounds(330, 170, 150, 25);
		panelDetalle.add(lblEstadoLbl);

		estadoTxt = new RoundedTextField(20);
		estadoTxt.setText("ESTADO");
		estadoTxt.setFont(new Font("Tahoma", Font.BOLD, 16));
		estadoTxt.setEditable(false);
		estadoTxt.setBackground(new Color(204, 204, 204));
		estadoTxt.setBounds(330, 196, 280, 34);
		panelDetalle.add(estadoTxt);

		JLabel lblModalidadLbl = new JLabel("MODALIDAD:");
		lblModalidadLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModalidadLbl.setForeground(TEXTO_OSCURO);
		lblModalidadLbl.setBounds(20, 245, 260, 25);
		panelDetalle.add(lblModalidadLbl);

		modalidadTxt = new RoundedTextField(20);
		modalidadTxt.setText("MODALIDAD");
		modalidadTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		modalidadTxt.setEditable(false);
		modalidadTxt.setBackground(new Color(204, 204, 204));
		modalidadTxt.setBounds(20, 271, 280, 34);
		panelDetalle.add(modalidadTxt);

		JLabel lblRangoLbl = new JLabel("RANGO SALARIAL:");
		lblRangoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRangoLbl.setForeground(TEXTO_OSCURO);
		lblRangoLbl.setBounds(330, 245, 260, 25);
		panelDetalle.add(lblRangoLbl);

		rangoSalarialTxt = new RoundedTextField(20);
		rangoSalarialTxt.setText("RANGO SALARIAL");
		rangoSalarialTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rangoSalarialTxt.setEditable(false);
		rangoSalarialTxt.setBackground(new Color(204, 204, 204));
		rangoSalarialTxt.setBounds(330, 271, 280, 34);
		panelDetalle.add(rangoSalarialTxt);

		JLabel lblDescripcionLbl = new JLabel("DESCRIPCION DE LA OFERTA:");
		lblDescripcionLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcionLbl.setForeground(TEXTO_OSCURO);
		lblDescripcionLbl.setBounds(20, 320, 350, 25);
		panelDetalle.add(lblDescripcionLbl);

		txtDescripcionOferta = new JTextArea();
		txtDescripcionOferta.setLineWrap(true);
		txtDescripcionOferta.setWrapStyleWord(true);
		txtDescripcionOferta.setEditable(false);
		txtDescripcionOferta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtDescripcionOferta.setBackground(new Color(204, 204, 204));
		txtDescripcionOferta.setBounds(20, 350, 590, 250);
		panelDetalle.add(txtDescripcionOferta);
		
		JLabel lblNewLabel_1 = new JLabel("Mis Solicitudes");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 41));
		lblNewLabel_1.setBounds(43, 10, 385, 72);
		layeredPane.add(lblNewLabel_1);

		setTitle("MIS SOLICITUDES");
		setSize(1280, 720);
		setLocationRelativeTo(null);
	}

	public void cargarSolicitudes(ArrayList<SolicitudEmp> solicitudes) {
		panelListado.removeAll();
		for (final SolicitudEmp s : solicitudes) {
			panelListado.add(crearTarjeta(s));
			panelListado.add(javax.swing.Box.createRigidArea(new Dimension(0, 10)));
		}
		panelListado.revalidate();
		panelListado.repaint();

		if (!solicitudes.isEmpty()) {
			mostrarDetalle(solicitudes.get(0));
		}
	}

	private JPanel crearTarjeta(final SolicitudEmp s) {
		JPanel tarjeta = new JPanel();
		tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
		tarjeta.setBackground(TARJETA_BLANCA);
		tarjeta.setBorder(new MatteBorder(1, 1, 1, 1, new Color(220, 220, 220)));
		tarjeta.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
		tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

		String puesto = (s.getOferta() != null) ? s.getOferta().getPuesto() : "PUESTO NO DISPONIBLE";
		String empresa = (s.getOferta() != null && s.getOferta().getMyEmpresa() != null)
				? s.getOferta().getMyEmpresa().getNombre() : "EMPRESA NO DISPONIBLE";

		JLabel lblPuesto = new JLabel(puesto);
		lblPuesto.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		lblPuesto.setForeground(TEXTO_OSCURO);
		lblPuesto.setBorder(new EmptyBorder(8, 12, 2, 12));
		lblPuesto.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

		JLabel lblEmpresa = new JLabel(empresa);
		lblEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmpresa.setForeground(AZUL_PRINCIPAL);
		lblEmpresa.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblEmpresa.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

		JLabel lblEstado = new JLabel(s.isEstado() ? "ACEPTADA" : "PENDIENTE / RECHAZADA");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setForeground(s.isEstado() ? VERDE_AZULADO : new Color(0xC0, 0x5B, 0x5B));
		lblEstado.setBorder(new EmptyBorder(2, 12, 2, 12));
		lblEstado.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

		JLabel lblFecha = new JLabel(s.getFecha() != null ? s.getFecha().toString() : "");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setForeground(new Color(150, 150, 150));
		lblFecha.setBorder(new EmptyBorder(2, 12, 8, 12));
		lblFecha.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);

		tarjeta.add(lblPuesto);
		tarjeta.add(lblEmpresa);
		tarjeta.add(lblEstado);
		tarjeta.add(lblFecha);

		MouseAdapter listenerTarjeta = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarDetalle(s);
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
		lblEstado.addMouseListener(listenerTarjeta);
		lblFecha.addMouseListener(listenerTarjeta);

		return tarjeta;
	}

	private void mostrarDetalle(SolicitudEmp s) {
		if (s.getOferta() != null) {
			nombreEmpresa.setText(s.getOferta().getMyEmpresa() != null
					? s.getOferta().getMyEmpresa().getNombre() : "");
			puestoTxt.setText(s.getOferta().getPuesto());
			modalidadTxt.setText(s.getOferta().getModalidad());
			txtDescripcionOferta.setText(s.getOferta().getDescripcion());
		} else {
			nombreEmpresa.setText("");
			puestoTxt.setText("");
			modalidadTxt.setText("");
			txtDescripcionOferta.setText("");
		}

		fechaSolicitudTxt.setText(s.getFecha() != null ? s.getFecha().toString() : "");
		estadoTxt.setText(s.isEstado() ? "ACEPTADA" : "PENDIENTE / RECHAZADA");
		estadoTxt.setForeground(s.isEstado() ? VERDE_AZULADO : new Color(0xC0, 0x5B, 0x5B));
		rangoSalarialTxt.setText(s.getRangoSalarial());
	}
}