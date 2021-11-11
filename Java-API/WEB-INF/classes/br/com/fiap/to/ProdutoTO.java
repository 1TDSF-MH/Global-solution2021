package br.com.fiap.to;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProdutoTO {

	private int codigo;
	private int cdEstab;
	private String nome;
	private byte tipo;
	private String marca;
	private String validade;
	private String dataEntrada;
	private int estoque;
	
	public ProdutoTO() {
		
	}

	public ProdutoTO(int codigo, int cdEstab, String nome, byte tipo, String marca, String validade,
			String dataEntrada, int estoque) {
		
		this.codigo = codigo;
		this.cdEstab = cdEstab;
		this.nome = nome;
		this.tipo = tipo;
		this.marca = marca;
		this.validade = validade;
		this.dataEntrada = dataEntrada;
		this.estoque = estoque;
	}

	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCdEstab() {
		return cdEstab;
	}

	public void setCdEstab(int cdEstab) {
		this.cdEstab = cdEstab;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte getTipo() {
		return tipo;
	}

	public void setTipo(byte tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	@Override
	public String toString() {
		return "ProdutoTO [codigo=" + codigo + ", cod estab=" + cdEstab + ", nome=" + nome + ", tipo=" + tipo + ", marca="
				+ marca + ", validade=" + validade + ", dataEntrada=" + dataEntrada + ", estoque=" + estoque + "]";
	}
	
}
