package br.com.gomining.gestaonotas.controller;

import br.com.gomining.gestaonotas.dto.NotaDTO;
import br.com.gomining.gestaonotas.dto.NotaTotalDTO;
import br.com.gomining.gestaonotas.model.Nota;
import br.com.gomining.gestaonotas.service.NotaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/notas")
@RequiredArgsConstructor
@Api("API Gestão de Notas - Notas")
@CrossOrigin("*")
public class NotaController {

    private final NotaService notaService;

    @PostMapping("/{idAluno}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Adiciona uma nota ao boletim do aluno, referenciado pelo ID")
    public Nota salvaNota(@PathVariable @Valid String idAluno, @RequestBody @Valid NotaDTO notaDTO) {
        return this.notaService.salvaNota(idAluno, notaDTO);
    }

    @GetMapping
    @ApiOperation("Busca todas as notas")
    public List<Nota> buscaNotas() {
        return this.notaService.buscaNotas();
    }

    @PatchMapping("/{idAluno}")
    @ApiOperation("Edita valor da nota final de um aluno, referenciado pelo ID")
    public Nota editaNotaTotal(@PathVariable @Valid String idAluno, @RequestBody @Valid NotaTotalDTO notaTotalDTO) {
        return this.notaService.editaNotaTotal(idAluno, notaTotalDTO);
    }

}
