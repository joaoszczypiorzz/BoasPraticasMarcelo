package Acervo;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Gerencia o acervo de livros possibilitando adição,
 * emprestimo e exibição 
 */
public class Biblioteca {
    private int totalEmprestimos;
    private List<Livro> acervo;

    /** Nome do Arquivo criado,é utilizado Final pois é imutavel */
    private static final String ARQUIVO_lIVROS = "livros.txt";

    /**
     * Construtor chama o método carregar livro para que já comece com a lista assim que instanciado
     * @author: Andrey Marucci
     */
    public Biblioteca(){
        acervo = new ArrayList<>();
        carregarLivros();
    }

    private int getTotalEmprestimos() {
        return totalEmprestimos;
    }

    private void setTotalEmprestimos(int totalEmprestimos) {
        this.totalEmprestimos = totalEmprestimos;
    }

    private List<Livro> getAcervo() {
        return acervo;
    }

    private void setAcervo(List<Livro> acervo) {
        this.acervo = acervo;
    }

    /**
     * Função para adicionar os Livros na lista
     * @param titulo Input do usuário
     * @return true quando adicionado, e false quando algumas das exceções forem lançadas
     * @throws IllegalArgumentException Caso titulo informado pelo usuário for menor que 3 caracteres ou nulo
     * @throws InputMismatchException Caso titulo informado já existir na Lista
     */
    public boolean adicionarLivro(String titulo) {
        if(titulo == null || titulo.length() < 3){
            throw new IllegalArgumentException("Titulo informado é muito curto!");
        } else if (verificaExiste(titulo) == true){
            throw new InputMismatchException("Titulo informado já existe no Acervo!");
        }

        acervo.add(new Livro(titulo));
        salvarLivros();
        return true;
    }

    /**
     * Empresta livros, os removendo do acervo
     * @param tituloProcurado input do usuário
     * @return true caso o livro exista na lista e a lista não esteja vazia, false se não achar o Acervo.Livro
     * @throws IllegalStateException caso a lista estiver vazia
     * @author João Szczypior
     * @version: 1.0
     */
    public boolean emprestarLivro(String tituloProcurado){
        if(listaVazia()){
            throw new IllegalStateException("Lista está vazia, não há o que emprestar");
        }

        /** Compara os Livros contidos no acervo com o tituloProcurado, ignorando maiusculos e minusculos */
        boolean tituloAchado =  acervo.removeIf(l -> l.getTitulo().equalsIgnoreCase(tituloProcurado));
        if(tituloAchado){
            totalEmprestimos++;
            salvarLivros(); //Atualiza o arquivo
            System.out.println("Total de empréstimos: " + totalEmprestimos);
            return true;
        }

        return false; //não achou o Acervo.Livro

    }

    /**
     * Verifica se o titulo informado pelo usuário existe na lista,
     * retornando true se encontrar e false se não encontrar
     * @param inputTitulo input de titulo informado pelo usuário
     * @return true se encontrar e false se não encontrar
     * @version: 1.0
     * @author: João Szczypior
     */
    private boolean verificaExiste(String inputTitulo){

        for (Livro l : acervo) {
            if(l.getTitulo().equalsIgnoreCase(inputTitulo))
                return true; //Acervo.Livro já existe na lista
        }
        return false; //Acervo.Livro não existe na Lista
    }

    /**
     * Garante que o ARQUIVO_lIVROS tenha seu estado mantido no livros.txt
     * @author: Andrey Marucci
     */
    private void salvarLivros(){
        try {
            //Instanciando um escritor de arquivos e atribuindo ARQUIVO_LIVROS como alvo de escrita
            try(PrintWriter escritor = new PrintWriter(ARQUIVO_lIVROS);){
                for (Livro l : acervo) {
                    escritor.println(l.getTitulo());
                }
            }

        } catch (Exception e) {
            System.out.println("Erro salvando Acervo: " + e.getMessage());
        }
    }

    /**
     * Le os títulos do arquivo os carregando na lista
     * @author: Andrey Marucci
     */
    private void carregarLivros() {
        try {
            File f = new File(ARQUIVO_lIVROS);
            if (f.exists()) {
                Scanner fileSc = new Scanner(f);
                while (fileSc.hasNextLine()) {
                    acervo.add(new Livro(fileSc.nextLine()));
                }
                fileSc.close();
            }
        } catch (Exception e) {
            System.out.println("Erro carregando acervo: " + e.getMessage());
        }
    }

    /**
     * Demonstra todos os livros contidos no terminal
     */
    public void exibir(){
        for(Livro livro : acervo){
            System.out.println(livro);
        }
    }

    /**
     * Verifica se a lista esta vazia, retornando false se não
     * @throws illegalStateException caso a lista esta vazia
     * @return false se a lista não estiver vazia
     */
    public boolean listaVazia (){
        if(acervo.isEmpty()){
            throw new IllegalStateException("Lista Está vazia, não há o que emprestar!");
        }
        return false;
    }

}
