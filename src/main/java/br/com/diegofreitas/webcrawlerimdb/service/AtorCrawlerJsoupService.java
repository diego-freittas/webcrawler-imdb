package br.com.diegofreitas.webcrawlerimdb.service;


import br.com.diegofreitas.webcrawlerimdb.entity.Ator;
import br.com.diegofreitas.webcrawlerimdb.entity.Filme;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class AtorCrawlerJsoupService {


    public List<Ator> atoresDoFilme(Filme filme) throws IOException {

        String url = "https://www.imdb.com/title/"+filme.getIdentificadorIMDb()+"/fullcredits?ref_=tt_ov_st_sm#cast";
        Document doc = Jsoup.connect(url).get();

        Element table = doc.getElementsByClass("cast_list").first();
        Element tbody = table.getElementsByTag("tbody").first();
        List<Element> atores = tbody.getElementsByTag("tr");
        List<Ator> atoresDoFilme = new ArrayList<>();

        for (Element atorElement : atores) {
            List<Element> attributes = atorElement.getElementsByTag("td");

            if (attributes.size() > 1) {
                if (!attributes.get(1).text().equals("")) {
                    Ator ator = new Ator();
                    ator.setNomeDoAtor(attributes.get(1).text());
                    ator.setPersonagem(attributes.get(3).text());
                    atoresDoFilme.add(ator);
                }
            }
        }
        return atoresDoFilme;
    }
}
