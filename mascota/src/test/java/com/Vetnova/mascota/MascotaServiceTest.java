package com.Vetnova.mascota;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestTemplate;
import com.Vetnova.mascota.dto.ClienteDto;
import com.Vetnova.mascota.model.Mascota;
import com.Vetnova.mascota.repository.MascotaRepository;
import com.Vetnova.mascota.service.MascotaService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MascotaServiceTest {
    @MockitoBean
    private MascotaRepository repository;

    @MockitoBean
    private RestTemplate restTemplate;

    @Autowired
    private MascotaService service;

    @Test
    void crearMascota_ClienteExiste() {
        Mascota m = new Mascota();
        m.setNombre("Rex");
        m.setEspecie("Perro");
        m.setClienteId(1L);

        ClienteDto cdto = new ClienteDto();
        cdto.setId(1L);

        when(restTemplate.getForObject(anyString(), eq(ClienteDto.class))).thenReturn(cdto);
        when(repository.save(any(Mascota.class))).thenReturn(m);

        assertNotNull(service.crearMascota(m));
    }

    @Test
    void crearMascota_ClienteNoExiste() {
        Mascota m = new Mascota();
        m.setNombre("Rex");
        m.setEspecie("Perro");
        m.setClienteId(999L);

        when(restTemplate.getForObject(anyString(), eq(ClienteDto.class))).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> service.crearMascota(m));
    }
}
