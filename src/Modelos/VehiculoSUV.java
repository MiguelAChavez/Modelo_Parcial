/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author Miguel Angel
 */
public class VehiculoSUV extends Vehiculo {

    private static final double AUMENTO = 1.10;

    private boolean traccionIntegral;

    public VehiculoSUV(String codVehiculo, String modelo, double precioBase, boolean traccionIntegral) {
        super(codVehiculo, modelo, precioBase);
        this.traccionIntegral = traccionIntegral;
    }

    @Override
    public double calcularCostoReparacion(int horas) {
        if (horas < 1) {
            throw new IllegalArgumentException("Las horas de reparación deben ser mayores a 0");
        }

        double precioFinal = this.precioBase * horas;

        if (traccionIntegral) {
            precioFinal *= AUMENTO;
        }
        return precioFinal;
    }

    public boolean isTraccionIntegral() {
        return traccionIntegral;
    }

}
