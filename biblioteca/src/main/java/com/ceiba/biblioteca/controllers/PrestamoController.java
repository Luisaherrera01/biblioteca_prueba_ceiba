package com.ceiba.biblioteca.controllers;

import com.ceiba.biblioteca.entities.Prestamo;
import com.ceiba.biblioteca.services.PrestamoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    @Autowired
    protected PrestamoServicio prestamoServicio;

    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(@RequestBody Prestamo registrarPrestamo){
        try {
            Prestamo prestamoRegistrado=prestamoServicio.crearPrestamo(registrarPrestamo);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(prestamoRegistrado);
        }catch (Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo>buscarPorId(@PathVariable Integer id){
        try {
            Prestamo prestamoEncontrado=prestamoServicio.mostrarPrestamo(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(prestamoEncontrado);
        }catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }
}
