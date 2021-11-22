package br.com.fiap.bo;

import java.util.List;

import br.com.fiap.dao.ProdutoDAO;
import br.com.fiap.to.ProdutoTO;

public class ProdutoBO {

	private ProdutoDAO cd = null;
	
	public List<ProdutoTO> listar(){
		cd = new ProdutoDAO();
		return cd.select();
	}
	
	public ProdutoTO listar(int codigo){
		cd = new ProdutoDAO();
		return cd.select(codigo);
	}
	
	public boolean inserir(ProdutoTO cdp) {
		cd = new ProdutoDAO();
		cdp.setValidade(formatarData(cdp.getValidade()));
		return cd.insert(cdp);
	}
	
	public String formatarData(String data) {
		String[] dataSplit = data.split("-");
		data = dataSplit[2]+"/"+dataSplit[1]+"/"+(Integer.parseInt(dataSplit[0])-2000);
		return data;
	}
    public void atualizar(ProdutoTO cdp) {
    	cdp.setValidade(formatarData(cdp.getValidade()));
    	cdp.setDataEntrada(formatarData(cdp.getDataEntrada()));
        cd = new ProdutoDAO();
        cd.update(cdp);
    }

    public void apagar(int codigo) {
    	cd = new ProdutoDAO();
    	cd.delete(codigo);
    }
	
}
