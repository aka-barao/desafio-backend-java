import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Principal {

	private static Scanner entradaTeclado;
	private static ClienteDAO dao;
	private static Cliente cliente;
	private static List<Cliente> listaDeClientes;
	
	public static void main(String[] args) {
		
		dao = new ClienteDAO();
		entradaTeclado = new Scanner(System.in);
		
		System.out.println("Selecione a Operação desejada:");
		System.out.println("Digite 1 para: Cadastrar Clientes");
		System.out.println("Digite 2 para: Consultar todos os Clientes Cadastrados");
		System.out.println("Digite 3 para: Consulta Desafio");
		
		int operacaoSelecionada = entradaTeclado.nextInt();
		entradaTeclado.useLocale(Locale.US); // Permite a digitação de números decimais separados por 'Ponto'
		
		switch(operacaoSelecionada) {
			case 1:
				System.out.println("-- Cadastro de Clientes --");
				cadastraCliente();
				break;
			case 2:
				System.out.println("-- Consulta todos os Clientes --");
				listaDeClientes = dao.consultaTodosClientes();
				imprimeListaClientes(listaDeClientes);
				break;
			case 3:
				System.out.println("-- Consulta Desafio --");
				listaDeClientes = dao.consultaTodosClientes();
				consultaDesafio(listaDeClientes);
				imprimeListaClientes(listaDeClientes);
				break;
			default:
				System.out.println("-- Opção Inválida! --");
		}
		
	}

	public static void cadastraCliente() {
		boolean continuaCadastrando = true;
		
		do {
			entradaTeclado.nextLine(); // Consome o caractere \n e evita que ele seja atribuido ao setNome no próximo nextLine.
			
			cliente = new Cliente();
			
			System.out.println("Digite o Nome: ");
			cliente.setNome(entradaTeclado.nextLine());
			
			System.out.println("Digite o CPF/CNPJ (Com acentuação): ");
			cliente.setCpf_cnpj(entradaTeclado.nextLine());
			
			System.out.println("Digite o Saldo (Com centavos): ");
			cliente.setSaldo(entradaTeclado.nextDouble());
			
			System.out.println("O Cliente está Ativo? (Tecle 1 para Sim || 0 para Não): ");
			cliente.setAtivo(entradaTeclado.nextInt());
			
			dao.insereCliente(cliente);
			
			System.out.println("Cadastrar outro cliente? (Tecle 1 para Sim || Outro Número para Não):");
			int cadastraOutro = entradaTeclado.nextInt();
			continuaCadastrando = (cadastraOutro == 1);
			
		} while(continuaCadastrando);
	}
	
	public static void imprimeListaClientes(List<Cliente> listaDeClientes) {
		if(listaDeClientes.isEmpty()) {
			System.out.println("A Lista de Clientes consultados está vazia!");
		} else {
			System.out.println("Total de Clientes:" + listaDeClientes.size() + "\n");
			for(Cliente cliente : listaDeClientes) {
				System.out.println("Identificação:" + cliente.getId());
				System.out.println("Cliente: " + cliente.getNome());
				System.out.println("CPF/CNPJ: " + cliente.getCpf_cnpj());
				System.out.println("Saldo: " + cliente.getSaldo());
				System.out.println("Status: " + cliente.getStatus() + "\n");
			}
		}
	}
	
	public static void consultaDesafio(List<Cliente> listaDeClientes) {
		
		double qtdClientesRestantes = 0;
		double somaDosSaldos = 0;
		
		Iterator<Cliente> iterador = listaDeClientes.iterator();
		
		// Filtra e remove clientes que não atendem ao critério do desafio
		while(iterador.hasNext()) {
			Cliente analisado = iterador.next();
			if(analisado.getSaldo() <= 560 || (analisado.getId() < 1500 || analisado.getId() > 2700)) {
				iterador.remove();
			} else {
				qtdClientesRestantes++;
				somaDosSaldos += analisado.getSaldo();
			}
		}
		
		System.out.println("Tamanho da lista: " + listaDeClientes.size() + "\n");
		
		if(listaDeClientes.isEmpty()) {
			System.out.println("Não há clientes que atendam ao critério do desafio");
		} else {
			double mediaDosSaldos = somaDosSaldos / qtdClientesRestantes;
			System.out.println("Média Final: R$ " + mediaDosSaldos);
			Collections.sort(listaDeClientes, Collections.reverseOrder()); //Por padrão, o metódo 'sort' ordena em ordem crescente. 'reverseOrder' tem efeito contrário.
		}	
		
	}
	
}
