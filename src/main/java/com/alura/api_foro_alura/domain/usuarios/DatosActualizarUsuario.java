package com.alura.api_foro_alura.domain.usuarios;

import com.alura.api_foro_alura.domain.topico.Estatus;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
        @NotNull Long id,
        String login,
        String clave
        ) {
}
