package SOLID;

import Acervo.Livro;
import SOLID.LivroRepositorio;

import java.util.Collections;
import java.util.List;

/**
 * Gerencia o acervo de livros e as operações de empréstimo da biblioteca.
 */
public class Biblioteca {

    private final List<Livro> acervo;
    private final LivroRepositorio repositorio;
    private int totalEmprestimos;

    /**
     * Construtor da classe Biblioteca.
     * Inicializa o acervo carregando os dados do repositório fornecido.
     * @param repositorio O repositório de dados que será injetado.
     *
     */
    public Biblioteca(LivroRepositorio repositorio) {
        this.repositorio = repositorio;
        this.acervo = repositorio.carregar();
    }

    /**
     * Adiciona um novo livro ao acervo após validação.
     * @param titulo O título do livro a ser adicionado.
     *
     */
    public void adicionarLivro(String titulo) {
        validarNovoLivro(titulo);
        acervo.add(new Livro(titulo));
        repositorio.salvar(acervo);
    }

    /**
     * Realiza o empréstimo de um livro, removendo-o do acervo se encontrado.
     * * @param tituloProcurado O título do livro para empréstimo.
     * @return true se o livro foi encontrado e removido, false caso contrário.
     * @throws IllegalStateException se o acervo estiver vazio.
     */
    public boolean emprestarLivro(String tituloProcurado) {
        if (acervo.isEmpty()) {
            throw new IllegalStateException("Acervo vazio.");
        }

        boolean removido = acervo.removeIf(l -> l.getTitulo().equalsIgnoreCase(tituloProcurado));

        if (removido) {
            totalEmprestimos++;
            repositorio.salvar(acervo);
            return true;
        }
        return false;
    }

    /**
     * Valida as regras de negócio para a inserção de um novo livro.
     * * @param titulo O título a ser validado.
     * @throws IllegalArgumentException se o título for nulo ou menor que 3 caracteres.
     * @throws RuntimeException se o livro já existir no acervo.
     */
    private void validarNovoLivro(String titulo) {
        if (titulo == null || titulo.length() < 3) {
            throw new IllegalArgumentException("Título muito curto!");
        }
        if (verificaExiste(titulo)) {
            throw new RuntimeException("Livro já existe!");
        }
    }

    /**
     * Verifica se um livro com o título informado já existe no acervo.
     * * @param titulo O título para busca.
     * @return true se o livro existir, false caso contrário.
     */
    public boolean verificaExiste(String titulo) {
        return acervo.stream().anyMatch(l -> l.getTitulo().equalsIgnoreCase(titulo));
    }

    /**
     * Retorna uma visualização imutável do acervo atual.
     * @return Uma lista de livros que não permite modificação direta.
     */
    public List<Livro> getAcervo() {
        return Collections.unmodifiableList(acervo);
    }
}