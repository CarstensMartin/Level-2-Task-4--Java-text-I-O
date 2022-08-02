package poetry;

//import all the relevant packages
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Cypher {

	public static void main(String[] args) {

		// Declare String variable that will be the final text after cipher is used
		String afterCipherText = "";

		/*
		 * Read the data from the file, run the cipher method on the data line by line
		 * and output final message
		 */
		try {
			// Read data from file using the Scanner function
			File file = new File(
					"C:/Users/MARTIN/Dropbox/Martin Carstens-115604/2. Advanced Programming Concepts/Task 4/Task1/poem.txt");
			Scanner textScanner = new Scanner(file);

			// Use a while loop - while there is more lines in the text, the content should run again.
			while (textScanner.hasNextLine()) {
				// Read the next line and store in String variable
				String inputText = textScanner.nextLine();
				// Use the string inputText and run the cipher method on the text and store in new String cypherConverted
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
					"C:/Users/MARTIN/Dropbox/Martin Carstens-115604/2. Advanced Programming Concepts/Task 4/Task1/encodedPoem.txt");
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

	// Method to take a string convert the text with the ciphered
	// Return the new string after conversion
	public static String cipher(String cyperTextStart) {
		// Declare amount of letters to move forward
		int lettersMove = 15;
		// Determine the length of the line
		int lineLength = cyperTextStart.length();
		// Create a list array to store all the characters in after moved positions
		ArrayList<String> arrayCharacters = new ArrayList<String>();

		// Use a for loop to run the amount if characters that is in the line of text
		for (int i = 0; i < lineLength; i++) {
			// Get the character at position "i" as the loop runs
			char character = cyperTextStart.charAt(i);
			// The current character - to use when it is a special character example @ or !
			int oldAsciiInt = character;
			// New position of character + lettersMove - in this case 15
			int newAscciiInt = character + lettersMove;

			/*
			 * 122 is the Ascii value of 'z' so if the value is bigger, then we -26
			 * positions to start at a again.
			 * And must also be less than or equal to (122 + LettersMoved) otherwise will
			 * also change the special characters and other Acii values
			 */
			if (newAscciiInt > 122 && newAscciiInt <= (122 + lettersMove)) {
				// Add the character with the new (Ascii value + 15 places) - 26 because after z
				char newCharacter = (char) (newAscciiInt - 26);
				String letter = Character.toString(newCharacter);
				arrayCharacters.add(letter);
			}

			// Ascii answer between or equal to (97(a value) + lettersMove) and 122(z value)
			else if (newAscciiInt >= (97 + lettersMove) && newAscciiInt <= 122) {
				// Add the character with the new (Ascii value + 15 places)
				char newCharacter = (char) newAscciiInt;
				String letter = Character.toString(newCharacter);
				arrayCharacters.add(letter);
			}

			/*
			 * 90 is the Ascii value of 'Z', LettersMove for the new Ascii value bigger than
			 * 90 and smaller than or equal to (90 + LettersMove) need to -26 letters
			 * because running after Z
			 */
			else if (newAscciiInt > 90 && newAscciiInt <= (90 + lettersMove)) {
				// Add the character with the new (Ascii value + LettersMoved(15)) - 26 because after Z
				char newCharacter = (char) (newAscciiInt - 26);
				String letter = Character.toString(newCharacter);
				arrayCharacters.add(letter);
			}

			// Ascii answer between or equal to (65(A value) + lettersmove) and 90(Z value)
			else if (newAscciiInt >= (65 + lettersMove) && newAscciiInt <= 90) {
				// Add the character with the Ascii value + 15 letters
				char newCharacter = (char) newAscciiInt;
				String letter = Character.toString(newCharacter);
				arrayCharacters.add(letter);
			}

			/*
			 * Else if the characters are not between (A to Z) and (a to z) - it must return
			 * the current value example '@' is '@'
			 */
			else {
				char newCharacter = (char) oldAsciiInt;
				String letter = Character.toString(newCharacter);
				arrayCharacters.add(letter);
			}
		}
		// Convert the new array sentence back to a string by joining all the characters.
		String newSentence = String.join("", arrayCharacters);
		// Return the new String that is ciphered
		return newSentence;
	}
}