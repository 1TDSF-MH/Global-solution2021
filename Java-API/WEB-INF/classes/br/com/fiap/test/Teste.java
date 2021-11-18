package br.com.fiap.test;

import java.util.List;

import br.com.fiap.bo.ProdutoBO;
import br.com.fiap.bo.ReservaBO;
import br.com.fiap.to.ProdutoTO;

public class Teste {
	public static void main(String[] args) {
	TesteDAO teste = new TesteDAO();
	ProdutoBO testeBO = new ProdutoBO();
	ReservaBO reserva = new ReservaBO();
	
//	List<ProdutoTO> produtos = teste.select();
//	System.out.println(produtos.toString());
//	System.out.println(teste.verificaSenha("email@teste.com", "aas123"));
//	System.out.println(teste.retornaCodigoUsr("bruna@brunaalimentos.com"));
	System.out.println(testeBO.formatarData("2021-11-18"));
		
//	String data = "2021-11-21";
//	String[] dataSplit = data.split("-");
//	data = dataSplit[2]+"/"+dataSplit[1]+"/"+(Integer.parseInt(dataSplit[0])-2000);
//	System.out.println(data);
	reserva.atualizarEstoque(10014, 3);
	}
}
