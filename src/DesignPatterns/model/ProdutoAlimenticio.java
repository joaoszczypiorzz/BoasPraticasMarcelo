package DesignPatterns.model;

import DesignPatterns.strategy.EstrategiaDesconto;

public class ProdutoAlimenticio extends Produto {
    private String validade;
    public ProdutoAlimenticio(String c, String d, double p, String v, EstrategiaDesconto s) {
        super(c, d, p, s);
        this.validade = v;
    }
    @Override public String toString() { return "[ALIMENTO] " + super.toString() + " | Validade: " + validade; }
}