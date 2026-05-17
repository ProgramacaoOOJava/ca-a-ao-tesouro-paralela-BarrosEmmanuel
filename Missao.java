/**
 * Classe imutável que representa uma missão na ilha misteriosa.
 */
public final class Missao {
    private final String descricao;
    private final String local;
    private final String dificuldade;

    public Missao(String descricao, String local, String dificuldade) {
        this.descricao = descricao;
        this.local = local;
        this.dificuldade = dificuldade; // CORRIGIDO: Mudado de difficulty para dificuldade
    }

    public String getDescricao() { return descricao; }
    public String getLocal() { return local; }
    public String getDificuldade() { return dificuldade; }
}
