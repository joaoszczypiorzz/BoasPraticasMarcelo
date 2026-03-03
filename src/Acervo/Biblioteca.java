package Acervo;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

public class Biblioteca {
    private int totalEmprestimos;
    private List<Livro> acervo;
    private static final String ARQUIVO_lIVROS = "livros.txt"; //Final são para Variavéis que não mudam

    /**
     * Construtor chama o método carregar livro para que já comece com a lista asssim que instanciado
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
     * @throws IllegalArgumentException Caso titulo informado pelo usuário for menor que 3 caracteres
     * @throws InputMismatchException Caso titulo informado já existir na Lista
     */
    public boolean adicionarLivro(String titulo) {
        if(titulo == null || titulo.length() < 3){
            throw new IllegalArgumentException("ERRO: Titulo informado é muito curto!");
        } else if (verificaExiste(titulo) == true){
            throw new InputMismatchException("ERRO: Titulo informado já existe no Acervo!");
        }
        acervo.add(new Livro(titulo));
        salvarLivros();
        return true;
    }

    /**
     * Função para emprestar livros
     * @param tituloProcurado input do usuário
     * @return true caso o livro não exista na lista e a lista não esteja vazia, e false se não achar o Acervo.Livro
     * @throws IllegalStateException caso a lista estiver vazia
     * @author João Szczypior
     * @version: 1.0
     */
    public boolean emprestarLivro(String tituloProcurado){
        if(acervo.isEmpty()){
            throw new IllegalStateException("Erro: Lista está vazia não há o que emprestar");
        }
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
     * Função que verifica se o titulo informado pelo usuário existe na lista
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
     * Correções: Ajustei o nome das variáveis para ficar mais legível, alterei a mensagem de erro porque ela não informava o erro
     * e cololquei um try para garantir que o PrintWriter seja fechado.
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
     * Correções: Ajustei o nome das variáveis para ficar mais legível, alterei a mensagem de erro porque estava confusa,
     * inclui um try dentro do if para garantir que o leitor seja fechado mesmo se acontecesse algum erro dentro do while
     * e verifico se não há linhas vazias dentro do txt, o que faz com que evite de ler uma quebra de linha como um livro
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

    public void exibir(){
        for(Livro livro : acervo){
            System.out.println(livro);
        }
    }

}
