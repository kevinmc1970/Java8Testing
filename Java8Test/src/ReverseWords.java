import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class ReverseWords {

	public static void main(String[] args) {

		// this is the proper latest way to do it I think - using Collections
		String sentence = "this is the sentence to reverse";
		List<String> wordsList = Arrays.asList(sentence.split(" "));
		System.out.println(wordsList);
		Collections.reverse(wordsList);
		System.out.println(wordsList);
		String result = "";
		for(String word: wordsList) {
			result = result + word + " ";
		}
		System.out.println("result= >" + result.trim() + "<");
	}

}
