import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		File dictionary = new File("D:/JavaStuff/Hang/src/english2.txt");
		Scanner sc = new Scanner(dictionary); 									// read from file
		Scanner input = new Scanner(System.in);									// read from user
		boolean gameFinished = false;
		int lives = 6;
		
		ArrayList<String> words = new ArrayList<>(); 							// Store data from dictionary as array list
		while(sc.hasNextLine()) 
			words.add(sc.nextLine());
		
		String hiddenWord = words.get((int)(Math.random() * words.size())); 		// Random word from dictionary
		char[] textArray = hiddenWord.toCharArray(); 							// convert hidden word to char array
		char[] userAnswer = new char[textArray.length]; 
		for(int i = 0; i < textArray.length; i++) 								// fill array of user's answers
			userAnswer[i] = '?';												// with blank values
		
		while(!gameFinished) {
			System.out.println("*****************************************");
			String guess = input.next();
			while(guess.length() != 1 || Character.isDigit(guess.charAt(0))) {	// validates input
				System.out.println("Input Error - Try Again");
				guess = input.next(); 
			}
			
			boolean found = false;
			for(int i = 0; i < textArray.length; i++) {
				if(guess.charAt(0) == textArray[i]) {
					userAnswer[i] = textArray[i];
					found = true;
				}
			}
			if(!found) {
				lives--;
				System.out.println("Wrong Letter");
			}
			
			boolean done = true;
			for(int i = 0; i < userAnswer.length; i++) {
				if(userAnswer[i] == '?') {
					System.out.print(" _");
					done = false;
				} else {
					System.out.print(" " + userAnswer[i]);
				}
			}
			
			System.out.println("\nLives Left: " + lives );
			drawHangman(lives);
			
			if(done) {
				System.out.println("Congrats");
				gameFinished = true;
			}
			if(lives <= 0) {
				System.out.println("Failed \nThe word was: " + hiddenWord);
				gameFinished = true;
			}
			
			
		}
	}
	
	public static void drawHangman(int l) {
		  if(l == 6) {
		   System.out.println("|----------");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		  }
		  else if(l == 5) {
		   System.out.println("|----------");
		   System.out.println("|    O");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		  }
		  else if(l == 4) {
		   System.out.println("|----------");
		   System.out.println("|    O");
		   System.out.println("|    |");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		  }
		  else if(l == 3) {
		   System.out.println("|----------");
		   System.out.println("|    O");
		   System.out.println("|   -|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		  }
		  else if(l == 2) {
		   System.out.println("|----------");
		   System.out.println("|    O");
		   System.out.println("|   -|-");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		  }
		  else if(l == 1) {
		   System.out.println("|----------");
		   System.out.println("|    O");
		   System.out.println("|   -|-");
		   System.out.println("|   /");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		  }
		  else{
		   System.out.println("|----------");
		   System.out.println("|    O");
		   System.out.println("|   -|-");
		   System.out.println("|   /|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		  }
		 }

}
