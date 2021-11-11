package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;
import br.com.fiap.to.OngTO;

public class OngDAO {
	public static List<OngTO> ongs;

	public OngDAO() {
		if (ongs == null) {
			ongs = new ArrayList<OngTO>();

			OngTO ong = new OngTO();

			ong.setCodigo(1011);
			ong.setCnpj("26.695.542/0002-94");
			ong.setNome("Cidade Três");
			ong.setCep("12545-652");
			ong.setEndereco("Rua Vergueiro, 118");
			ong.setTelefone((long) 11);
			ong.setEmail("contato@c3.com");
			ong.setSenha("cidad3");
			ong.setNmResponsavel("Caio Lizeo");
			ongs.add(ong);
		}
	}
	public List<OngTO> select(){
		return ongs;
	}
	
	public OngTO select(int codigo) {

		for (OngTO o: ongs) {
			if(o.getCodigo() == codigo) {
				return o;
			}
		}
		return null;
	}
	
	public boolean insert(OngTO ong) {
		return ongs.add(ong);
	}
	
	public void update(OngTO ong) {
		OngTO o = select(ong.getCodigo());
		o.setCodigo(ong.getCodigo());
	}
	
	public void delete(int id) {
		ongs.remove(select(id));
	}
	
}
