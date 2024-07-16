package com.aluracursos.forohub.controller;


import com.aluracursos.forohub.model.*;
import com.aluracursos.forohub.model.dto.RespuestaDTO;
import com.aluracursos.forohub.model.dto.TopicoDTO;
import com.aluracursos.forohub.repository.CursoRepository;
import com.aluracursos.forohub.repository.TopicoRepository;
import com.aluracursos.forohub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> getAllTopicos() {
        List<Topico> topicos = topicoRepository.findAll();
        List<TopicoDTO> topicosDTO = topicos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(topicosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> getTopicoById(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = optionalTopico.get();
        TopicoDTO topicoDTO = convertToDto(topico);

        return ResponseEntity.ok(topicoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> updateTopico(@PathVariable Long id, @RequestBody TopicoDTO topicoDTO) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topico existingTopico = optionalTopico.get();

        // Actualizar los campos del tópico existente con los nuevos valores
        existingTopico.setTitulo(topicoDTO.titulo());
        existingTopico.setMensaje(topicoDTO.mensaje());
        existingTopico.setFechaCreacion(topicoDTO.fechaCreacion());
        existingTopico.setStatus(topicoDTO.status());

        // Guardar el tópico actualizado en la base de datos
        Topico updatedTopico = topicoRepository.save(existingTopico);

        // Convertir y devolver el DTO actualizado como respuesta
        TopicoDTO updatedTopicoDTO = convertToDto(updatedTopico);
        return ResponseEntity.ok(updatedTopicoDTO);
    }



    @PostMapping
    public ResponseEntity<TopicoDTO> createTopico(@Valid @RequestBody CrearTopicoDTO crearTopicoDTO) {
        Optional<Usuario> autorOpt = usuarioRepository.findById(crearTopicoDTO.getAutorId());
        Optional<Curso> cursoOpt = cursoRepository.findById(crearTopicoDTO.getCursoId());

        if (!autorOpt.isPresent() || !cursoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }



        Topico topico = new Topico();
        if (topico.getStatus() == null || topico.getStatus().isEmpty()) {
            topico.setStatus("sin resolver"); // Cambia "valor_predeterminado" por el valor adecuado
        }
        topico.setTitulo(crearTopicoDTO.getTitulo());
        topico.setMensaje(crearTopicoDTO.getMensaje());
        topico.setAutor(autorOpt.get());
        topico.setCurso(cursoOpt.get());

        Topico savedTopico = topicoRepository.save(topico);
        TopicoDTO topicoDTO = convertToDto(savedTopico);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicoDTO);
    }

    private TopicoDTO convertToDto(Topico topico) {
        List<RespuestaDTO> respuestaDTOs = topico.getRespuestas().stream()
                .map(respuesta -> new RespuestaDTO(
                        respuesta.getId(),
                        respuesta.getMensaje(),
                        respuesta.getFechaCreacion(),
                        respuesta.getAutor() != null ? respuesta.getAutor().getNombre() : null,
                        respuesta.isSolucion()
                ))
                .collect(Collectors.toList());

        return new TopicoDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor() != null ? topico.getAutor().getNombre() : null,
                topico.getCurso() != null ? topico.getCurso().getNombre() : null,
                respuestaDTOs
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopico(@PathVariable Long id) {
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


