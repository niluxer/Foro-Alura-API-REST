package com.alura.api_foro_alura.domain.usuarios;

import com.alura.api_foro_alura.domain.topico.Topico;

public record DatosRespuestaUsuario(Long id, String login) {

    public DatosRespuestaUsuario(Usuario usuario)
    {
        this(usuario.getId(), usuario.getLogin());
    }
}
