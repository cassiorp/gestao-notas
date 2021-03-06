package br.com.gomining.gestaonotas.service;


import br.com.gomining.gestaonotas.dto.AlunoDTO;
import br.com.gomining.gestaonotas.dto.NomeDTO;
import br.com.gomining.gestaonotas.exception.AlunoNotFoundException;
import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.repository.AlunoRepository;
import br.com.gomining.gestaonotas.service.impl.AlunoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
public class AlunoServiceTest {

    AlunoService alunoService;

    @MockBean
    AlunoRepository alunoRepository;

    @BeforeEach
    public void init(){
        this.alunoService = new AlunoServiceImpl(alunoRepository);
    }

    @Test
    @DisplayName("Deve Criar Aluno")
    public void deveCriarAlunoDTO() {
        Aluno retorno = alunoEntity();

        AlunoDTO alunoDTO = new AlunoDTO("Cássio");

        when(alunoRepository.save(any(Aluno.class))).thenReturn(retorno);

        Aluno salvo = alunoService.salvaAluno(alunoDTO);
        assertEquals(retorno, salvo);
    }


    @Test
    @DisplayName("Deve Buscar Todos Alunos")
    public void deveBuscarTodosAlunos() {
        List<Aluno> alunosRetorno = listAlunos();

        when(alunoRepository.findAll()).thenReturn(alunosRetorno);

        List<Aluno> salvos = alunoService.buscaAlunos();

        assertEquals(alunosRetorno, salvos);
    }

    @Test
    @DisplayName("Deve Buscar Aluno Por ID")
    public void deveBuscarAlunoPorId() {
        Aluno retorno = alunoEntity();

        when(alunoRepository.findById("id")).thenReturn(java.util.Optional.ofNullable(retorno));

        Aluno buscado = this.alunoService.buscaAlunoPorId("id");

        assertEquals(retorno, buscado);
    }


    @Test
    @DisplayName("Deve Editar Nome de Aluno")
    public void deveEditarNomeAluno() {
        NomeDTO nomeDTO = new NomeDTO();
        nomeDTO.setNome("novo nome");

        Aluno retorno = alunoEntity();

        when(alunoRepository.findById("id")).thenReturn(Optional.ofNullable((retorno)));
        when(alunoRepository.save(any(Aluno.class))).thenReturn(retorno);

        Aluno editado = this.alunoService.editaNomeAluno("id", nomeDTO);

        assertEquals(retorno, editado);
    }


    @Test
    @DisplayName("Deve Lançar Exceção Para Aluno não Encontrado")
    public void deveLancarExcecaoParaClienteNaoEncontrado(){
        assertThrows(AlunoNotFoundException.class,
                ()->{ this.alunoService.buscaAlunoPorId("id"); });
    }

    @Test
    @DisplayName("Deve Deletar Aluno Por ID")
    public void deveDeletarAlunoPorID() {
        Aluno retorno = alunoEntity();

        when(alunoRepository.findById("id")).thenReturn(Optional.ofNullable(retorno));
        this.alunoService.deletaAluno("id");

        verify(alunoRepository, times(1)).deleteById("id");
    }


    public Aluno alunoEntity(){
        return Aluno.builder().id("id").nome("Cássio").build();
    }
    public List<Aluno> listAlunos() {
        return Arrays.asList(
                Aluno.builder().nome("Cássio").build(),
                Aluno.builder().nome("Cássio").build(),
                Aluno.builder().nome("Cássio").build()
                );
    }




}
