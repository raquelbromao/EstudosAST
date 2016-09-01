package alg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AcaoRecursivo {	
	private static final Pattern PATTERNCLASS = Pattern.compile(".*[\\w]+[\\.][class].*");
	
	// String p/ testes que o algoritmo DEVE cobrir
	public static final String[] testeValido = {"objeto.function(function3().function4()).function2();",
			"objeto.function().function2().function3();",
			"objeto.function().function2().function3().function4();", 
			"objeto.function().function2().function3().function4().function5();",
			"a.somadiferente().subdiferente().multdiferente().raizdiferente();", "enemies.get(i).isAlive();", 
			"objeto.function(param1, param2, param3).function2(param4 , param5 , param6);",
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
			"type.getEnemyType().getConstructor(Map.class, Path.class);"};
	
	// String p/ testes que talvez o algoritmo não cubra
	public static final String[] testeExcecoes = {
			"object.function1(hahajahsh6263).function2(jhshye88e9e9w);",
			"this.gameMap.getSector(x,y).occupant.add(newTower);", 
			"BasicEnemy.class.getConstructor(Map.class, Path.class);", 
			"mainMenuScene.getStylesheets().addAll(this.getClass().getResource('style.css').toExternalForm());"};
	
	public static void findDotClass(String t) {
        Matcher matcher = PATTERNCLASS.matcher(t);
        if(matcher.matches() && matcher.groupCount() == 1){
            String dotClass = matcher.group(1);
            System.out.println("Dot Class: " + dotClass);
        } else {
            System.out.println("Não encontrou dot Class.");
        }
    }
	
	public static void splitMessageChainRec (String s, int j) {
		String[] auxrec = null;		
		
		if (j == 0) {
			// Quebra a variável quando acha "." e armazena o resto numa posição do array aux
			// a().b() -> "." é descartado e "a()" fica em aux[0] e "b()" em aux[1]
			auxrec = s.split(Pattern.quote("."));		
			
			if (auxrec[0].equals("this") || auxrec[0].equals("This")) {
				System.out.println("\tThis -> " + auxrec[0]);	
				System.out.println("\tObjeto: " + auxrec[1]);	
				for (int i = 2; i < auxrec.length; i++) {
					System.out.println("\tMétodo[" + (i - 1) + "]: " + auxrec[i]);
					verificaMessageChainRec(auxrec[i]);
				}
			} else {
				System.out.println("\tObjeto: " + auxrec[0]);		
				for (int i = 1; i < auxrec.length; i++) {
					System.out.println("\tMétodo[" + i + "]: " + auxrec[i]);	
					verificaMessageChainRec(auxrec[i]);
				}
			}		
		} else if (j == 1) {			
			// Quebra a variável quando acha um "." que NÃO SEJA seguido de "class" e
			// armazena o resto numa posição do array aux
			// a().b() -> "." é descartado e "a()" fica em aux[0] e "b()" em aux[1]
			// BUG: está cortando "m" junto com ".", ou seja, cortando ".m"
			auxrec = s.split("[\\.][^class]"); 
			System.out.println("\tObjeto: " + auxrec[0]);	
			for (int i = 1; i < auxrec.length; i++) {
				System.out.println("\tMétodo[" + i + "]: " + auxrec[i]);
				verificaMessageChainRec(auxrec[i]);
			}
		} else if (j == 5) {
			System.out.println("\tMétodo único: " + s);
		}
		
		// reinicializa a variável aux liberando a memória
		//auxrec = null;
	}
	
	public static void splitMessageChain (String s, int j) {
		String[] aux = null;
		s = s.replace(";", " ");	

		if (j == 0) {
			// Quebra a variável quando acha "." e armazena o resto numa posição do array aux
			// a().b() -> "." é descartado e "a()" fica em aux[0] e "b()" em aux[1]
			aux = s.split(Pattern.quote("."));		
			
			if (aux[0].equals("this") || aux[0].equals("This")) {
				System.out.println("This -> " + aux[0]);	
				System.out.println("Objeto: " + aux[1]);	
				for (int i = 2; i < aux.length; i++) {
					System.out.println("Método[" + (i - 1) + "]: " + aux[i]);
					verificaMessageChainRec(aux[i]);
				}
			} else {
				System.out.println("Objeto: " + aux[0]);		
				for (int i = 1; i < aux.length; i++) {
					System.out.println("Método[" + i + "]: " + aux[i]);	
					verificaMessageChainRec(aux[i]);
				}
			}		
		} else if (j == 1) {
			// Quebra a variável quando acha um "." que NÃO SEJA seguido de "class" e
			// armazena o resto numa posição do array aux
			// a().b() -> "." é descartado e "a()" fica em aux[0] e "b()" em aux[1]
			// BUG: está cortando "m" junto com ".", ou seja, cortando ".m"
			findDotClass(s);
			aux = s.split("[\\.][^class]"); 
			System.out.println("Objeto: " + aux[0]);	
			for (int i = 1; i < aux.length; i++) {
				System.out.println("Método[" + i + "]: " + aux[i]);
				//verificaMessageChainRec(aux[i]);
			}
		}
		
		// reinicializa a variável aux liberando a memória
		//aux = null;
	}
	
	public static void verificaMessageChainRec (String s) {		
		if (s!=null && s.matches("[\\w]+([\\.]{1}[\\w]+[(][\\w]*([\\s]*[,][\\s]*[.]+)*[)]){2,}[\\s]*")) {
			/*
			 * EXPLICAÇÃO REGEX:
			 * 
			 * CASO 1: objeto.function1().function2()...functionN();
			 * CASO 2: objeto.function1(param1).function2()...functionN();
			 * CASO 3: objeto.function1().function2(param2)...functionN();
			 * CASO 4: objeto.function1(param1).function2(param2)...functionN(paramN);
			 * CASO 5: CASO 1: objeto.function1().function2(param2,param3)...functionN(paramN1,paramN2,paramN3);
			 * 
			 */
			//System.out.println("\nÉ Message Chain para "+s+"\n");
			splitMessageChainRec(s,0); 
		} else if (s!=null && s.matches("([tT]his)[\\.][\\w]+([\\.][\\w]+[(][\\w]*([\\s]*[,][\\s]*[.]+)*[)]){2,}[\\s]*")) {
			/*
			 * EXPLICAÇÃO REGEX:
			 * 
			 * CASO 1: [tT]his.objeto.function1().function2()...functionN();
			 * CASO 2: [tT]his.function1().function2()...functionN();
			 * 
			 */
			//System.out.println("\nÉ Message Chain para "+s+"\n");
			splitMessageChainRec(s,0);
		} else if (s!=null && s.matches("[\\w]+([\\.][\\w]+[(]([\\w]+([\\.](class))*([\\s]*[,][\\s]*[.]+([\\.](class))*)*)*[)]){2,}[\\s]*")) {
			/*
			 * EXPLICAÇÃO REGEX:
			 * 
			 * CASO 1: objeto.function1().function2(param2.class,param3)...functionN(paramN1,paramN2.class,paramN3);
			 * CASO 2: objeto.function1(param1.class, param2, param3.class).function2(param2,param3)...functionN(paramN1,paramN2.class,paramN3);
			 * 
			*/	
			//System.out.println("\nÉ Message Chain para "+s+"\n");
			splitMessageChainRec(s,1);
		} else if (s.isEmpty()) { 
			// Retorna true (0) qnd for vazia false (1) qnd for diferente de nula
			System.out.println("\n\tParâmetros vazia!\n"); 
		}  else if (s!=null && s.matches("[\\w]+[(][)]([\\.][\\w]+[(].*[)]){1,}[\\s]*")) {
			splitMessageChainRec(s,4);
		} else if (s!=null && s.matches("[\\w]+[(][\\w]+([\\s]*[,][\\s]*[\\w]+)*[)][\\s]*")) {
			splitMessageChainRec(s,5);
		} else if (s!=null && s.matches("[\\w]+[(][)][\\s]*")) {
			splitMessageChainRec(s,5);
		}
	}
	
	public static void verificaMessageChain (String s) {		
		if (s!=null && s.matches("[\\w]+([\\.]{1}[\\w]+[(][\\w]*([\\s]*[,][\\s]*[\\w]+)*[)]){2,}[;]")) {
			/*
			 * EXPLICAÇÃO REGEX:
			 * 
			 * CASO 1: objeto.function1().function2()...functionN();
			 * CASO 2: objeto.function1(param1).function2()...functionN();
			 * CASO 3: objeto.function1().function2(param2)...functionN();
			 * CASO 4: objeto.function1(param1).function2(param2)...functionN(paramN);
			 * CASO 5: CASO 1: objeto.function1().function2(param2,param3)...functionN(paramN1, paramN2 ,paramN3);
			 * 
			 */
			System.out.println("\nÉ Message Chain para "+s+"\n");
			splitMessageChain(s,0); 
		} else if (s!=null && s.matches("([tT]his)[\\.][\\w]+([\\.][\\w]+[(][\\w]*([\\s]*[,][\\s]*[\\w]+)*[)]){2,}[;]")) {
			/*
			 * EXPLICAÇÃO REGEX:
			 * 
			 * CASO 1: [tT]his.objeto.function1().function2()...functionN();
			 * CASO 2: [tT]his.function1().function2()...functionN();
			 * 
			 */
			System.out.println("\nÉ Message Chain para "+s+"\n");
			splitMessageChain(s,0);
		} else if (s!=null && s.matches("[\\w]+([\\.][\\w]+[(]([\\w]+([\\.](class))*([\\s]*[,][\\s]*[\\w]+([\\.](class))*)*)*[)]){2,}[;]")) {
			/*
			 * EXPLICAÇÃO REGEX:
			 * 
			 * CASO 1: objeto.function1().function2(param2.class,param3)...functionN(paramN1,paramN2.class,paramN3);
			 * CASO 2: objeto.function1(param1.class, param2, param3.class).function2(param2,param3)...functionN(paramN1,paramN2.class,paramN3);
			 * 
			*/	
			System.out.println("\nÉ Message Chain para "+s+"\n");
			splitMessageChain(s,1);
		} else if (s!=null && s.matches("[\\w]+([\\.][\\w]+[(].*[)]){2,}[;]")) { 
			System.out.println("\nÉ Message Chain para "+s+"\n");
			splitMessageChain(s,0);
		}	else if (s.isEmpty()) {
			// Retorna true (0) qnd for vazia e false (1) qnd for diferente de nula
			System.out.println("\nString vazia!\n");
		} else {
			System.out.println("\nNÃO É MESSAGE CHAIN PARA "+s+"\n");	
		}
	}
	
	public static void testaStrings (String[] s) {
		for (int i = 0; i<s.length; i++) {
			verificaMessageChain(s[i]);
		}
	}

	public static void main(String[] args) {
		//testaStrings(testeErro);		
		//System.out.println("\n#####################################################\n");		
		testaStrings(testeValido);
		System.out.println("\n#####################################################\n");	
		testaStrings(testeExcecoes);
	}
}