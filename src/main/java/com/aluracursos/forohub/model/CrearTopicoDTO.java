package com.aluracursos.forohub.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CrearTopicoDTO {
    @NotBlank
    private String titulo;
    @NotBlank
    private String mensaje;
    @NotNull
    private Long autorId;
    @NotNull
    private Long cursoId;

    // Getters y setters
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

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}

