package com.aluracursos.forohub.model.dto;

import java.time.LocalDateTime;

public record RespuestaDTO(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autorNombre,
        boolean solucion
) {}
