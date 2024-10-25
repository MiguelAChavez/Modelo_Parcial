/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Utils.Estado;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Miguel Angel
 */
public class Taller {

    private String nombreTaller;
    private Set<Vehiculo> inventarioVehiculos;
    private Set<Vehiculo> vehiculosEnReparacion;
    private Set<Vehiculo> vehiculosFueraDeServicio;

    public Taller(String nombreTaller) {
        validarParametro(nombreTaller, "El nombre del taller no puede ser nulo o vacio");
        this.nombreTaller = nombreTaller;
        this.inventarioVehiculos = new HashSet<>();
        this.vehiculosEnReparacion = new HashSet<>();
        this.vehiculosFueraDeServicio = new HashSet<>();
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        if (!this.inventarioVehiculos.add(vehiculo)) {
            throw new IllegalArgumentException("El vehiculo ya esta en el taller " + nombreTaller);
        }
    }

    public double calcularCostoReparacion(String codigoVehiculo, int horas) throws IllegalArgumentException {
        Vehiculo vehiculoAReparar = traerVehiculo(codigoVehiculo);
        if (vehiculoAReparar == null) {
            throw new IllegalArgumentException("El vehiculo no se encuentra en el taller");
        }
        return vehiculoAReparar.calcularCostoReparacion(horas);
    }

    public void iniciarReparacion(String codigoVehiculo, String descripcion) {
        Vehiculo ve = traerVehiculo(codigoVehiculo);
        if (ve == null) {
            throw new IllegalArgumentException("El vehiculo no se encuentra en el taller");
        }
        if (this.vehiculosEnReparacion.add(ve)) {
            ve.iniciarReparacion(this.nombreTaller, descripcion);
        }
    }

    public void finalizarReparacion(String codigoVehiculo) {
        Vehiculo vehiculo = traerVehiculo(codigoVehiculo);
        if (!vehiculosEnReparacion.remove(vehiculo)) {
            throw new IllegalArgumentException("El vehículo no está en reparación");
        }
        vehiculo.finalizarReparacion();
    }

    private Vehiculo traerVehiculo(String codigoVehiculo) {
        for (Vehiculo ve : this.inventarioVehiculos) {
            if (ve.getCodVehiculo().equals(codigoVehiculo)) {
                return ve;
            }
        }
        return null;
    }

    public List<String> obtenerHistorialReparaciones(String codigoVehiculo) {
        Vehiculo ve = traerVehiculo(codigoVehiculo);
        return ve.obtenerHistorialReparaciones();
    }

    public Set<Vehiculo> traerVehiculos(Estado estado) {
        Set<Vehiculo> vehiculos = new HashSet<>();
        for (Vehiculo ve : this.inventarioVehiculos) {
            if (ve.getEstado().equals(estado)) {
                vehiculos.add(ve);
            }
        }
        return vehiculos;
    }

    public void mostrarVehiculosEnReparacion() {
        this.vehiculosEnReparacion.forEach(System.out::println);
    }


    public Set<Vehiculo> traerVehiculosDisponibles() {
        return this.inventarioVehiculos.stream().filter(vehiculo -> vehiculo.getEstado().equals(Estado.DISPONIBLE)).collect(Collectors.toSet());
    }

    private void validarParametro(String parametro, String mensaje) {
        if (parametro == null || parametro.isBlank() || parametro.isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }
}
