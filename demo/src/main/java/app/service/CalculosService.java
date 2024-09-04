package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Entrada;
import app.entity.Resultado;
import app.repository.CalculosRepository;

@Service
public class CalculosService {
	
	@Autowired
	private CalculosRepository calculosRepository;
	
	
	public List<Resultado> findAll(){
		return this.calculosRepository.findAll();
	}
	
	public Resultado calcular(Entrada entrada) {
		
		Resultado resultado = new Resultado();
		int soma = this.somar(  entrada.getLista() );
		System.out.println("A soma deu "+ soma);
		
		resultado.setSoma(soma);
		
		double media = this.media(entrada.getLista());
		resultado.setMedia(media);
		
		resultado = this.calculosRepository.save(resultado);
		
		return resultado;
		
	}
	
	public int somar(List<Integer> lista) {
		
		int soma = 0;
		
		for(int i=0; i<lista.size(); i++) {
			soma += lista.get(i);
		}
		
		return soma;
		
	}
	
	private double media(List<Integer> lista) {
		return (double) this.somar(lista) / lista.size();
	}

}
