package br.com.gomining.gestaonotas.controller;

import br.com.gomining.gestaonotas.dto.NotaDTO;
import br.com.gomining.gestaonotas.dto.NotaTotalDTO;
import br.com.gomining.gestaonotas.model.Nota;
import br.com.gomining.gestaonotas.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;

    @PostMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.CREATED)
    public Nota salvaNota(@PathVariable @Valid String idCliente, @RequestBody @Valid NotaDTO notaDTO) {
        return this.notaService.salvaNota(idCliente, notaDTO);
    }

    @PatchMapping("/{id}")
    public Nota editaNotaTotal(@PathVariable @Valid String id, @RequestBody @Valid NotaTotalDTO notaTotalDTO) {
        return this.notaService.editaNotaTotal(id, notaTotalDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteNota(@PathVariable @Valid String id) {
        this.notaService.deletaNota(id);
    }

}
