package tests;
import lists.ArrayList;

public class ListClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> aList = new ArrayList<String>();
		
		aList.add("Bob");
		aList.add("Tim");
		aList.add("John");
		aList.add("Bill");
		aList.add("Frank");
		
		System.out.println(aList);
		
		aList.add(2, "Sam");
		
		System.out.println(aList);
		
		System.out.println(aList.remove(2));
		
		System.out.println(aList);
		
		System.out.println(aList.indexOf("John"));
		System.out.println(aList.indexOf("Trevor"));
		
		System.out.println(aList.get(4));

	}

}
