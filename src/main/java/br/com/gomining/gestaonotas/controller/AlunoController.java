package br.com.gomining.gestaonotas.controller;

import br.com.gomining.gestaonotas.dto.AlunoDTO;
import br.com.gomining.gestaonotas.dto.NomeDTO;
import br.com.gomining.gestaonotas.dto.NotaDTO;
import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno salvaAluno(@RequestBody AlunoDTO alunoDTO) {
        return this.alunoService.salvaAluno(alunoDTO);
    }

    @GetMapping
    public List<Aluno> buscaAlunos() {
        return this.alunoService.buscaAlunos();
    }

    @GetMapping("/{id}")
    public Aluno buscaAluno(@PathVariable String id) {
        return this.alunoService.buscaAlunoPorId(id);
    }

    @PatchMapping("/{id}")
    public Aluno editaNomeAluno(@PathVariable String id, @RequestBody NomeDTO nomeDTO) {
        return this.alunoService.editaNomeAluno(id, nomeDTO);
    }

    @DeleteMapping("/{id}")
    public void deletaAluno(@PathVariable String id) {
        this.alunoService.deletaAluno(id);
    }

}
