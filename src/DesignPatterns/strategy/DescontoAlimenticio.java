package DesignPatterns.strategy;

public class DescontoAlimenticio implements EstrategiaDesconto {
    public double calcular(double preco, double percentual) {
        if (percentual > 15) percentual = 15;
        return preco * (percentual / 100);
    }
}