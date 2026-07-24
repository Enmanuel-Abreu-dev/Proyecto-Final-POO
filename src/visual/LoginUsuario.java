package visual;

import java.awt.*;
import java.net.URL;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

import logico.BolsaTrabajo;
import logico.Usuario;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.*;

public class LoginUsuario extends JDialog {

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

    private final RoundedPanel panelTarjeta = new RoundedPanel(25, new Color(0, 0, 51)) {

        private static final long serialVersionUID = 1L;
    };

    public static void main(String[] args) {

        try {
            LoginUsuario dialog = new LoginUsuario();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    public LoginUsuario() {
    	cargarControladora();
    	setIconImage(Toolkit.getDefaultToolkit().getImage(LoginUsuario.class.getResource("/imagenes/iconoInicioSesion.png")));

        setTitle("Iniciar Sesion");
        setSize(574, 507);
        setLocationRelativeTo(null);
        setResizable(false);

        URL rutaImagen = getClass().getResource("/imagenes/fondoLogin.jpg");

        if (rutaImagen != null) {
            imagenFondo = new ImageIcon(rutaImagen).getImage();
        }

       
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 400));

        setContentPane(layeredPane);
        layeredPane.setLayout(null);

        panelFondo.setBounds(0, 0, 574, 507);
        layeredPane.add(panelFondo, JLayeredPane.DEFAULT_LAYER);
        panelFondo.setLayout(null);

        panelTarjeta.setBounds(121, 94, 300, 260);
        panelFondo.add(panelTarjeta);
        panelTarjeta.setLayout(null);

        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setBounds(89, 25, 122, 24);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTarjeta.add(lblTitulo);

        RoundedTextField txtEmail = new RoundedTextField(20);
        txtEmail.setText("Usuario");
        txtEmail.setForeground(new Color(0, 0, 0));

        txtEmail.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {

                if (txtEmail.getText().equals("Usuario")) {
                    txtEmail.setText("");
                    txtEmail.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (txtEmail.getText().trim().isEmpty()) {
                    txtEmail.setText("Usuario");
                    txtEmail.setForeground(Color.BLACK);
                }
            }
        });

        txtEmail.setBounds(25, 67, 250, 30);
        txtEmail.setMaximumSize(new Dimension(250, 30));
        txtEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTarjeta.add(txtEmail);

        RoundedPasswordField txtPassword = new RoundedPasswordField(20);
        txtPassword.setToolTipText("");
        txtPassword.setBounds(25, 107, 250, 30);
        txtPassword.setMaximumSize(new Dimension(250, 30));
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTarjeta.add(txtPassword);

        RoundedButton okButton = new RoundedButton("Ingresar", 20);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = txtEmail.getText();
                String pass = new String(txtPassword.getPassword());

                boolean ok = BolsaTrabajo.getInstance().iniciarSesion(usuario, pass);

                if (ok) {
                    Principal myPrincipal = new Principal(BolsaTrabajo.getInstance().getUsuarioActual());
                    myPrincipal.setVisible(true);
                    myPrincipal.setModal(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        okButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        okButton.setBounds(28, 173, 107, 30);
        okButton.setForeground(Color.WHITE);
        okButton.setBackground(new Color(0, 0, 153));
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.setFocusPainted(false);
        panelTarjeta.add(okButton);

        RoundedButton cancelButton = new RoundedButton("Registrar", 20);
        cancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        cancelButton.setBounds(163, 173, 107, 30);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(new Color(0, 0, 153));
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.setFocusPainted(false); 
        

        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
            	TipoDeUsuario tipo = new TipoDeUsuario();
            	tipo.setModal(true);
            	tipo.setVisible(true);
            }
        });

        panelTarjeta.add(cancelButton);

        getRootPane().setDefaultButton(okButton);
    }
    
    public void cargarControladora() {
    	File source = new File("save.bin");
    	if (!source.exists()) {
    		Usuario admin = new Usuario(BolsaTrabajo.getInstance().generarIdUsuario(), "admin", "admin.32@gmail.com", "admin1234@", null, null);
    		BolsaTrabajo.getInstance().registrarUsuario(admin);
    		return;
    	}
    	
    	try {
    		ObjectInputStream io = new ObjectInputStream(new FileInputStream(source));
    		io.readObject();
    		io.close();
    	} catch (IOException | ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }
}