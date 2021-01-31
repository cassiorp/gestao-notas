package br.com.gomining.gestaonotas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class NotaTest {


    @Test
    @DisplayName("Deve Criar Conta")
    public void deveCriarConta() {
        Nota nota = Nota.builder()
                .disciplina("Matematica")
                .nomeAluno("Cassio")
                .notaTotal(6.0)
                .build();
        assertEquals("Matematica", nota.getDisciplina());
        assertEquals("Cassio", nota.getNomeAluno());
    }

}
