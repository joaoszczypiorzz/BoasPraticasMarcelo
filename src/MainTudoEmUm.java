import java.util.*;
import java.io.*;

public class MainTudoEmUm {

    private static List<String> livros = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static int totalEmprestimos = 0;

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
                case 4: rodando = false; break;
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


    private static int totalEmprestimosGeral = 0;

    private static void emprestarLivro() {
        System.out.print("Título para empréstimo: ");
        String tituloEmprestimo = sc.nextLine().toLowerCase();
        boolean encontrado = false;

        if (livros.isEmpty()) {
            System.out.println("A biblioteca está vazia! Não há o que emprestar.");
            return;
        }

        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).toLowerCase().contains(tituloEmprestimo)) {

                //Remoção do Livro encontrado
                String livroRemovido = livros.remove(i);

                //Contador de empréstimos (global)
                totalEmprestimosGeral++;

                System.out.println("Empréstimo realizado com sucesso: " + livroRemovido);
                System.out.println("Total de empréstimos feitos até agora: " + totalEmprestimosGeral);
                System.out.println("Livros restantes no acervo: " + livros.size());

                encontrado = true;
                break;
            }

            //Validador caso livro nao seja encontrado
            if (!encontrado) {
                System.out.println("Livro não encontrado no!");
            }
        }
    }

    private static void listarLivros() {
        for (String l : livros) System.out.println(l);
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
