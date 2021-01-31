package br.com.gomining.gestaonotas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends MongoRepository<NotaRepository, String> {
}
