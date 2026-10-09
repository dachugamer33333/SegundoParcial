package factorial;

public class Factorial {
	public static int factorialRecursivo(int n)
	{
		if(n< 0)
		{
			throw new IllegalArgumentException("no definido para negativos bro");
		}
		if(n<=1) {
			return 1;
		}
		return n * factorialRecursivo(n-1);
	}
	
	public static int factorialIterativo(int n)
	{
		if(n< 0)
		{
			throw new IllegalArgumentException("no definido para negativos bro");
		}
		int fact = 1;
		while(n>0)
		{
			fact = n*fact;
			n--;
		}
		return fact;
	}
	
	public static void main(String[] args) {
		int num = 5;
		int factorialNumRecursivo = factorialRecursivo(num);
		
		int num1 = 6;
		int factorialNumIterativo= factorialIterativo(num1);
		
		System.out.printf("""
				Factorial Recursivo
				numero base: %d
				numero factorial %d
				""", num, factorialNumRecursivo);
		System.out.printf("""
				Factorial Iterativo
				numero base: %d
				numero factorial %d
				""", num1, factorialNumIterativo);
	}
		
}
