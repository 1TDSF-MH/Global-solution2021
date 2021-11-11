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
	
	public boolean inserir(UsuarioTO cdp) {
		return new UsuarioDAO().insert(cdp);
	}
	
    public void atualiza(UsuarioTO cdp) {
        usr = new UsuarioDAO();
        usr.update(cdp);
    }

    public void apagar(int id) {
    	usr = new UsuarioDAO();
    	usr.delete(id);
    }
	
}
