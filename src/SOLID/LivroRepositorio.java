package SOLID;

import Acervo.Livro;
import java.util.List;

/**
 * Interface que define o contrato para operações de persistência de livros.
 * Permite que diferentes implementações (Arquivo, Banco de Dados, Memória)
 * sejam utilizadas de forma intercambiável.
 */
public interface LivroRepositorio {

    /**
     * Salva a lista atual de livros na fonte de persistência definida pela implementação.
     *  @param livros Lista de objetos do tipo Livro a serem persistidos.
     */
    void salvar(List<Livro> livros);

    /**
     * Carrega os livros armazenados da fonte de persistência para uma lista em memória.
     * @return Uma lista contendo os livros recuperados.
     */
    List<Livro> carregar();
}