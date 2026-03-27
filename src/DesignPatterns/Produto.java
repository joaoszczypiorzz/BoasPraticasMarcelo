public abstract class Produto implements Vendavel {
    private String codigo;
    private String descricao;
    private double preco;

    // O CORAÇÃO DO STRATEGY: Composição em vez de Herança
    protected EstrategiaDesconto estrategiaDesconto;

    public Produto(String codigo, String descricao, double preco, EstrategiaDesconto estrategia) {
        // ... (suas validações de preço)
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.estrategiaDesconto = estrategia; // Definimos a estratégia no nascimento
    }

    // Método para mudar a estratégia a qualquer momento (Flexibilidade!)
    public void setEstrategiaDesconto(EstrategiaDesconto novaEstrategia) {
        this.estrategiaDesconto = novaEstrategia;
    }

    // Agora o cálculo apenas DELEGA para a estratégia
    public void aplicarDesconto(double percentual) {
        this.preco = estrategiaDesconto.aplicar(this.preco, percentual);
    }

    // ... (restante do seu código: vender, calcularValorTotal, etc)
}