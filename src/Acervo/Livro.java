package Acervo;

import java.util.Objects;

/**
 * Representa um livro no acervo da biblioteca.
 * Contém as informações básicas e garante a integridade dos dados na sua criação.
 * @author João Vitor Novaes
 */
public class Livro {
    private final String titulo;

    /**
     * Construtor para instanciar um Livro.
     * @param titulo O título do livro. Não pode ser nulo ou vazio.
     * @throws NullPointerException     se o parâmetro título for passado como nulo.
     * @throws IllegalArgumentException se o parâmetro título estiver vazio ou contiver apenas espaços em branco.
     */
    public Livro(String titulo) {
        this.titulo = Objects.requireNonNull(titulo, "Erro: Título não pode ser nulo").trim();
        if (this.titulo.isEmpty()) {
            throw new IllegalArgumentException("Erro: Título não pode ser vazio");
        }
    }

    /**
     * Obtém o título do livro.
     * @return O título atual do livro instanciado.
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Retorna uma representação estruturada da classe Livro em formato de texto.
     * @return Uma string contendo o título formatado para exibição.
     */
    @Override
    public String toString() {
        return "| Titulo : " + this.titulo + " |";
    }
}