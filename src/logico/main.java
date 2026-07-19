package logico;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;

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
            LocalDate.now(), 
            LocalDate.of(2026, 7, 29),
            true, 
            "Presencial",
            2,
            "Universitario"
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
            true,
            o1, 
            p1, 
            "100",
            "Presencial"
        );

        SolicitudEmp s2 = new SolicitudEmp
        (
            BolsaTrabajo.getInstance().generarIdSolicitud(), 
            true,
            o1, 
            p2, 
            "1000",
            "Virtual"
        );

        BolsaTrabajo.getInstance().registrarInstitucion(empresa1);
        BolsaTrabajo.getInstance().registrarPersona(p2);
        empresa1.setUsuario(user1);
        empresa1.publicarOferta(o1);
        p1.agregarExperiencia(exp1);
        BolsaTrabajo.getInstance().registrarUsuario(user1);
        o1.registrarSolicitudEmp(s1);
        o1.registrarSolicitudEmp(s2);

        ArrayList<Coincidencia> listaCoinci = BolsaTrabajo.getInstance().calcularCoincidencia("Google", "123", "Ingeniero Software");

        for ( Coincidencia c : listaCoinci )
            System.out.printf("Nombre: %s -> %.2f %n", c.getPersona().getPersona().getNombre(), c.getPorcentaje());

    }
}
