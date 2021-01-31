package br.com.gomining.gestaonotas.service;


import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @Mock
    AlunoRepository alunoRepository;

    @InjectMocks
    AlunoService alunoService;

    @Test
    public void deveCriarAluno() {
        Aluno stub = Aluno.builder().nome("Cássio").build();
        Aluno retorno = Aluno.builder().nome("Cássio").build();


        when(alunoRepository.save(stub)).thenReturn(retorno);

        Aluno salvo = alunoService.salvaAluno(stub);

        assertEquals(retorno, salvo);
    }




}
