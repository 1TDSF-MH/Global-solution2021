
package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.to.UsuarioTO;
import br.com.fiap.jdbc.CycleDbManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
	private Connection conexao;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public UsuarioDAO() {
		
	}

	public List<UsuarioTO> select() {
		List<UsuarioTO> usuarios = new ArrayList<>();

		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT * FROM t_c4f_estabelecimento");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UsuarioTO usuario = new UsuarioTO(rs.getInt("cd_estab"), rs.getString("ds_cnpj"),
						rs.getString("nm_estabelecimento"), rs.getString("ds_cep"), rs.getString("ds_endereco"),
						rs.getLong("nr_telefone"), rs.getString("ds_email"), rs.getString("ds_senha"),
						rs.getByte("ds_tipo"));
				usuarios.add(usuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public UsuarioTO select(int codigo) {
		UsuarioTO user = null;

		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT * FROM t_c4f_estabelecimento WHERE cd_estab = ?");
			pstmt.setInt(1, codigo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new UsuarioTO(rs.getInt("cd_estab"), rs.getString("ds_cnpj"), rs.getString("nm_estabelecimento"),
						rs.getString("ds_cep"), rs.getString("ds_endereco"), rs.getLong("nr_telefone"),
						rs.getString("ds_email"), rs.getString("ds_senha"), rs.getByte("ds_tipo"));
			}
			pstmt.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
	
	public String[] retornaDadosLoginUser(String email, String senha) {
		String[] dados = {email, ""};
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT ds_senha FROM t_c4f_estabelecimento WHERE ds_email = ?");
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
	
	public int buscaCodigoUser(String email) {
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

	public boolean insert(UsuarioTO usr) {
		try {
			conexao = CycleDbManager.conectar();

			pstmt = conexao.prepareStatement("insert into t_c4f_estabelecimento values (cd_estab_c4f.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, usr.getCnpj());
			pstmt.setString(2, usr.getNome());
			pstmt.setString(3, usr.getCep());
			pstmt.setString(4, usr.getEndereco());
			pstmt.setLong(5, usr.getTelefone());
			pstmt.setString(6, usr.getEmail());
			pstmt.setString(7, usr.getSenha());
			pstmt.setByte(8, usr.getTipo());
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
