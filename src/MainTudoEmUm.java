import java.util.*;
import java.io.*;

public class MainTudoEmUm {

    private static List<String> livros = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== BIBLIOTECA ORGANIZADA ===");

        // Loop para o menu não fechar sozinho
        boolean rodando = true;
        while (rodando) {
            System.out.println("\n1-Adicionar 2-Emprestar 3-Listar 4-Sair");
            int op = sc.nextInt();
            sc.nextLine(); // Limpa o buffer

            switch (op) {
                case 1: adicionarLivro(); break;
                case 2: emprestarLivro(); break;
                case 3: listarLivros(); break;
                case 4: rodando = false;break;
                default: System.out.println("Input digitado Inválido!"); //executa apenas se op for 1,2,3 ou 4
            }
        }
    }


    private static void adicionarLivro() {
        while (true){


            System.out.print("Titulo: ");
            String titulo = sc.nextLine();
            {
                if (titulo.length() < 3) { // Validacao inline
                    System.out.println("ERRO: Titulo curto!");
                    continue;
                }
                // Regra de negocio + Persistencia
                if (livros.contains(titulo.toLowerCase())) {
                    System.out.println("Livro ja existe!");
                } else {
                    livros.add(titulo);
                    salvarLivros(); // Salva arquivo toda vez!
                    System.out.println("Adicionado!");
                    break;
                }
            }
        }

    }


    /**
     * Correções incluidas:
     * Validação de Lista vazia, emitindo msg de erro ao usuario
     * Lógica do for que percorre a List, para encontrar corretamente os Livros
     * @version: 2.0
     * @author: João Szczypior
     */
    private static void emprestarLivro() {
        if(livros.isEmpty()){ //verificação Lista de Livros vazia
            System.out.println("A lista de Livros está vazia, não há o que emprestar!");
            return;
        }
        System.out.println("Título para empréstimo: ");
        String inputEmprestimo = sc.nextLine();
        int totalEmprestimos = 0;
        for(int i = 0; i < livros.size(); i++){
            if(livros.get(i).equalsIgnoreCase(inputEmprestimo)){
                String livroEmprestimo = livros.remove(i);  //remove o livro encontado da lista, pois foi emprestado
                totalEmprestimos++; //adiciona +1 no total dos emprestimos
                System.out.println("Empréstimo do Livro: " + inputEmprestimo + " Realizado com sucesso!");
                System.out.println("Total de Empréstimos feitos até agora: " + totalEmprestimos);
                System.out.println("Livros restantes no Acervo: " + livros.size());
                break;
            } else {
                System.out.println("Livro: " + inputEmprestimo + " Não encontrado no acervo!");
                break;
            }
        }
    }

    /**
     * Correções de validação caso a lista for vazia emitir mensagem de erro ao usuário
     * @author: João szczypior
     */
    private static void listarLivros() {
        if(livros.isEmpty()){  //Validação caso a lista esteja vazia
            System.out.println("A lista De livros Está vazia!");
        } else {
            for (String l : livros) System.out.println(l);
        }

    }


    private static void carregarLivros() {
        try {
            File f = new File("livros.txt");
            if (f.exists()) {
                Scanner fileSc = new Scanner(f);
                while (fileSc.hasNextLine()) {
                    livros.add(fileSc.nextLine());
                }
                fileSc.close();
            }
        } catch (Exception e) {
            System.out.println("Erro carregando: " + e.getMessage());
        }
    }


    private static void salvarLivros() {
        try {
            PrintWriter pw = new PrintWriter("livros.txt");
            for (String l : livros) {
                pw.println(l);
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Erro salvando!");
        }
    }
}
