package com.infnet.applicationexception.service;

import com.infnet.applicationexception.model.FinanceiroModel;
import com.infnet.applicationexception.exception.ValorZeroException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinanceiroService {
    // Cria a lista que armazena os lançamentos em memória
    private final List<FinanceiroModel> armazenados = new ArrayList<>();
    // Cria id de forma incremental
    private Long proximoId = 1L;

    // Salva os lançamentos em memória
    public FinanceiroModel salvar(FinanceiroModel financeiro) {
        // Verifica se é um valor válido, não nulo e diferente de 0
        validarValores(financeiro);
        financeiro.setId(proximoId++);
        armazenados.add(financeiro);
        return financeiro;
    }

    public List<FinanceiroModel> listarTodos() {
        return new ArrayList<>(armazenados);
    }

    // Não pode ser null e não pode ser == 0, retorna erro excesão personalizada
    private void validarValores(FinanceiroModel financeiro) {
        if (financeiro.getValor() == null || financeiro.getValor().doubleValue() == 0) {
            throw new ValorZeroException("O valor do lançamento não pode ser 0 ou null", financeiro.getLancamento());
        }
    }
}
