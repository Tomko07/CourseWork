package AnagramChecker;

import java.util.Scanner;

public class AnagramChecker {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		System.out.print ("Enter string 1: ");
		String str1 = sc.nextLine();
		System.out.print ("Enter string 2: ");
		String str2 = sc.nextLine();
		
		boolean check = isAnagram (str1, str2);
		System.out.println ("Anagram check for '" + str1 + "' and '" + str2 + "': " + check);
		sc.close();
	}
	
	public static boolean isAnagram (String s1, String s2) {
		if(s1.length() != s2.length())
			return false;
		
		char[] letters1 = s1.toLowerCase().toCharArray();
		char[] letters2 = s2.toLowerCase().toCharArray();
		boolean[] checked = new boolean[letters2.length];
			// so that each character from s2 is never counted more than once
		
		for(int index1 = 0; index1 < letters1.length; index1++) {
			int index2 = 0;
			boolean found = false;	// whether the current character from s1 was found in s2
			
			while(!found && index2 < letters2.length) {
				if(!checked[index2] && letters1[index1] == letters2[index2]) {
					checked[index2] = true;
					found = true;
				}
				else 
					index2++;
			}
			
			if(!found)	// one of s1's characters was not found in s2 
				return false;
		}		
		return true;
	}
}
