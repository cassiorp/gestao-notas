package br.com.gomining.gestaonotas.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.gomining.gestaonotas.dto.AlunoDTO;
import br.com.gomining.gestaonotas.dto.NomeDTO;
import br.com.gomining.gestaonotas.exception.AlunoNotFoundException;
import br.com.gomining.gestaonotas.model.Aluno;
import br.com.gomining.gestaonotas.repository.AlunoRepository;
import br.com.gomining.gestaonotas.service.AlunoService;
import br.com.gomining.gestaonotas.service.impl.AlunoServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AlunoController.class)
public class AlunoControllerTest {

    static String GESTAO_NOTAS = "/api/alunos";

    @Autowired
    MockMvc mvc;

    @MockBean
    AlunoService alunoService;

    @Test
    @DisplayName("Deve Criar Aluno")
    public void deveCriarAluno() throws Exception {
        AlunoDTO dto = new AlunoDTO("Edu");
        Aluno retorno = Aluno.builder().id("id").nome("Edu").build();

        when(this.alunoService.salvaAluno(any(Aluno.class))).thenReturn(retorno);

        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(GESTAO_NOTAS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);


        mvc.perform(request)
                .andExpect(status().isCreated());
    }


    @Test
    @DisplayName("Deve Lançar erro Bad Request.")
    public void deveLancarErroBadRequest() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new AlunoDTO());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(GESTAO_NOTAS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("Deve Erro Aluno Não Encontrado.")
    public void deveLancarExceptionAlunoNaoEncontrado() throws Exception {
        BDDMockito.given(alunoService.buscaAlunoPorId(Mockito.anyString())).willThrow(AlunoNotFoundException.class);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(GESTAO_NOTAS.concat("/" + "id"))
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }

}
