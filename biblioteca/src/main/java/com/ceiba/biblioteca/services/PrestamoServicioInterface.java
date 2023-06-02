package com.ceiba.biblioteca.services;

public interface PrestamoServicioInterface<Prestamo> {
    public Prestamo crearPrestamo (Prestamo prestamoCreado) throws Exception;
    public Prestamo mostrarPrestamo(Integer id) throws Exception;
}
