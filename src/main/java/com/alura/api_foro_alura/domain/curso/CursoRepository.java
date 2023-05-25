package com.alura.api_foro_alura.domain.curso;

import com.alura.api_foro_alura.domain.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
