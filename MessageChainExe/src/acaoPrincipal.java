import java.util.Scanner;
import java.util.regex.Pattern;

public class acaoPrincipal {
	
	// objeto.a().b().c().d().f()
	// objeto.a
	// objeto().a()
	// objeto().
	// objeto()
	// objeto.
	// objeto
	
	
	public static void verificaMessageChain (String s) {
		if (s.matches("[\\w][\\.][\\w][()]")) {
			System.out.println("É Message Chain para "+s);
		} else {
			System.out.println("Não é Message Chain para "+s);			
		}
	}

	public static void main(String[] args) {
		// Variáveis globais
		Scanner ler = new Scanner(System.in);
		String messagechain = null;
		int resposta = 4;
		boolean continua = true;

		while (continua) {
			// Pede o Message Chain
			System.out.println("Informe o Message Chain:\n");
			messagechain = ler.nextLine();
			
			if (messagechain.isEmpty() == true) {
				System.out.println("\nMessage Chain vazio! Não é válido!");
				//TERMINA PROGRAMA
				//return; ????????
			}
			
			// Mostra o Message Chain na tela e dá seu tamanho
			System.out.println("\nMessage Chain: " + messagechain);
			System.out.println("Tamanho: " + messagechain.length());

			// QUEBRA DA STRING E VALIDAÇÃO DE MESSAGE CHAIN
			verificaMessageChain(messagechain);
			
			/*if (messagechain.contains(".") == true) {
				System.out.println("\nÉ Message Chain!");

				// Quebra a variável quando acha . e armazena a sobra numa posição do array aux
				// a().b() -> . é descartando e a() fica em aux[0] e b() em aux[1]
				String[] aux = messagechain.split(Pattern.quote("."));

				// Pega o tamanho da string aux
				// Imprime a variável aux na tela
				for (int i = 0; i < aux.length; i++) {
					System.out.println("String[" + i + "] :" + aux[i]);
				}

			} else {
				System.out.println("\nNão é Message Chain!");
				//TERMINA PROGRAMA
			}*/

			// Pergunta se quer continuar testando
			System.out.println("\nOutro teste? 0 SIM // 1 NÃO");
			resposta = ler.nextInt();
			
			if (resposta == 1) {
				//TERMINA PROGRAMA
				System.out.println("\nTESTE FINALIZADO!");
				continua = false;
			} else {
				System.out.println("\n--------------------------------------------\n");
			}

			// Reinicializa a variável para evitar erros
			messagechain = null;
		} 
	}
}