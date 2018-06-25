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
		
		System.out.println("Selecione a Opera��o desejada:");
		System.out.println("Digite 1 para: Cadastrar Clientes");
		System.out.println("Digite 2 para: Consultar todos os Clientes Cadastrados");
		System.out.println("Digite 3 para: Consulta Desafio");
		
		int operacaoSelecionada = entradaTeclado.nextInt();
		entradaTeclado.useLocale(Locale.US); // Permite a digita��o de n�meros decimais separados por 'Ponto'
		
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
				System.out.println("-- Op��o Inv�lida! --");
		}
		
	}

	public static void cadastraCliente() {
		boolean continuaCadastrando = true;
		
		do {
			entradaTeclado.nextLine(); // Consome o caractere \n e evita que ele seja atribuido ao setNome no pr�ximo nextLine.
			
			cliente = new Cliente();
			
			System.out.println("Digite o Nome: ");
			cliente.setNome(entradaTeclado.nextLine());
			
			System.out.println("Digite o CPF/CNPJ (Com acentua��o): ");
			cliente.setCpf_cnpj(entradaTeclado.nextLine());
			
			System.out.println("Digite o Saldo (Com centavos): ");
			cliente.setSaldo(entradaTeclado.nextDouble());
			
			System.out.println("O Cliente est� Ativo? (Tecle 1 para Sim || 0 para N�o): ");
			cliente.setAtivo(entradaTeclado.nextInt());
			
			dao.insereCliente(cliente);
			
			System.out.println("Cadastrar outro cliente? (Tecle 1 para Sim || Outro N�mero para N�o):");
			int cadastraOutro = entradaTeclado.nextInt();
			continuaCadastrando = (cadastraOutro == 1);
			
		} while(continuaCadastrando);
	}
	
	public static void imprimeListaClientes(List<Cliente> listaDeClientes) {
		if(listaDeClientes.isEmpty()) {
			System.out.println("A Lista de Clientes consultados est� vazia!");
		} else {
			System.out.println("Total de Clientes:" + listaDeClientes.size() + "\n");
			for(Cliente cliente : listaDeClientes) {
				System.out.println("Identifica��o:" + cliente.getId());
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
		
		// Filtra e remove clientes que n�o atendem ao crit�rio do desafio
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
			System.out.println("N�o h� clientes que atendam ao crit�rio do desafio");
		} else {
			double mediaDosSaldos = somaDosSaldos / qtdClientesRestantes;
			System.out.println("M�dia Final: R$ " + mediaDosSaldos);
			Collections.sort(listaDeClientes, Collections.reverseOrder()); //Por padr�o, o met�do 'sort' ordena em ordem crescente. 'reverseOrder' tem efeito contr�rio.
		}	
		
	}
	
}
