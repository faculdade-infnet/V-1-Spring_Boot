package com.infnet.applicationexception.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

// Representação dos dados no sistema
@Setter
@Getter
public class FinanceiroModel {
    private Long id;
    private BigDecimal valor;
    private String lancamento;
}