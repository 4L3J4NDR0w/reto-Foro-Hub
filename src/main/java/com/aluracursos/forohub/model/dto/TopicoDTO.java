package com.aluracursos.forohub.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public record TopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        String autorNombre,
        String cursoNombre,
        List<RespuestaDTO> respuestas
) {}
