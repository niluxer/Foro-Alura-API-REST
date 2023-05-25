package com.alura.api_foro_alura.controller;

import com.alura.api_foro_alura.domain.autor.Autor;
import com.alura.api_foro_alura.domain.autor.AutorRepository;
import com.alura.api_foro_alura.domain.curso.Curso;
import com.alura.api_foro_alura.domain.curso.CursoRepository;
import com.alura.api_foro_alura.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> getTopicos(@PageableDefault(size = 5) Pageable paginacion)
    {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }
    @PostMapping
    public ResponseEntity<Object> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder)
    {
        Optional<Autor> autor = autorRepository.findById(datosRegistroTopico.autor_id());
        if (autor.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Autor no válido");
        }
        Optional<Curso> curso = cursoRepository.findById(datosRegistroTopico.curso_id());
        if (curso.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Curso no válido");
        }
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico, autor.get(), curso.get()));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getEstatus(),
                topico.getFecha_creacion().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(datosRespuestaTopico);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<Object> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico)
    {
        Optional<Autor> autor = null;
        Optional<Curso> curso = null;
        if (datosActualizarTopico.autor_id() != null && datosActualizarTopico.curso_id() != null)
        {
            autor = autorRepository.findById(datosActualizarTopico.autor_id());
            if (autor.isEmpty())
            {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body("Autor no válido");
            }
            curso = cursoRepository.findById(datosActualizarTopico.curso_id());
            if (curso.isEmpty())
            {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Curso no válido");
            }

        }
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        if (Objects.nonNull(autor))
            topico.actualizarDatos(datosActualizarTopico, autor.get(), curso.get());
        else topico.actualizarDatos(datosActualizarTopico);

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getEstatus(),
                topico.getFecha_creacion().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
        return ResponseEntity.ok(datosRespuestaTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id)
    {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> getTopico(@PathVariable Long id)
    {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getEstatus(),
                topico.getFecha_creacion().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
        return ResponseEntity.ok(datosTopico);
    }

}
