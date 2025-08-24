package com.infnet.applicationexception.exception;

// Extende a permissão de lançamento de erros wem tempo de execução
public class ValorZeroException extends RuntimeException {
    private final String lancamento;
    // Construtor recebe(menssagem e lancamento)
    public ValorZeroException(String message, String lancamento) {
        super(message);
        this.lancamento = lancamento;
    }

    // Getter para recuperar o lançamento associado
    public String getLancamento() {
        return lancamento;
    }
}
