package com.Vetnova.mascota.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Vetnova.mascota.model.Mascota;
import com.Vetnova.mascota.service.MascotaService;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {
    @Autowired
    private MascotaService service;

    @PostMapping
    public ResponseEntity<Mascota> crearMascota(@Valid @RequestBody Mascota mascota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearMascota(mascota));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerMascota(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerMascota(id));
    }
}
