package br.com.fiap.test;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.bo.OngBO;
import br.com.fiap.bo.ProdutoBO;
import br.com.fiap.bo.ReservaBO;
import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.OngTO;
import br.com.fiap.to.ProdutoTO;
import br.com.fiap.to.UsuarioTO;

public class Teste {
	public static void main(String[] args) {
		OngBO ong = new OngBO();
		ProdutoBO produto = new ProdutoBO();
		ReservaBO reserva = new ReservaBO();
		UsuarioBO usuario = new UsuarioBO();
		
		
		System.out.println("Classe OngBO:\n");
		
			
//		OngTO ongTeste = new OngTO(4048, "60.183.952/0001-54", "Ong teste3", "04599000", "Rua teste, 515", (long)1193695654, "emailt3@email.com", "123256", "Carlos da Silva");
//		System.out.println("Método que cadastra uma Ong no banco de dados(retorna true se o cadastro foi realizado com sucesso):");
//		System.out.println(ong.inserir(ongTeste));
//		System.out.println("\n====================================\n");
 			
		List<OngTO> ongs = ong.listar();
		System.out.println("Método que lista todas as Ongs:");
		for(int i = 0; i<ongs.size(); i++) {
			System.out.println(ongs.get(i).toString());
		}
		System.out.println("\n====================================");
		System.out.println("\nMétodo que busca uma Ong pelo id:\n"+ ong.listar(1010));
		System.out.println("\n====================================\n");
		
		System.out.println("Método que valida o email e senha da Ong(true = email e senha corretos):\n");
		System.out.println("email e senha corretos(email@teste.com, aas123): "+ong.validarEntrada("email@teste.com", "aas123"));
		System.out.println("email incorreto(email@tst.com, aas123): "+ong.validarEntrada("email@tst.com", "aas123"));
		System.out.println("senha incorreta(email@teste.com, xyz987): "+ong.validarEntrada("email@teste.com", "abc123"));
		
		System.out.println("\n====================================\n");
		System.out.println("Método que retorna o código da Ong pelo email cadastrado:\n"+ong.retornaCodigoOng("email@teste.com"));
		
		
		System.out.println("\n====================================\n");
		System.out.println("\nClasse ProdutoBO:");
		
//		System.out.println("Método que cadastra um produto no banco de dados(retorna true se o cadastro foi realizado com sucesso):");
//		ProdutoTO prod = new ProdutoTO(10099, 104, "Chocolate ao leite 90g", (byte)1, "Nestlé", "2021-12-25", "20/12/2021",35);
//		System.out.println(produto.inserir(prod));
	
		System.out.println("\n====================================\n");
		List<ProdutoTO> prd = produto.listar();
		System.out.println("Método que lista todos os produtos:");
		for(int i = 0; i<prd.size(); i++) {
			System.out.println(prd.get(i).toString());
		}
	
		System.out.println("\n====================================\n");
		System.out.println("Método que formata a data que foi recebida do site:");
		System.out.println("2021-12-25 ==> "+produto.formatarData("2021-12-25"));
		
		System.out.println("\n====================================\n");
		System.out.println("Método que busca um produto pelo id:\n"+ produto.listar(10040));
		
//		System.out.println("\n====================================\n");
//		System.out.println("Método que atualiza um produto:");
//		ProdutoTO prod2 = new ProdutoTO(10040, 104, "Chocolate ao leite 100g", (byte)1, "Nestlé", "2021-12-25", "2021-12-20",30);
//		produto.atualizar(prod2);
//		System.out.println(produto.listar(10040));
//		
//		System.out.println("\n====================================\n");
//		System.out.println("método que apaga um produto do banco de dados(depois de apagado será retornado um null):");
//		produto.apagar(10042);
//		System.out.println(produto.listar(10042));
		
		System.out.println("\n====================================\n");
		System.out.println("\nClasse ReservaBO:");
		
		//Método que cadastra uma reserva no banco de dados List de arrays: a primeira posição sempre será o Codigo da Ong que reservou o produto, o restante será: [codigo do produto, quantidade reservada].
//		List<int[]> res1 = new ArrayList<>();
//		int[] a = {1004};
//		res1.add(a);
//		int[] b = {10010, 1};
//		res1.add(b);
//		int[] c = {10024, 2};
//		res1.add(c);
//		int[] d = {10018, 3};
//		res1.add(d);
//		reserva.registrarReserva(res1);
		
		System.out.println("\n====================================\n");
		System.out.println("Método que lista todos os produtos de uma reserva:");
		List<ProdutoTO> lstRes = reserva.listarProdutos(1004);
		for(int i=0; i<lstRes.size(); i++) {
			System.out.println(lstRes.get(i).toString());
		}
		
		System.out.println("\n====================================\n");
		System.out.println("Método que lista o código do protudo e a quantidade de uma reserva:");
		List<int[]> listaRes = reserva.listarDadosReserva(1004);
		
		for(int i = 0; i < listaRes.size(); i++) {
			System.out.println("Cód produto: "+listaRes.get(i)[0]+ " | qtd reservada: "+listaRes.get(i)[1]);
		}
		
		System.out.println("\n====================================\n");
		System.out.println("Método que lista os dados dos produtos reservados:");
		List<String[]> resumoReserva = reserva.mostrarDadosResumo(1004);
		for(int i=1; i<resumoReserva.size(); i++) {
			System.out.println("Código de reserva: "+resumoReserva.get(0)[0]+" | Nome do estabelecimento: "+resumoReserva.get(i)[0]+" | Endereço: "+resumoReserva.get(i)[1]+" | Telefone: "+resumoReserva.get(i)[2]+" | Cód do produto:"+resumoReserva.get(i)[3]);
		}
		
		
//		//Método que atualiza o estoque após uma reserva ser confirmada
//		reserva.atualizarEstoque(1004);
//		
//		//Método que cancela uma reserva de uma Ong, removendo os dados do banco de dados
//		reserva.cancelarReserva(1004);
		
		System.out.println("\n====================================\n");
		System.out.println("\nClasse UsuarioBO:");
		
		System.out.println("\n====================================\n");
		System.out.println("Método que lista todos as empresas cadastradas no banco de dados:");
		List<UsuarioTO> users = usuario.listar();
		for(int i = 0; i< users.size(); i++) {
			System.out.println(users.get(i).toString());
		}
		
		System.out.println("\n====================================\n");
		System.out.println("Método que busca um usuário pelo seu id:");
		System.out.println(usuario.listar(104));
		
		System.out.println("\n====================================\n");
		System.out.println("Método que valida o email e senha da empresa(true = email e senha corretos):\n");
		System.out.println("email e senha corretos(sac@chris.com.br, 123abc): "+ong.validarEntrada("sac@chris.com.br", "123abc"));
		System.out.println("email incorreto(sc@chris.com.br, 123abc): "+ong.validarEntrada("sc@chris.com.br", "123abc"));
		System.out.println("senha incorreta(sac@chris.com.br, xyz987): "+ong.validarEntrada("sac@chris.com.br", "xyz987"));
		
		System.out.println("\n====================================\n");
		System.out.println("Método que retorna o id do usuário pelo seu email: ");
		System.out.println(usuario.retornaCodigoUser("sac@chris.com.br"));
		
//		System.out.println("\n====================================\n");
//		System.out.println("Método que cadastra uma empresa no banco de dados: ");
//		UsuarioTO usr1 = new UsuarioTO(113, "58.053.900/0001-01", "Empresa Teste3", "04422000", "Travessa teste, 55",(long)1191564445,"teste_emp3@teste.com", "aaa121", (byte)1);
//		usuario.inserir(usr1);
//		System.out.println(usuario.listar(113));
	}
}
