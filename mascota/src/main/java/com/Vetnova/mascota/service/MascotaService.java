package com.Vetnova.mascota.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Vetnova.mascota.dto.ClienteDto;
import com.Vetnova.mascota.model.Mascota;
import com.Vetnova.mascota.repository.MascotaRepository;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public Mascota crearMascota(Mascota mascota) {
        String url = "http://localhost:8081/api/clientes/" + mascota.getClienteId();
        try {
            restTemplate.getForObject(url, ClienteDto.class);
        } catch (Exception e) {
            throw new RuntimeException("El Cliente con ID " + mascota.getClienteId() + " no existe.");
        }
        return repository.save(mascota);
    }

    public Mascota obtenerMascota(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));
    }
}