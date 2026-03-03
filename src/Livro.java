public class Livro {
    //Atributos da classe:
    private String titulo;
    private String autor;
    private int id;

    /**
     * Construtor completo para criar um Livro com todos os atributos.
     *  @param titulo O título do livro. Não pode ser nulo ou vazio.
     * @param autor  O nome do autor do livro. Não pode ser nulo ou vazio.
     * @param id     O identificador único.
     * @throws NullPointerException se o título ou autor forem nulos.
     * @throws IllegalArgumentException se o título ou autor estiverem em branco.
     * @author João Vitor Novaes
     */
    public Livro(String titulo, String autor, int id) {
        java.util.Objects.requireNonNull(titulo, "O título do livro não pode ser nulo.");
        java.util.Objects.requireNonNull(autor, "O autor do livro não pode ser nulo.");

        if (titulo.isBlank()) {
            throw new IllegalArgumentException("O título do livro não pode estar vazio.");
        }
        if (autor.isBlank()) {
            throw new IllegalArgumentException("O autor do livro não pode estar vazio.");
        }

        this.titulo = titulo.trim();
        this.autor = autor.trim();
        this.id = id;
    }

    /**
     * Construtor simplificado que inicializa apenas o título do livro.
     * Útil para buscas rápidas ou instâncias temporárias.
     * * @param titulo O título do livro. Não pode ser nulo ou vazio.
     * @throws NullPointerException se o título for nulo.
     * @throws IllegalArgumentException se o título estiver em branco.
     * @author João Vitor Novaes
     */
    public Livro(String titulo) {
        java.util.Objects.requireNonNull(titulo, "O título do livro não pode ser nulo.");

        if (titulo.isBlank()) {
            throw new IllegalArgumentException("O título do livro não pode estar vazio.");
        }

        this.titulo = titulo.trim();
        this.autor = "Desconhecido"; // Boa prática: inicializar campos obrigatórios
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Livro setAutor(String autor) {
        this.autor = autor;
        return this;
    }

    public int getId() {
        return id;
    }

    public Livro setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Função para exibir atributos referentes ao objeto Livro.
     * @author João Vitor Novaes
     * @return
     */

    // precisa terminar de ajustar conforme adicionado os novos atributos> autor e id.
    @Override
    public String toString() {
        return "| Titulo : " + this.titulo + " |\n";
    }
}
