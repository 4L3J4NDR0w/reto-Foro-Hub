package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.model.Curso;
import com.aluracursos.forohub.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        Curso savedCurso = cursoRepository.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCurso);
    }
}
