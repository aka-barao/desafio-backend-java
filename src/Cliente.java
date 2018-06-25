
public class Cliente implements Comparable<Cliente>{
	private String nome;
	private String cpf_cnpj;
	private double saldo;
	private long id;
	private boolean ativo;
	public String status;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf_cnpj() {
		return cpf_cnpj;
	}
	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public int isAtivo() {
		int status = this.ativo ? 1 : 0;
		return status;
	}
	
	public void setAtivo(int ativo) {
		if(ativo == 0) {
			this.ativo = false;
		} else {
			this.ativo = true;
		}
		this.setStatus();
	}	
	
	public void setStatus() {
		if(isAtivo() == 0) {
			this.status = "Inativo";
		} else {
			this.status = "Ativo";
		}
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public int compareTo(Cliente outroCliente) {
		if (this.saldo < outroCliente.saldo) {
            return -1;
          }

          if (this.saldo > outroCliente.saldo) {
            return 1;
          }

          return 0;
	}
	
}
