package br.com.gomining.gestaonotas.service;

import br.com.gomining.gestaonotas.dto.NomeDTO;
import br.com.gomining.gestaonotas.dto.NotaDTO;
import br.com.gomining.gestaonotas.dto.NotaTotalDTO;
import br.com.gomining.gestaonotas.exception.AlunoNotFoundException;
import br.com.gomining.gestaonotas.exception.NotaNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class NotaServiceTest {

    @Mock
    NotaRepository notaRepository;

    @Mock
    AlunoService alunoService;

    @InjectMocks
    NotaService notaService;

    @Test
    @DisplayName("Deve Salvar Nota")
    public void deveSalvarNota() {
        Aluno aluno = Aluno.builder().nome("Cássio").build();
        when(alunoService.buscaAlunoPorId("id")).thenReturn(aluno);
        when(alunoService.salvaAluno(any(Aluno.class))).thenReturn(aluno);
        Nota stub = notaEntity();
        Nota retorno = notaEntity();

        when(notaRepository.save(any(Nota.class))).thenReturn(retorno);

        Nota salva = this.notaService.salvaNota("id", notaDTO());

        assertEquals(retorno.getNomeAluno(), salva.getNomeAluno());
        assertEquals(retorno.getNotaTotal(), salva.getNotaTotal());
        assertEquals(retorno.getDate(), salva.getDate());
        assertEquals(retorno.getId(), salva.getId());
        assertEquals(retorno.getSituacao(), salva.getSituacao());
    }

    @Test
    @DisplayName("Deve Buscar Nota Por ID")
    public void deveBuscarNotaPorID() {

        Nota notaRetorno = notaEntity();

        when(notaRepository.findById("id")).thenReturn(Optional.ofNullable(notaRetorno));

        Nota notaBuscada = this.notaService.buscaNotaPorID("id");
        assertEquals(notaRetorno, notaBuscada);
        assertNotNull(notaBuscada);
    }

    @Test
    @DisplayName("Deve Lançar Exceção Para Nota não Encontrada")
    public void deveLancarExcecaoParaClienteNaoEncontrado(){
        assertThrows(NotaNotFoundException.class,
                ()->{ this.notaService.buscaNotaPorID("id"); });
    }

    @Test
    @DisplayName("Deve Pegar Situacao Aprovado")
    public void devePegarSituacaAprovado() {
        Situacao situacao = this.notaService.getSituacao(6.0);
        assertEquals(Situacao.APROVADO, situacao);
    }

    @Test
    @DisplayName("Deve Deletar Nota Por ID")
    public void deveDeletarAlunoPorID() {
        Nota notaRetorno = notaEntity();

        when(notaRepository.findById("id")).thenReturn(Optional.ofNullable(notaRetorno));
        this.notaService.deletaNota("id");

        verify(notaRepository, times(1)).deleteById("id");
    }

    @Test
    @DisplayName("Deve Pegar Situacao Reprovado")
    public void devePegarSituacaReprovado() {
        Situacao situacao = this.notaService.getSituacao(5.9);
        assertEquals(Situacao.REPROVADO, situacao);
    }


    public Nota notaEntity() {
        return Nota.builder()
                .nomeAluno("Cássio")
                .notaTotal(6.0).disciplina("mat")
                .situacao(Situacao.APROVADO).date(LocalDate.now()).build();
    }
    public NotaDTO notaDTO() {
       return NotaDTO.builder()
               .disciplina("mat")
               .notaTotal(6.0)
               .date(LocalDate.now()).build();
    }


}
