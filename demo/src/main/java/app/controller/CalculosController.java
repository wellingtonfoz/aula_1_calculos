package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Entrada;
import app.entity.Resultado;
import app.service.CalculosService;

@RestController
@RequestMapping("/api/calculos")
public class CalculosController {
	
	@Autowired
	private CalculosService calculosService;
	
	@PostMapping("/calcular")
	public ResponseEntity<Resultado> calcular(@RequestBody Entrada entrada){
		
		try {
			Resultado resultado = this.calculosService.calcular(entrada);
			return new ResponseEntity<>(resultado,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}	
		
	}
	
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Resultado>> findAll(){
		
		try {
			List<Resultado> lista = this.calculosService.findAll();
			return new ResponseEntity<>(lista,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}	
		
	}
	
	
	

}
