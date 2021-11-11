package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.jdbc.CycleDbManager;
import br.com.fiap.to.ProdutoTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO {
	private Connection conexao;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public static List<ProdutoTO> produto;

	public ProdutoDAO() {
		if(produto == null) {
			produto = new ArrayList<ProdutoTO>();

			ProdutoTO cdp = new ProdutoTO();
			cdp.setCodigo(1);
			cdp.setCdEstab(105);
			cdp.setNome("Arroz 5Kg");
			cdp.setTipo((byte) 1);
			cdp.setMarca("Camil");
			cdp.setValidade("20/11/2021");
			cdp.setDataEntrada("15/11/2021");
			cdp.setEstoque(68);
			produto.add(cdp);
			
			cdp = new ProdutoTO();
			cdp.setCodigo(2);
			cdp.setCdEstab(106);
			cdp.setNome("Banana Prata 1Kg");
			cdp.setTipo((byte) 0);
			cdp.setMarca(null);
			cdp.setValidade("08/11/2021");
			cdp.setDataEntrada("05/11/2021");
			cdp.setEstoque(35);
			produto.add(cdp);
			
		}
	}

	public List<ProdutoTO> select() {
		List<ProdutoTO> produtos = new ArrayList<>();
		
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT * FROM t_c4f_produto");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProdutoTO produto = new ProdutoTO(rs.getInt("cd_produto"), rs.getInt("cd_estab"), rs.getString("nm_produto"), rs.getByte("ds_tipo"),
						rs.getString("ds_marca"), rs.getString("dt_validade"), rs.getString("dt_entrada"), rs.getInt("qt_estoque"));
			produtos.add(produto);
			}
			
			pstmt.close();
			conexao.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}
	
	
	public ProdutoTO select(int codigo){
		ProdutoTO produto = null;
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("SELECT * FROM t_c4f_produto where cd_produto = ?");
			pstmt.setInt(1, codigo);
			rs = pstmt.executeQuery();
		while(rs.next()) {
			produto = new ProdutoTO(rs.getInt("cd_produto"), rs.getInt("cd_estab"), rs.getString("nm_produto"), rs.getByte("ds_tipo"),
					rs.getString("ds_marca"), rs.getString("dt_validade"), rs.getString("dt_entrada"), rs.getInt("qt_estoque"));
		}
		
		pstmt.close();
		conexao.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return produto;
	}
	
	public boolean insert(ProdutoTO pd) {
		try {
				conexao = CycleDbManager.conectar();
				pstmt = conexao.prepareStatement("insert into t_c4f_produto values (cd_produto_c4f.nextval, ?, ?, ?, ?, ?, ?, ?);");
				pstmt.setInt(1, pd.getCdEstab());
				pstmt.setString(2, pd.getNome());
				pstmt.setByte(3, pd.getTipo());		
				pstmt.setString(4, pd.getMarca());
				pstmt.setString(5, pd.getValidade());
				pstmt.setString(6, pd.getDataEntrada());
				pstmt.setInt(7, pd.getEstoque());
				pstmt.executeUpdate();
				
				pstmt.close();
				conexao.close();
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void update(ProdutoTO pd) {
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("UPDATE t_c4f_produto SET nm_produto = ?, ds_tipo = ?, ds_marca=?, dt_validade= ?, qt_estoque= ? where cd_produto = ?");
			pstmt.setString(1, pd.getNome());
			pstmt.setByte(2, pd.getTipo());
			pstmt.setString(3, pd.getMarca());
			pstmt.setString(4, pd.getValidade());
			pstmt.setInt(5, pd.getEstoque());
			pstmt.setInt(6, pd.getCodigo());
			pstmt.executeUpdate();
			
			pstmt.close();
			conexao.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int codigo) {
		try {
			conexao = CycleDbManager.conectar();
			pstmt = conexao.prepareStatement("DELETE FROM t_c4f_produto WHERE cd_produto = ?");
			pstmt.setInt(1, codigo);
			pstmt.executeUpdate();
			pstmt.close();
			conexao.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
