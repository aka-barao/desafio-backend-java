import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
	
	private PreparedStatement instrucao;
	private List<Cliente> listaDeClientes;
	
	public ClienteDAO() { // Avisa o usuário caso o programa consiga se conectar sem problemas ao BD;
		try {
			Connection testaConexao = new ConnectionFactory().getConnection();
			System.out.println("Conexão estabelecida com sucesso!");
			testaConexao.close();
		} catch(SQLException e) {
			System.out.println("Erro ao conectar com o Banco de Dados!");
		}
		
	}
	
	public void insereCliente(Cliente cliente) {
		String codigoSQL = "INSERT INTO tb_customer_account(cpf_cnpj, nm_customer, is_active, vl_total) VALUES (?,?,?,?)";
		
		// try-with-resources || Conexão será aberta novamente dentro do "try" e fechada automaticamente ao final dele.
		try(Connection conexao = new ConnectionFactory().getConnection()) {
			instrucao = conexao.prepareStatement(codigoSQL);
			
			instrucao.setString(1, cliente.getCpf_cnpj());
			instrucao.setString(2, cliente.getNome());
			instrucao.setInt(3, cliente.isAtivo());
			instrucao.setDouble(4, cliente.getSaldo());
			
			instrucao.execute();
			instrucao.close();
			
		} catch (SQLException e) {
			System.out.println("Erro na operação de Cadastro!");
			throw new RuntimeException(e);
		}
	}
	
	public List<Cliente> consultaTodosClientes() {
		String codigoSQL = "SELECT * FROM tb_customer_account";
		this.listaDeClientes = new ArrayList<Cliente>();
		
		try(Connection conexao = new ConnectionFactory().getConnection()) {
			instrucao = conexao.prepareStatement(codigoSQL);
			ResultSet resultado = instrucao.executeQuery();
			
			while(resultado.next()) {
				Cliente cliente = new Cliente();
				cliente.setCpf_cnpj(resultado.getString("cpf_cnpj"));
				cliente.setNome(resultado.getString("nm_customer"));
				cliente.setSaldo(resultado.getDouble("vl_total"));
				cliente.setId(resultado.getInt("id_customer"));
				cliente.setAtivo(resultado.getInt("is_active"));
				this.listaDeClientes.add(cliente);
			}
			
			resultado.close();
			instrucao.close();
			conexao.close();
			
			return listaDeClientes;
		} catch (SQLException e) {
			System.out.println("Erro na operação de Consulta!");
			throw new RuntimeException(e);
		}	
	}


}
