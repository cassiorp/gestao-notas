package br.com.gomining.gestaonotas.controller;

import br.com.gomining.gestaonotas.dto.NotaDTO;
import br.com.gomining.gestaonotas.dto.NotaTotalDTO;
import br.com.gomining.gestaonotas.model.Nota;
import br.com.gomining.gestaonotas.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;

    @PostMapping("/{idCliente}")
    public Nota salvaNota(@PathVariable String idCliente, @RequestBody @Valid NotaDTO notaDTO) {
        return this.notaService.salvaNota(idCliente, notaDTO);
    }

    @PatchMapping("/{id}")
    public Nota editaNotaTotal(@PathVariable String id, @RequestBody NotaTotalDTO notaTotalDTO) {
        return this.notaService.editaNotaTotal(id, notaTotalDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteNota(@PathVariable String id) {
        this.notaService.deletaNota(id);
    }

}
