package br.com.diegofreitas.webcrawlerimdb.controller;


import br.com.diegofreitas.webcrawlerimdb.entity.Filme;
import br.com.diegofreitas.webcrawlerimdb.service.FilmeCrawlerJsoupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/filmes")
public class FilmesController {


    @Autowired
    private FilmeCrawlerJsoupService filmeCrawlerJsoupService;

    @GetMapping
    public List<Filme> listPiroresFilmes() throws IOException {
        return filmeCrawlerJsoupService.listaComOsDezPioresFilmes();
    }


}
