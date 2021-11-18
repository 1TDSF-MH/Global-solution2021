package br.com.fiap.bo;

import java.util.List;
import br.com.fiap.dao.OngDAO;
import br.com.fiap.dao.UsuarioDAO;
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
	
	public boolean validarEntrada(String email, String senha) {
		ong = new OngDAO();
		
		String[] dados = ong.retornaDadosLoginOng(email, senha);
		if (dados[1].equals(senha)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int retornaCodigoOng(String email) {
		ong = new OngDAO();
		return ong.buscaCodigoOng(email);
	}
	
	public boolean inserir(OngTO o) {
		return new OngDAO().insert(o);
	}

}
