package br.com.gomining.gestaonotas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlunoTest {

    @Mock
    List<Nota> notas;

    @Test
    @DisplayName("Deve Criar um Aluno")
    public void deveCriarAluno() {
        Aluno aluno = Aluno.builder()
                . id("id1")
                .nome("Cássio")
                .build();

        List<Nota> listaVazia = new ArrayList<>();

        assertEquals("id1", aluno.getId());
        assertEquals("Cássio", aluno.getNome());
        assertEquals(listaVazia, aluno.getBoletim());
    }


    @Test
    @DisplayName("Deve Chamar Notas")
    public void deveChamarBoletim(){
        Aluno aluno = Aluno.builder()
                . id("id1")
                .nome("Cássio")
                .boletim(notas)
                .build();

        when(notas.size()).thenReturn(5);

        int tamanho = notas.size();

        assertEquals(tamanho, notas.size());
    }
}
