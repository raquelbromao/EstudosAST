package alg;

import java.util.regex.Pattern;

public class acaoPrincipal {
	
	public static final String[] testeErro = {"objeto", ".objeto", "objeto.", "objeto;",
			"objeto.function", "objeto.function;", ";objeto.function", "objeto.;function", "objeto;.function",
			"objeto()", ".objeto()", "objeto.()", "objeto().",
			"objeto().function", "objeto.()function","()objeto.function",
			"objeto.function()","objeto.function().", 
			"a.somadiferente().subdiferente().multdiferente().raizdiferente()",
			"objeto.function().function2", "objeto.function().function2.", "objeto.function().function2.;"};
	
	public static final String[] testeValido = {"objeto.function().function2();",
			"objeto.function().function2().function3();",
			"objeto.function().function2().function3().function4();", 
			"objeto.function().function2().function3().function4().function5();",
			"a.somadiferente().subdiferente().multdiferente().raizdiferente();"};
	
	// String p/ testes de message chains que talvez o algoritmo n�o cubra
	public static final String[] testeExcecoes = {"enemies.get(i).isAlive();", "tower.getType().initialPrice();",
			"tower.getType().initialPrice(f);",
			"tower.getType(i,j).initialPrice(f,g);", "tower.getType().initialPrice().love(f,g);",
			"this.gameMap.getSector(x,y).occupant.add(newTower);",
			"type.getTowerType().getConstructor(ArrayList.class, Map.class, Integer.class, Integer.class);", 
			"BasicEnemy.class.getConstructor(Map.class, Path.class);", 
			"type.getEnemyType().getConstructor(Map.class, Path.class);", 
			"mainMenuScene.getStylesheets().addAll(this.getClass().getResource('style.css').toExternalForm());"};
	
	public static void splitMessageChain (String s) {
		// retira o ";" do final da string		
		s = s.replace(";", " ");
		
		// Quebra a vari�vel quando acha . e armazena a sobra numa posi��o do array aux
		// a().b() -> . � descartando e a() fica em aux[0] e b() em aux[1]
		String[] aux = s.split(Pattern.quote("."));		

		// Pega o tamanho da string aux
		// Imprime a vari�vel aux na tela
		System.out.println("Objeto: " + aux[0]);		
		for (int i = 1; i < aux.length; i++) {
			System.out.println("M�todo[" + i + "]: " + aux[i]);
		}		
	}
	
	public static void verificaMessageChain (String s) {		
		if (s!=null && s.matches("[\\w]+([\\.]+[\\w]+[(]+[\\w]*+[)]){2,}+[;]")) {
			System.out.println("\n� Message Chain para "+s+"\n");
			splitMessageChain(s);
		}
		else if (s!=null && s.matches("[\\w] +	([\\.] + [\\w] + [(] + [\\w]* + ([,] + [\\w])* + [)]) {2,} + [;]")) {
			System.out.println("\n� Message Chain para "+s+"\n");
			splitMessageChain(s);
		} else {
			System.out.println("\nN�o � Message Chain para "+s+"\n");	
		}
	}
	
	public static void testaStrings (String[] s) {
		for (int i = 0; i<s.length; i++) {
			verificaMessageChain(s[i]);
		}
	}

	public static void main(String[] args) {
		testaStrings(testeErro);		
		System.out.println("\n#####################################################\n");		
		testaStrings(testeValido);
		System.out.println("\n#####################################################\n");	
		testaStrings(testeExcecoes);
		
		// Vari�veis globais
		/*Scanner ler = new Scanner(System.in);
		String messagechain = null;
		int resposta = 4;
		//boolean continua = true;

		while (continua) {
			// Pede o Message Chain
			System.out.println("Informe o Message Chain:\n");
			messagechain = ler.nextLine();
			
			if (messagechain.isEmpty() == true) {
				System.out.println("\nMessage Chain vazio! N�o � v�lido!");
			}
			
			// Mostra o Message Chain na tela e d� seu tamanho
			System.out.println("\nString: " + messagechain);
			System.out.println("Tamanho: " + messagechain.length());

			// VALIDA��O DE MESSAGE CHAIN E QUEBRA DELE EM PARTES CASO SEJA
			verificaMessageChain(messagechain);
			
			// Pergunta se quer continuar testando
			System.out.println("\nOutro teste? 0 SIM // 1 N�O");
			resposta = ler.nextInt();
			
			if (resposta == 1) {
				//TERMINA PROGRAMA
				System.out.println("\nTESTE FINALIZADO!");
				//continua = false;
				ler.close();
			} else {
				// Reinicializa a vari�vel para evitar erros
				messagechain = null;
				System.out.println("\n--------------------------------------------\n");
			}
		//}*/
	}
}