package com.alura.api_foro_alura.controller;

import com.alura.api_foro_alura.domain.usuarios.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> getUsuarios(@PageableDefault(size = 5) Pageable paginacion)
    {
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(DatosListadoUsuario::new));
    }
    @PostMapping
    public ResponseEntity<Object> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistrousuario, UriComponentsBuilder uriComponentsBuilder)
    {
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistrousuario));
        DatosRespuestaUsuario datosRespuestausuario = new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getLogin()
        );
        URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(datosRespuestausuario);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<Object> actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarusuario)
    {
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarusuario.id());
        usuario.actualizarDatos(datosActualizarusuario);

        DatosRespuestaUsuario datosRespuestausuario = new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getLogin()
        );
        return ResponseEntity.ok(datosRespuestausuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id)
    {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> getUsuario(@PathVariable Long id)
    {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        var datosUsuario = new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getLogin()
        );
        return ResponseEntity.ok(datosUsuario);
    }

}
