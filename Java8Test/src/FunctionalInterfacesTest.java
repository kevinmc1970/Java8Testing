import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalInterfacesTest {
     public static void main(String[] args) {
        // Functions
        System.out.println("*** Function (1 input and return value)");   
        Map<String, Integer> nameMap = new HashMap<>(); 
        // Integer value = nameMap. computerfabsent ("john", s-> s.length(); 
        // adds John if absent with sting length as value 
        Integer value = nameMap.computeIfAbsent("John", String::length); 
        System.out.println("value=" + value); 
        // Now john exists we wont add 2 to the sting length for value 
        Integer value2 = nameMap.computeIfAbsent("john", s-> s.length() + 2); 
        System.out.println("value2=" + value2 + " shows that did not increase because 'John' not absent from map now");
        // using apply method to use the function without passing as a lambda 
        Function<Integer, String> stringFunction = String::valueOf; 
        System.out.println(stringFunction.apply(2)); 
        
        // Suppliers - provide values that may take time to retrieve etc 
        // they usually need some sort of external state to be useful i.e. this array 
        System.out.println("*** This shows how to use a supplier (o input and return value) ***");
        int[] fibs = { 0, 1 }; 
        Stream<Integer> fibonacci = Stream.generate(() -> { 
            int result = fibs[1]; 
            int fib3 = fibs[0] + fibs [1]; 
            fibs[0] = fibs [1]; 
            fibs [1] = fib3; 
            return result; 
        }).limit(10); 
        System.out.println("fibonacci numbers - this shows that the external state (array) changed using the supplier");
        fibonacci.forEach(s -> System.out.print(s + ",")); 

        // Using Supplier to create a new object 
        Supplier<Person> personSupplier = Person::new; 
        Person pe = personSupplier.get(); // new Person

        //consumers - side effects i, e. doesn't change the input - logging/counters 
        System.out.println("\n*** This shows how to use a consumer (1 input no return value) **");
        List<String> names = Arrays.asList("john", "Freddy", "Samuel"); 
        names.forEach(name -> System.out.println("Hello, " + name)); 
        // the accept method just uses the consumer without it being passed as a Tambda call 
        Consumer<String> printConsumer = (p) -> System.out.println(p); 
        printConsumer.accept("Using the accept method of the consumer"); 

        // Predicates - 1 input returns a Boolean 
        System.out.println("*** This shows how to use a Predicate (i input and return Boolean) ***"); 

        List<String> names2 = Arrays.asList("Angela", "Aaron", "Bob", "claire", "David");
        List<String> nameswithA = names2.stream().filter(name -> name.startsWith("A")).collect(Collectors.toList()); 
        nameswithA.forEach(System.out::println); 
        // same result without having to create another_list 
        names2.stream().filter(name -> name.startsWith("A")).forEach(System.out::println); 

        // Operators - Functions that return the same type as the input 
        System.out.println("*** This shows how to use Operators (Unary) (1 input 1 return with same type) ***"); 
        List<String> names3 = Arrays.asList("bob", "josh", "megan"); 
        names3.forEach(System.out::println); 
        names3.replaceAll(name -> name.toUpperCase()); 
        names3.forEach(System.out::println); 
        // using a Binary operator - the reduce method starts the value at 0, adds the first 2 numbers then 
        // removes/reduces 
        // the numbers from the stream until they're all used - in this case to add them all up 
        List<Integer> values = Arrays.asList(3, 5, 8, 9, 12); 
        int sum = values.stream().reduce(0, (il, i2) -> 11 + i2); 
        System.out.println("sum = " + sum); 
        // Existing java7 interfaces like Runnable are also functional interfaces 
        // it is like Supplier in that no input passed 
        Thread thread = new Thread(() -> System.out.println("Hello From Another Thread")); 
        thread. start(); 
        
     }
}