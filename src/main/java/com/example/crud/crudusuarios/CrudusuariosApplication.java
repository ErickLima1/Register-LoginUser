package com.example.crud.crudusuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CrudusuariosApplication {
	//[X]Tratar os campo Em branco
	//[x]Tratar os Exception  pra eviar email Repetitivo
	//[x]Adicionar o Cryptografia na senha
	//[x] Fazer o Login -> Usuário Logado Com Sucesso.
	public static void main(String[] args) {
		SpringApplication.run(CrudusuariosApplication.class, args);
	}

}
