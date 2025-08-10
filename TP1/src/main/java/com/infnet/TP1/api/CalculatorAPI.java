package com.infnet.TP1.api;

import com.infnet.TP1.facade.CalculatorFacade;
import com.infnet.TP1.models.CalculationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// Expoe os endpoints
@Controller
@RequestMapping( "/")
public class CalculatorAPI {

    @Autowired
    private CalculatorFacade calculatorFacade;

    @GetMapping("/adicao")
    @ResponseBody
    public double addGet(@RequestParam double number1, @RequestParam double number2) {
        CalculationModel calc = new CalculationModel();
        calc.setNumber1(number1);
        calc.setNumber2(number2);
        return calculatorFacade.adicao(calc);
    }

    @PostMapping("/adicao")
    @ResponseBody
    public double addPost(@RequestBody CalculationModel calc) {
        return calculatorFacade.adicao(calc);
    }

    // SUBTRACT
    @GetMapping("/subtracao")
    @ResponseBody
    public double subtractGet(@RequestParam double number1, @RequestParam double number2) {
        CalculationModel calc = new CalculationModel();
        calc.setNumber1(number1);
        calc.setNumber2(number2);
        return calculatorFacade.subtracao(calc);
    }

    @PostMapping("/subtracao")
    @ResponseBody
    public double subtractPost(@RequestBody CalculationModel calc) {
        return calculatorFacade.subtracao(calc);
    }

    // MULTIPLY
    @GetMapping("/multiplicacao")
    @ResponseBody
    public double multiplyGet(@RequestParam double number1, @RequestParam double number2) {
        CalculationModel calc = new CalculationModel();
        calc.setNumber1(number1);
        calc.setNumber2(number2);
        return calculatorFacade.multiplicacao(calc);
    }

    @PostMapping("/multiplicacao")
    @ResponseBody
    public double multiplyPost(@RequestBody CalculationModel calc) {
        return calculatorFacade.multiplicacao(calc);
    }

    // DIVIDE
    @GetMapping("/divisao")
    @ResponseBody
    public ResponseEntity<?> divideGet(@RequestParam double number1, @RequestParam double number2) {
        try {
            CalculationModel calc = new CalculationModel();
            calc.setNumber1(number1);
            calc.setNumber2(number2);
            double result = calculatorFacade.divisao(calc);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/divisao")
    @ResponseBody
    public ResponseEntity<?> dividePost(@RequestBody CalculationModel calc) {
        try {
            double result = calculatorFacade.divisao(calc);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // POWER
    @GetMapping("/exponenciacao")
    @ResponseBody
    public double powerGet(@RequestParam double number1, @RequestParam double number2) {
        CalculationModel calc = new CalculationModel();
        calc.setNumber1(number1);
        calc.setNumber2(number2);
        return calculatorFacade.exponenciacao(calc);
    }

    @PostMapping("/exponenciacao")
    @ResponseBody
    public double powerPost(@RequestBody CalculationModel calc) {
        return calculatorFacade.exponenciacao(calc);
    }
}
