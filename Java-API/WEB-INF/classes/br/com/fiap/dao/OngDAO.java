package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.jdbc.CycleDbManager;
import br.com.fiap.to.OngTO;

public class OngDAO {
	private Connection conexao;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<OngTO> select() {
		List<OngTO> ongs = new ArrayList<>();

		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT * FROM t_c4f_associacao");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OngTO ong = new OngTO(rs.getInt("cd_assoc"), rs.getString("ds_cnpj"), rs.getString("nm_associacao"),
						rs.getString("ds_cep"), rs.getString("ds_endereco"), rs.getLong("nr_telefone"),
						rs.getString("ds_email"), rs.getString("ds_senha"), rs.getString("nm_responsavel"));
				ongs.add(ong);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ongs;
	}

	public OngTO select(int codigo) {

		OngTO ong = null;

		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT * FROM t_c4f_associacao WHERE cd_assoc = ?");
			pstmt.setInt(1, codigo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ong = new OngTO(rs.getInt("cd_assoc"), rs.getString("ds_cnpj"), rs.getString("nm_associacao"),
						rs.getString("ds_cep"), rs.getString("ds_endereco"), rs.getLong("nr_telefone"),
						rs.getString("ds_email"), rs.getString("ds_senha"), rs.getString("nm_responsavel"));
			}
			pstmt.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ong;
	}
	
	public int buscaCodigoOng(String email) {
		int codigo = 0;
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT cd_assoc FROM t_c4f_associacao WHERE ds_email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				codigo = rs.getInt("cd_assoc");
			}
			pstmt.close();
			conexao.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return codigo;
	}

	public String[] retornaDadosLoginOng(String email, String senha) {
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
		return dados;
	}



	public boolean insert(OngTO ong) {

		try {
			conexao = CycleDbManager.conectar();

			pstmt = conexao.prepareStatement(
					"insert into t_c4f_associacao values (cd_assoc_c4f.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, ong.getCnpj());
			pstmt.setString(2, ong.getNome());
			pstmt.setString(3, ong.getCep());
			pstmt.setString(4, ong.getEndereco());
			pstmt.setLong(5, ong.getTelefone());
			pstmt.setString(6, ong.getEmail());
			pstmt.setString(7, ong.getSenha());
			pstmt.setString(8, ong.getNmResponsavel());
			pstmt.executeUpdate();

			pstmt.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}