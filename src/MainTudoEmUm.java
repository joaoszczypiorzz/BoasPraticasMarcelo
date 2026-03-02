import java.util.*;
import java.io.*;

public class MainTudoEmUm {

    private static List<String> livros = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    /* TEMOS QUE AJUSTAR A MAIN PARA COMPORTAR O COMPORTAMENTO DAS CLASSES
    public static void main(String[] args) {
        System.out.println("=== BIBLIOTECA ORGANIZADA ===");
        carregarLivros();
        // Loop para o menu não fechar sozinho
        boolean rodando = true;
        do{
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
        } while (rodando);
            
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

    /**
     * Verifica se a lista está vazia
     * @author: Andrey Marucci
     * @return retorna verdadeiro se a lista está vazia
     */
    private static boolean estaVazia(){
        return livros.isEmpty();
    }

    /**
     * Correções: Ajustei o nome das variáveis para ficar mais legível, alterei a mensagem de erro porque estava confusa, 
     * inclui um try dentro do if para garantir que o leitor seja fechado mesmo se acontecesse algum erro dentro do while
     * e verifico se não há linhas vazias dentro do txt, o que faz com que evite de ler uma quebra de linha como um livro
     * @author: Andrey Marucci
     */
    private static void carregarLivros() {
        try {

            File arquivoTxt = new File("livros.txt");

            if (arquivoTxt.exists()) {

                try(Scanner fileSc = new Scanner(arquivoTxt)){
                    
                    while (fileSc.hasNextLine()) {
                        String livro = fileSc.nextLine().trim();
                        if(!livro.isEmpty()){
                            livros.add(livro);
                        }
                    }
                }
            }
        } catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Correções: Ajustei o nome das variáveis para ficar mais legível, alterei a mensagem de erro porque ela não informava o erro
     * e cololquei um try para garantir que o PrintWriter seja fechado.
     * @author: Andrey Marucci
     */
    private static void salvarLivros() {
        try {
            //Instanciando um escritor de arquivos e atribuindo "livros.txt" como alvo de escrita
            try(PrintWriter escritor = new PrintWriter("livros.txt");){
                for (String livro : livros) {
                escritor.println(livro);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
