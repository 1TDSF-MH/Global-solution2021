package br.com.fiap.bo;

import java.util.List;
import br.com.fiap.dao.OngDAO;
import br.com.fiap.to.OngTO;

public class OngBO {
    private OngDAO ong = null;

    public List<OngTO> listar(){
		ong = new OngDAO();
		return ong.select();
	}
	
	public OngTO listar(int id){
		ong = new OngDAO();
		return ong.select(id);
	}
	
	public boolean inserir(OngTO o) {
		return new OngDAO().insert(o);
	}
	
    public void atualiza(OngTO o) {
        ong = new OngDAO();
        ong.update(o);
    }

    public void apagar(int codigo) {
    	ong = new OngDAO();
    	ong.delete(codigo);
    }

}
