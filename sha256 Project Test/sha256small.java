import java.util.*;
import java.security.*;
import java.io.*;

public class Main{
    
    public static void main (String[] args) throws Exception{
		String words[] = {"hello", "goodbye", "yo" , "for" , "sure"};
		String s1 = "", s2 = "";
		int count = 0;
		
        for(int i = 0; i < words.length; i++) {
            
            String s1hash = sha256(words[i]);
            int[] wordVal = new int[words.length];
            
            for(int j = i+1; j < words.length; j++) {
                
                int temp = countComparisons(s1hash, sha256(words[j]) );
                wordVal[j] = temp;
                
                if(temp > count) {
                    count = temp;
                    s2 = words[j];
                    s1 = words[i];
                }
            }
            
            System.out.println("Word = " + words[i] + ": => " + wordVal[0] + ", " + wordVal[1]+ ", " + wordVal[2]+ ", " + wordVal[3]+ ", " + wordVal[4]);
        }
        
        System.out.println("\nMost common count: " + count + "\nS1: " + s1 +"\nS2: " + s2);
    }

    public static String sha256(String input) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] salt = "CS210+".getBytes("UTF-8");
            mDigest.update(salt);
            byte[] data = mDigest.digest(input.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i=0;i<data.length;i++){
                sb.append(Integer.toString((data[i]&0xff)+0x100,16).substring(1));
            }
            return sb.toString();
        } catch(Exception e){
            return(e.toString());
        }
    }
    
    public static int countComparisons (String s1, String s2) {
        int x = 0;
        for(int i = 0; i < 64; i++)
            if(s1.charAt(i) == s2.charAt(i))
                x++;
        return x;
    }
}

/* *** OUTPUT ***
  
Word 0 = hello: => 0, 5, 5, 3, 4
Word 1 = goodbye: => 0, 0, 3, 4, 2
Word 2 = yo: => 0, 0, 0, 4, 6         // <= ANSWER WE'RE LOOKING FOR !
Word 3 = for: => 0, 0, 0, 0, 4
Word 4 = sure: => 0, 0, 0, 0, 0       // 0's indicate it didn't search as results have already been resolved

Most common count: 6
S1: yo
S2: sure


** Process exited - Return Code: 0 **



*/
