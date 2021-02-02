package br.com.gomining.gestaonotas.service.impl;

import br.com.gomining.gestaonotas.dto.NotaDTO;
import br.com.gomining.gestaonotas.dto.NotaTotalDTO;
import br.com.gomining.gestaonotas.exception.NotaNotFoundException;
import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.model.Enum.Situacao;
import br.com.gomining.gestaonotas.model.Nota;
import br.com.gomining.gestaonotas.repository.NotaRepository;
import br.com.gomining.gestaonotas.service.AlunoService;
import br.com.gomining.gestaonotas.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {

    private final NotaRepository notaRepository;
    private final AlunoService alunoService;


    private final String NOTA_NAO_ENCONTRADA = "Nota não encontrada";
    private final Double MEDIA = 6.0;


    @Override
    public Nota salvaNota(String idAluno, NotaDTO notaDTO) {

        Aluno aluno = alunoService.buscaAlunoPorId(idAluno);

        Nota nota = Nota.builder()
                .disciplina(notaDTO.getDisciplina())
                .notaTotal(notaDTO.getNotaTotal())
                .nomeAluno(aluno.getNome())
                .situacao(getSituacao(notaDTO.getNotaTotal()))
                .date(notaDTO.getDate())
                .build();

        aluno.setBoletim(nota);
        this.notaRepository.save(nota);
        this.alunoService.salvaAluno(aluno);
        return nota;
    }

    @Override
    public List<Nota> buscaNotas() {
        return this.notaRepository.findAll();
    }

    @Override
    public Nota buscaNotaPorID(String id) {
        Nota nota = this.notaRepository.findById(id)
                .orElseThrow(() -> new NotaNotFoundException(NOTA_NAO_ENCONTRADA));
        return nota;
    }

    @Override
    public Nota editaNotaTotal(String idAluno, NotaTotalDTO notaTotalDTO) {
        Aluno aluno = this.alunoService.buscaAlunoPorId(idAluno);
        Nota nota = buscaNotaNoBoletin(aluno, notaTotalDTO.getId());
        aluno.getBoletim().remove(nota);

        nota.setNotaTotal(notaTotalDTO.getNotaTotal());
        nota.setSituacao(getSituacao(notaTotalDTO.getNotaTotal()));
        this.notaRepository.save(nota);

        aluno.setBoletim(nota);
        this.alunoService.salvaAluno(aluno);

        return nota;
    }


    public Nota buscaNotaNoBoletin(Aluno aluno, String id) {
        return  aluno.getBoletim().stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotaNotFoundException(NOTA_NAO_ENCONTRADA));
    }

    public Situacao getSituacao(Double nota) {
        if(nota >= 6.0) return Situacao.APROVADO;
        else return Situacao.REPROVADO;
    }

}
