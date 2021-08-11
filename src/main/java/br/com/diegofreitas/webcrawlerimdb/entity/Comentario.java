package br.com.diegofreitas.webcrawlerimdb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nota_comentario")
    private int notaDoComentario;

    @Column(name = "texto_comentario")
    private String textoDoComentario;

    @Override
    public String toString() {
        return "Comentario{" +
                "notaDoComentario=" + notaDoComentario +
                ", textoDoComentario='" + textoDoComentario + '\'' +
                '}';
    }
}
