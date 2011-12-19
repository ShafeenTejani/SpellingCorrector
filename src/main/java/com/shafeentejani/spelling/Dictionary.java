package com.shafeentejani.spelling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionary {

	private Set<String> knownWords = new HashSet<String>();
	
	public boolean containsWord(String word){ 
		return knownWords.contains(word);
	}
	
	public void initialiseWords(List<String> words){
		for(String word : words){
			knownWords.add(word);
		}
	}

	public void initialiseWords(File wordsFile) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(wordsFile));
		
		String line;
		
		while((line = reader.readLine()) != null)
			knownWords.add(line.trim().intern());
		
		reader.close();
		
	};
	
}
