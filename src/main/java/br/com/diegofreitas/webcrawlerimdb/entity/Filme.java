package br.com.diegofreitas.webcrawlerimdb.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Filme {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imdb")
    private String identificadorIMDb;

    @Column(name = "nome_filme")
    private String nomeDofilme;

    @Column(name = "nota_filme")
    private BigDecimal notaDoFilme;

    @Column(name = "nome_diretor")
    private String nomeDiretores;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Ator> elencoPrincipal;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Comentario> comentariosPositivos;

}
