package br.com.gomining.gestaonotas.controller;

import br.com.gomining.gestaonotas.dto.NotaDTO;
import br.com.gomining.gestaonotas.dto.NotaTotalDTO;
import br.com.gomining.gestaonotas.model.Nota;
import br.com.gomining.gestaonotas.service.impl.NotaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaServiceImpl notaServiceImpl;

    @PostMapping("/{idCliente}")
    public Nota salvaNota(@PathVariable @Valid String idCliente, @RequestBody @Valid NotaDTO notaDTO) {
        return this.notaServiceImpl.salvaNota(idCliente, notaDTO);
    }

    @PatchMapping("/{id}")
    public Nota editaNotaTotal(@PathVariable @Valid String id, @RequestBody @Valid NotaTotalDTO notaTotalDTO) {
        return this.notaServiceImpl.editaNotaTotal(id, notaTotalDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteNota(@PathVariable @Valid String id) {
        this.notaServiceImpl.deletaNota(id);
    }

}
