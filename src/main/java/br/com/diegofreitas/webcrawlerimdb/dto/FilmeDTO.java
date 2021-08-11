package br.com.diegofreitas.webcrawlerimdb.dto;

import br.com.diegofreitas.webcrawlerimdb.entity.Ator;
import br.com.diegofreitas.webcrawlerimdb.entity.Comentario;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Data
public class FilmeDTO {

    private String identificadorIMDb;
    private String nomeDofilme;
    private BigDecimal notaDoFilme;
    private String nomeDiretores;
    private List<Ator> elencoPrincipal;
    private List<Comentario> comentariosPositivos;
}
