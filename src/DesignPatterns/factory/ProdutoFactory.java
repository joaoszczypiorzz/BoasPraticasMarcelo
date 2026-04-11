package DesignPatterns.factory;

import DesignPatterns.model.Produto;
import DesignPatterns.model.ProdutoAlimenticio;
import DesignPatterns.model.ProdutoEletronico;
import DesignPatterns.strategy.DescontoAlimenticio;
import DesignPatterns.strategy.DescontoEletronico;

public class ProdutoFactory {
    public static Produto criar(String tipo, String cod, String desc, double preco, String info) {
        if (tipo.equalsIgnoreCase("E")) {
            return new ProdutoEletronico(cod, desc, preco, Integer.parseInt(info), new DescontoEletronico());
        } else if (tipo.equalsIgnoreCase("A")) {
            return new ProdutoAlimenticio(cod, desc, preco, info, new DescontoAlimenticio());
        }
        throw new IllegalArgumentException("Tipo inválido");
    }
}