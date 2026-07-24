package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import logico.Institucion;


public class ListSolicitudesRecibidas extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final Color AZUL_OSCURO = new Color(0x16, 0x3A, 0x5F);
	private static final Color AZUL_PRINCIPAL = new Color(0x25, 0x63, 0xA6);
	private static final Color VERDE_AZULADO = new Color(0x0F, 0x8B, 0x8D);
	private static final Color FONDO_GRIS = new Color(0xF4, 0xF6, 0xF8);
	private static final Color TARJETA_BLANCA = Color.WHITE;
	private static final Color TEXTO_OSCURO = new Color(0x1F, 0x29, 0x37);

	private JPanel panelListado;
	private RoundedTextField nombreCandidatoTxt;
	private RoundedTextField cedulaTxt;
	private RoundedTextField tipoCandidatoTxt;
	private RoundedTextField fechaSolicitudTxt;
	private RoundedTextField estadoTxt;
	private RoundedTextField dispViajarTxt;
	private RoundedTextField dispResidenciaTxt;
	private JTextArea txtExperiencia;

	private final Institucion empresa;

	public static void main(String[] args) {
		try {
			ListSolicitudesRecibidas dialog = new ListSolicitudesRecibidas(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListSolicitudesRecibidas(Institucion empresa) {
		this.empresa = empresa;

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ListSolicitudesRecibidas.class.getResource("/imagenes/iconoBuscarOferta.png")));
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

		JLabel lblNombreLbl = new JLabel("CANDIDATO:");
		lblNombreLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreLbl.setForeground(TEXTO_OSCURO);
		lblNombreLbl.setBounds(20, 20, 150, 25);
		panelDetalle.add(lblNombreLbl);

		nombreCandidatoTxt = new RoundedTextField(20);
		nombreCandidatoTxt.setText("NOMBRE CANDIDATO");
		nombreCandidatoTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nombreCandidatoTxt.setEditable(false);
		nombreCandidatoTxt.setBackground(new Color(204, 204, 204));
		nombreCandidatoTxt.setBounds(20, 46, 590, 34);
		panelDetalle.add(nombreCandidatoTxt);

		JLabel lblCedulaLbl = new JLabel("CEDULA:");
		lblCedulaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCedulaLbl.setForeground(TEXTO_OSCURO);
		lblCedulaLbl.setBounds(20, 95, 260, 25);
		panelDetalle.add(lblCedulaLbl);

		cedulaTxt = new RoundedTextField(20);
		cedulaTxt.setText("CEDULA");
		cedulaTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cedulaTxt.setEditable(false);
		cedulaTxt.setBackground(new Color(204, 204, 204));
		cedulaTxt.setBounds(20, 121, 280, 34);
		panelDetalle.add(cedulaTxt);

		JLabel lblTipoLbl = new JLabel("TIPO DE CANDIDATO:");
		lblTipoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoLbl.setForeground(TEXTO_OSCURO);
		lblTipoLbl.setBounds(330, 95, 260, 25);
		panelDetalle.add(lblTipoLbl);

		tipoCandidatoTxt = new RoundedTextField(20);
		tipoCandidatoTxt.setText("TIPO");
		tipoCandidatoTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tipoCandidatoTxt.setEditable(false);
		tipoCandidatoTxt.setBackground(new Color(204, 204, 204));
		tipoCandidatoTxt.setBounds(330, 121, 280, 34);
		panelDetalle.add(tipoCandidatoTxt);

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

		JLabel lblDispViajarLbl = new JLabel("DISPONIBILIDAD DE VIAJE:");
		lblDispViajarLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDispViajarLbl.setForeground(TEXTO_OSCURO);
		lblDispViajarLbl.setBounds(20, 245, 260, 25);
		panelDetalle.add(lblDispViajarLbl);

		dispViajarTxt = new RoundedTextField(20);
		dispViajarTxt.setText("DISP. VIAJE");
		dispViajarTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dispViajarTxt.setEditable(false);
		dispViajarTxt.setBackground(new Color(204, 204, 204));
		dispViajarTxt.setBounds(20, 271, 280, 34);
		panelDetalle.add(dispViajarTxt);

		JLabel lblDispResidenciaLbl = new JLabel("DISPONIBILIDAD DE MUDANZA:");
		lblDispResidenciaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDispResidenciaLbl.setForeground(TEXTO_OSCURO);
		lblDispResidenciaLbl.setBounds(330, 245, 280, 25);
		panelDetalle.add(lblDispResidenciaLbl);

		dispResidenciaTxt = new RoundedTextField(20);
		dispResidenciaTxt.setText("DISP. MUDANZA");
		dispResidenciaTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dispResidenciaTxt.setEditable(false);
		dispResidenciaTxt.setBackground(new Color(204, 204, 204));
		dispResidenciaTxt.setBounds(330, 271, 280, 34);
		panelDetalle.add(dispResidenciaTxt);

		JLabel lblExperienciaLbl = new JLabel("EXPERIENCIA:");
		lblExperienciaLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExperienciaLbl.setForeground(TEXTO_OSCURO);
		lblExperienciaLbl.setBounds(20, 320, 350, 25);
		panelDetalle.add(lblExperienciaLbl);

		txtExperiencia = new JTextArea();
		txtExperiencia.setLineWrap(true);
		txtExperiencia.setWrapStyleWord(true);
		txtExperiencia.setEditable(false);
		txtExperiencia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtExperiencia.setBackground(new Color(204, 204, 204));
		txtExperiencia.setBounds(20, 350, 590, 250);
		panelDetalle.add(txtExperiencia);

		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBackground(VERDE_AZULADO);
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAceptar.setBounds(20, 615, 280, 42);
		panelDetalle.add(btnAceptar);

		JButton btnRechazar = new JButton("RECHAZAR");
		btnRechazar.setForeground(Color.WHITE);
		btnRechazar.setBackground(new Color(0xC0, 0x5B, 0x5B));
		btnRechazar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRechazar.setBounds(330, 615, 280, 42);
		panelDetalle.add(btnRechazar);

		JLabel lblNewLabel_1 = new JLabel("Solicitudes Recibidas");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 41));
		lblNewLabel_1.setBounds(43, 10, 500, 72);
		layeredPane.add(lblNewLabel_1);

		setTitle("SOLICITUDES RECIBIDAS");
		setSize(1280, 720);
		setLocationRelativeTo(null);
	}
}