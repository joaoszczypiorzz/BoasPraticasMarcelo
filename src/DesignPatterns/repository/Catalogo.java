package DesignPatterns.repository;

import DesignPatterns.factory.ProdutoFactory;
import DesignPatterns.model.Produto;

import java.util.*;

public class Catalogo<T extends Produto> {
    private static Catalogo<Produto> instancia;
    private List<T> lista = new ArrayList<>();

    private Catalogo() {
        lista.add((T) ProdutoFactory.criar("E", "E001", "Notebook Gamer", 5000.00, "24"));
        lista.add((T) ProdutoFactory.criar("E", "E002", "Smartphone X", 2500.00, "12"));
        lista.add((T) ProdutoFactory.criar("E", "E003", "Smart TV 50\"", 3200.00, "18"));
        lista.add((T) ProdutoFactory.criar("E", "E004", "Fone Bluetooth", 350.00, "6"));
        lista.add((T) ProdutoFactory.criar("E", "E005", "Mouse Sem Fio", 90.00, "6"));

        // PRODUTOS ALIMENTÍCIOS
        lista.add((T) ProdutoFactory.criar("A", "A001", "Arroz 5kg", 25.90, "31/12/2025"));
        lista.add((T) ProdutoFactory.criar("A", "A002", "Leite 1L", 4.50, "15/01/2025"));
        lista.add((T) ProdutoFactory.criar("A", "A003", "Feijão 1kg", 8.90, "30/11/2025"));
        lista.add((T) ProdutoFactory.criar("A", "A004", "Café 500g", 15.00, "20/10/2025"));
        lista.add((T) ProdutoFactory.criar("A", "A005", "Macarrão 500g", 6.50, "05/09/2025"));

        System.out.println("Catálogo inicializado com sucesso via Factory!");
    }

    public static synchronized Catalogo<Produto> getInstance() {
        if (instancia == null) instancia = new Catalogo<>();
        return instancia;
    }

    public void adicionar(T p) { lista.add(p); }
    public void listarProdutos() { lista.forEach(System.out::println); }
    public T buscarPorCodigo(String cod) {
        return lista.stream().filter(p -> p.getCodigo().equalsIgnoreCase(cod)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Não encontrado"));
    }
    public boolean verificadorCodigo(String cod) {
        return lista.stream().anyMatch(p -> p.getCodigo().equalsIgnoreCase(cod));
    }
}