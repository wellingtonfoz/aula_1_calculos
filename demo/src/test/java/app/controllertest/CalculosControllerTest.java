package app.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import app.controller.CalculosController;
import app.entity.Entrada;
import app.entity.Resultado;
import app.repository.CalculosRepository;

@SpringBootTest
public class CalculosControllerTest {
	
	@Autowired
	CalculosController calculosController;
	
	@MockBean //TO INDICANDO QUE VAI ROLAR UM MOCK/FIXAÇÃO DE DADOS
	CalculosRepository calculosRepository;
	
	
	@BeforeEach
	void setup() {
	
		//MOCAR O SAVE
		Resultado result = new Resultado();
		result.setMedia(4);
		result.setSoma(12);
		
		//MOCAR O FINDALL
		List<Resultado> lista = new ArrayList<>();
		lista.add(new Resultado(1, 12, 4));
		lista.add(new Resultado(2, 12, 4));
		
		Mockito.when(this.calculosRepository.save(Mockito.any())).thenReturn(result);
		Mockito.when(this.calculosRepository.findAll()).thenReturn(lista);
		
	}
	
	
	@Test //JUNIT
	@DisplayName("INTEGRAÇÃO - soma que dá 10")
	void cenario01() {
		
		List<Integer> lista = new ArrayList<>();
		lista.add(4);
		lista.add(4);
		lista.add(4);
		
		Entrada entrada = new Entrada();
		entrada.setLista(lista);
		
		ResponseEntity<Resultado> retorno = this.calculosController.calcular(entrada);
		Resultado resultado = retorno.getBody();
		HttpStatusCode status = retorno.getStatusCode();
		
		assertEquals(12, resultado.getSoma());
		assertEquals(4, resultado.getMedia());
		assertEquals(HttpStatus.OK, status);
	}
	
	@Test //JUNIT
	@DisplayName("INTEGRAÇÃO - requisição q dá bad request")
	void cenario02() {
		
		List<Integer> lista = new ArrayList<>();
		lista.add(4);
		lista.add(4);
		lista.add(null);
		
		Entrada entrada = new Entrada();
		entrada.setLista(lista);
		
		ResponseEntity<Resultado> retorno = this.calculosController.calcular(entrada);
		Resultado resultado = retorno.getBody();
		HttpStatusCode status = retorno.getStatusCode();
		
		//assertEquals(12, resultado.getSoma());
		//assertEquals(4, resultado.getMedia());
		assertEquals(HttpStatus.BAD_REQUEST, status);
	}
	
	
	@Test
	@DisplayName("INTEGRAÇÃO - mock do repository.. .retorno de 2 objetos")
	void cenario03(){
		ResponseEntity<List<Resultado>> retorno = this.calculosController.findAll();
		List<Resultado> lista = retorno.getBody();
		
		assertEquals(2, lista.size());
	}

}
