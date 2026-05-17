/**
 * Classe imutável que representa uma tarefa na Caça ao Tesouro.
 * Garante segurança total no compartilhamento entre múltiplas threads.
 */
public final class Tarefa {
    private final String descricao;

    public Tarefa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
