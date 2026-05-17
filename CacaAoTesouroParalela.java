import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Simulador principal atualizado para o Nível Aventureiro.
 * Coordena a fila de exploradores através de um Semáforo de concorrência.
 */
public class CacaAoTesouroParalela {
    
    public static void main(String[] args) {
        System.out.println("=== CAÇA AO TESOURO PARALELA - NÍVEL AVENTUREIRO ===");
        System.out.println("Coordenação tática: Semáforos e Objetos Imutáveis em Ação\n");
        
        // EXIGÊNCIA REAL: Criar um Semaphore limitando a 2 exploradores simultâneos
        Semaphore portaoDaIlha = new Semaphore(2);
        
        ArrayList<Thread> listaThreads = new ArrayList<>();
        
        // 1. Criação das Tarefas Imutáveis
        Tarefa tTiManu = new Tarefa("Mapear a caverna central");
        Tarefa tJulia = new Tarefa("Explorar ruínas antigas");
        Tarefa tIzaccar = new Tarefa("Desarmar armadilhas de flecha");
        Tarefa tDroodle = new Tarefa("Escavar baú submerso na praia");
        
        // 2. Instanciando nosso exército com o semáforo compartilhado
        ExploradorRapido tiManu = new ExploradorRapido("TiManu", Thread.MAX_PRIORITY, tTiManu, portaoDaIlha);
        ExploradorRapido julia = new ExploradorRapido("Julia", Thread.NORM_PRIORITY, tJulia, portaoDaIlha);
        ExploradorCuidadoso izaccar = new ExploradorCuidadoso("Izaccar", Thread.MIN_PRIORITY, tIzaccar, portaoDaIlha);
        ExploradorCuidadoso droodle = new ExploradorCuidadoso("Droodle", Thread.MIN_PRIORITY, tDroodle, portaoDaIlha);
        
        // 3. Encapsulando em Threads
        Thread th1 = new Thread(tiManu);
        Thread th2 = new Thread(julia);
        Thread th3 = new Thread(izaccar);
        Thread th4 = new Thread(droodle);
        
        // Prioridades configuradas
        th1.setPriority(Thread.MAX_PRIORITY);
        th2.setPriority(Thread.NORM_PRIORITY);
        th3.setPriority(Thread.MIN_PRIORITY);
        th4.setPriority(Thread.MIN_PRIORITY);
        
        // Registrando na lista de controle
        listaThreads.add(th1);
        listaThreads.add(th2);
        listaThreads.add(th3);
        listaThreads.add(th4);
        
        System.out.println("=== INICIANDO EXPLORAÇÃO PARALELA COORDENADA ===");
        
        // Disparando o ataque simultâneo
        for (Thread t : listaThreads) {
            t.start();
        }
        
        // Aguardando todos os guerreiros retornarem para o navio
        for (Thread t : listaThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("A linha do tempo de comando foi cortada!");
            }
        }
        
        System.out.println("\n=== MISSÃO DE COORDENAÇÃO FINALIZADA ===");
        System.out.println("O mapa 10x10 foi esvaziado sem nenhuma colisão de exploradores!");
    }
}
