package br.com.gomining.gestaonotas.service;

import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public Aluno salvaAluno(Aluno aluno) {
        return this.alunoRepository.save(aluno);
    }

    public List<Aluno> buscaAlunos() {
        return this.alunoRepository.findAll();
    }

}
