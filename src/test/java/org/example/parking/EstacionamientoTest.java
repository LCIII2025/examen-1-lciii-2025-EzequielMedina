package org.example.parking;

import org.junit.Test;

import static junit.framework.Assert.*;

public class EstacionamientoTest {

    @Test
    public void testRetirarVehiculo() throws Exception {
        //TODO test

        Estacionamiento estacionamiento = new Estacionamiento();
        Cliente cliente = new Cliente
                ("38504157", "Ezequiel Medina");
        Vehiculo vehiculo = new Vehiculo("ab234ha", "Palio", Vehiculo.Tipo.AUTO);
        cliente.agregarVehiculo(vehiculo);
        estacionamiento.ingresarVehiculo(cliente.getDni(), cliente.getNombre(), vehiculo);
        Ticket ticket = estacionamiento.retirarVehiculo(vehiculo.getPatente());

        assertEquals(vehiculo.getPatente(), ticket.getVehiculo().getPatente());
        assertEquals(cliente.getNombre(), ticket.getCliente().getNombre());
        assertEquals(vehiculo.getModelo(), ticket.getVehiculo().getModelo());
        assertEquals(vehiculo.getTipo(), ticket.getVehiculo().getTipo());
        assertNotNull(ticket.getHoraSalida());
        assertNotNull(ticket.getHoraEntrada());
        assertTrue(ticket.calcularMinutos() > 0);
        assertTrue(ticket.calcularPrecio() > 0);

    }

    @Test
    public void testCalcularPrecio() throws Exception {
        // TODO test

        Estacionamiento estacionamiento = new Estacionamiento();
        Cliente cliente = new Cliente
                ("38504157", "Ezequiel Medina");
        Vehiculo vehiculo = new Vehiculo("ab234ha", "Palio", Vehiculo.Tipo.AUTO);
        cliente.agregarVehiculo(vehiculo);
        estacionamiento.ingresarVehiculo(cliente.getDni(), cliente.getNombre(), vehiculo);
        Ticket ticket = estacionamiento.retirarVehiculo(vehiculo.getPatente());
        double precio = ticket.calcularPrecio();

        assertTrue(precio > 0);

    }

}