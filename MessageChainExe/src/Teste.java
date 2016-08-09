import java.util.Scanner;
import java.util.regex.Pattern;

public class Teste {

	public static void main(String[] args) {
		// Vari�veis globais
		Scanner ler = new Scanner(System.in);
		String messagechain;
		String resposta;
		
		do {
			// Pede o Message Chain
			System.out.printf("Informe o Message Chain:\n");
			messagechain = ler.nextLine();
			
			// Mostra o Message Chain na tela e d� seu tamanho
			System.out.println("Message Chain: " + messagechain);
			System.out.println("Tamanho: " + messagechain.length());

			// QUEBRA DA STRING E RECONHECER QUE � UM MESSAGE CHAIN
			if (messagechain.contains(".") == true) {
				System.out.println("� Message Chain!");
				
				// Quebra a vari�vel quando acha . e armazena no array aux
				String[] aux = messagechain.split(Pattern.quote ("."));
				
				// Pega o tamanho da string aux				
				// Imprime a vari�vel aux na tela
				for (int i = 0; i < aux.length; i++) {
					System.out.println("String["+i+"] :"+aux[i]);
				}
				
			} else {
				System.out.println("N�o � Message Chain!");
			}
			
			// Reinicializa a vari�vel para evitar erros
			messagechain = null;
			
			// Pergunta se quer continuar testando
			System.out.println("Outro teste?");
			resposta = ler.nextLine();
			
		} while (resposta != "n");
	}
}