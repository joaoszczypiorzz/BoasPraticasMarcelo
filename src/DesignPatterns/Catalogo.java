package DesignPatterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Catalogo transformado em Singleton para gerenciar a lista única de produtos.
 */
public class Catalogo<T extends Produto> {

    // 1. A instância única (estática)
    // Usamos o tipo base 'Produto' para permitir misturar os itens da sua lista
    private static Catalogo<Produto> instancia;

    private List<T> lista;

    // 2. Construtor PRIVADO
    // Agora ele inicializa a lista com os dados que você forneceu
    private Catalogo() {
        this.lista = new ArrayList<>();
        popularDadosIniciais(); // Método auxiliar para carregar sua lista
    }

    // 3. Método para obter a instância única
    public static synchronized Catalogo<Produto> getInstance() {
        if (instancia == null) {
            instancia = new Catalogo<>();
        }
        return instancia;
    }

    // Método para popular a lista conforme sua solicitação
    @SuppressWarnings("unchecked")
    private void popularDadosIniciais() {
        List<Produto> iniciais = Arrays.asList(
                new ProdutoEletronico("E001", "Notebook Gamer", 5000.00, 24),
                new ProdutoEletronico("E002", "Smartphone X", 2500.00, 12),
                new ProdutoAlimenticio("A001", "Arroz 5kg", 25.90, "31/12/2025"),
                new ProdutoAlimenticio("A002", "Leite 1L", 4.50, "15/01/2025")
                // ... adicione os outros aqui
        );
        // Adicionamos todos à nossa lista interna
        this.lista.addAll((List<T>) iniciais);
    }

    public void adicionar(T produto) {
        lista.add(produto);
    }

    /**
     * Versão Melhorada: Usando Optional para evitar erros de busca
     */
    public T buscarPorCodigo(String codigo) {
        return lista.stream()
                .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Produto " + codigo + " não encontrado!"));
    }

    public void removerPorCodigo(String codigo) {
        T produto = buscarPorCodigo(codigo); // Se não achar, já lança a exceção do método acima
        lista.remove(produto);
        System.out.println("Produto " + codigo + " removido com sucesso!");
    }

    public boolean verificadorCodigo(String codigo) {
        return lista.stream().anyMatch(p -> p.getCodigo().equalsIgnoreCase(codigo));
    }

    public void listarProdutos() {
        if (lista.isEmpty()) {
            System.out.println("O catálogo está vazio.");
        } else {
            lista.forEach(System.out::println);
        }
    }
}