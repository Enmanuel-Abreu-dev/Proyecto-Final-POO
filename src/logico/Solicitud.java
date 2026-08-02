package logico;

import java.time.LocalDate;
import java.io.Serializable;

public abstract class Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String identificador;
    protected LocalDate fecha;
    protected EstadoSolicutud estado;
    protected Oferta oferta;

    public Solicitud(String identificador, Oferta oferta) {
        this.identificador = identificador;
        this.fecha = LocalDate.now();
        this.estado = EstadoSolicutud.PENDIENTE;
        this.oferta = oferta;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EstadoSolicutud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicutud estado) {
        this.estado = estado;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    
}
