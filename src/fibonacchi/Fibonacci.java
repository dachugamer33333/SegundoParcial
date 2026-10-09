package fibonacchi;

public class Fibonacci {
	public static int fibonacciRecursivo(int n)
	{
		if(n<0)
		{
			throw new IllegalArgumentException("Valor debe ser mayor a 0");
		}
		if(n==0 || n==1)
		{
			return n;
		}
		else
		{
			return fibonacciRecursivo(n-1)+ fibonacciRecursivo(n-2);
		}
	}
	public static int fibonacciIterativo(int n)
	{
		int fibo=0;
		int fiba=0;
		int fibb=0;
		int i=0;
		if(n<0)
		{
			throw new IllegalArgumentException("Valor debe ser mayor a 0");
		}
		if(n==1 || n==0)
		{
			fibo= n;
		}
		else
		{
			fiba =0;
			fibb =1;
			i=2;
		}
		while(i<=n)
		{
			fibo=fibb+fiba;
			fiba=fibb;
			fibb=fibo;
			i++;
		}
		return fibo;
	}
	
	public static void main(String[] args) {
		int num=19;
		int fibonacciRecursivo= fibonacciRecursivo(num);
		
		int num1=19;
		int fibonacciIterativo= fibonacciIterativo(num1);
		
		System.out.printf("""
				||Fibonacci Recursivo||
				numero = %d
				fibonacci Recursivo = %d
				""",num,fibonacciRecursivo);
		
		System.out.printf("""
				||Fibonacci Iterativo||
				numero = %d
				fibonacci Iterativo = %d
				""",num1,fibonacciIterativo);
	}
}


