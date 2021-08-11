package br.com.diegofreitas.webcrawlerimdb.repository;


import br.com.diegofreitas.webcrawlerimdb.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {


}
