package com.alura.api_foro_alura.domain.usuarios;

import com.alura.api_foro_alura.domain.topico.Estatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @NotNull @NotBlank String login,
        @NotBlank String clave
        ) {
}
