import java.util.Optional;
import java.util.function.Function;

public class OptionalTest {
    public static void main(String args[]) {
        Optional<String> optional = Optional.of("bam");

        optional.isPresent(); // true;
        optional.get(); // bam
        optional.orElse("fallback"); // bam

        // best pracitce to use ifPresent(Consumer) as not doing a null check
        optional.ifPresent((s) -> System.out.println("character at index 0 - ifPresent so dont need to do null check - " + s.charAt(0))); // b
        Optional<String> a = Optional.of("test");
        if (a.isPresent()) {
            System.out.println("It is present");
        }
        a.ifPresent(System.out::println);

        String c = getTheOptionalStringEmpty().orElse("used this instead");
        System.out.println(c);

        //Optional should be used as return value of functions that might return not return a value
        Optional<String> month = Optional.of("October");
        Optional<String> nothing = Optional.ofNullable(null);
        nothing.ifPresent((s) -> System.out.println("value was NOT present so NOT output here - " + s + "-"));

        //map method
        Optional<String> smallerMonth = month.map(s -> s.substring(0,4));
        Optional<String> nothing2 = nothing.map(s -> s.substring(0,4));
        System.out.println("This shows that map function is also doing a null check first");
        System.out.println("month not empty so substring output - " + smallerMonth.get());
        System.out.println("nothing is empty so map ended and nothing2 is still empty - " + nothing2.isPresent());

        //flatmap
        // create our own first-class function to pass around like an object
        // this one will return an optional of empty if input null or an optional of the uppercase input value
        Function<String, Optional<String>> upperCaseOptionalString = s -> (s == null ) ? Optional.empty() : Optional.of(s.toUpperCase());
        Optional<String> word = Optional.of("apple");
        // using new function on 'word' results in an option within an option
        Optional<Optional<String>> optionalOfOptional = word.map(upperCaseOptionalString);
        // easily get the optional with the optional using flatmap
        Optional<String> optionalFromFlatmap = word.flatMap(upperCaseOptionalString);
        System.out.println("get the optional within the optional using flatmap " + optionalFromFlatmap.get());
        System.out.println("get the optional within the optional by chainging get() methods " + optionalOfOptional.get().get());

        // filtering optional
        Optional<Integer> numberOptional = Optional.of(10);
        Optional<Integer> filteredOut = numberOptional.filter(n -> n > 100);
        Optional<Integer> notFiltered = numberOptional.filter(n -> n < 100);
        System.out.println("filtered value is false because not > 100 " + filteredOut.isPresent());
        System.out.println("filtered value is true because not < 100 " + notFiltered.isPresent());
    }

    public static Optional<String> getTheOptionalString() {
        return Optional.of("test");
    }

    public static Optional<String> getTheOptionalStringEmpty() {
        return Optional.empty();
    }
}