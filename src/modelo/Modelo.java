/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package modelo;

import Modelos.Taller;
import Modelos.Vehiculo;
import Modelos.VehiculoCompacto;

/**
 * @author Miguel Angel
 */
public class Modelo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here


        try {
            Taller taller = new Taller("Taller 1");
            Vehiculo ve = new VehiculoCompacto("1234567", "algo", 100, 2024);

            taller.agregarVehiculo(ve);
            System.out.println("agregado....");

            taller.iniciarReparacion("1234567", "algo");
            taller.mostrarVehiculosEnReparacion();

            taller.finalizarReparacion("1234567");

            taller.mostrarVehiculosEnReparacion();
            System.out.println("finalizado....");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


        try {
            Taller taller = new Taller("Taller 2");
            Vehiculo ve = new VehiculoCompacto("1234565", "algo", 100, 2024);
            System.out.println("vehiculo creado.... " + ve);

            taller.agregarVehiculo(ve);
            System.out.println("agregado....");

            taller.traerVehiculosDisponibles().forEach(System.out::println);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

}
