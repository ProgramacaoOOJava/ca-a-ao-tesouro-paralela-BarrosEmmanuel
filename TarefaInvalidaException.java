/**
 * Exceção personalizada para validar missões dos exploradores.
 */
public class TarefaInvalidaException extends Exception {
    public TarefaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
