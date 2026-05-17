/**
 * Especialidade focada em mapeamento e rastreio de perímetros.
 */
public class Rastreador extends Explorador {
    private double pontosBase;

    public Rastreador(String nome, Missao missao, double pontosBase) {
        super(nome, "Rastreador", missao);
        this.pontosBase = pontosBase;
    }

    @Override
    public Double call() throws Exception {
        // Simula o tempo de mapeamento tático no mapa 10x10
        Thread.sleep(500);
        exibirResultado(pontosBase);
        return pontosBase;
    }
}
