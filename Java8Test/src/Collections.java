import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Collections {

	// need to finally know these off-by-heart
	public static void main(String[] args) {

		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("c");
		System.out.println("Sets do not allow duplicates but Lists do");
		System.out.println("Sets can have 1 null - lists can have multiple");
		set.add(null);
		System.out.println(set);
		
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add(null);
		list.add("c");
		list.add(null);
		list.add("c");
		System.out.println(list);
		System.out.println("Sets not ordered and no index - Lists in insertion order and have an index ");
		
		list.add(2, "can insert into position based list");
		System.out.println(list);
		list.set(3, "change value with index in position based list");
		System.out.println(list);
		
	}

}
