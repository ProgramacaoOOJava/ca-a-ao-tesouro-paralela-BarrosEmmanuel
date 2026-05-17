/**
 * Explorador rápido que disputa permissões no semáforo para agir instantaneamente.
 */
public class ExploradorRapido extends Explorador implements Runnable {
    
    public ExploradorRapido(String nome, int prioridade, Tarefa tarefa, java.util.concurrent.Semaphore semaforo) {
        super(nome, "Rápido", prioridade, tarefa, semaforo);
    }
    
    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        if (getTarefa() == null || getTarefa().getDescricao().trim().isEmpty()) {
            throw new TarefaInvalidaException("Tarefa inválida para " + getNome());
        }

        try {
            // Disputa o semáforo de forma coordenada
            getSemaforo().acquire();
            exibirStatus("iniciou tarefa: " + getTarefa().getDescricao());
            
            // Região crítica controlada (TiManu executa com agilidade extrema!)
            Thread.sleep(800); 
            
            System.out.println("-> [SUCESSO] O líder " + getNome() + " extraiu o tesouro do quadrante e liberou o espaço.");
        } catch (InterruptedException e) {
            System.out.println(getNome() + " foi interrompido na caverna.");
        } finally {
            // Sempre devolve a permissão para os outros guerreiros jogarem
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
