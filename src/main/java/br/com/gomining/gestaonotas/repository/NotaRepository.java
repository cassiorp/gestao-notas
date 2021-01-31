package br.com.gomining.gestaonotas.repository;

import br.com.gomining.gestaonotas.model.Nota;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends MongoRepository<Nota, String> {
}
