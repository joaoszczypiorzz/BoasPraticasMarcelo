package DesignPatterns.model;

import DesignPatterns.contract.Vendavel;
import DesignPatterns.strategy.EstrategiaDesconto;

public abstract class Produto implements Vendavel {
    private String codigo;
    private String descricao;
    private double preco;
    private EstrategiaDesconto estrategiaDesconto; // STRATEGY

    public Produto(String codigo, String descricao, double preco, EstrategiaDesconto estrategia) {
        if (preco <= 0) throw new IllegalArgumentException("Preço inválido.");
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.estrategiaDesconto = estrategia;
    }

    public void aplicarDesconto(double percentual) {
        double valorDesconto = estrategiaDesconto.calcular(this.preco, percentual);
        this.preco -= valorDesconto;
        System.out.printf("Desconto de R$ %.2f aplicado em %s%n", valorDesconto, descricao);
    }

    // Getters e métodos de Vendavel (vender, calcularValorTotal) permanecem iguais...
    @Override
    public void vender(int quantidade) {
        System.out.println("Vendido: " + quantidade + "x " + descricao + " | Total: R$" + (preco * quantidade));
    }

    public String getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }

    @Override
    public String toString() {
        return String.format("[%s] %s - R$ %.2f", codigo, descricao, preco);
    }
}