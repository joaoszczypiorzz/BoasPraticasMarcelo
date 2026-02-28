import java.util.*;
import java.io.*;

public class MainTudoEmUm {
    private static List<String> livros = new ArrayList<>(); // Persistencia em memoria
    private static Scanner sc = new Scanner(System.in);
    private static int totalEmprestimos = 0;

    public static void main(String[] args) {
        System.out.println("=== BIBLIOTECA BAGUNCADA ===");
        carregarLivros(); // Persistencia misturada

        while (true) {
            // Entrada misturada com UI
            System.out.println("\n1-Adicionar 2-Emprestar 3-Listar 4-Sair");
            int op = sc.nextInt(); sc.nextLine();

            if (op == 1) {
                // Entrada + Validacao misturada
                System.out.print("Titulo: ");
                String titulo = sc.nextLine();
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
                }
            } else if (op == 2) {
                // Emprestimo com regra misturada
                System.out.print("Titulo para emprestimo: ");
                String tituloEmprestimo = sc.nextLine().toLowerCase();
                boolean encontrado = false;
                for (int i = 0; i < livros.size(); i++) {
                    if (livros.get(i).toLowerCase().contains(tituloEmprestimo)) {
                        totalEmprestimos++;
                        System.out.println("Emprestado! Total: " + totalEmprestimos);
                        encontrado = true;
                        // Saida misturada com logica
                        System.out.println("Livros disponiveis: " + (livros.size() - totalEmprestimos));
                        break;
                    }
                }
                if (!encontrado) System.out.println("Livro nao encontrado!");
            } else if (op == 3) {
                // Listagem com formatacao inline
                System.out.println("Livros (" + livros.size() + "):");
                for (String l : livros) {
                    System.out.println("- " + l);
                }
            } else if (op == 4) {
                salvarLivros();
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opcao invalida!");
            }
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