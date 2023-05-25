package com.alura.api_foro_alura.domain.topico;

public record DatosRespuestaTopico(Long id, String titulo, String mensaje, String fecha_creacion, String estatus, String autor, String curso) {

    public DatosRespuestaTopico(Topico topico)
    {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getEstatus(), topico.getFecha_creacion().toString(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}
