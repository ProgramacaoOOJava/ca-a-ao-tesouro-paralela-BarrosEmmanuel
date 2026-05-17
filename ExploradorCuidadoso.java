/**
 * Explorador cuidadoso que executa tarefas com precisão e atenção aos detalhes.
 * Implementa Runnable para execução em thread separada.
 */
public class ExploradorCuidadoso extends Explorador implements Runnable {
    
    // Construtor do explorador cuidadoso.
    public ExploradorCuidadoso(String nome, int prioridade, String tarefa) {
        super(nome, "Cuidadoso", prioridade, tarefa);
    }
    
    /**
     * Implementação específica da execução de tarefa para exploradores cuidadosos.
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
            // Executam com cautela e lentidão, dando tempo para você vencer
            Thread.sleep(1000);
            System.out.println("-> " + getNome() + " mapeou os quadrantes com segurança.");
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
