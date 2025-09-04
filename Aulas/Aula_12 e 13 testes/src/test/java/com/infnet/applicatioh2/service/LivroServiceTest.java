package com.infnet.applicatioh2.service;

import com.infnet.applicatioh2.entity.Livro;
import com.infnet.applicatioh2.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LivroServiceTest {
    // Cria um objeto simulado (mock) de LivroRepository.
    //Isso significa que, durante os testes, não será acessado o banco de dados real. O mock retorna valores definidos no teste.
    @Mock
    private LivroRepository livroRepository;

    // Cria uma instância real de LivroService e injeta automaticamente o mock (livroRepository) dentro dela.
    @InjectMocks
    private LivroService livroService;

    private Livro livro1;
    private Livro livro2;

    // metodo que é executado antes de cada teste.
    @BeforeEach
    void setUp() {
    // usado MockitoAnnotations.openMocks(this) para inicializar os mocks, e são criados dois objetos Livro.
        MockitoAnnotations.openMocks(this);
        livro1 = new Livro(1L, "Livro A");
        livro2 = new Livro(2L, "Livro B");
    }

    @Test
    void testCreateLivro() {
        // verifica se quando o livroRepository.save(livro1) for chamado, ele retorna livro1.
        when(livroRepository.save(livro1)).thenReturn(livro1);

        Livro resultado = livroService.create(livro1); // Chama o metodo create

        // Se resultado não é null e se o nome retornado é "Livro A"
        assertNotNull(resultado);
        assertEquals("Livro A", resultado.getNome());
        // verifica se foi chamado o metodo sava pasando o objeto "livro1" apenas 1 vez
        verify(livroRepository, times(1)).save(livro1);
    }

    @Test
    void testGetAllLivros() {
        // verifica se quando livroRepository.findAll() for chamado, ele retorna livro1.
        when(livroRepository.findAll()).thenReturn(Arrays.asList(livro1, livro2));

        List<Livro> livros = livroService.getAll();

        assertEquals(2, livros.size());
        // verifica se o metodo foi chamado apenas 1 vez
        verify(livroRepository, times(1)).findAll();
    }

    @Test
    void testGetLivroById() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro1));

        Livro resultado = livroService.getId(1L);

        assertNotNull(resultado);
        assertEquals("Livro A", resultado.getNome());
        // verifica se o metodo foi chamado apenas 1 vez com o valor de id "1L"
        verify(livroRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateLivro() {
        Livro livroAtualizado = new Livro(1L, "Livro A Atualizado");
        // Configura o mock: Quando o LivroService chamar livroRepository.findById(1L), o mock vai retornar Optional.of(livro1) (ou seja, dizendo: "o livro existe no banco").
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro1));
        // Configura o mock para o save: Não importa qual instância de Livro (any(Livro.class)) seja passada, o mock vai retornar o livroAtualizado.
        // Isso simula o comportamento do banco de dados após salvar o livro atualizado.
        when(livroRepository.save(any(Livro.class))).thenReturn(livroAtualizado);

        // Chama de fato o metodo update que queremos testar.
        Livro resultado = livroService.update(livroAtualizado);

        // verifica se o nome nao é nulo e se o valor foi atualziado
        assertNotNull(resultado);
        assertEquals("Livro A Atualizado", resultado.getNome());
        // verifica se o metodo findById() passando o valor "1L" foi chamado somente 1 vez
        verify(livroRepository, times(1)).findById(1L);
        // verifica se o metodo save( ) passando qualquer livro foi chamado somente 1 vez
        verify(livroRepository, times(1)).save(any(Livro.class));
    }

    @Test
    void testDeleteLivro() {
        // quando o metodo deleteById(1L) for chamado no mock do livroRepository, não faça nada".
        doNothing().when(livroRepository).deleteById(1L);
        // chama o metodo delete edo LivroService que deve chamar o livroRepository.deleteById(1L)
        livroService.delete(1L);
        // verifica que o metodo deleteById(1L) foi chamado exatamente uma vez no mock livroRepository-
        verify(livroRepository, times(1)).deleteById(1L);
    }
}
