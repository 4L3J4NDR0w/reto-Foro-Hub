package com.aluracursos.forohub.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TopicoRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensaje;

    @NotNull
    private Long idAutor;

    @NotNull
    private Long idCurso;

    // Getters y Setters omitidos para brevedad

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    // Constructores, si es necesario
}

