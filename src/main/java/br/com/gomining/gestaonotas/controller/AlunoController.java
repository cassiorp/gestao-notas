package br.com.gomining.gestaonotas.controller;

import br.com.gomining.gestaonotas.dto.AlunoDTO;
import br.com.gomining.gestaonotas.dto.NomeDTO;
import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.service.AlunoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/alunos")
@RequiredArgsConstructor
@Api("API Gestão de Notas - Alunos")
@CrossOrigin("*")
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cria um novo aluno.")
    public Aluno salvaAluno(@RequestBody @Valid AlunoDTO alunoDTO) {
        return this.alunoService.salvaAluno(alunoDTO);
    }

    @GetMapping
    @ApiOperation("Busca todos os alunos.")
    public List<Aluno> buscaAlunos() {
        return this.alunoService.buscaAlunos();
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca aluno pelo ID.")
    public Aluno buscaAluno(@PathVariable @Valid String id) {
        return this.alunoService.buscaAlunoPorId(id);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Edita nome de aluno.")
    public Aluno editaNomeAluno(@PathVariable @Valid String id, @Valid @RequestBody NomeDTO nomeDTO) {
        return this.alunoService.editaNomeAluno(id, nomeDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclui aluno por ID.")
    public void deletaAluno(@PathVariable @Valid String id) {
        this.alunoService.deletaAluno(id);
    }

}
