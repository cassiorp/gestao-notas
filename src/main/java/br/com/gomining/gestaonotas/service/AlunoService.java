package br.com.gomining.gestaonotas.service;

import br.com.gomining.gestaonotas.dto.AlunoDTO;
import br.com.gomining.gestaonotas.dto.NomeDTO;
import br.com.gomining.gestaonotas.exception.AlunoNotFoundException;
import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    private final String alunoNotFoundException = "Aluno não encontrado";

    public Aluno salvaAluno(AlunoDTO alunoDTO) {
        Aluno aluno = Aluno.builder().nome(alunoDTO.getNome()).build();
        return this.alunoRepository.save(aluno);
    }

    public List<Aluno> buscaAlunos() {
        return this.alunoRepository.findAll();
    }

    public Aluno buscaAlunoPorId(String id) {
        return this.alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNotFoundException(alunoNotFoundException));
    }

    public Aluno editaNomeAluno(String id, NomeDTO nomeDTO) {
        Aluno aluno = buscaAlunoPorId(id);
        aluno.setNome(nomeDTO.getNome());
        return this.alunoRepository.save(aluno);
    }

    public Boolean deletaAluno(String id) {
        buscaAlunoPorId(id);
        this.alunoRepository.deleteById(id);
        if(alunoRepository.existsById(id)){
            return true;
        }
        return false;
    }
}
