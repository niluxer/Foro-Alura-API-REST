package com.alura.api_foro_alura.domain.topico;

import com.alura.api_foro_alura.domain.autor.Autor;
import com.alura.api_foro_alura.domain.curso.Curso;

public record DatosListadoTopico(Long id, String titulo, String mensaje, String fecha_creacion, String estatus, String autor, String curso) {

    public DatosListadoTopico(Topico topico)
    {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha_creacion().toString(), topico.getEstatus(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}
