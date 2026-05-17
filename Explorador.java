import java.util.concurrent.Semaphore;

/**
 * Classe abstrata atualizada para o Nível Aventureiro.
 * Agora gerencia a disputa por recursos usando Semáforos.
 */
public abstract class Explorador {
    private String nome;
    private String tipo;
    private int prioridade;
    private Tarefa tarefa; // Agora utiliza a classe imutável Tarefa
    private Semaphore semaforo; // Portão de controle de fluxo

    public Explorador(String nome, String tipo, int prioridade, Tarefa tarefa, Semaphore semaforo) {
        this.nome = nome;
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.tarefa = tarefa;
        this.semaforo = semaforo;
    }

    public abstract void executarTarefa() throws TarefaInvalidaException;
    
    public void exibirStatus(String mensagem) {
        System.out.println("Explorador: " + nome + " | Status: " + mensagem);
    }
    
    // Getters para as subclasses e monitoramento
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public int getPrioridade() { return prioridade; }
    public Tarefa getTarefa() { return tarefa; }
    public Semaphore getSemaforo() { return semaforo; }
}
