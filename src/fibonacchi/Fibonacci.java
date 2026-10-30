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
	    if(n<0)
	    {
	        throw new IllegalArgumentException("El valor no puede ser negativo");
	    }
	    if(n<=1)
	    {
	        return n;
	    }
	    int fiba=0;
	    int fibb=1;
	    for(int i=2;i<=n;i++)
	    {
	        int fibo=Math.addExact(fiba,fibb); // lanza ArithmeticException si se desborda
	        fiba=fibb;
	        fibb=fibo;
	    }
	    return fibb;
	}
	
	public static void main(String[] args) {
		int num=19;
		int fibonacciRecursivo= fibonacciRecursivo(num);
		
		int num1=1;
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


