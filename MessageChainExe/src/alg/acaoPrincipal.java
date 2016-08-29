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
			"objeto.function(param1,param2,param3));", "objeto.metodo1(param1.class);",
			"objeto.metodo1(param1.class).metodo2((param2.class);",
			"objeto.metodo1((param1.class)).metodo2(param2.class);",
			"objeto.metodo1(param1.class).metodo2(param2.class));",
			"objeto.metodo1(param1.clas).metodo2(param2.clas));",""};
	
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
			"tower.getType(i,j).initialPrice(f,g);", "tower.getType().initialPrice().exe(f,g);",
			"objeto.function(param1,param2,param3).function2(param4,param5,param6);",
			"This.objeto.function(a,b,c).function2(d);","this.objeto.function(param1).function2(param2,param3);",
			"objeto.metodo1(param1.class).metodo2(param2.class);",
			"objeto.metodo1(param1,param2.class).metodo2(param3.class);", 
			"objeto.metodo1(param1,param2.class,param3).metodo2(param4).metodo3(metodo5).metodo4(param6.class);",
			"objeto.metodo1(param1.class, param2.class).metodo2(param3.class).metodo3(a, b, c);",
			"objeto.metodo1().metodo2(param1.class).metodo3(a,b,c);",
			"type.getTowerType().getConstructor(ArrayList.class,Map.class,Integer.class,Integer.class);",
			"type.getEnemyType().getConstructor(Map.class, Path.class);",
			"objeto.function(param1, param2, param3).function2(param4 , param5 , param6);",
			"object.function();"};
	
	// String p/ testes que talvez o algoritmo n„o cubra
	public static final String[] testeExcecoes = {
			"this.gameMap.getSector(x,y).occupant.add(newTower);", 
			"BasicEnemy.class.getConstructor(Map.class, Path.class);", 
			"type.getEnemyType().getConstructor(Map.class, Path.class);", 
			"mainMenuScene.getStylesheets().addAll(this.getClass().getResource('style.css').toExternalForm());"};
	
	public static void splitMessageChain (String s, int j) {
		String[] aux = null;
		
		// retira o ";" do final da string, substituindo por espaÁo em branco	
		s = s.replace(";", " ");	

		// Imprime a vari·vel aux na tela, separando os componentes do message chain analisado
		if (j == 0) {
			// Quebra a vari·vel quando acha "." e armazena o resto numa posiÁ„o do array aux
			// a().b() -> "." È descartado e "a()" fica em aux[0] e "b()" em aux[1]
			aux = s.split(Pattern.quote("."));		
			
			if (aux[0].equals("this") || aux[0].equals("This")) {
				System.out.println("This -> " + aux[0]);	
				System.out.println("Objeto: " + aux[1]);	
				for (int i = 2; i < aux.length; i++) {
					System.out.println("MÈtodo[" + (i - 1) + "]: " + aux[i]);
				}
			} else {
				System.out.println("Objeto: " + aux[0]);		
				for (int i = 1; i < aux.length; i++) {
				System.out.println("MÈtodo[" + i + "]: " + aux[i]);		
				}
			}		
		} else if (j == 1) {
			// Quebra a vari·vel quando acha um "." que N√O SEJA seguido de "class" e
			// armazena o resto numa posiÁ„o do array aux
			// a().b() -> "." È descartado e "a()" fica em aux[0] e "b()" em aux[1]
			// BUG: est· cortando "m" junto com ".", ou seja, cortando ".m"
			aux = s.split("[\\.][^class]"); 
			System.out.println("Objeto: " + aux[0]);	
			for (int i = 1; i < aux.length; i++) {
				System.out.println("MÈtodo[" + i + "]: " + aux[i]);
			}
		}
		
		// reinicializa a vari·vel aux liberando a memÛria
		aux = null;
	}
	
	public static void verificaMessageChain (String s) {		
		if (s!=null && s.matches("[\\w]+([\\.]{1}[\\w]+[(][\\w]*([\\s]*[,][\\s]*[\\w]+)*[)]){2,}[;]")) {
			/*
			 * EXPLICA«√O REGEX:
			 * 
			 * CASO 1: objeto.function1().function2()...functionN();
			 * CASO 2: objeto.function1(param1).function2()...functionN();
			 * CASO 3: objeto.function1().function2(param2)...functionN();
			 * CASO 4: objeto.function1(param1).function2(param2)...functionN(paramN);
			 * CASO 5: CASO 1: objeto.function1().function2(param2,param3)...functionN(paramN1,paramN2,paramN3);
			 * 
			 * [\\w]+ -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, repetindo  1 ou infinitas vezes
			 * 
			 * ... (G1){2,} -> grupo 1 que deve ser repetido 2 ou mais vezes
			 *  
			 * [\\.] -> necess·rio conter "." uma vez
			 * 
			 * [\\w]+ -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, repetindo  1 ou infinitas vezes
			 * 
			 * [(] -> necess·rio conter "(" uma vez
			 * 
			 * [\\w]* -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, 0 ou infinitas vezes
			 *  
			 * (G1 (G2)* G1) -> grupo 2 que deve ser repetido 0 ou infinitas vezes
			 * 
			 * [\\s]* -> necess·rio conter espaÁo em branco 0 ou infinitas vezes
			 * 
			 * [,] -> necess·rio conter o caractere "," uma vez
			 * 
			 * [\\s]* -> necess·rio conter espaÁo em branco 0 ou infinitas vezes
			 * 
			 * [\\w]+ -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, repetindo  1 ou infinitas vezes
			 * 
			 * [)] -> necess·rio conter ")" uma vez
			 * 
			 * [;] -> necess·rio o caractere ";" no final para ser aceito
			 * 
			 */
			System.out.println("\n… Message Chain para "+s+"\n");
			splitMessageChain(s,0); 
		} else if (s!=null && s.matches("([tT]his)[\\.][\\w]+([\\.][\\w]+[(][\\w]*([\\s]*[,][\\s]*[\\w]+)*[)]){2,}[;]")) {
			/*
			 * EXPLICA«√O REGEX:
			 * 
			 * CASO 1: [tT]his.objeto.function1().function2()...functionN();
			 * CASO 2: [tT]his.function1().function2()...functionN();
			 * 
			 * (G1)... -> grupo 1
			 * 
			 * [Tt]his -> This ou this uma ˙nica vez
			 * 
			 * [\\.] -> necess·rio conter "." uma vez
			 * 
			 * [\\w]+ -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, repetindo  1 ou infinitas vezes
			 * 
			 * (G1) ... (G2){2,} -> grupo 2 que deve ser repetido 2 ou mais vezes
			 * 
			 * [\\.] -> necess·rio conter "." uma vez
			 * 
			 * [\\w]+ -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, repetindo  1 ou infinitas vezes
			 * 
			 * [(] -> necess·rio conter "(" uma vez
			 * 
			 * [\\w]* -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, 0 ou infinitas vezes
			 * 
			 * (G1)...(G2 (G3)* G2) -> grupo 3 que deve ser repetido 0 ou infinitas vezes
			 * 
			 * [\\s]* -> necess·rio conter espaÁo em branco 0 ou infinitas vezes
			 * 
			 * [,] -> necess·rio conter o caractere "," uma vez
			 * 
			 * [\\s]* -> necess·rio conter espaÁo em branco 0 ou infinitas vezes
			 * 
			 * [\\w]+ -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, repetindo  1 ou infinitas vezes 
			 *  
			 * [)] -> necess·rio conter ")" uma vez
			 * 
			 * [;] -> necess·rio o caracter ";" no final para ser aceito
			 * 
			 */
			System.out.println("\n… Message Chain para "+s+"\n");
			splitMessageChain(s,0);
		} else if (s!=null && s.matches("[\\w]+([\\.][\\w]+[(]([\\w]+([\\.](class))*([\\s]*[,][\\s]*[\\w]+([\\.](class))*)*)*[)]){2,}[;]")) {
			/*
			 * EXPLICA«√O REGEX:
			 * 
			 * CASO 1: objeto.function1().function2(param2.class,param3)...functionN(paramN1,paramN2.class,paramN3);
			 * CASO 2: objeto.function1(param1.class, param2, param3.class).function2(param2,param3)...functionN(paramN1,paramN2.class,paramN3);
			 * 
			 * [\\w]+ -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, repetindo  1 ou infinitas vezes
			 * 
			 * ... (G1){2,} -> grupo 1 que deve ser repetido 2 ou mais vezes
			 * 
			 * [\\.] -> necess·rio conter "." uma vez
			 * 
			 * [\\w]+ -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, repetindo  1 ou infinitas vezes
			 * 
			 * [(] -> necess·rio conter "(" uma vez
			 * 
			 * (G1 (G2)*) -> grupo 2 que deve ser repetido 0 ou infinitas vezes
			 * 
			 * [\\w]+ -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, repetindo  1 ou infinitas vezes
			 * 
			 * (G1 (G2 (G3)*)) -> grupo 3 que deve ser repetido 0 ou infinitas vezes
			 * 
			 * [\\.] -> necess·rio conter "." uma vez
			 * 
			 * (class) -> necess·rio conter "class" uma vez
			 * 
			 * (G1 (G2 (G3) (G4)*)) -> grupo 4 que deve ser repetido 0 ou infinitas vezes
			 * 
			 * [\\s]* -> necess·rio conter espaÁo em branco 0 ou infinitas vezes
			 * 
			 * [,] -> necess·rio conter o caractere "," uma vez
			 * 
			 * [\\s]* -> necess·rio conter espaÁo em branco 0 ou infinitas vezes
			 * 
			 * [\\w]+ -> qlqr combinaÁ„o de caracteres numÈricos e/ou literais, repetindo  1 ou infinitas vezes
			 * 
			 * (G1 (G2 (G3) (G4 (G5)))) -> grupo 5
			 * 
			 * [\\.] -> necess·rio conter "." uma vez
			 * 
			 * (class) -> necess·rio conter "class" uma vez 
			 * 
			 * [;] -> necess·rio o caracter ";" no final para ser aceito
			 * 
			 */
			System.out.println("\n… Message Chain para "+s+"\n");
			splitMessageChain(s,1);
		} else if (s.isEmpty()) { 
			// Retorna true (0) qnd for vazia false (1) qnd for diferente de nula
			System.out.println("\nString vazia!\n");
		} else {
			System.out.println("\nN√O … MESSAGE CHAIN PARA "+s+"\n");	
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