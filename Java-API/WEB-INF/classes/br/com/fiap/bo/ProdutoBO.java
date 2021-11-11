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
		return new ProdutoDAO().insert(cdp);
	}
	
    public void atualiza(ProdutoTO cdp) {
        cd = new ProdutoDAO();
        cd.update(cdp);
    }

    public void apagar(int codigo) {
    	cd = new ProdutoDAO();
    	cd.delete(codigo);
    }
	
}
