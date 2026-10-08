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
		return 1;
	}
	
	public static void main(String[] args) {
		int num = 5;
		int factorialNum = factorialRecursivo(num);
		System.out.printf("""
				numero base: %d
				numero factorial %d
				""", num, factorialNum);
	}
}
