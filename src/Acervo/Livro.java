package Acervo;

import java.util.Objects;
public class Livro {
    private final String titulo;

    /**
     * Construtor completo para criar um Acervo.Livro com todos os atributos.
     * @param titulo O título do livro. Não pode ser nulo ou vazio.
     * @author João Vitor Novaes
     */
    public Livro(String titulo) {
        this.titulo = Objects.requireNonNull(titulo, "Título não pode ser nulo").trim();
        if (this.titulo.isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
    }

    public String getTitulo(){ return this.titulo; }

    /**
     * Construtor simplificado que inicializa apenas o título do livro.
     * Útil para buscas rápidas ou instâncias temporárias.
     * * @param titulo O título do livro. Não pode ser nulo ou vazio.
     *
     * @throws NullPointerException     se o título for nulo.
     * @throws IllegalArgumentException se o título estiver em branco.
     * @author João Vitor Novaes
     */

    @Override
    public String toString() {
        return "| Titulo : " + this.titulo + " |";
    }
}


