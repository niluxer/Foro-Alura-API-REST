package com.alura.api_foro_alura.domain.usuarios;

import com.alura.api_foro_alura.domain.topico.Topico;

public record DatosListadoUsuario(Long id, String login) {

    public DatosListadoUsuario(Usuario usuario)
    {
        this(usuario.getId(), usuario.getLogin());
    }
}
