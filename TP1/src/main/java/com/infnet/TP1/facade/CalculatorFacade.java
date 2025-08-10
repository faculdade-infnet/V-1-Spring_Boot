package com.infnet.TP1.facade;

import com.infnet.TP1.models.CalculationModel;
import org.springframework.stereotype.Service;

@Service
public class CalculatorFacade {

    public double adicao(CalculationModel calc) {
        return calc.getNumber1() + calc.getNumber2();
    }

    public double subtracao(CalculationModel calc) {
        return calc.getNumber1() - calc.getNumber2();
    }

    public double multiplicacao(CalculationModel calc) {
        return calc.getNumber1() * calc.getNumber2();
    }

    public double divisao(CalculationModel calc) {
        if (calc.getNumber2() == 0) {
            throw new IllegalArgumentException("Divisor não pode ser zero.");
        }
        return calc.getNumber1() / calc.getNumber2();
    }

    public double exponenciacao(CalculationModel calc) {
        return Math.pow(calc.getNumber1(), calc.getNumber2());
    }
}
