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
			System.out.println("� Message Chain para "+s);
		} else {
			System.out.println("N�o � Message Chain para "+s);			
		}
	}

	public static void main(String[] args) {
		// Vari�veis globais
		Scanner ler = new Scanner(System.in);
		String messagechain = null;
		int resposta = 4;
		boolean continua = true;

		while (continua) {
			// Pede o Message Chain
			System.out.println("Informe o Message Chain:\n");
			messagechain = ler.nextLine();
			
			if (messagechain.isEmpty() == true) {
				System.out.println("\nMessage Chain vazio! N�o � v�lido!");
				//TERMINA PROGRAMA
				//return; ????????
			}
			
			// Mostra o Message Chain na tela e d� seu tamanho
			System.out.println("\nMessage Chain: " + messagechain);
			System.out.println("Tamanho: " + messagechain.length());

			// QUEBRA DA STRING E VALIDA��O DE MESSAGE CHAIN
			verificaMessageChain(messagechain);
			
			/*if (messagechain.contains(".") == true) {
				System.out.println("\n� Message Chain!");

				// Quebra a vari�vel quando acha . e armazena a sobra numa posi��o do array aux
				// a().b() -> . � descartando e a() fica em aux[0] e b() em aux[1]
				String[] aux = messagechain.split(Pattern.quote("."));

				// Pega o tamanho da string aux
				// Imprime a vari�vel aux na tela
				for (int i = 0; i < aux.length; i++) {
					System.out.println("String[" + i + "] :" + aux[i]);
				}

			} else {
				System.out.println("\nN�o � Message Chain!");
				//TERMINA PROGRAMA
			}*/

			// Pergunta se quer continuar testando
			System.out.println("\nOutro teste? 0 SIM // 1 N�O");
			resposta = ler.nextInt();
			
			if (resposta == 1) {
				//TERMINA PROGRAMA
				System.out.println("\nTESTE FINALIZADO!");
				continua = false;
			} else {
				System.out.println("\n--------------------------------------------\n");
			}

			// Reinicializa a vari�vel para evitar erros
			messagechain = null;
		} 
	}
}