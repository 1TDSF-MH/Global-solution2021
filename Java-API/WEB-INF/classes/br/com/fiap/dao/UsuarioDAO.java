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
	
	public static List<UsuarioTO> usuario;

	public UsuarioDAO() {
		if(usuario == null) {
			usuario = new ArrayList<UsuarioTO>();
			
			UsuarioTO usr = new UsuarioTO();
			usr.setCnpj("03.043.389/0001-40");
			usr.setNome("Corleone");
			usr.setCep("72006-875");
			usr.setEndereco("Rua Brooklin, 1982");
			usr.setTelefone((long)12);
			usr.setEmail("corleone@tmoc.com");
			usr.setSenha("1chris");
			usr.setTipo((byte)1);
			usuario.add(usr);
		}
	}

	public List<UsuarioTO> select(){
		List<UsuarioTO> usuarios = new ArrayList<>();
		
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT * FROM t_c4f_estabelecimento");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UsuarioTO usuario = new UsuarioTO(rs.getInt("cd_estab"), rs.getString("ds_cnpj"), rs.getString("nm_estabelecimento"), rs.getString("ds_cep"), 
						rs.getString("ds_endereco"), rs.getLong("nr_telefone"), rs.getString("ds_email"), rs.getString("ds_senha"), rs.getByte("ds_tipo"));
				usuarios.add(usuario);
			}
			
		}catch(SQLException e){
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
			while(rs.next()) {
				user = new UsuarioTO(rs.getInt("cd_estab"), rs.getString("ds_cnpj"), rs.getString("nm_estabelecimento"), rs.getString("ds_cep"), 
						rs.getString("ds_endereco"), rs.getLong("nr_telefone"), rs.getString("ds_email"), rs.getString("ds_senha"), rs.getByte("ds_tipo"));
			}
			pstmt.close();
			conexao.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
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
	} catch(SQLException e){
		e.printStackTrace();
		return false;
	}
	return true;
	}
	
	public void update(UsuarioTO usr) {
		UsuarioTO c = select(usr.getCodigo());
		c.setCnpj(usr.getCnpj());
	}
	
	public void delete(int id) {
		usuario.remove(select(id));
	}

}
