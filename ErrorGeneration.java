import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ErrorGeneration {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a sentence: ");
		String inputString = input.nextLine();

		System.out.println("\n" + generateErrors(inputString));
		input.close();
	}

	private static String generateErrors(String input) {
		if (input.equals("") || input.equals(null)) {
			return "Error: Null string";
		}

		ArrayList<String> words = new ArrayList<String>();
		Scanner wordScanner = new Scanner(input);
		Random rand = new Random();
		wordScanner.useDelimiter(" ");
		while (wordScanner.hasNext()) {
			words.add(wordScanner.next());
		}

		String word = "";
		int index = -1;
		while (word.length() <= 1) {
			index = Math.abs(rand.nextInt()) % words.size();
			word = words.get(index);
		}
		words.remove(index);

		int charToTranspose1 = Math.abs(rand.nextInt()) % word.length();
		int charToTranspose2 = charToTranspose1+1;
		if (charToTranspose2 >= word.length()){
			charToTranspose2 = charToTranspose1-1;
		}
		int charToReplace = charToTranspose1;
		String newWord  = "";
		if (Math.random() < 0.5){
			newWord = transposeCharacter(word, charToTranspose1, charToTranspose2);
		}
		else {
			newWord = duplicateCharacter(word, charToReplace);
		}

		words.add(index, newWord);
		String finalSentence = "";
		for (int i = 0; i < words.size(); i++) {
			finalSentence += words.get(i) + " ";
		}
		wordScanner.close();

		return finalSentence;
	}

	private static String transposeCharacter(String word, int pos1, int pos2){
		String newWord = word.substring(0, pos1) + word.charAt(pos2) +
										word.charAt(pos1) + word.substring(pos2+1, word.length());
		return newWord;
	}

	private static String duplicateCharacter(String word, int duplicate){
		if (duplicate <= 0){duplicate = 1;}
		char insert = word.charAt(duplicate);
		String newWord = word.substring(0, duplicate+1) + insert +
										word.substring(duplicate+1, word.length());
		return newWord;
	}

}
