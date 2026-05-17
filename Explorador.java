import java.util.concurrent.Callable;

/**
 * Classe abstrata atualizada para o Nível Mestre.
 * Agora retorna o valor dos pontos obtidos na missão através de Callable.
 */
public abstract class Explorador implements Callable<Double> {
    private String nome;
    private String especialidade;
    private Missao missao;

    public Explorador(String nome, String especialidade, Missao missao) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.missao = missao;
    }

    // O método call() substitui o antigo run() e permite retornar valores!
    @Override
    public abstract Double call() throws Exception;

    public void exibirResultado(double pontos) {
        System.out.println("Explorador: " + nome + " | Especialidade: " + especialidade + " | Missão: " + missao.getDescricao() + " | Pontos: " + pontos);
    }

    public String getNome() { return nome; }
    public String getEspecialidade() { return especialidade; }
    public Missao getMissao() { return missao; }
}
