package app.servicetest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.service.CalculosService;

@SpringBootTest //Contexto do Spring nos testes
public class CalculosServiceTest {
	
	@Autowired
	private CalculosService calculosService;
	
	@Test //JUNIT - indicação de método de teste
	@DisplayName("Teste unitário - cenário de soma = 10")
	void cenario01() {
		
		List<Integer> lista = new ArrayList<>();
		lista.add(4);
		lista.add(4);
		lista.add(2);
		
		int soma = calculosService.somar(lista);
		
		Assertions.assertEquals(10, soma); //JUNIT - verificações - teste em si
		
	}
	
	@Test //JUNIT - indicação de método de teste
	@DisplayName("Teste unitário - cenário de erro")
	void cenario02() {
		
		List<Integer> lista = new ArrayList<>();
		lista.add(4);
		lista.add(4);
		lista.add(null);
		
		Assertions.assertThrows(Exception.class, () -> {
			int soma = calculosService.somar(lista);
		} );
		
	}

}
