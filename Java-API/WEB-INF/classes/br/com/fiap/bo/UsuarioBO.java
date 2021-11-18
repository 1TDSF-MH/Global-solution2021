package br.com.fiap.bo;

import java.util.List;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

public class UsuarioBO {

	private UsuarioDAO usr = null;
	
	public List<UsuarioTO> listar(){
		usr = new UsuarioDAO();
		return usr.select();
	}
	
	public UsuarioTO listar(int id){
		usr = new UsuarioDAO();
		return usr.select(id);
	}
	
	public boolean validarEntrada(String email, String senha) {
		usr = new UsuarioDAO();
		String[] dados = usr.retornaDadosLoginUser(email, senha);
		if (dados[1].equals(senha)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int retornaCodigoUser(String email) {
		usr = new UsuarioDAO();
		return usr.buscaCodigoUser(email);
	}
	
	public boolean inserir(UsuarioTO cdp) {
		return new UsuarioDAO().insert(cdp);
	}	
}
