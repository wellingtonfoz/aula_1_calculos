package app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import app.entity.Entrada;
import app.entity.Resultado;

@Service
public class CalculosService {
	
	
	public Resultado calcular(Entrada entrada) {
		
		Resultado resultado = new Resultado();
		int soma = this.somar(  entrada.getLista() );
		System.out.println("A soma deu "+ soma);
		
		resultado.setSoma(soma);
		
		double media = this.media(entrada.getLista());
		resultado.setMedia(media);
		
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
		return this.somar(lista) / lista.size();
	}

}
