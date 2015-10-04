
package ScrabbleScorer;

import java.util.Scanner;

public class ScrabbleScorer {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		System.out.print ("Enter string: ");
		String str = sc.nextLine();
		int score = computeScore (str);
		System.out.println ("The score for '" + str + "' is: " + score);
		sc.close();
	}
	
	public static int computeScore (String str) {
		final int[] VALUES = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
				1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
		
		int score = 0;
		
		for(int index = 0; index < str.length(); index++)
			if(str.toLowerCase().substring(index, index+1).matches("[a-z]"))
				score += VALUES[str.toLowerCase().charAt(index) - 'a'];
		
		return score;
	}
	

}
