package br.com.gomining.gestaonotas.service;

import br.com.gomining.gestaonotas.dto.NotaDTO;
import br.com.gomining.gestaonotas.dto.NotaTotalDTO;
import br.com.gomining.gestaonotas.model.Enum.Situacao;
import br.com.gomining.gestaonotas.model.Nota;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotaService {
    Nota salvaNota(String idAluno, NotaDTO notaDTO);
    List<Nota> buscaNotas();
    Nota buscaNotaPorID(String id);
    Nota editaNotaTotal(String idAluno, NotaTotalDTO notaTotalDTO);
    Situacao getSituacao(Double nota);
}
