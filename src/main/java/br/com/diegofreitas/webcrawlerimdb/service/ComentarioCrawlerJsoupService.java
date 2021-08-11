package br.com.diegofreitas.webcrawlerimdb.service;

import br.com.diegofreitas.webcrawlerimdb.entity.Comentario;
import br.com.diegofreitas.webcrawlerimdb.entity.Filme;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComentarioCrawlerJsoupService {

    public List<Comentario> comentariosDoFilme(Filme filme) throws IOException {


        String url = "https://www.imdb.com/title/"+filme.getIdentificadorIMDb()+"/reviews?ref_=tt_urv";
        Document doc = Jsoup.connect(url).get();

        Element table = doc.getElementsByClass("lister").first();
        Element tbody = table.getElementsByClass("lister-list").first();
        List<Element> atores = tbody.getElementsByClass("lister-item-content");
        List<Comentario> comentarisoDoFilme = new ArrayList<>();

        for (Element comentarioElement : atores) {
            String notaDoComentario = comentarioElement.getElementsByClass("ipl-ratings-bar").text();

            int nota = extrairEConverterNotaDoComentarioEmInteiro(notaDoComentario);
            if( nota >= 5) {
                Comentario comentario = new Comentario();
                comentario.setNotaDoComentario(nota);
                comentario.setTextoDoComentario(comentarioElement.getElementsByClass("content").text());
                comentarisoDoFilme.add(comentario);
            }
        }
        return comentarisoDoFilme;
    }
    public int extrairEConverterNotaDoComentarioEmInteiro(String notaString){
        int nota = 0;
        if( notaString.length() > 0 && notaString.length() == 4) {
            nota = Integer.parseInt(notaString.substring(0,1));
        }else if(notaString.length() > 0 && notaString.length() == 5){
            nota = Integer.parseInt(notaString.substring(0,2));
        }
        return nota;
    }
}