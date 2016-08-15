package alg;

public class analisaASTView {
	public int somadiferente(int a, int b) {
		int soma;
		soma = a + b;
		return soma;
	}

	public int subdiferente(int a, int b) {
		int sub = 0;
		if (a > b)
			sub = a - b;
		if (b > a)
			sub = b - a;
		return sub;
	}

	public int multdiferente(int a, int b) {
		int mult;
		mult = a * b;
		return mult;
	}

	public int raizdiferente() {
		return 0;
	}

	public void chamadacadeia() {
		Object a;
		//a.somadiferente().subdiferente().multdiferente().raizdiferente();
	}

}