package com.Vetnova.mascota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Vetnova.mascota.model.Mascota;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}