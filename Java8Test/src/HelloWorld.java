import java.lang.Math; // headers MUST be above the first class
import java.util.*;
import java.util.stream.Collectors;

// one class needs to have a main() method
public class HelloWorld {
  // arguments are passed using the text field below this editor
  public static void main(String[] args)
  { 
    //List<String> list = new ArrayList<String>();
    String[] primitiveArray = new String[] {"b", "c", "a"}; 
    List<String> list = Arrays.asList(primitiveArray);
    //list.add("b");
      //list.add("c");
        //list.add("a");
    // sort returns void so need to do this outside of sysout
    Collections.sort(list);
    System.out.println(list);
    
    Collections.sort(list, Collections.reverseOrder());
    System.out.println(list);
    
    //iterating
    for (String element: list) {
      //System.out.print(element);
    }
    System.out.println("Using Arrays.asList means that target array is also sorted");
    // using Arrays.asList means the target array is also changed
    for (int i=0;i<primitiveArray.length;i++) {
    	System.out.print(primitiveArray[i]);
    }
    System.out.println();
    
    String sentence = "this has words in it that need to be sorted into reverse alphabetical order";
    //List<String> words = new ArrayList<String>(Arrays.asList(sentence.split(" ")));
    List<String> words = Arrays.asList(sentence.split(" "));
    // reverse using stream
    List<String> wordsReverse = words.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    // reverse using Collections
    Collections.sort(words, Collections.reverseOrder());
    System.out.println(words);
    System.out.println(wordsReverse);
    
    // how to sort sentence by order of values?
    // just use Collection.reverse!!!!!!!!!!!!!!!!11
    String sentence2 = "this has words in it that need to be reverse order";
    List<String> words2 = new ArrayList<String>(Arrays.asList(sentence2.split(" ")));
    
    Collections.reverse(words2);
    System.out.println(words2);    
    
    
  }
}
