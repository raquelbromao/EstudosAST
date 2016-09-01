package alg;

import java.util.regex.Pattern;

public class TesteParenteses {

	public static void main(String[] args) {
		String testeParenteses = "objeto.function1(this.objeto.function3()).function2(amor).function4()";
		String[] split2 = null;
	
		System.out.println("Indíce da primeira ocorrência de '(': " + testeParenteses.indexOf("("));
		System.out.println("Indíce da última ocorrência de ')': " + testeParenteses.lastIndexOf(")") + "\n");
		
		String[] split1 = testeParenteses.split("[)][\\.]"); //Pattern.quote(").")
		System.out.println("###### PRIMEIRO SPLIT ");
		for (int w = 0; w < split1.length; w++) {
			System.out.println("Componente["+ w +"]: " + split1[w]);
		}		
		
		System.out.println("Indíce da primeira ocorrência de '(': " + split1[0].indexOf("("));
		System.out.println("Indíce da última ocorrência de ')': " + split1[0].lastIndexOf(")"));
		
		System.out.println("\n\n###### SEGUNDO SPLIT ");
		for (int j = 0; j < split1.length; j++) {
			split2 = split1[j].split("[(]"); //Pattern.quote("(")
			System.out.println("## Componentes de split1["+ j +"]");
			for (int k = 0; k < split2.length; k++) {
				System.out.println("Componente["+ k +"]: " + split2[k]);
			}
			System.out.print("\n");
		}
	}
	
}
