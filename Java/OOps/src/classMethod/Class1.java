package classMethod;

public class Class1 {
	int a;
	double b;
	String str;
	 Class1(int a, double b, String str){
		 this.a = a;
		 this.b = b;
		 this.str = str;
	 }
	 
	 public void print() {
		 System.out.println(str);
		System.out.println("a: "+ a +", b: "+b);
	}
	 public void sum(int c, int d) {
		System.out.println(c+d);
	}

	 @Override
	 public String toString() {
		return "Class1 [a=" + a + ", b=" + b + ", str=" + str + "]";
	 }
	 
	 
	 

}
