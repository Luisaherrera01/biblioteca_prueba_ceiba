package com.ceiba.biblioteca.services;

import com.ceiba.biblioteca.entities.Prestamo;
import com.ceiba.biblioteca.repositories.PrestamoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;
@Service
public class PrestamoServicio implements PrestamoServicioInterface<Prestamo>{
    @Autowired //se inyecta dependencia
    public PrestamoRepositorio prestamoRepositorio;

    @Override
    public Prestamo crearPrestamo(Prestamo prestamoCreado) throws Exception {
        try {
            if(prestamoCreado.getTipoUsuario() ==3) {
                String identificacionUsuario = prestamoCreado.getIdentificacionUsuario();
                boolean tienePrestamoElUsuario = prestamoRepositorio.existsByIdentificacionUsuario(identificacionUsuario);
                if (tienePrestamoElUsuario) {
                    throw new Exception("El usuario con identificacion " + identificacionUsuario + " ya tiene un libro prestado");
                }
            }
            LocalDate fechaActual=LocalDate.now();
            LocalDate fechaMaximaDevolucion = null;

            if(prestamoCreado.getTipoUsuario()==1){
                fechaMaximaDevolucion=fechaActual.plusDays(10);
            }else if (prestamoCreado.getTipoUsuario()==2) {
                fechaMaximaDevolucion=fechaActual.plusDays(8);
            }else if (prestamoCreado.getTipoUsuario()==3){
                fechaMaximaDevolucion=fechaActual.plusDays(7);
            }else {
                throw new Exception("tipo de usuario no permitido");
            }

            while (fechaMaximaDevolucion.getDayOfWeek()== DayOfWeek.SATURDAY ||
                   fechaMaximaDevolucion.getDayOfWeek()==DayOfWeek.SUNDAY){
                   fechaMaximaDevolucion = fechaMaximaDevolucion.plusDays(1);
                }

            prestamoCreado.setFechaMaximaDevolucion(fechaMaximaDevolucion);
            Prestamo prestamosCreado= prestamoRepositorio.save(prestamoCreado);
            return prestamosCreado;
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Prestamo mostrarPrestamo(Integer id) throws Exception {
        try {
            Optional<Prestamo>prestamoOpcional = prestamoRepositorio.findById(id);
            if(prestamoOpcional.isPresent()){
                return prestamoOpcional.get();
            }else {
                throw  new Exception("Prestamo no encontrado");
            }
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }
}
