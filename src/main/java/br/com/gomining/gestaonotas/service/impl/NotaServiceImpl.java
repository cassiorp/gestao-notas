package br.com.gomining.gestaonotas.service.impl;

import br.com.gomining.gestaonotas.dto.NotaDTO;
import br.com.gomining.gestaonotas.dto.NotaTotalDTO;
import br.com.gomining.gestaonotas.exception.NotaNotFoundException;
import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.model.Enum.Situacao;
import br.com.gomining.gestaonotas.model.Nota;
import br.com.gomining.gestaonotas.repository.NotaRepository;
import br.com.gomining.gestaonotas.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {

    private final NotaRepository notaRepository;
    private final AlunoServiceImpl alunoServiceImpl;

    private final Double MEDIA = 6.0;


    @Override
    public Nota salvaNota(String idAluno, NotaDTO notaDTO) {

        Aluno aluno = alunoServiceImpl.buscaAlunoPorId(idAluno);

        Nota nota = Nota.builder()
                .disciplina(notaDTO.getDisciplina())
                .notaTotal(notaDTO.getNotaTotal())
                .nomeAluno(aluno.getNome())
                .situacao(getSituacao(notaDTO.getNotaTotal()))
                .date(notaDTO.getDate())
                .build();

        aluno.setBoletim(nota);
        this.notaRepository.save(nota);
        this.alunoServiceImpl.salvaAluno(aluno);
        return nota;
    }

    @Override
    public Nota buscaNotaPorID(String id) {
        Nota nota = this.notaRepository.findById(id)
                .orElseThrow(() -> new NotaNotFoundException("Nota não encontrada"));
        return nota;
    }

    @Override
    public Nota editaNotaTotal(String idAluno, NotaTotalDTO notaTotalDTO) {
        Aluno aluno = this.alunoServiceImpl.buscaAlunoPorId(idAluno);
        Nota nota = this.alunoServiceImpl.buscaNotaNoBoletin(aluno, notaTotalDTO.getId());
        aluno.getBoletim().remove(nota);

        nota.setNotaTotal(notaTotalDTO.getNotaTotal());
        nota.setSituacao(getSituacao(notaTotalDTO.getNotaTotal()));
        this.notaRepository.save(nota);

        aluno.setBoletim(nota);
        this.alunoServiceImpl.salvaAluno(aluno);

        return nota;
    }


    @Override
    public void deletaNota(String id) {
        buscaNotaPorID(id);
        this.notaRepository.deleteById(id);
    }

    public Situacao getSituacao(Double nota) {
        if(nota >= 6.0) return Situacao.APROVADO;
        else return Situacao.REPROVADO;
    }
}
