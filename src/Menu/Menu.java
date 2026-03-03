package Menu;

import Acervo.Biblioteca;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Classe utilitária, contém as saídas de dados para o usuário e o Scanner, recebe a biblioteca e passa os valores para ela
 */
public class Menu {

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
     * Utiliza o método da biblioteca
     */
    private void menuAdicionar() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        try {
            // Tenta adicionar. Se der erro na regra de negócio, cai no catch.
            biblioteca.adicionarLivro(titulo);
            System.out.println("SUCESSO: Livro '" + titulo + "' adicionado ao acervo.");
        } catch (IllegalArgumentException | InputMismatchException e) {
            // Captura as exceções que você configurou na Acervo.Biblioteca
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Utiliza o método emprestar da biblioteca
     */
    private void menuEmprestar() {
        System.out.print("Digite o título do livro que deseja emprestar: ");
        String titulo = scanner.nextLine();

        try {
            boolean emprestou = biblioteca.emprestarLivro(titulo);
            if (emprestou) {
                System.out.println("Você emprestou o livro '" + titulo + "'.");
            } else {
                System.out.println("Livro não encontrado no acervo.");
            }
        } catch (IllegalStateException e) {
            // Captura erro de lista vazia
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Utiliza o método da biblioteca para listar os livros
     */
    private void menuListar() {
        System.out.println("\n--- ACERVO ---");
        biblioteca.exibir();
    }
}