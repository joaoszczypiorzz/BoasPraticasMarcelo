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
        this.titulo = Objects.requireNonNull(titulo, "Erro: Título não pode ser nulo").trim();
        if (this.titulo.isEmpty()) {
            throw new IllegalArgumentException("Erro: Título não pode ser vazio");
        }
    }

    public String getTitulo(){ return this.titulo; }

    /**
     * Retorna uma representação da classe Livro em texto
     */
    @Override
    public String toString() {
        return "| Titulo : " + this.titulo + " |";
    }
}
