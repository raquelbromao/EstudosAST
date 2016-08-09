import java.util.Scanner;
import java.util.regex.Pattern;

public class acaoPrincipal {

	public static void main(String[] args) {
		// Vari·veis globais
		Scanner ler = new Scanner(System.in);
		String messagechain;
		int resposta;
		boolean continua;

		// a().b().c().d().f()

		do {
			// Pede o Message Chain
			System.out.printf("Informe o Message Chain:\n");
			messagechain = ler.nextLine();

			// Mostra o Message Chain na tela e d· seu tamanho
			System.out.println("\nMessage Chain: " + messagechain);
			System.out.println("Tamanho: " + messagechain.length());

			// QUEBRA DA STRING E RECONHECER QUE … UM MESSAGE CHAIN
			if (messagechain.contains(".") == true) {
				System.out.println("\n… Message Chain!");

				// Quebra a vari·vel quando acha . e armazena no array aux
				String[] aux = messagechain.split(Pattern.quote("."));

				// Pega o tamanho da string aux
				// Imprime a vari·vel aux na tela
				for (int i = 0; i < aux.length; i++) {
					System.out.println("String[" + i + "] :" + aux[i]);
				}

			} else {
				System.out.println("\nN„o È Message Chain!");
			}

			// Pergunta se quer continuar testando
			System.out.println("\nOutro teste? 0 SIM // 1 N√O");
			resposta = ler.nextInt();
			
			if (resposta == 1) {
				System.out.println("Teste finalizado!\n");
				continua = false;
			} else {
				continua = true;
			}

			// Reinicializa a vari·vel para evitar erros
			messagechain = null;

		} while (continua);
	}
}