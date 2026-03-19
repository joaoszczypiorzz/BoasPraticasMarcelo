package SOLID;

import java.util.Scanner;

public class Menu {
    private final Biblioteca biblioteca;
    private final Scanner teclado;

    public Menu(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        this.teclado = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            exibirOpcoes();
            try {
                opcao = Integer.parseInt(teclado.nextLine());
                processarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números.");
            } catch (Exception e) {
                System.out.println("Erro no sistema: " + e.getMessage());
            }
        }
    }

    private void exibirOpcoes() {
        System.out.println("\n--- MENU BIBLIOTECA ---");
        System.out.println("1. Adicionar Livro");
        System.out.println("2. Listar Acervo");
        System.out.println("3. Emprestar Livro");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.print("Digite o título do livro: ");
                String titulo = teclado.nextLine();
                biblioteca.adicionarLivro(titulo);
                System.out.println("Livro adicionado!");
                break;
            case 2:
                System.out.println("\n--- ACERVO ATUAL ---");
                if (biblioteca.getAcervo().isEmpty()) {
                    System.out.println("Nenhum livro cadastrado.");
                } else {
                    biblioteca.getAcervo().forEach(l -> System.out.println("- " + l.getTitulo()));
                }
                break;
            case 3:
                if(!biblioteca.getAcervo().isEmpty()){
                    System.out.print("Título para empréstimo: ");
                    String busca = teclado.nextLine();
                    if (biblioteca.emprestarLivro(busca)) {
                        System.out.println("Empréstimo realizado com sucesso!");
                    } else {
                        System.out.println("Livro não encontrado no acervo.");
                    }
                } else{
                    System.out.println("Lista está vazia, Operação Inválida!");
                }
                break;
            case 0:
                System.out.println("Encerrando sistema...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
}
