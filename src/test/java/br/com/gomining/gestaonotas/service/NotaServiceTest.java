package br.com.gomining.gestaonotas.service;

import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.model.Enum.Situacao;
import br.com.gomining.gestaonotas.model.Nota;
import br.com.gomining.gestaonotas.repository.AlunoRepository;
import br.com.gomining.gestaonotas.repository.NotaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class NotaServiceTest {

    @Mock
    NotaRepository notaRepository;

    @Mock
    AlunoRepository alunoRepository;

    @InjectMocks
    NotaService notaService;

    @Test
    @DisplayName("Deve Salvar Nota")
    public void deveSalvarNota() {

        Aluno alunoRetorno = Aluno.builder().id("id").nome("Cássio").build();

        Nota notaStub = Nota.builder().nomeAluno(alunoRetorno.getNome()).notaTotal(6.0).disciplina("mat").build();
        Nota notaRetorno = Nota.builder().nomeAluno(alunoRetorno.getNome()).notaTotal(6.0).disciplina("mat").build();

        when(alunoRepository.findById("id")).thenReturn(Optional.ofNullable(alunoRetorno));
        when(notaRepository.save(notaStub)).thenReturn(notaRetorno);

        Nota notaSalva = this.notaService.salvaNota("id", notaStub);

        assertEquals(notaRetorno, notaSalva);
    }

    @Test
    @DisplayName("Deve Salvar Nota com Aprovacao")
    public void deveSalvarNotaComAprovacao() {

        Aluno alunoRetorno = Aluno.builder().id("id").nome("Cássio").build();

        Nota notaStub = Nota.builder().situacao(Situacao.APROVADO).date(LocalDate.now()).notaTotal(6.0).disciplina("mat").build();
        Nota notaRetorno = Nota.builder().situacao(Situacao.APROVADO).date(LocalDate.now()).notaTotal(6.0).disciplina("mat").build();

        when(alunoRepository.findById("id")).thenReturn(Optional.ofNullable(alunoRetorno));
        when(notaRepository.save(notaStub)).thenReturn(notaRetorno);

        Nota notaSalva = this.notaService.salvaNota("id", notaStub);

        assertEquals(notaRetorno.getSituacao(), notaSalva.getSituacao());
        assertEquals(Situacao.APROVADO, notaSalva.getSituacao());
    }



}
