package br.com.gomining.gestaonotas.service;

import br.com.gomining.gestaonotas.dto.AlunoDTO;
import br.com.gomining.gestaonotas.dto.NomeDTO;
import br.com.gomining.gestaonotas.dto.NotaDTO;
import br.com.gomining.gestaonotas.dto.NotaTotalDTO;
import br.com.gomining.gestaonotas.exception.AlunoNotFoundException;
import br.com.gomining.gestaonotas.exception.NotaNotFoundException;
import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.model.Nota;

import java.util.List;

public interface AlunoService {
    Aluno salvaAluno(AlunoDTO alunoDTO);
    Aluno salvaAluno(Aluno aluno);
    List<Aluno> buscaAlunos();
    Aluno buscaAlunoPorId(String id);
    Aluno editaNomeAluno(String id, NomeDTO nomeDTO);
    Boolean deletaAluno(String id);
}
