package logico;

import java.time.LocalDate;
import java.util.ArrayList;

public class SolicitudCentro extends Solicitud {
    
    private ArrayList<SolicitudEmp> solicitudEmps;
    private Institucion centro;
    private String perfil;
    private int cantVacante;
    private String urgencia;

    public SolicitudCentro(String identificador, boolean estado, Oferta oferta,
            ArrayList<SolicitudEmp> solicitudEmps, Institucion centro, String perfil, int cantVacante,
            String urgencia) {
        super(identificador, estado, oferta);
        this.solicitudEmps = solicitudEmps;
        this.centro = centro;
        this.perfil = perfil;
        this.cantVacante = cantVacante;
        this.urgencia = urgencia;
    }

    public ArrayList<SolicitudEmp> getSolicitudEmps() {
        return solicitudEmps;
    }

    public void setSolicitudEmps(ArrayList<SolicitudEmp> solicitudEmps) {
        this.solicitudEmps = solicitudEmps;
    }

    public Institucion getCentro() {
        return centro;
    }

    public void setCentro(Institucion centro) {
        this.centro = centro;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public int getCantVacante() {
        return cantVacante;
    }

    public void setCantVacante(int cantVacante) {
        this.cantVacante = cantVacante;
    }

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(String urgencia) {
        this.urgencia = urgencia;
    }

    
    

}
