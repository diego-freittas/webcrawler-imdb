package br.com.diegofreitas.webcrawlerimdb.service;

import br.com.diegofreitas.webcrawlerimdb.entity.Filme;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilmeCrawlerJsoupService {

    public List<Filme> listaComOsDezPioresFilmes() throws IOException {

        AtorCrawlerJsoupService atorCrawlerJsoupService = new AtorCrawlerJsoupService();
        ComentarioCrawlerJsoupService comentarioCrawlerJsoupService = new ComentarioCrawlerJsoupService();

        List<Filme> pioresFilmes = new ArrayList<>();
        String url = "https://www.imdb.com/list/ls002254630/";
        Document doc = Jsoup.connect(url).get();
        Elements content = doc.getElementsByClass("lister-list");
        Elements itens = content.first().getElementsByClass("lister-item mode-detail");

        for (int i = 0; i < 10; ++i) {

            Element item = (Element) itens.get(i);
            Filme filme = new Filme();
            filme.setIdentificadorIMDb(item.getAllElements().attr("data-tconst"));
            filme.setNotaDoFilme(BigDecimal.valueOf(Double.parseDouble(item.getElementsByClass("ipl-rating-star__rating").first().text())));
            filme = buscarNomeEDiretoresDoFilme(filme);
            filme.setElencoPrincipal(atorCrawlerJsoupService.atoresDoFilme(filme));
            filme.setComentariosPositivos(comentarioCrawlerJsoupService.comentariosDoFilme(filme));
            pioresFilmes.add(filme);

        }
        return pioresFilmes;
    }

    private Filme buscarNomeEDiretoresDoFilme(Filme filmeParaBuscar) throws IOException {

        String codigoIMDb = filmeParaBuscar.getIdentificadorIMDb();
        String url = "https://www.imdb.com/title/" + codigoIMDb;
        Document doc = Jsoup.connect(url).get();

        filmeParaBuscar.setNomeDofilme(doc.getElementsByClass("TitleHeader__TitleText-sc-1wu6n3d-0 dxSWFG").text());
        filmeParaBuscar.setNomeDiretores(doc.getElementsByClass("ipc-metadata-list-item__list-content-item ipc-metadata-list-item__list-content-item--link").first().text());
        return filmeParaBuscar;
    }

}
