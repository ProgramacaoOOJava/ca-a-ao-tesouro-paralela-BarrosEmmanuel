/**
 * Explorador rápido que executa tarefas com alta velocidade e eficiência.
 * Implementa Runnable para execução em thread separada.
 */
public class ExploradorRapido extends Explorador implements Runnable {
    
    // Construtor do explorador rápido.
    public ExploradorRapido(String nome, int prioridade, String tarefa) {
        super(nome, "Rápido", prioridade, tarefa);
    }
    
    /**
     * Implementação específica da execução de tarefa para exploradores rápidos.
     * @throws TarefaInvalidaException Se a tarefa for nula ou vazia
     */
    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        // Valida se a tarefa é válida
        if (getTarefa() == null || getTarefa().trim().isEmpty()) {
            throw new TarefaInvalidaException("Tarefa inválida para " + getNome());
        }
        exibirStatus();
        try {
            // TiManu voa pelo mapa para garantir a vitória absoluta!
            Thread.sleep(100);
            System.out.println("-> [VITÓRIA] O líder " + getNome() + " desbravou o mapa 10x10 e pegou o tesouro!");
        } catch (InterruptedException e) {
            System.out.println(getNome() + " foi interrompido.");
        }
    }
    
    /**
     * Método run() executado quando a thread é iniciada.
     * Trata exceções e chama executarTarefa().
     */
    @Override
    public void run() {
        try {
            executarTarefa();
        } catch (TarefaInvalidaException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
