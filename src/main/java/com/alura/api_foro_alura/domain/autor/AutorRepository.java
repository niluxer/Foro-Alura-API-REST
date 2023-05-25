package com.alura.api_foro_alura.domain.autor;

import com.alura.api_foro_alura.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
