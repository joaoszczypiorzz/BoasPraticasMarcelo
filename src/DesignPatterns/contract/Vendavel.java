package DesignPatterns.contract;

/**
 * Interface Vendavel - O contrato de comercialização.
 * * Aplicamos aqui o princípio de "Interface Segregation".
 * Qualquer coisa (Produto, Serviço, Assinatura) que implemente isso
 * poderá ser vendida pelo sistema.
 */
public interface Vendavel {

    // Adicionamos esse método ao contrato: se é vendável, tem que ter um preço!
    double getPreco();

    /**
     * Realiza a venda.
     */
    void vender(int quantidade);

    /**
     * "Chave de Ouro": Método Default.
     * Como a fórmula Preço * Qtd é quase sempre a mesma, a interface já entrega
     * a lógica pronta, mas permite que as classes sobrescrevam se precisarem
     * (ex: um produto que ganha desconto progressivo por quantidade).
     */
    default double calcularValorTotal(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        }
        return getPreco() * quantidade;
    }
}