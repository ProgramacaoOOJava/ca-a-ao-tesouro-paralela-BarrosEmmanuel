/**
 * Explorador cuidadoso que aguarda pacientemente sua permissão no semáforo.
 */
public class ExploradorCuidadoso extends Explorador implements Runnable {
    
    public ExploradorCuidadoso(String nome, int prioridade, Tarefa tarefa, java.util.concurrent.Semaphore semaforo) {
        super(nome, "Cuidadoso", prioridade, tarefa, semaforo);
    }
    
    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        if (getTarefa() == null || getTarefa().getDescricao().trim().isEmpty()) {
            throw new TarefaInvalidaException("Tarefa inválida para " + getNome());
        }

        try {
            // Se não houver permissão imediata, exibe o status de espera exigido pelo Tutor
            if (getSemaforo().availablePermits() == 0) {
                exibirStatus(getNome() + " aguardando permissão...");
            }
            
            // Bloqueia até o semáforo abrir uma vaga
            getSemaforo().acquire();
            exibirStatus("iniciou tarefa: " + getTarefa().getDescricao());
            
            // Execução cautelosa dentro da região protegida
            Thread.sleep(1500);
            
            System.out.println("-> " + getNome() + " terminou de assegurar o perímetro e recuou.");
        } catch (InterruptedException e) {
            System.out.println(getNome() + " foi interrompido durante a espera.");
        } finally {
            // Libera o recurso para os próximos exploradores
            getSemaforo().release();
        }
    }
    
    @Override
    public void run() {
        try {
            executarTarefa();
        } catch (TarefaInvalidaException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
