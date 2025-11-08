package ec.com.ecotrackapp.models;

import java.io.Serializable;

/**
 * Clase que representa un vehículo recolector de residuos
 */
public class VehiculoRecolector implements Comparable<VehiculoRecolector>, Serializable {
    private static final long serialVersionUID = 1L;
    private static int contadorID = 1;

    private int id;
    private String placa;
    private String zonaAsignada;
    private double capacidadMaxima;
    private double cargaActual;
    private int prioridad;
    private boolean enRuta;

    public VehiculoRecolector(String placa, String zonaAsignada,
                             double capacidadMaxima, int prioridad) {
        this.id = contadorID++;
        this.placa = placa;
        this.zonaAsignada = zonaAsignada;
        this.capacidadMaxima = capacidadMaxima;
        this.cargaActual = 0;
        this.prioridad = prioridad;
        this.enRuta = false;
    }

    public boolean cargarResiduo(Residuo residuo) {
        if (cargaActual + residuo.getPeso() <= capacidadMaxima) {
            cargaActual += residuo.getPeso();
            return true;
        }
        return false;
    }

    public void descargar() {
        this.cargaActual = 0;
    }

    public boolean estaLleno() {
        return cargaActual >= capacidadMaxima * 0.9;
    }

    public double getPorcentajeCarga() {
        return (cargaActual / capacidadMaxima) * 100;
    }

    public double getCapacidadDisponible() {
        return capacidadMaxima - cargaActual;
    }

    public int getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getZonaAsignada() {
        return zonaAsignada;
    }

    public void setZonaAsignada(String zonaAsignada) {
        this.zonaAsignada = zonaAsignada;
    }

    public double getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(double capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public double getCargaActual() {
        return cargaActual;
    }

    public void setCargaActual(double cargaActual) {
        this.cargaActual = cargaActual;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isEnRuta() {
        return enRuta;
    }

    public void setEnRuta(boolean enRuta) {
        this.enRuta = enRuta;
    }

    @Override
    public int compareTo(VehiculoRecolector otro) {
        return Integer.compare(otro.prioridad, this.prioridad);
    }

    @Override
    public String toString() {
        return String.format("Vehículo %s | Zona: %s | Carga: %.2f/%.2f kg (%.1f%%) | Prioridad: %d | Estado: %s",
                placa, zonaAsignada, cargaActual, capacidadMaxima,
                getPorcentajeCarga(), prioridad, enRuta ? "En ruta" : "Disponible");
    }
}
