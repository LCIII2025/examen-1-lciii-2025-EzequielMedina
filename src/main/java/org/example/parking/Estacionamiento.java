package org.example.parking;

import java.util.*;

public class Estacionamiento {
    private final int capacidadMaxima = 50;
    private final Map<String, Ticket> vehiculosEstacionados = new HashMap<>();
    private final Map<String, Cliente> clientesRegistrados = new HashMap<>();

    public boolean ingresarVehiculo(String dni, String nombre, Vehiculo vehiculo) {
        // TODO implementar la logica para registrar el ingreso de un nuevo vehiculo en el parking
        // la capacidad maxima del estacionamiento es de 50 vehiculos, si supera esta cap[acidad retornar FALSE
        // validar que no exista otro vehiculo con la misma patente, es un caso de error, retornar FALSE
        // validar si existe el cliente registrado, agregar el nuevo vehiculo en la lista del cliente existente, caso contrario crear un nuevo registro
        // si el proceso es exitoso retornar TRUE

        if (validarCapacidad()) return false;

        if (validarPatenteDeVehiculoEstacionado(vehiculo)) return false;

        Cliente cliente = getCliente(dni, nombre);

        cliente.agregarVehiculo(vehiculo);
        Ticket ticket = new Ticket(cliente, vehiculo);
        vehiculosEstacionados.put(vehiculo.getPatente(), ticket);

        return true;

    }

    private Cliente getCliente(String dni, String nombre) {
        Cliente cliente = clientesRegistrados.get(dni);
        if (cliente == null) {
            cliente = registrarCliente(dni, nombre);
        }
        return cliente;
    }

    private Cliente registrarCliente(String dni, String nombre) {
        Cliente cliente;
        cliente = new Cliente(dni, nombre);
        clientesRegistrados.put(dni, cliente);
        return cliente;
    }

    private boolean validarPatenteDeVehiculoEstacionado(Vehiculo vehiculo) {
        return vehiculosEstacionados.containsKey(vehiculo.getPatente());
    }

    private boolean validarCapacidad() {
        return vehiculosEstacionados.size() >= capacidadMaxima;
    }

    public Ticket retirarVehiculo(String patente) throws Exception {
        // TODO implementar la lógica para retirar un vehiculo del parking
        // validar que exista la patente, caso contrario arrojar la exception "Vehiculo no encontrado"
        // calcular y retornar el ticket del vehiculoEstacionado (ver Ticket.marcarSalida())

        Ticket ticket = vehiculosEstacionados.get(patente);
        if (ticket == null) {
            throw new Exception("Vehiculo no encontrado");
        }
        ticket.marcarSalida();
        vehiculosEstacionados.remove(patente);
        return ticket;
    }

    public List<Ticket> listarVehiculosEstacionados() {
        return new ArrayList<>(vehiculosEstacionados.values());
    }
}
