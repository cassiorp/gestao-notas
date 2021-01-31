package br.com.gomining.gestaonotas.controller;

import br.com.gomining.gestaonotas.dto.AlunoDTO;
import br.com.gomining.gestaonotas.dto.NomeDTO;
import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.service.impl.AlunoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoServiceImpl alunoServiceImpl;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno salvaAluno(@RequestBody @Valid AlunoDTO alunoDTO) {
        return this.alunoServiceImpl.salvaAluno(alunoDTO);
    }

    @GetMapping
    public List<Aluno> buscaAlunos() {
        return this.alunoServiceImpl.buscaAlunos();
    }

    @GetMapping("/{id}")
    public Aluno buscaAluno(@PathVariable @Valid String id) {
        return this.alunoServiceImpl.buscaAlunoPorId(id);
    }

    @PatchMapping("/{id}")
    public Aluno editaNomeAluno(@PathVariable @Valid String id, @Valid @RequestBody NomeDTO nomeDTO) {
        return this.alunoServiceImpl.editaNomeAluno(id, nomeDTO);
    }

    @DeleteMapping("/{id}")
    public void deletaAluno(@PathVariable @Valid String id) {
        this.alunoServiceImpl.deletaAluno(id);
    }

}
