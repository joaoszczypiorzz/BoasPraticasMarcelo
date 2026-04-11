package DesignPatterns.model;

import DesignPatterns.strategy.EstrategiaDesconto;

public class ProdutoEletronico extends Produto {
    private int garantia;
    public ProdutoEletronico(String c, String d, double p, int g, EstrategiaDesconto s) {
        super(c, d, p, s);
        this.garantia = g;
    }
    @Override public String toString() { return "[ELETRÔNICO] " + super.toString() + " | Garantia: " + garantia + " meses"; }
}