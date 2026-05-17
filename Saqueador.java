/**
 * Especialidade focada em invasão e recuperação rápida de artefatos.
 */
public class Saqueador extends Explorador {
    private double pontosBase;

    public Saqueador(String nome, Missao missao, double pontosBase) {
        super(nome, "Saqueador", missao);
        this.pontosBase = pontosBase;
    }

    @Override
    public Double call() throws Exception {
        // Simula o tempo de invasão e coleta de baús
        Thread.sleep(500);
        exibirResultado(pontosBase);
        return pontosBase;
    }
}
