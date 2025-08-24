package com.infnet.applicationexception.model;

public class ErrorModel {
    private String message;
    private String lancamento;

    public ErrorModel(String message, String lancamento) {
        this.message = message;
        this.lancamento = lancamento;
    }

    public String getMessage() {      // Retorna a mensagem de erro
        return message;
    }

    public void setMessage(String message) { // Define uma nova mensagem de erro
        this.message = message;
    }

    public String getLancamento() {       // Retorna o lançamento associado ao erro
        return lancamento;
    }

    public void setLancamento(String lancamento) { // Define um novo lançamento associado
        this.lancamento = lancamento;
    }
}
