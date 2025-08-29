package classMethod;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start the programm...!!");
		Class1 cl = new Class1(10, 20.0, "Hello Sumit");
		System.out.println(cl.str);
		cl.print();
		cl.sum(20, 40);
		Class2 cl2 = new Class2(2, 3, 4, 5, null);
		cl2.sum(2, 5);
		cl.print();
//		cl2.print();
		System.out.println(cl2.c);
		
		
	}

}
