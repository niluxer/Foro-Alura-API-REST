package com.alura.api_foro_alura.domain.topico;

import com.alura.api_foro_alura.domain.autor.Autor;
import com.alura.api_foro_alura.domain.curso.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Date;
/*import java.util.Optional;
import static com.alura.api_foro_alura.domain.autor.Registry.autorRepo;
import static com.alura.api_foro_alura.domain.autor.Registry.cursoRepo;*/

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private Date fecha_creacion;
    private String estatus;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico(DatosRegistroTopico datosRegistroTopico, Autor a, Curso c) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.estatus = datosRegistroTopico.estatus().toString();
        System.out.println("FECHA ->" + datosRegistroTopico.fecha_creacion());
        this.fecha_creacion = Date.valueOf(datosRegistroTopico.fecha_creacion());
        this.autor = a;
        this.curso = c;
        /*Optional<Autor> autor = autorRepo().findById(datosRegistroTopico.autor_id());
        autor.ifPresent(value -> this.autor = value);
        Optional<Curso> curso = cursoRepo().findById(datosRegistroTopico.curso_id());
        curso.ifPresent(value -> this.curso = value);*/
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null)
            this.titulo = datosActualizarTopico.titulo();
        if (datosActualizarTopico.mensaje() != null)
            this.mensaje = datosActualizarTopico.mensaje();
        if (datosActualizarTopico.estatus() != null)
            this.estatus = datosActualizarTopico.estatus().toString();
        if (datosActualizarTopico.titulo() != null)
            this.titulo = datosActualizarTopico.titulo();
    }
    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico, Autor a, Curso c) {
        if (datosActualizarTopico.titulo() != null)
            this.titulo = datosActualizarTopico.titulo();
        if (datosActualizarTopico.mensaje() != null)
            this.mensaje = datosActualizarTopico.mensaje();
        if (datosActualizarTopico.estatus() != null)
            this.estatus = datosActualizarTopico.estatus().toString();
        if (datosActualizarTopico.titulo() != null)
            this.titulo = datosActualizarTopico.titulo();
        this.autor = a;
        this.curso = c;
    }
}
