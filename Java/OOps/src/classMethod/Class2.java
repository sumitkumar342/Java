package classMethod;

public class Class2 extends Class1 {
	int c, d, e, f, g;
	String str;
	
	Class2(int a, int b, int c, int d, String str){
		super(a, b, str);
//		this(c, d);
	}
//	Class2(int c, int d){
//		this.c = c;
//		this.d = d;
//	}
	

	
	@Override
	public void sum(int a, int b) {
		System.out.println("Override methode");
		System.out.println(2*(a+b));
		
		try {
			
		} finally {
			
		}
	}
}
