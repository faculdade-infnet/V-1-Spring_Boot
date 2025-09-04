package com.infnet.applicatioh2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.applicatioh2.entity.Livro;
import com.infnet.applicatioh2.service.LivroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// criar somente o contexto web necessário para testar o LivroController.
// //Ele não sobe a aplicação inteira, só o que é necessário para testes de API.
@WebMvcTest(LivroController.class)
class LivroControllerTest {

    // classe que permite simular requisições HTTP (GET, POST, PUT, DELETE, etc.) contra os endpoints do controller, sem precisar rodar um servidor de verdade.
    @Autowired
    private MockMvc mockMvc;

    // LivroService e mockado, então quando o controller chamar livroService.create(...), quem responde é o mock.
    // Assim, o teste só avalia o comportamento do controller, não a lógica de serviço nem o banco.
    @MockBean
    private LivroService livroService;

    private Livro livro1;
    private ObjectMapper objectMapper;

    // metodo que é executado antes de cada teste.
    @BeforeEach
    void setUp() {
        livro1 = new Livro(1L, "Livro A");
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateLivro() throws Exception {
        // Quando o controller chamar livroService.create( ), ele vai retornar livro1.
        when(livroService.create(any(Livro.class))).thenReturn(livro1);

        // Simula uma requisição HTTP POST para /livros e define o tipo de conteúdo como application/json.
        // Converte livro1 em JSON e envia no corpo da requisição.
        //      Espera que a resposta seja status 201 Created e que O JSON de resposta deve ter a propriedade "nome" com valor "Livro A".
        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livro1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Livro A"));

        // verifica se o metodo foi chamado apenas 1 vez
        verify(livroService, times(1)).create(any(Livro.class));
    }

    @Test
    void testGetAllLivros() throws Exception {
        // Quando o controller chamar livroService.getAll( ), ele vai retornar uma lista com apenas livro1.
        when(livroService.getAll()).thenReturn(Arrays.asList(livro1));

        // Simula uma requisição HTTP GET para o endpoint /livros.
        // Verifica se a resposta do controller tem status HTTP 200 (OK).
        // Usa o JsonPath para verificar o JSON retornado:
        //      $.length() pega o tamanho da lista retornada.
        //      .value(1) garante que a lista tem 1 item.
        mockMvc.perform(get("/livros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        // Confirma que o metodo livroService.getAll() foi chamado uma vez pelo controller.
        verify(livroService, times(1)).getAll();
    }

    @Test
    void testGetLivroById() throws Exception {
        // Quando o controller chamar livroService.getId( ), ele vai retornar livro1.
        when(livroService.getId(1L)).thenReturn(livro1);

        // Simula uma requisição HTTP GET para o endpoint /livros/1.
        // Verifica se a resposta tem status HTTP 200 (OK).
        // Usa o JsonPath, verifica se o campo nome tem valor = "Livro A".
        mockMvc.perform(get("/livros/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Livro A"));

        // Verifica se o metodo livroService.getId(1L) foi chamado apenas uma vez pelo controller.
        verify(livroService, times(1)).getId(1L);
    }

    @Test
    void testDeleteLivro() throws Exception {
        // Configura o mock do service para não fazer nada quando o delete for chamado com o ID 1
        doNothing().when(livroService).delete(1L);

        // Executa a chamada DELETE para o endpoint /livros/1
        // Verifica se a resposta HTTP tem status 204 No Content
        mockMvc.perform(delete("/livros/1"))
                .andExpect(status().isNoContent());

        // Garante que o metodo delete do service foi chamado exatamente 1 vez com o ID 1
        verify(livroService, times(1)).delete(1L);
    }

    @Test
    void testUpdateLivro() throws Exception {
        Livro livroAtualizado = new Livro(1L, "Livro Atualizado");
        // Quando o controller chamar livroService.update( ), ele vai retornar livroAtualizado.
        when(livroService.update(any(Livro.class))).thenReturn(livroAtualizado);

        // Faz a requisição PUT para /livros/1 enviando JSON no corpo
        // pega o objeto Java livroAtualizado e o transforma em string JSON usando o objectMapper (do Jackson).
        // Espera retorno 200 OK
        // Espera que o campo "nome" no JSON seja "Livro Atualizado"
        mockMvc.perform(put("/livros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livroAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Livro Atualizado"));

        // Garante que o metodo update do service foi chamado 1 vez
        verify(livroService, times(1)).update(any(Livro.class));
    }
}
