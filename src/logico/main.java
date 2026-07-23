    package logico;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class main {
    public static void main(String[] args) {

        Persona p1 = new Universitario(
            BolsaTrabajo.getInstance().generarIdPersona(), 
            "03131321", 
            "Enmanuel", 
            "Abreu",
            "enma@gmail.com", 
            "Santiago",
            "Masculino", 
            "80923023",
            "Republica Dominicana", 
            null,
            LocalDate.of(2007, 9, 15), 
            true, true,
            "Ingeniero Software",
            "PUCMM"
        );

        Persona p2 = new Tecnico(
            BolsaTrabajo.getInstance().generarIdPersona(), 
            "43232", 
            "Jose", 
            "Tineo",
            "Tineo@gmail.com", 
            "California",
            "Masculino", 
            "4903231",
            "Estados Unidos", 
            null,
            LocalDate.of(2007, 4, 1), 
            true, true,
            "Tecnico en Sistemas",
            "Loyola"
        );

        Experiencia exp1 = new Experiencia
        (
            "Capex", 
            "Pasante", 
            LocalDate.of(2024, 2, 1), 
            LocalDate.of(2025, 2, 1), 
            "Programador"
        );

        Institucion empresa1 = new Institucion
        (
            BolsaTrabajo.getInstance().generarIdInstitucion(),
            "Google", 
            "030123312",
            "Estados Unidos",
            "313232",
            "California",
            "2313323",
            "Google@gmail.com",
            null,
            100,
            true
        );

        Oferta o1 = new Oferta
        (
            BolsaTrabajo.getInstance().generarIdOferta(),
            empresa1,
            "Ingeniero Software",
            "Habil",
            50000,
            "Presencial",
            "Responsable",
            1, 
            18,
            "California",
            "Estados Unidos",
            "Masculino",
            LocalDate.of(2026, 7, 29),
            "Presencial",
            2,
            "Universitario"
        );

        Oferta o2 = new Oferta
        (
            BolsaTrabajo.getInstance().generarIdOferta(),
            empresa1,
            "Tecnico Software",
            "Oferta de Trabajo",
            10000,
            "Virtual", 
            "Habilidades Tecnicas",
            0, 
            20, 
            "California",
            "Estados Unidos",
            "Masculino",
            LocalDate.of(2026, 7, 30),
            "Pasante",
            3,
            "Tecnico en Sistemas"
        );

        Usuario user1 = new Usuario
        (
            BolsaTrabajo.getInstance().generarIdUsuario(), 
            "Google",
            "Google@gmail.com",
            "123", 
            empresa1, 
            null
        );

        SolicitudEmp s1 = new SolicitudEmp
        (
            BolsaTrabajo.getInstance().generarIdSolicitud(), 
            o1, 
            p1, 
            "100",
            "Presencial"
        );

        SolicitudEmp s2 = new SolicitudEmp
        (
            BolsaTrabajo.getInstance().generarIdSolicitud(), 
            o1, 
            p2, 
            "1000",
            "Virtual"
        );
        

        BolsaTrabajo.getInstance().registrarInstitucion(empresa1);
        BolsaTrabajo.getInstance().registrarPersona(p2);
        empresa1.setUsuario(user1);
        empresa1.publicarOferta(o1);
        empresa1.publicarOferta(o2);
        p1.agregarExperiencia(exp1);
        BolsaTrabajo.getInstance().registrarUsuario(user1);
        o1.registrarSolicitudEmp(s1);
        o1.registrarSolicitudEmp(s2);
        o2.registrarSolicitudEmp(s1);
        o2.registrarSolicitudEmp(s2);
        System.out.println(BolsaTrabajo.getInstance().iniciarSesion("Google", "123"));
        Usuario user = BolsaTrabajo.getInstance().crearUsuario(empresa1.getEmail(), empresa1, null);
        System.out.println(user.getNombre() + " " + user.getPassword());
 
        for ( int i = 0; i < empresa1.getMyOfertas().size(); i++ )
        {
            try
            {
                System.out.println(empresa1.getMyOfertas().get(i).getPuesto());
                ArrayList<Coincidencia> listaCoinci = BolsaTrabajo.getInstance().calcularCoincidencia(empresa1.getMyOfertas().get(i).getPuesto());

                for ( Coincidencia c : listaCoinci )
                    System.out.printf("Nombre: %s --> %.2f %n", c.getPersona().getPersona().getNombre(), c.getPorcentaje());
                    
            } catch ( NullPointerException e )
            {
                System.out.println("Algo a fallado");
            }

            System.out.println();
        }

        p1.setRutaImagen(BolsaTrabajo.getInstance().buscarImagen(p1.getNombre(), p1.getCedula()));

        BufferedImage imagen = BolsaTrabajo.getInstance().cargarImagenPersona(p1);

        if ( imagen !=  null )
            JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(imagen)));
        else System.out.println("No cargo");

        System.exit(0);
    }
}
