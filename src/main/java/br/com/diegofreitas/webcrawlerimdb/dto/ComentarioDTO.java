package br.com.diegofreitas.webcrawlerimdb.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ComentarioDTO {

    private int notaDoComentario;
    private String textoDoComentario;
}
