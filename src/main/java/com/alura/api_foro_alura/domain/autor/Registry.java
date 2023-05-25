package com.alura.api_foro_alura.domain.autor;

import com.alura.api_foro_alura.domain.autor.AutorRepository;
import com.alura.api_foro_alura.domain.curso.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Registry {

    @Autowired
    private static AutorRepository autorRepository;
    @Autowired
    private static CursoRepository cursoRepository;

    public static AutorRepository autorRepo()
    {
        return autorRepository;
    }

    public static CursoRepository cursoRepo()
    {
        return cursoRepository;
    }

}
