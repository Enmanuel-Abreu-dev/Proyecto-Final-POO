package logico;

import java.time.LocalDate;

abstract class Solicitud {
    
    protected String identificador;
    protected LocalDate fecha;
    protected boolean estado;
    protected Oferta oferta;

    public Solicitud(String identificador, boolean estado, Oferta oferta) {
        this.identificador = identificador;
        this.fecha = LocalDate.now();
        this.estado = estado;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    
    
}
