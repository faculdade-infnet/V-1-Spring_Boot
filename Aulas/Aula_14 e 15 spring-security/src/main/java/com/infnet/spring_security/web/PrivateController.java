package com.infnet.spring_security.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// É uma rota privada, ou seja, só pode ser acessado se o usuário estiver autenticado e enviar um token JWT válido
// (porque no SecurityConfig, todas as rotas exceto /authenticate exigem autenticação).
@RestController
@RequestMapping("private")
public class PrivateController {

  @GetMapping
  public String getMessage() {
    return "Hello from private API controller";
  }
}
