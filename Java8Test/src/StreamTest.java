import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
	public static void main(String[] args) {
		// streams - just an array
		List<String> stringcollection = new ArrayList<>();
		stringcollection.add("ddd2");
		stringcollection.add("aaa2");
		stringcollection.add("bbb1");
		stringcollection.add("aaal");
		stringcollection.add("bbb3");
		stringcollection.add("ccc");
		stringcollection.add("bbb2");
		stringcollection.add("dddi");

		// stream matching
		boolean anystartswitha = stringcollection.stream().anyMatch(s -> s.startsWith("a"));
		System.out.println("anyMatch - do any begin with 'a' - " + anystartswitha); // true
		boolean allstartswitha = stringcollection.stream().allMatch(s -> s.startsWith("a"));
		System.out.println("allMatch - do all begin with 'a' - " + allstartswitha); // false
		System.out.println("noneMatch - do none start with 'z' - "
				+ stringcollection.stream().noneMatch((s) -> s.startsWith("z"))); // true

		// counting
		long startswithB = stringcollection.stream().filter(s -> s.startsWith("b")).count();
		System.out.println("filter then count - starting with 'b' - " + startswithB); // 3

		// reducing
		// optionals use because possibility of null in result
		Optional<String> reduced = stringcollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
		System.out.println("reduce all values into a single value - in this case a string with # inbetween values - ");
		reduced.ifPresent(System.out::println);
		// aaal#aaa2#bbbi#bbb2#bbb3#ccc#ddd1#ddd2'

		// primitive streams
		System.out.print("Primitive streams - Arrays.stream get average from arrav - ");
		Arrays.stream(new int[] { 1, 2, 3 }) // create primitive array..
				.map(n -> (2 * n) + 1) // change each value in the result stream not the initial array
				.average() // compute the average - this only avail because this is a primitive stream
							// (ints)
				.ifPresent(System.out::println); // 5.0

		System.out.print("converting between streams and primitive streams - average = ");
		Stream.of("a1", "a2", "a3") // create a list without a list object
				.map(s -> s.substring(1)) // change each value in the resulting stream (to get int String)
				.mapToInt(Integer::parseInt) // change each value in the resulting stream to primitive int
				.max() // get the max value
				.ifPresent(System.out::println); // 3

		// converting to objects - also shows creating Stream without a list
		IntStream.range(1, 4).mapToObj(i -> "a" + i).forEach(System.out::print);
		System.out.println();

		// intermediate operations are lazy (used when needed) and
		// only executed when there is a terminal operation i.e. forEach, collect
		Stream.of("d2", "a2", "b1", "b3", "c").sorted().filter(s -> {
			System.out.println("This will NOT be printed " + s);
			return true;
		});

		// elements are pipelined one at a time through ALL the operations in the stream
		// this means that the stream can end once the desired outcome reached without
		// having to process through all of the elements
		// so here d2 goes through to anymatch before a2 starts
		System.out.println(
				"Each element pipelined thru ALL operations - So can end stream without processing every element");
		Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).anyMatch(s -> {
			System.out.println("anyMatch: " + s + " - if match returns true and stream ends");
			return s.startsWith("A");
		});

		// However, sorted operation is executed for entire list of elements
		// and can introduce needless processing - fix this code by running filter first
		// then there will be just one element so no sorting
		System.out.println("sorted operation happens on ALL elements so filter first");
		Stream.of("d2", "a2", "b1").sorted((s1, s2) -> {
			System.out.printf("sorted: %s; %s - even b1 when it will be filtered out in next operation\n", s1, s2);
			return s1.compareTo(s2);
		}).filter(s -> s.startsWith("a")).map(s -> s.toUpperCase())
				.forEach(s -> System.out.println("forEach now only has a2 : " + s));

		System.out.println();
		System.out.println("Cannot reuse streams after terminal operation");
		Stream<String> stream = Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> s.startsWith("a"));
		stream.anyMatch(s -> true); // ok
		// stream.noneMatch(s -> true); // exception
		// get round this...
		System.out.println("get round this by using a Stream Supplier");
		Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c")
				.filter(s -> s.startsWith("a"));
		streamSupplier.get().anyMatch(s -> true); // ok
		streamSupplier.get().noneMatch(s -> true); // ok

		System.out.println();
		System.out.println("Collect to create new List from Stream - list only contains P* names - ");
		// collect to create new List from Stream
		List<Person> persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pam", 23),
				new Person("David", 12));
		List<Person> filtered = persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList());
		filtered.forEach(s -> System.out.println(s.getName() + " - ")); // Peter, Pamela

		System.out.println();
		System.out.println("groupBy - map only contains persons grouped by age");
		// groupBy
		Map<Integer, List<Person>> personsByAge = persons.stream().collect(Collectors.groupingBy(p -> p.age));
		personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
		// Flatmap - flattens list of lists into 1 stream
		// { {1,2}, {3,4}, {5,6} } -> flatMap -> {1,2,3,4,5,6}
		// {'a', 'b'}, {'c','d'}, {'e','f'} } -> flatMap --> {'a','b','c','d','e','f'}
		// use this when need to process lists with lists OR an object in an Optional
		// in this case we create 3 Foos each with a list of 2 Bars
		// then use flatmap to output the Bars for each Foo
		// peek performs additional action on element as it is consumed/created by the
		// stream
		// in this case each new Foo object so that can then create Bars on it
		System.out.println();
		System.out.println("flatMap - process lists within lists");
		System.out.println("3 Foos each with a list of 2 Bars");
		IntStream.range(1, 4).mapToObj(i -> new Foo("Foo" + i)) // create Foos
				.peek(f -> IntStream.range(1, 3).mapToObj(i -> new Bar("Bar" + i + " inside " + f.name))
						.forEach(f.bars::add)) // create Bars for each new Foo created
				.flatMap(f -> f.bars.stream()) // create stream (ALWAYS DOES THIS) for the Bars in each Foo
				.forEach(b -> System.out.println("flatmapping through the Bars (not the Foos) - " + b.name));
		// the Bars are in the stream now not the Foos

		// counting pairs etc.
		List<String> list = new ArrayList<>();
		list.add("red");
		list.add("yellow");
		list.add("yellow");
		list.add("yellow");
		list.add("blue");
		list.add("red");
		// need this because lamdas can only access final variables outside of lambda
		// which obviously can't be incremented
		AtomicInteger counted2 = new AtomicInteger(0);
		Map<String, Long> counted = list.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(counted);
		list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.forEach(x -> counted2.getAndAdd(x.getValue().intValue() / 2));
		System.out.println(counted2);

	}
}