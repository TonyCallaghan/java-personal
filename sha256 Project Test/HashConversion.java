import java.util.*;
import java.security.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class HashConversion
{    
    public static void main (String[] args) throws Exception {
		Path myPath = Paths.get("D:/CS 210 Project/sentenceHashes.txt");                    // Find file with sentences in it
        List<String> sentences = new ArrayList<>();                                        
		sentences = Files.readAllLines(myPath, StandardCharsets.UTF_8);                     // Read all lines of .txt and store in ArrayList
		
	    try {
	        File hashes = new File("SentenceHashes.txt");                                 // File to store hashes
	        if (hashes.createNewFile())                                                   // Try to create it
	        	System.out.println("File created: " + hashes.getName());                    // Successful
	        else 
	        	System.out.println("File already exists.");                                 // Unsuccesful
	    } catch (IOException e) {
	        System.out.println("An error occurred.");
	    }
	    FileWriter myWriter = new FileWriter("SentenceHashes.txt");                       // Declare file writer object which allows text to be written
	    int n = sentences.size(); 
	    System.out.println(n + " hashes being added");                                    // Show how many sentences being converted               
	    for(int i = 0; i < n; i++ ) {
	    	myWriter.write(sha256(sentences.get(i)) +"\n");                                 // send sentence to sha method and store output in txt file
	    }
	    myWriter.close();
        System.out.println("Done!");                                                    // Finished
    }

    public static String sha256(String input){
        try{
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] salt = "CS210+".getBytes("UTF-8");
            mDigest.update(salt);
            byte[] data = mDigest.digest(input.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i=0;i<data.length;i++){
                sb.append(Integer.toString((data[i]&0xff)+0x100,16).substring(1));
            }
            return sb.toString();
        }catch(Exception e){
            return(e.toString());
        }
    }   
}
