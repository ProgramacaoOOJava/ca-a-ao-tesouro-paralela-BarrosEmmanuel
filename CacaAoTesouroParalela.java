import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ForkJoinPool;

/**
 * Classe principal atualizada para o Nível Mestre.
 * Coordena o pool de threads e executa a consolidação Fork/Join.
 */
public class CacaAoTesouroParalela {
    
    public static void main(String[] args) {
        System.out.println("=== CAÇA AO TESOURO PARALELA - NÍVEL MESTRE ===");
        System.out.println("Estratégia Avançada: ExecutorService, Callable/Future e Fork/Join\n");
        
        // 1. Cadastrando as Missões Imutáveis
        Missao m1 = new Missao("Mapear cavernas profundas", "Quadrante Alfa", "Alta");
        Missao m2 = new Missao("Decifrar glifos antigos", "Templo do Sol", "Média");
        Missao m3 = new Missao("Recuperar artefatos de ouro", "Ruínas Secretas", "Alta");
        Missao m4 = new Missao("Escanear rotas de fuga", "Costa Leste", "Baixa");

        // 2. Cadastrando nossa equipe com suas respectivas pontuações base
        List<Explorador> exercito = new ArrayList<>();
        exercito.add(new Rastreador("TiManu", m1, 300.0)); // Vossa Graça liderando o placar
        exercito.add(new Rastreador("Julia", m2, 150.0));
        exercito.add(new Saqueador("Izaccar", m3, 200.0));
        exercito.add(new Saqueador("Droodle", m4, 100.0));

        // 3. Criando o ExecutorService para gerenciar o pool de threads de forma escalável
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<Double>> resultadosFutures = new ArrayList<>();

        System.out.println("=== EXECUTANDO MISSÕES COM EXECUTOR SERVICE ===");
        
        // Submetendo os exploradores ao pool e capturando os contratos Future
        for (Explorador explorador : exercito) {
            resultadosFutures.add(executor.submit(explorador));
        }

        // Lista que guardará os pontos brutos coletados para o Fork/Join
        List<Double> listaPontos = new ArrayList<>();

        // Coletando as respostas dos contratos assim que terminarem
        for (Future<Double> future : resultadosFutures) {
            try {
                listaPontos.add(future.get()); // Bloqueia com segurança até o retorno do Callable
            } catch (Exception e) {
                System.out.println("Erro ao extrair relatórios da missão.");
            }
        }

        // Desativando o comitê de threads após o uso obrigatório
        executor.shutdown();

        // 4. Aplicando o Fork/Join Framework para consolidar a pontuação final
        System.out.println("\n=== CONSOLIDANDO PONTUAÇÃO COM FORK/JOIN ===");
        ForkJoinPool poolForkJoin = new ForkJoinPool();
        SomaPontos tarefaMestre = new SomaPontos(listaPontos, 0, listaPontos.size());
        
        double somaTotal = poolForkJoin.invoke(tarefaMestre);

        System.out.println("\n--------------------------------------------");
        System.out.println("Soma total dos pontos: " + somaTotal);
        System.out.println("--------------------------------------------");
        System.out.println("Parabéns, General TiManu! O Rei Tutor foi completamente superado!");
    }
}
