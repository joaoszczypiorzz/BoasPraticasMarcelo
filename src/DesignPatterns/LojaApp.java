package DesignPatterns;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal responsável pela execução e interação do Sistema de Gerenciamento de Produtos.
 * Esta classe gerencia o fluxo da aplicação, permitindo cadastro, listagem, vendas
 * e aplicação de descontos em produtos eletrônicos e alimentícios.
 * * @author Joao Vitor Novaes
 * @version 1.0
 */
public class LojaApp {

    /**
     * Método de entrada da aplicação.
     * Inicializa o catálogo, popula com dados iniciais e gerencia o menu de interação com o usuário.
     * * @param args Argumentos de linha de comando (não utilizados neste contexto).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Catalogo<Produto> catalogo = new Catalogo<>();

        // Inicialização de dados pré-definidos para teste
        List<Produto> iniciais = Arrays.asList(
                new ProdutoEletronico("E001", "Notebook Gamer", 5000.00, 24),
                new ProdutoEletronico("E002", "Smartphone X", 2500.00, 12),
                new ProdutoEletronico("E003", "Smart TV 50\"", 3200.00, 18),
                new ProdutoEletronico("E004", "Fone Bluetooth", 350.00, 6),
                new ProdutoEletronico("E005", "Mouse Sem Fio", 90.00, 6),
                // Produtos Alimentícios
                new ProdutoAlimenticio("A001", "Arroz 5kg", 25.90, "31/12/2025"),
                new ProdutoAlimenticio("A002", "Leite 1L", 4.50, "15/01/2025"),
                new ProdutoAlimenticio("A003", "Feijão 1kg", 8.90, "30/11/2025"),
                new ProdutoAlimenticio("A004", "Café 500g", 15.00, "20/10/2025"),
                new ProdutoAlimenticio("A005", "Macarrão 500g", 6.50, "05/09/2025")
        );

        // Adiciona a lista inicial ao catálogo
        iniciais.forEach(p -> catalogo.adicionar(p));
            
       int opcao = -1;

while (opcao != 0) {
    try {
        System.out.println("\n=== SISTEMA DE GESTÃO DE CATÁLOGO ===");
        System.out.println("1 - Listar Produtos");
        System.out.println("2 - Cadastrar Novo Produto");
        System.out.println("3 - Aplicar Desconto");
        System.out.println("4 - Registrar Venda");
        System.out.println("5 - Remover Produto");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
        
        opcao = sc.nextInt();
        sc.nextLine(); // Limpa o buffer

        switch (opcao) {
            case 1:
                System.out.println("=== PRODUTOS CADASTRADOS ===");
                catalogo.listarProdutos();
                break;

            case 2:
                System.out.println("=== CADASTRO DE NOVO PRODUTO ===");
                System.out.print("Informe o tipo (E - Eletrônico / A - Alimentício): ");
                String tipo = sc.next();
                
                if (!tipo.equalsIgnoreCase("a") && !tipo.equalsIgnoreCase("e")) {
                    throw new IllegalArgumentException("Tipo do produto deve ser A ou E.");
                }

                System.out.print("Digite o código: ");
                String codigo = sc.next();
                sc.nextLine(); 

                if (catalogo.verificadorCodigo(codigo)) {
                    throw new IllegalArgumentException("Código já cadastrado!");
                }

                System.out.print("Digite a descrição: ");
                String descricao = sc.nextLine();
                System.out.print("Digite o preço: ");
                double preco = sc.nextDouble();

                if (tipo.equalsIgnoreCase("e")) {
                    System.out.print("Meses de garantia: ");
                    int garantia = sc.nextInt();
                    catalogo.adicionar(new ProdutoEletronico(codigo, descricao, preco, garantia));
                } else {
                    System.out.print("Validade: ");
                    sc.nextLine(); 
                    String validade = sc.nextLine();
                    catalogo.adicionar(new ProdutoAlimenticio(codigo, descricao, preco, validade));
                }
                System.out.println("Produto cadastrado com sucesso!");
                break;

            case 3:
                System.out.print("Digite o código do produto para desconto: ");
                String codDesc = sc.next();
                System.out.print("Percentual de desconto: ");
                double percentual = sc.nextDouble();
                
                catalogo.buscarPorCodigo(codDesc).calcularDesconto(percentual);
                System.out.println("Desconto aplicado!");
                break;

            case 4:
                System.out.print("Digite o código para venda: ");
                String codVenda = sc.next();
                System.out.print("Quantidade: ");
                int qtd = sc.nextInt();
                
                catalogo.buscarPorCodigo(codVenda).vender(qtd);
                break;

            case 5:
                System.out.print("Digite o código para remoção: ");
                String codRemover = sc.next();
                catalogo.removerPorCodigo(codRemover);
                System.out.println("Operação de remoção concluída.");
                break;

            case 0:
                System.out.println("Encerrando o sistema...");
                break;

            default:
                System.out.println("Opção inválida!");
                break;
        }

    } catch (IllegalArgumentException e) {
        System.out.println("Ação inválida! Motivo: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Ocorreu um erro: " + e.getMessage());
        sc.nextLine(); // Evita loop infinito em caso de erro de input
    }
}
    }
}
// sc.close(); // Fechar apenas aqui, ao sair do loop