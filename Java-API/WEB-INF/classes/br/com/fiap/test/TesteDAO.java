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
	ResultSet rs = null;

	public List<ProdutoTO> select() {
		List<ProdutoTO> produtos = new ArrayList<>();

		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT * FROM t_c4f_produto");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProdutoTO produto = new ProdutoTO(rs.getInt("cd_produto"), rs.getInt("cd_estab"),
						rs.getString("nm_produto"), rs.getByte("ds_tipo"), rs.getString("ds_marca"),
						rs.getString("dt_validade"), rs.getString("dt_entrada"), rs.getInt("qt_estoque"));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}

	public boolean verificaSenha(String email, String senha) {
		String[] dados = {email, ""};
		

		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT ds_senha FROM t_c4f_associacao WHERE ds_email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dados[1] = rs.getString("ds_senha");
			}
			pstmt.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (dados[1].equals(senha)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int retornaCodigoUsr(String email) {
		int codigo = 0;
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT cd_estab FROM t_c4f_estabelecimento WHERE ds_email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				codigo = rs.getInt("cd_estab");
			}
			pstmt.close();
			conexao.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return codigo;
		
	}
	
	public void deletar(int codigo) {
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("DELETE FROM t_c4f_reserva WHERE cd_assoc = ?");
			pstmt.setInt(1, codigo);
			pstmt.executeUpdate();
			pstmt.close();
			conexao.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
