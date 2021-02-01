package br.com.gomining.gestaonotas.service.impl;

import br.com.gomining.gestaonotas.dto.AlunoDTO;
import br.com.gomining.gestaonotas.dto.NomeDTO;
import br.com.gomining.gestaonotas.dto.NotaDTO;
import br.com.gomining.gestaonotas.exception.AlunoNotFoundException;
import br.com.gomining.gestaonotas.exception.NotaNotFoundException;
import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.model.Nota;
import br.com.gomining.gestaonotas.repository.AlunoRepository;
import br.com.gomining.gestaonotas.service.AlunoService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;

    private final String alunoNotFoundException = "Aluno não encontrado";

    @Override
    public Aluno salvaAluno(AlunoDTO alunoDTO) {
        Aluno aluno = Aluno.builder().nome(alunoDTO.getNome()).build();
        return this.alunoRepository.save(aluno);
    }

    @Override
    public Aluno salvaAluno(Aluno aluno) {
        return this.alunoRepository.save(aluno);
    }

    @Override
    public List<Aluno> buscaAlunos() {
        return this.alunoRepository.findAll();
    }

    @Override
    public Aluno buscaAlunoPorId(String id) {
        return this.alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNotFoundException(alunoNotFoundException));
    }

    @Override
    public Aluno editaNomeAluno(String id, NomeDTO nomeDTO) {
        Aluno aluno = buscaAlunoPorId(id);
        aluno.setNome(nomeDTO.getNome());
        return this.alunoRepository.save(aluno);
    }

    @Override
    public Boolean deletaAluno(String id) {
        buscaAlunoPorId(id);
        this.alunoRepository.deleteById(id);
        if(alunoRepository.existsById(id)){
            return true;
        }
        return false;
    }


}
