public class Strings {

	static String weak = "password";
	static String invalid1 = "thishasa?";
	static String invalid2 = "hereisa~inthemiddle";
	static String invalid3 = "this has spaces ";
	
	public static void main(String[] args) {

		if(weak.matches(".*[@~? ].*")) {
			System.out.println(weak);
			System.out.println("Cannot contain @~?");
		}		
		if(invalid1.matches(".*[@~? ].*")) {
			System.out.println(invalid1);
			System.out.println("Cannot contain @~?");
		}
		if(invalid2.matches(".*[@~? ].*")) {
			System.out.println(invalid2);
			System.out.println("Cannot contain @~?");
		}
		if(invalid3.matches(".*[@~? ].*")) {
			System.out.println(invalid3);
			System.out.println("Cannot contain spaces");
		}
	}

}
