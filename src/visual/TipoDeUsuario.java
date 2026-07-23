package visual;

import java.awt.*;
import java.net.URL;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TipoDeUsuario extends JDialog {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TipoDeUsuario dialog = new TipoDeUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TipoDeUsuario() {
		setTitle("TIPO DE REGISTRO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TipoDeUsuario.class.getResource("/imagenes/iconoRegistrar.png")));
		setBounds(100, 100, 450, 300);

		Dimension dim = getToolkit().getScreenSize();
		setSize(607, 357);
		setLocationRelativeTo(null);

		URL rutaImagen = getClass().getResource("/imagenes/FondoSelectUserType.png");

		if (rutaImagen != null) {
			imagenFondo = new ImageIcon(rutaImagen).getImage();
		}

		JLayeredPane layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(null);

		panelFondo.setBounds(0, 0, 607, 357);
		layeredPane.add(panelFondo, JLayeredPane.DEFAULT_LAYER);
		panelFondo.setLayout(null);

		// --- Botón USUARIO ---
		RoundedButton btnNewButton = new RoundedButton("USUARIO", 20);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegPersona reg = new RegPersona(null);
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		btnNewButton.setBounds(52, 129, 225, 103);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(102, 204, 255)); // azul principal
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelFondo.add(btnNewButton);

		URL rutaIconoUsuario = getClass().getResource("/imagenes/iconoProfesion.png");
		if (rutaIconoUsuario != null) {
			ImageIcon iconoUsuario = new ImageIcon(rutaIconoUsuario);
			Image imgUsuarioEscalada = iconoUsuario.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			btnNewButton.setIcon(new ImageIcon(imgUsuarioEscalada));
			btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
			btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnNewButton.setIconTextGap(10);
		}

		// --- Botón EMPRESA ---
		RoundedButton btnNewButton_1 = new RoundedButton("EMPRESA", 20);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegEmpresa reg = new RegEmpresa(null);
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(329, 129, 225, 103);
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBackground(new Color(102, 204, 255)); // verde azulado
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelFondo.add(btnNewButton_1);

		URL rutaIconoEmpresa = getClass().getResource("/imagenes/iconoEmpresa.png");
		if (rutaIconoEmpresa != null) {
			ImageIcon iconoEmpresa = new ImageIcon(rutaIconoEmpresa);
			Image imgEmpresaEscalada = iconoEmpresa.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			btnNewButton_1.setIcon(new ImageIcon(imgEmpresaEscalada));
			btnNewButton_1.setHorizontalTextPosition(SwingConstants.CENTER);
			btnNewButton_1.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnNewButton_1.setIconTextGap(10);
		}

		// --- Título ---
		JLabel lblNewLabel = new JLabel("¿Como Desea Registrarse?");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Bahnschrift", Font.BOLD, 40));
		lblNewLabel.setBounds(38, 56, 548, 45);
		panelFondo.add(lblNewLabel);

	}
}