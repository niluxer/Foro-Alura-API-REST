package com.alura.api_foro_alura.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull Long id,
        String titulo,
        String mensaje,
        Estatus estatus,
        Long autor_id,
        Long curso_id
        ) {
}
