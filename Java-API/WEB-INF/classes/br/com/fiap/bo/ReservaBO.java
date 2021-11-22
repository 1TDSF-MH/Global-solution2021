package br.com.fiap.bo;


import java.util.ArrayList;
import java.util.List;

import br.com.fiap.dao.ReservaDAO;
import br.com.fiap.to.ProdutoTO;
import br.com.fiap.to.UsuarioTO;

public class ReservaBO {
	private ProdutoBO pd = new ProdutoBO();
	private ReservaDAO reserva = new ReservaDAO();
	private UsuarioBO usr = new UsuarioBO();
		
	public void registrarReserva(List<int[]> a) {
		for(int i=1; i < a.size(); i++) {
			reserva.insert(a.get(i)[0], a.get(0)[0], a.get(i)[1]);
		}
	}
	
	public List<ProdutoTO> listarProdutos(int cdAssoc) {
		List<Integer> lista = reserva.selectProdId(cdAssoc);
		List<ProdutoTO> prdt = new ArrayList<>();
		for(int i=0; i < lista.size(); i++) {
			ProdutoTO pd2 = pd.listar(lista.get(i));
			String data = pd2.getValidade();
			data = data.replace(" 00:00:00", "");
			pd2.setValidade(pd.formatarData(data));
			prdt.add(pd2);
		}
		return prdt;
	}
	
	public List<int[]> listarDadosReserva(int cdAssoc){
		List<int[]> lista = reserva.selectDadosProd(cdAssoc);
		return lista;
	}

	public void cancelarReserva(int cdAssoc) {
		reserva.deletar(cdAssoc);
	}
	
	public void atualizarEstoque(int cdAssoc){
		List<int[]> lista = reserva.selectDadosProd(cdAssoc);
		for(int i = 0; i < lista.size(); i++) {
			int estoque = reserva.selectEstoque(lista.get(i)[0]);
			estoque = estoque - lista.get(i)[1];
			if(estoque <= 0) {
				pd.apagar(lista.get(i)[0]);
			} else {
				reserva.update(lista.get(i)[0], estoque);
			}
		}
	}
	
	public List<String[]> mostrarDadosResumo(int cdAssoc) {
		List<int[]> lista = reserva.selectDadosProd(cdAssoc);
		List<String[]> dados = new ArrayList<>();
		String[] cdReserva = {Integer.toString(reserva.selectCdReserva(cdAssoc))};
		dados.add(cdReserva);
		for(int i = 0; i < lista.size(); i++) {
			ProdutoTO produto = pd.listar(lista.get(i)[0]);
			UsuarioTO user = usr.listar(produto.getCdEstab());
			String[] info = {user.getNome(), user.getEndereco(), Long.toString(user.getTelefone()), Integer.toString(lista.get(i)[0])};
			dados.add(info);
		}
		return dados;
	}
}
