package Acervo;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Gerencia o acervo de livros da aplicação, possibilitando a adição de novos títulos,
 * controle de empréstimos e persistência de dados em arquivo de texto.
 * @author Andrey Marucci, João Szczypior
 */
public class Biblioteca {
    private int totalEmprestimos;
    private List<Livro> acervo;

    /** Nome do arquivo onde o acervo será salvo e lido. */
    private static final String ARQUIVO_LIVROS = "livros.txt";

    /**
     * Construtor da Biblioteca.
     * Inicializa a lista do acervo e carrega automaticamente os livros salvos no arquivo físico.
     */
    public Biblioteca() {
        acervo = new ArrayList<>();
        carregarLivros();
    }

    /**
     * Adiciona um novo livro à lista do acervo e atualiza o arquivo físico.
     * @param titulo O nome do livro inserido pelo usuário.
     * @return true se o livro for adicionado com sucesso.
     * @throws IllegalArgumentException Se o título informado for nulo ou possuir menos de 3 caracteres.
     * @throws InputMismatchException   Se o título informado já existir no acervo atual.
     */
    public boolean adicionarLivro(String titulo) {
        if (titulo == null || titulo.length() < 3) {
            throw new IllegalArgumentException("Erro: Titulo informado é muito curto!");
        } else if (verificaExiste(titulo)) {
            throw new InputMismatchException("Erro: Titulo informado já existe no Acervo!");
        }

        acervo.add(new Livro(titulo));
        salvarLivros();
        return true;
    }

    /**
     * Empresta um livro, removendo-o temporariamente do acervo.
     * * @param tituloProcurado O título do livro a ser buscado no acervo.
     * @return true se o livro for encontrado e removido; false caso contrário.
     * @throws IllegalStateException se a lista do acervo estiver vazia antes da busca.
     */
    public boolean emprestarLivro(String tituloProcurado) {
        if (listaVazia()) {
            throw new IllegalStateException("Lista está vazia, não há o que emprestar.");
        }

        // Compara os Livros contidos no acervo com o tituloProcurado, ignorando maiúsculas e minúsculas
        boolean tituloAchado = acervo.removeIf(l -> l.getTitulo().equalsIgnoreCase(tituloProcurado));

        if (tituloAchado) {
            totalEmprestimos++;
            salvarLivros(); // Atualiza o arquivo
            System.out.println("Total de empréstimos: " + totalEmprestimos);
            return true;
        }

        return false; // Não achou o Livro
    }

    /**
     * Verifica se o título informado pelo usuário já existe na lista do acervo.
     * * @param inputTitulo O título informado pelo usuário.
     * @return true se o livro for encontrado; false se não encontrar.
     */
    private boolean verificaExiste(String inputTitulo) {
        for (Livro l : acervo) {
            if (l.getTitulo().equalsIgnoreCase(inputTitulo)) {
                return true; // Livro já existe na lista
            }
        }
        return false; // Livro não existe na Lista
    }

    /**
     * Sobrescreve o arquivo de texto com o estado atual da lista de livros.
     */
    private void salvarLivros() {
        try (PrintWriter escritor = new PrintWriter(ARQUIVO_LIVROS)) {
            for (Livro l : acervo) {
                escritor.println(l.getTitulo());
            }
        } catch (Exception e) {
            System.out.println("Erro salvando Acervo: " + e.getMessage());
        }
    }

    /**
     * Lê os títulos do arquivo de texto e os carrega na lista do acervo na memória.
     */
    private void carregarLivros() {
        try {
            File f = new File(ARQUIVO_LIVROS);
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
     * Exibe todos os livros contidos no acervo diretamente no terminal.
     */
    public void exibir() {
        if(!listaVazia()){
            for (Livro livro : acervo) {
                System.out.println(livro);
            }
        } else {
            System.out.println("Lista Vazia!");
        }
    }

    /**
     * Verifica se a lista do acervo está vazia.
     * Nota: O comportamento atual lança uma exceção em vez de retornar true.
     * @return false se a lista contiver elementos.
     * @throws IllegalStateException caso a lista não contenha nenhum livro.
     */
    public boolean listaVazia() {
        if (acervo.isEmpty()) {
            throw new IllegalStateException("Lista está vazia, operação inválida!");
        }
        return false;
    }
}