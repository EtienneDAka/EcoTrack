package ec.com.ecotrackapp.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa un residuo en el sistema EcoTrack
 */
public class Residuo implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int contadorID = 1;

    private int id;
    private String nombre;
    private TipoResiduo tipo;
    private double peso;
    private LocalDate fechaRecoleccion;
    private String zona;
    private int prioridadAmbiental;

    public enum TipoResiduo {
        ORGANICO("Orgánico"),
        PLASTICO("Plástico"),
        VIDRIO("Vidrio"),
        ELECTRONICO("Electrónico"),
        METAL("Metal"),
        PAPEL("Papel"),
        PELIGROSO("Peligroso");

        private String nombre;

        TipoResiduo(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }
    }

    public Residuo(String nombre, TipoResiduo tipo, double peso,
                   LocalDate fechaRecoleccion, String zona, int prioridadAmbiental) {
        this.id = contadorID++;
        this.nombre = nombre;
        this.tipo = tipo;
        this.peso = peso;
        this.fechaRecoleccion = fechaRecoleccion;
        this.zona = zona;
        this.prioridadAmbiental = Math.max(1, Math.min(10, prioridadAmbiental));
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoResiduo getTipo() {
        return tipo;
    }

    public void setTipo(TipoResiduo tipo) {
        this.tipo = tipo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public LocalDate getFechaRecoleccion() {
        return fechaRecoleccion;
    }

    public void setFechaRecoleccion(LocalDate fechaRecoleccion) {
        this.fechaRecoleccion = fechaRecoleccion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public int getPrioridadAmbiental() {
        return prioridadAmbiental;
    }

    public void setPrioridadAmbiental(int prioridadAmbiental) {
        this.prioridadAmbiental = Math.max(1, Math.min(10, prioridadAmbiental));
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | Tipo: %s | Peso: %.2f kg | Zona: %s | Prioridad: %d",
                id, nombre, tipo.getNombre(), peso, zona, prioridadAmbiental);
    }
}

