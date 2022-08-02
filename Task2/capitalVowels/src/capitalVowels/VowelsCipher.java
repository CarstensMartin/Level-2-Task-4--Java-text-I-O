package capitalVowels;

//Import all the required functions
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class VowelsCipher {

	public static void main(String[] args) {

		// Declare String variable that will be the final text after capital vowels Cipher is used
		String afterCipherText = "";

		/*
		 * Read the data from the file, run the cipher method on the data line by line
		 * and output final message
		 */
		try {
			// Read data from file using the Scanner function
			File file = new File(
					"C:/Users/MARTIN/Dropbox/Martin Carstens-115604/2. Advanced Programming Concepts/Task 4/Task2/poem.txt");
			Scanner textScanner = new Scanner(file);

			// Use a while loop - while there is more lines in the text, the content should run again.
			while (textScanner.hasNextLine()) {
				// Read the next line and store in String variable
				String inputText = textScanner.nextLine();
				// Use the string inputText and run the cipher method on the text and store in
				// new String cypherConverted
				String cypherConverted = cipher(inputText);
				// Add the cipher converted text to the String variable afterCypherText
				// Also add \r\n for a new line in windows
				afterCipherText += (cypherConverted + "\r\n");
			}
			// Close the Scanner function
			textScanner.close();
		}
		// If the file does not load the scan - display the following error message
		catch (FileNotFoundException e) {
			System.out.println("Error - Reading file not found");
		}

		// Write a file with the new cipher text inside
		try {
			// Create a new Text file
			Formatter newFileWrite = new Formatter(
					"C:/Users/MARTIN/Dropbox/Martin Carstens-115604/2. Advanced Programming Concepts/Task 4/Task2/capitalVowels.txt");
			// Write the new String text inside
			newFileWrite.format("%s", afterCipherText);
			// Close the file writer
			newFileWrite.close();
		}
		// If there was a problem creating the file - display the below error message
		catch (FileNotFoundException e) {
			System.out.println("Error - File not created");
		}
	}

	public static String cipher(String cyperTextStart) {
		// Determine the length of the line
		int lineLength = cyperTextStart.length();
		// Create a list array to store all the characters in after moved positions
		ArrayList<String> arrayCharacters = new ArrayList<String>();

		// Use a for loop to run the amount if characters that is in the line of text
		for (int loopI = 0; loopI < lineLength; loopI++) {
			// Convert the character of the position of the loop into a string
			String letter = Character.toString(cyperTextStart.charAt(loopI));

			// Declare all the String vowel letters that must be capitalized
			String a = "a";
			String e = "e";
			String i = "i";
			String o = "o";
			String u = "u";

			// If the the letter contains the vowel letter, add the capitalized vowel to the array of characters
			if ((letter.contains(a)) || (letter.contains(e)) || (letter.contains(i)) || (letter.contains(o))
					|| (letter.contains(u))) {
				arrayCharacters.add(letter.toUpperCase());
			}
			// Else if it is any other character then it should keep it' value & Upper / Lower case value.
			else {
				arrayCharacters.add(letter);
			}
		}

		// Convert the new array sentence back to a string by joining all the characters.
		String newSentence = String.join("", arrayCharacters);
		// Return the new String that is ciphered
		return newSentence;
	}
}