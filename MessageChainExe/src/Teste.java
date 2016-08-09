import java.util.Scanner;
import java.util.regex.Pattern;

public class Teste {

	public static void main(String[] args) {
		// Variáveis globais
		Scanner ler = new Scanner(System.in);
		String messagechain;
		String resposta;
		
		do {
			// Pede o Message Chain
			System.out.printf("Informe o Message Chain:\n");
			messagechain = ler.nextLine();
			
			// Mostra o Message Chain na tela e dá seu tamanho
			System.out.println("Message Chain: " + messagechain);
			System.out.println("Tamanho: " + messagechain.length());

			// QUEBRA DA STRING E RECONHECER QUE É UM MESSAGE CHAIN
			if (messagechain.contains(".") == true) {
				System.out.println("É Message Chain!");
				
				// Quebra a variável quando acha . e armazena no array aux
				String[] aux = messagechain.split(Pattern.quote ("."));
				
				// Pega o tamanho da string aux				
				// Imprime a variável aux na tela
				for (int i = 0; i < aux.length; i++) {
					System.out.println("String["+i+"] :"+aux[i]);
				}
				
			} else {
				System.out.println("Não é Message Chain!");
			}
			
			// Reinicializa a variável para evitar erros
			messagechain = null;
			
			// Pergunta se quer continuar testando
			System.out.println("Outro teste?");
			resposta = ler.nextLine();
			
		} while (resposta != "n");
	}
}