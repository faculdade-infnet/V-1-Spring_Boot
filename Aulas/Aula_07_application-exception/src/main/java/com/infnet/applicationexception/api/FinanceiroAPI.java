package com.infnet.applicationexception.api;

import com.infnet.applicationexception.model.FinanceiroModel;
import com.infnet.applicationexception.service.FinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroAPI {
    // Ingeçã ode dependência automática
    @Autowired
    private FinanceiroService financeiroService;

    // Salva, cadastra uma nova operação financeira, através do serviço com a regra de negócio
    @PostMapping
    public FinanceiroModel salvar(@RequestBody FinanceiroModel financeiro) {
        return financeiroService.salvar(financeiro);
    }

    // Obtém a lista de operações financeiras, da regra de negócio
    @GetMapping
    public List<FinanceiroModel> listarTodos() {
        return financeiroService.listarTodos();
    }
}
