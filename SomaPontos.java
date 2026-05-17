import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Aplicação do Fork/Join Framework para consolidar a pontuação final.
 * Divide a lista de pontos recursivamente para somar em paralelo.
 */
public class SomaPontos extends RecursiveTask<Double> {
    private final List<Double> pontos;
    private final int inicio;
    private final int fim;
    private static final int LIMITE = 2; // Critério de parada da divisão

    public SomaPontos(List<Double> pontos, int inicio, int fim) {
        this.pontos = pontos;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Override
    protected Double compute() {
        int tamanho = fim - inicio;
        
        // Caso base: Se o pedaço da lista for pequeno o suficiente, soma diretamente
        if (tamanho <= LIMITE) {
            double soma = 0;
            for (int i = inicio; i < fim; i++) {
                soma += pontos.get(i);
            }
            return soma;
        } else {
            // Caso contrário, divide a tarefa ao meio (Fork)
            int meio = inicio + (tamanho / 2);
            SomaPontos tarefaEsquerda = new SomaPontos(pontos, inicio, meio);
            SomaPontos tarefaDireita = new SomaPontos(pontos, meio, fim);
            
            // Executa a primeira metade em paralelo
            tarefaEsquerda.fork();
            
            // Executa a segunda metade e junta os resultados (Join)
            double resultadoDireita = tarefaDireita.compute();
            double resultadoEsquerda = tarefaEsquerda.join();
            
            return resultadoEsquerda + resultadoDireita;
        }
    }
}
