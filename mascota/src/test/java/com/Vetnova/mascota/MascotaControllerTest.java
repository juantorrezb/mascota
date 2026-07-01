package com.Vetnova.mascota;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.Vetnova.mascota.controller.MascotaController;
import com.Vetnova.mascota.service.MascotaService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MascotaController.class)
class MascotaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MascotaService service;

    @Test
    void crearMascota_ValidacionExitosa() throws Exception {
        String json = "{\"nombre\":\"Rex\",\"especie\":\"Perro\",\"clienteId\":1}";
        mockMvc.perform(post("/api/mascotas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void crearMascota_ValidacionFallida() throws Exception {
        // Faltan nombre y clienteId
        String json = "{\"especie\":\"Perro\"}";
        mockMvc.perform(post("/api/mascotas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }
}
