package com.alura.api_foro_alura.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotNull @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull String fecha_creacion,
        @NotNull Estatus estatus,
        @NotNull Long autor_id,
        @NotNull Long curso_id
        ) {
}
