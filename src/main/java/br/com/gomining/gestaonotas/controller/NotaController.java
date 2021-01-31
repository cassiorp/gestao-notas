package br.com.gomining.gestaonotas.controller;

import br.com.gomining.gestaonotas.dto.NotaDTO;
import br.com.gomining.gestaonotas.model.Nota;
import br.com.gomining.gestaonotas.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;
    private final ModelMapper modelMapper;

    @PostMapping("/{id}")
    public Nota salvaNota(@PathVariable String id, @RequestBody @Valid NotaDTO notaDTO) {
        Nota nota = modelMapper.map(notaDTO, Nota.class);
        return this.notaService.salvaNota(id, nota);
    }

}
