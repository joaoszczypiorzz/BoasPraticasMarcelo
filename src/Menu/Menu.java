package Menu;

import Acervo.Biblioteca;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Classe utilitária, contém as saídas de dados para o usuário e o Scanner, recebe a biblioteca e passa os valores para ela
 */
public class Menu  {

    private Biblioteca biblioteca;
    private Scanner scanner;

    /**
     * @param biblioteca é a biblioteca que estamos usando
     */
    public Menu(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Inicia o programa
     */
    public void iniciar() {
        /** Booleano definido por true, quando é substituido por false encerra o programa */
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== SISTEMA DE BIBLIOTECA ===");
            System.out.println("1. Adicionar");
            System.out.println("2. Emprestar");
            System.out.println("3. Listar Acervo");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1": menuAdicionar(); break;
                case "2": menuEmprestar(); break;
                case "3": menuListar(); break;
                case "4": System.out.println("Encerrando sistema..."); rodando = false; break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    /**
     * Abre um menu para utilizar os metodos da biblioteca
     * com o objetivo de adicionar um livro ao acervo
     */
    private void menuAdicionar() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        try {
            
            biblioteca.adicionarLivro(titulo);
            System.out.println("SUCESSO: Livro '" + titulo + "' adicionado ao acervo.");
        } catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Abre um menu para utilizar métodos da biblioteca,
     * com o objetivo de efetuar e confirmar o emprestimo de um livro 
     */
    private void menuEmprestar() {

        try{

            /** Cria uma variavel igual a função listaVazia da biblioteca */
            boolean listaVazia = biblioteca.listaVazia();
            if(!listaVazia){ //verificação de Lista vazia
                System.out.print("Digite o título do livro que deseja emprestar: ");
                String titulo = scanner.nextLine();

                /** Cria uma variavel que verifica se o livro digitado existe */
                boolean emprestou = biblioteca.emprestarLivro(titulo);
                if (emprestou) {
                    System.out.println("Você emprestou o livro '" + titulo + "'.");
                } else {
                    System.out.println("Erro: Livro '" + titulo + "' não foi encontrado no acervo.");
                }
            }
        }catch (IllegalStateException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Utiliza o método listar da biblioteca para exibir todos os livros contidos em livros.txt
     */
    private void menuListar() {
        System.out.println("\n--- ACERVO ---");
        biblioteca.exibir();
    }
}