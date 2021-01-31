package br.com.gomining.gestaonotas.repository;

import br.com.gomining.gestaonotas.model.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends MongoRepository<Aluno, String> {
}
