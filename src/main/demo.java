package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class demo {
	private static final String FilePath = "resources/data.txt";
	
	public static void main(String[] args) throws IOException {
		ArrayList<String> doc = read_data();
		
		TFIDF tfidf = new TFIDF();
		ArrayList<String[]> data = tfidf.TFIDF(doc);
		
		show(data);
	}
	
	private static void show(ArrayList<String[]> data) {
		for(String[] st_array:data) {
			for(String s:st_array) {
				System.out.print(s+" ");
			}
			System.out.println();
		}
	}
	
	public static ArrayList<String> read_data() throws IOException {
		FileInputStream fis = new FileInputStream(FilePath); 
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); 
        BufferedReader br = new BufferedReader(isr); 
        String line = null; 
        
        ArrayList<String> document = new ArrayList<String>();
        while ((line = br.readLine()) != null) { 
        	document.add(line);
        }
        
        return document;
	}
}
