package br.com.fiap.test;

import br.com.fiap.jdbc.CycleDbManager;
import br.com.fiap.to.ProdutoTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TesteDAO {
	private Connection conexao;
	PreparedStatement pstmt = null;
	
	public List<ProdutoTO> select() {
		List<ProdutoTO> produtos = new ArrayList<>();
		ResultSet rs = null;
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT * FROM t_c4f_produto");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProdutoTO produto = new ProdutoTO(rs.getInt("cd_produto"), rs.getInt("cd_estab"), rs.getString("nm_produto"), rs.getByte("ds_tipo"),
						rs.getString("ds_marca"), rs.getString("dt_validade"), rs.getString("dt_entrada"), rs.getInt("qt_estoque"));
			produtos.add(produto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}	

}
