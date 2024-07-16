package com.aluracursos.forohub.model;

import com.aluracursos.forohub.repository.CursoRepository;
import com.aluracursos.forohub.repository.TopicoRepository;
import com.aluracursos.forohub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository topicoRepository,
                         UsuarioRepository usuarioRepository,
                         CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public Topico registrarTopico(TopicoRequest request) {
        Usuario autor = usuarioRepository.findById(request.getIdAutor())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + request.getIdAutor()));

        Curso curso = cursoRepository.findById(request.getIdCurso())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado con ID: " + request.getIdCurso()));

        if (topicoRepository.existsByTituloAndMensaje(request.getTitulo(), request.getMensaje())) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje");
        }

        Topico topico = new Topico();
        topico.setTitulo(request.getTitulo());
        topico.setMensaje(request.getMensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setStatus("Activo"); // Opcional: establecer un estado inicial

        return topicoRepository.save(topico);
    }

    public List<Topico> getAllTopicos() {
        return topicoRepository.findAll();
    }
}


