package DesignPatterns;
import DesignPatterns.factory.ProdutoFactory;
import DesignPatterns.model.Produto;
import DesignPatterns.repository.Catalogo;

import java.util.Scanner;

public class LojaApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // SINGLETON: Não usamos 'new'
        Catalogo<Produto> catalogo = Catalogo.getInstance();

        int opcao = -1;
        while (opcao != 0) {
            System.out.print("\n1-Listar, 2-Cadastrar, 3-Desconto, 0-Sair: ");
            opcao = sc.nextInt();

            if (opcao == 1) catalogo.listarProdutos();

            if (opcao == 2) {
                System.out.print("Tipo (E/A), Código, Descrição, Preço, Info Extra: ");
                // FACTORY: Delegamos a criação
                Produto p = ProdutoFactory.criar(sc.next(), sc.next(), sc.next(), sc.nextDouble(), sc.next());
                catalogo.adicionar(p);
            }

            if (opcao == 3) {
                System.out.print("Código e % Desconto: ");
                // STRATEGY: O produto sabe como se descontar via estratégia
                catalogo.buscarPorCodigo(sc.next()).aplicarDesconto(sc.nextDouble());
            }
        }
    }
}