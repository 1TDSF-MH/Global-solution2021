package br.com.fiap.test;

import java.util.List;
import br.com.fiap.to.ProdutoTO;

public class Teste {
	public static void main(String[] args) {
	TesteDAO teste = new TesteDAO();
	
	List<ProdutoTO> produtos = teste.select();
	System.out.println(produtos.toString());

	}

}
