package DesignPatterns.strategy;

public class DescontoEletronico implements EstrategiaDesconto {
    public double calcular(double preco, double percentual) {
        if (percentual > 30) percentual = 30;
        return preco * (percentual / 100);
    }
}