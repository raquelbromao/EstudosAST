package alg;

import java.util.regex.Pattern;

public class acaoPrincipal {
	
	// String p/ testes que o algoritmo N√O deve cobrir
	public static final String[] testeErro = {"objeto", ".objeto", "objeto.", "objeto;",
			"objeto.function", "objeto.function;", ";objeto.function", "objeto.;function", "objeto;.function",
			"objeto()", ".objeto()", "objeto.()", "objeto().",
			"objeto().function", "objeto.()function","()objeto.function",
			"objeto.function()","objeto.function().", 
			"a.somadiferente().subdiferente().multdiferente().raizdiferente()",
			"objeto.function().function2", "objeto.function().function2.", "objeto.function().function2.;",
			"objeto..function().function2();","objeto.function(()).function2();",
			"objeto.function()).function2();", "objeto.function(().function2();", 
			"this.object.function();", "This.object.function();", "This.objeto.function(()).function2();",
			"This.objeto.function(().function2();", "This.objeto.function()).function2();",
			"objeto.function(param1,param2,param3);", "objeto.function(param1,param2,param3)",
			"objeto.function((param1,param2,param3));", "objeto.function((param1,param2,param3);",
			"objeto.function(param1,param2,param3));", ""};
	
	// String p/ testes que o algoritmo DEVE cobrir
	public static final String[] testeValido = {"objeto.function().function2();",
			"objeto.function().function2().function3();",
			"objeto.function().function2().function3().function4();", 
			"objeto.function().function2().function3().function4().function5();",
			"a.somadiferente().subdiferente().multdiferente().raizdiferente();", "enemies.get(i).isAlive();", 
			"tower.getType().initialPrice();","tower.getType().initialPrice(f);", 
			"tower.getType().initialPrice(PackageFragment);",
			"this.objeto.function().function2().function3().function4().function5();",
			"this.objeto.function().function2().function3().function4();",
			"this.objeto.function().function2().function3();", "this.objeto.function().function2();",
			"This.objeto.function().function2().function3().function4().function5();",
			"This.objeto.function().function2().function3().function4();",
			"This.objeto.function().function2().function3();","This.objeto.function().function2();",
			"this.objeto.function(param1).function2();", "This.objeto.function().function2(param2);",
			"tower.getType(i,j).initialPrice(f,g);", "tower.getType().initialPrice().love(f,g);",
			"objeto.function(param1,param2,param3).function2(param4,param5,param6);"};
	
	// String p/ testes que talvez o algoritmo n„o cubra
	public static final String[] testeExcecoes = {"this.gameMap.getSector(x,y).occupant.add(newTower);",
			"type.getTowerType().getConstructor(ArrayList.class, Map.class, Integer.class, Integer.class);", 
			"BasicEnemy.class.getConstructor(Map.class, Path.class);", 
			"type.getEnemyType().getConstructor(Map.class, Path.class);", 
			"mainMenuScene.getStylesheets().addAll(this.getClass().getResource('style.css').toExternalForm());"};
	
	public static void splitMessageChain (String s, int j) {
		// retira o ";" do final da string, substituindo por espaÁo em branco	
		s = s.replace(";", " ");
		
		// Quebra a vari·vel quando acha "." e armazena o resto numa posiÁ„o do array aux
		// a().b() -> "." È descartado e "a()" fica em aux[0] e "b()" em aux[1]
		String[] aux = s.split(Pattern.quote("."));		

		// Imprime a vari·vel aux na tela, separando os componentes do message chain analisado
		if (j == 0) {
			System.out.println("Objeto: " + aux[0]);		
			for (int i = 1; i < aux.length; i++) {
			System.out.println("MÈtodo[" + i + "]: " + aux[i]);
			}				
		} else if (j == 1) {	
			System.out.println("This -> " + aux[0]);	
			System.out.println("Objeto: " + aux[1]);	
			for (int i = 2; i < aux.length; i++) {
				System.out.println("MÈtodo[" + (i - 1) + "]: " + aux[i]);
			}
		} 
	}
	
	public static void verificaMessageChain (String s) {		
		if (s!=null && s.matches("[\\w]+([\\.]{1}[\\w]+[(][\\w]*[)]){2,}[;]")) {
			/*
			 * EXPLICA«√O REGEX:
			 * 
			 * CASO 1: objeto.function1().function2()...functionN();
			 * CASO 2: objeto.function1(param1).function2()...functionN();
			 * CASO 3: objeto.function1().function2(param2)...functionN();
			 * CASO 4: objeto.function1(param1).function2(param2)...functionN(paramN);
			 * 
			 * [\\w]+ -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, repetindo  1 ou infinitas vezes
			 * 
			 * (...){2,} -> grupo que deve ser repetido 2 ou mais vezes
			 * 
			 * [\\.]{1} -> necess·rio conter "." uma vez
			 * 
			 * [\\w]+ -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, repetindo  1 ou infinitas vezes
			 * 
			 * [(] -> necess·rio conter "(" uma vez
			 * 
			 * [\\w]* -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, 0 ou infinitas vezes
			 * 
			 * [)] -> necess·rio conter ")" uma vez
			 * 
			 * [;] -> necess·rio o caracter ";" no final para ser aceito
			 * 
			 */
			System.out.println("\n… Message Chain para "+s+"\n");
			splitMessageChain(s,0); 
		} else if (s!=null && s.matches("([tT]his)[\\.][\\w]+([\\.][\\w]+[(][\\w]*[)]){2,}[;]")) {
			/*
			 * EXPLICA«√O REGEX:
			 * 
			 * CASO 1: [tT]his.objeto.function1().function2()...functionN();
			 * CASO 2: [tT]his.function1().function2()...functionN();
			 * 
			 * (...) -> grupo 1
			 * 
			 * [Tt]his -> This ou this uma ˙nica vez
			 * 
			 * [\\.] -> necess·rio conter "." uma vez
			 * 
			 * [\\w]+ -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, repetindo  1 ou infinitas vezes
			 * 
			 * (...){2,} -> grupo 2 que deve ser repetido 2 ou mais vezes
			 * 
			 * [\\.] -> necess·rio conter "." uma vez
			 * 
			 * [\\w]+ -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, repetindo  1 ou infinitas vezes
			 * 
			 * [(] -> necess·rio conter "(" uma vez
			 * 
			 * [\\w]* -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, 0 ou infinitas vezes
			 *  
			 * [)] -> necess·rio conter ")" uma vez
			 * 
			 * [;] -> necess·rio o caracter ";" no final para ser aceito
			 * 
			 */
			System.out.println("\n… Message Chain para "+s+"\n");
			splitMessageChain(s,1);
		} else if (s!=null && s.matches("[\\w]+([\\.][\\w]+[(][\\w]+([,][\\w]+)*[)]){2,}[;]")) {
			/*
			 * EXPLICA«√O REGEX:
			 * 
			 * CASO 1:
			 * CASO 2:
			 * 
			 * 
			 * 
			 */
			System.out.println("\n… Message Chain para "+s+"\n");
			splitMessageChain(s,0);
		}/* else if (s!=null && s.matches("[\\w]+ ([\\.] + [\\w] + [(] + [\\w]* + ([,]+([\\s])*[\\w]+)* + [)]) {2,}[;]")) {
		}
		}
			System.out.println("\n… Message Chain para "+s+"\n");
			splitMessageChain(s, 1); 
		} else if (s!=null && s.matches("[[Tt]his]? [\\w]+ ( [\\.]+ [\\w]+ [(]+ [\\w]*+ [)]) {2,} [;]")) {
			// CASO: this.object.function1(x,y).function2(w,z);
			System.out.println("\n… Message Chain para "+s+"\n");
			splitMessageChain(s,0); 
		}*/ else {
			System.out.println("\nN„o È Message Chain para "+s+"\n");	
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
	}
}