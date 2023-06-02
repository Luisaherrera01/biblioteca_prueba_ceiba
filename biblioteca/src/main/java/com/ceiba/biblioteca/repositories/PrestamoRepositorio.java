package com.ceiba.biblioteca.repositories;

import com.ceiba.biblioteca.entities.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo,Integer> {
    boolean existsByIdentificacionUsuario(String identificacionUsuario);
    Optional<Prestamo> findByIdentificacionUsuario(String identificacionUsuario);

}
