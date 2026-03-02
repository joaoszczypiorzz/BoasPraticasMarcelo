public class Livro {
    //Atributos da classe:
    private String titulo;
    //getters e Setters
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    //Constructor da Classe:
    public Livro(String titulo){
        this.titulo = titulo;
    }

    //ToString para facilitar a exibição
    @Override
    public String toString() {
        return this.getTitulo();
    }
}
