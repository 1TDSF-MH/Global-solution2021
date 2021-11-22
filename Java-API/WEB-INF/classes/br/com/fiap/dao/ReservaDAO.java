package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.jdbc.CycleDbManager;

import br.com.fiap.to.ReservaTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservaDAO {
	ReservaTO reserva = new ReservaTO();

	private Connection conexao;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void recebeArrayList(List<int[]> e) {
		reserva.setReserva(e);

	}

	public void insert(int cdProd, int cdAssoc, int qtdProd) {
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("insert into t_c4f_reserva values (cd_reserva_c4f.nextval, ?, ?, ?)");

			pstmt.setInt(1, cdProd);
			pstmt.setInt(2, cdAssoc);
			pstmt.setInt(3, qtdProd);
			pstmt.executeUpdate();

			pstmt.close();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Integer> selectProdId(int idAssoc) {
		List<Integer> lista = new ArrayList<>();
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT * FROM t_c4f_reserva where cd_assoc = ?");
			pstmt.setInt(1, idAssoc);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lista.add(rs.getInt("cd_produto"));
			}
			pstmt.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public List<int[]> selectDadosProd(int idAssoc) {
		List<int[]> lista = new ArrayList<>();
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT * FROM t_c4f_reserva where cd_assoc = ?");
			pstmt.setInt(1, idAssoc);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int[] a = { rs.getInt("cd_produto"), rs.getInt("qt_reservada") };
				lista.add(a);
			}
			pstmt.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public int selectEstoque(int cdProduto) {
		int estoque = 0;
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT * FROM t_c4f_produto where cd_produto = ?");
			pstmt.setInt(1, cdProduto);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				estoque = rs.getInt("qt_estoque");
			}
			pstmt.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estoque;
	}
	
	
	public int selectCdReserva(int idAssoc) {
		int cdReserva = 0;
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT * FROM t_c4f_reserva where cd_assoc = ?");
			pstmt.setInt(1, idAssoc);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cdReserva = rs.getInt("cd_reserva");
			}
			pstmt.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cdReserva;
	}
	
	public void update(int cdProd, int qtdProd) {
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("UPDATE t_c4f_produto SET qt_estoque = ? where cd_produto = ?");
			pstmt.setInt(1, qtdProd);
			pstmt.setInt(2, cdProd);

			pstmt.executeUpdate();

			pstmt.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletar(int codigo) {
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("DELETE FROM t_c4f_reserva WHERE cd_assoc = ?");
			pstmt.setInt(1, codigo);
			pstmt.executeUpdate();
			pstmt.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}