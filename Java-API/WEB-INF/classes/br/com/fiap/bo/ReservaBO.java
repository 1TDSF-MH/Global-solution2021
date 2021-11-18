package br.com.fiap.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.dao.ReservaDAO;
import br.com.fiap.to.ProdutoTO;
import br.com.fiap.to.ReservaTO;
import br.com.fiap.bo.ProdutoBO;

public class ReservaBO {
	private ProdutoBO pd = new ProdutoBO();
	private ReservaDAO reserva = new ReservaDAO();
	
	public void mostrarValores(ArrayList<int[]> a) {
		for(int i = 0; i< a.size(); i++) {
			int[] b = a.get(i);
			System.out.println("id: "+b[0]+" | valor: "+b[1]);
		}
	}
	
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
	
	public void atualizarEstoque(int cdProd, int qtReserva){
		int estoque = reserva.selectEstoque(cdProd);
		estoque = estoque - qtReserva;
		if(estoque <= 0) {
			pd.apagar(cdProd);
		} else {
			reserva.update(cdProd, estoque);
		}
	}
}
