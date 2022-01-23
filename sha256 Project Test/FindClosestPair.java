import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FindClosestPair 
{
	static List<String> hashes = new ArrayList<>();
	public static void main(String args[]) throws IOException {
		Path myPath = Paths.get("D:/CS 210 Project/hashesTest.txt");                            // Get file with hashes in it
		hashes = Files.readAllLines(myPath, StandardCharsets.UTF_8);                            // Read all lines of .txt and store in ArrayList hashes
		int pool = 6; 
		
		ExecutorService service = Executors.newFixedThreadPool(pool);                           // Allows mangement of threads
		int n = hashes.size();  
		for(int i = 0; i < pool; i++)
			service.execute(run("Thread " + i, i*(n / pool), (i + 1) * (n / pool)));            // Run() takes a String to display what thread it is, 2 ints to start/stop the loop
                                                                                                // for eg: if n == 6 million => The second thread will begin at 2 million and end at 3 million etc.
        service.shutdown();                                                                     // close executor service
	}
	
	public static int countComparisons(String s1, String s2){
        int count = 0;
        for(int i = 0; i < 64; i++) {
        	if(i > 45 && count < 5)                                                             // cut it short as it's not worth continuing
        		return 0;
            if(s1.charAt(i) == s2.charAt(i))
                count++;
        }      	
        return count;
	}
	
	private static Runnable run(String msg, int index, int stop) {
		return new Runnable() {
			public void run() {
				int count = 22, n = hashes.size();                                              // Start count at 22 as we don't want anything below that
				System.out.println(msg + " has started at index: "+ index);                     // Indicates that thread has started
		        for(int i = index; i < stop; i++) {	                                            
		            for(int j = i+1; j < n; j++) {
		                int temp = countComparisons(hashes.get(i),hashes.get(j));
		                if(temp > count)                                                        // Print to the console if get anything over 22
		                    System.out.println("Update from: "+ msg+" :> Hash in pos: " + i + " and: " + j + " with: " + temp);
		            }
		        }
			}
		};
	}
}
