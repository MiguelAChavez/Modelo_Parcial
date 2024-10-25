/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Utils.Estado;

/**
 *
 * @author Miguel Angel
 */
public abstract class Vehiculo {

    protected String codVehiculo;
    protected String modelo;
    protected double precioBase;
    protected Estado estado;
    protected List<String> historialReparaciones;
    protected static final Set<String> codigosVehiculos = new HashSet<>();

    public Vehiculo(String codVehiculo, String modelo, double precioBase) {
        sonValidos(codVehiculo, modelo);
        codigoValido(codVehiculo);
        agregarCodVehiculo(codVehiculo);
        this.codVehiculo = codVehiculo;
        this.modelo = modelo;
        this.precioBase = precioBase;
        this.estado = Estado.DISPONIBLE;
        this.historialReparaciones = new ArrayList<>();
    }

    public String getCodVehiculo() {
        return codVehiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
   
    public Estado getEstado() {
        return estado;
    }

    protected void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "codVehiculo='" + codVehiculo + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precioBase=" + precioBase +
                ", estado=" + estado +
                '}';
    }

    public abstract double calcularCostoReparacion(int horas) throws IllegalArgumentException;

    public void iniciarReparacion(String nombreTaller, String descripcion) throws IllegalArgumentException, NullPointerException {
        sonValidos(nombreTaller, descripcion);

        if (this.estado.equals(Estado.EN_REPARACION)) {
            throw new IllegalArgumentException("El vehiculo ya se encuentra en reparacion");
        }

        setEstado(Estado.EN_REPARACION);

        StringBuilder sb = new StringBuilder();
        sb.append(LocalDate.now())
                .append(" : ")
                .append(nombreTaller)
                .append(" ")
                .append(descripcion);

        this.historialReparaciones.add(sb.toString());
    }

    public void finalizarReparacion() {
        if (estado != Estado.EN_REPARACION) {
            throw new IllegalArgumentException("El vehiculo no esta en reparacion");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(LocalDate.now())
                .append(" : Reparacion finalizada");

        this.historialReparaciones.add(sb.toString());

        setEstado(Estado.DISPONIBLE);
    }

    public List<String> obtenerHistorialReparaciones() {
        return this.historialReparaciones;
    }

    private void codigoValido(String codVehiculo) {
        if (codVehiculo.length() != 7) {
            throw new IllegalArgumentException("La longitud del codigo no es de 7 caracteres");
        }
    }

    private boolean sonValidos(String... parametros) {
        if (parametros == null || parametros.length == 0) {
            throw new IllegalArgumentException("Los parámetros no pueden ser nulos ni vacíos.");
        }

        for (String param : parametros) {
            if ( param == null || param.isEmpty() || param.isBlank()) {
                throw new IllegalArgumentException("parametro incorrecto: " + param + "no puede ser nulo, vacío o contener solo espacios.");
            }
        }

        return true;
    }

    private void agregarCodVehiculo(String codVehiculo) {
        if (!codigosVehiculos.add(codVehiculo)) {
            throw new IllegalArgumentException("El codigo de vehiculo ya existe");
        }
    }

}
