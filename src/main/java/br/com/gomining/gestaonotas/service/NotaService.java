package br.com.gomining.gestaonotas.service;

import br.com.gomining.gestaonotas.exception.AlunoNotFoundException;
import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.model.Enum.Situacao;
import br.com.gomining.gestaonotas.model.Nota;
import br.com.gomining.gestaonotas.repository.AlunoRepository;
import br.com.gomining.gestaonotas.repository.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaRepository notaRepository;
    private final AlunoRepository alunoRepository;

    private final Double MEDIA = 6.0;
    private final String AlunoNotFoundExceptionMessage = "Aluno não encontrado";

    public Nota salvaNota(String idAluno, Nota nota) {
        Aluno aluno = this.alunoRepository.findById(idAluno)
                .orElseThrow(() -> new AlunoNotFoundException(AlunoNotFoundExceptionMessage));

        nota.setNomeAluno( aluno.getNome() );
        nota.setSituacao( getSituacao( nota.getNotaTotal() ) );
        Nota notaSalva = this.notaRepository.save(nota);

        aluno.setNotas(notaSalva);
        alunoRepository.save(aluno);

        return notaSalva;
    }

    public Situacao getSituacao(Double nota) {
        if(nota >= 6.0) return Situacao.APROVADO;
        else return Situacao.REPROVADO;
    }
}
