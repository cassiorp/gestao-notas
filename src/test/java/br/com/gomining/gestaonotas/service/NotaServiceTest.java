package br.com.gomining.gestaonotas.service;

import br.com.gomining.gestaonotas.dto.NotaDTO;
import br.com.gomining.gestaonotas.exception.NotaNotFoundException;
import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.model.Enum.Situacao;
import br.com.gomining.gestaonotas.model.Nota;
import br.com.gomining.gestaonotas.repository.NotaRepository;
import br.com.gomining.gestaonotas.service.impl.AlunoServiceImpl;
import br.com.gomining.gestaonotas.service.impl.NotaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
public class NotaServiceTest {

    NotaService notaService;

    @MockBean
    NotaRepository notaRepository;

    @MockBean
    AlunoServiceImpl alunoService;

    @BeforeEach
    public void init(){
        this.notaService = new NotaServiceImpl(notaRepository, alunoService);
    }


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
    @DisplayName("Deve Buscar Todas as Notas")
    public void deveBuscarTodasNotas() {
        List<Nota> retorno = listNotas();

        when(notaRepository.findAll()).thenReturn(retorno);

        List<Nota> buscadas = this.notaService.buscaNotas();
        assertEquals(retorno, buscadas);
        assertNotNull(buscadas);
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
    public List<Nota> listNotas() {
        return Arrays.asList(
                notaEntity(),
                notaEntity(),
                notaEntity()
        );
    }

}
