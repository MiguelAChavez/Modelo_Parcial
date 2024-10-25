/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.time.LocalDate;

/**
 * @author Miguel Angel
 */
public class VehiculoCompacto extends Vehiculo {

    private static final double DESCUENTO = 0.95;
    private int anio;

    public VehiculoCompacto(String codVehiculo, String modelo, double precioBase, int anio) {
        super(codVehiculo, modelo, precioBase);
        this.anio = anio;
    }

    @Override
    public double calcularCostoReparacion(int horas) {
        if (horas < 1) {
            throw new IllegalArgumentException("Las horas de reparación deben ser mayores a 0");
        }
        double precioFinal = this.precioBase * horas;
        return (esVehiculoNuevo()) ? precioFinal * DESCUENTO : precioFinal;
    }

    public boolean esVehiculoNuevo() {
        return LocalDate.now().getYear() == this.anio;
    }

}
