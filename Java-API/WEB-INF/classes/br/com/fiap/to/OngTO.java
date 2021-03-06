package br.com.fiap.to;

public class OngTO {
	
	public OngTO() {
		
	}
	
	public OngTO(int codigo, String cnpj, String nome, String cep, String endereco, long telefone, String email,
			String senha, String nmResponsavel) {
		super();
		this.codigo = codigo;
		this.cnpj = cnpj;
		this.nome = nome;
		this.cep = cep;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		this.nmResponsavel = nmResponsavel;
	}
	
	private int codigo;
	private String cnpj;
	private String nome;
	private String cep;
	private String endereco;
	private long telefone;
	private String email;
	private String senha;
	private String nmResponsavel;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNmResponsavel() {
		return nmResponsavel;
	}
	public void setNmResponsavel(String nmResponsavel) {
		this.nmResponsavel = nmResponsavel;
	}

	@Override
	public String toString() {
		return "OngTO [codigo=" + codigo + ", cnpj=" + cnpj + ", nome=" + nome + ", cep=" + cep + ", endereco="
				+ endereco + ", telefone=" + telefone + ", email=" + email + ", senha=" + senha + ", nmResponsavel="
				+ nmResponsavel + "]";
	}
	
	
}
