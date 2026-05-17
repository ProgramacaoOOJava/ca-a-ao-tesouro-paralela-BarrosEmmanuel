import java.util.ArrayList;

/**
 * Classe principal que simula a Caça ao Tesouro Paralela.
 * Demonstra o uso de threads, prioridades, tipos (user e daemon) e exceções personalizadas
 * através de diferentes tipos de exploradores.
 */
public class CacaAoTesouroParalela {
    
    public static void main(String[] args) {
        System.out.println("=== CAÇA AO TESOURO PARALELA ===");
        System.out.println("Exploradores em ação: threads, prioridades e exceções em Java\n");
        
        // Lista para gerenciar as threads dos exploradores
        ArrayList<Thread> listaThreads = new ArrayList<>();
        
        // Criando exploradores rápidos
        ExploradorRapido tiManu = new ExploradorRapido("TiManu", Thread.MAX_PRIORITY, "Vasculhando a caverna principal!");
        
        // Criando um explorador com tarefa inválida para demonstrar exceção
        // Julia sabotará o próprio avanço com uma tarefa vazia
        ExploradorRapido julia = new ExploradorRapido("Julia", Thread.MAX_PRIORITY, "");
        
        // Criando exploradores cuidadosos
        ExploradorCuidadoso izaccar = new ExploradorCuidadoso("Izaccar", Thread.MIN_PRIORITY, "Mapeando a floresta com cautela.");
        ExploradorCuidadoso droodle = new ExploradorCuidadoso("Droodle", Thread.MIN_PRIORITY, "Rastreando pistas na praia perigosa.");
        
        // Encapsulando exploradores em threads
        Thread t1 = new Thread(tiManu, "Thread-TiManu");
        Thread t2 = new Thread(julia, "Thread-Julia");
        Thread t3 = new Thread(izaccar, "Thread-Izaccar");
        Thread t4 = new Thread(droodle, "Thread-Droodle");
        
        // Configurando prioridades das threads
        t1.setPriority(Thread.MAX_PRIORITY); // 10
        t2.setPriority(Thread.MAX_PRIORITY); // 10
        t3.setPriority(Thread.MIN_PRIORITY); // 1
        t4.setPriority(Thread.MIN_PRIORITY); // 1
        
        // Configurando algumas threads como daemon (tarefas secundárias)
        t3.setDaemon(true);
        t4.setDaemon(true);
        
        // Adicionando threads à lista
        listaThreads.add(t1);
        listaThreads.add(t2);
        listaThreads.add(t3);
        listaThreads.add(t4);
        
        // Exibindo informações das threads antes da execução
        System.out.println("\n=== INFORMAÇÕES DAS THREADS ===");
        for (Thread t : listaThreads) {
            System.out.println("Nome: " + t.getName() + " | Prioridade: " + t.getPriority() + " | Daemon: " + t.isDaemon() + " | Estado: " + t.getState());
        }
        
        // Iniciando todas as threads
        System.out.println("\n=== INICIANDO EXPLORAÇÃO ===");
        for (Thread t : listaThreads) {
            t.start();
        }
        
        // Aguardando conclusão das threads não-daemon (TiManu e Julia)
        System.out.println("\n=== AGUARDANDO CONCLUSÃO DOS EXPLORADORES ===");
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("A execução principal foi interrompida.");
        }
        
        // Verificando estado final das threads principais
        System.out.println("\nEstado final do Líder TiManu: " + t1.getState());
        
        System.out.println("\n=== CAÇA AO TESOURO PARALELA FINALIZADA ===");
        System.out.println("Todos os exploradores principais completaram suas missões!");
    }
}
