package br.com.gomining.gestaonotas.service;

import br.com.gomining.gestaonotas.dto.NotaDTO;
import br.com.gomining.gestaonotas.dto.NotaTotalDTO;
import br.com.gomining.gestaonotas.model.Enum.Situacao;
import br.com.gomining.gestaonotas.model.Nota;

public interface NotaService {
    Nota salvaNota(String idAluno, NotaDTO notaDTO);
    Nota buscaNotaPorID(String id);
    Nota editaNotaTotal(String idAluno, NotaTotalDTO notaTotalDTO);
    void deletaNota(String id);
    Situacao getSituacao(Double nota);
}
