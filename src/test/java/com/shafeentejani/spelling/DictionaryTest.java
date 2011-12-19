package com.shafeentejani.spelling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.shafeentejani.test.Utils.*;


public class DictionaryTest {

	private Dictionary dictionary;
	private List<String> knownWords;
	
	@Before
	public void initialiseFields(){
		dictionary = new Dictionary();
		knownWords = toList("gorgonzola");
		dictionary.initialiseWords(knownWords);
	}
	
	@Test
	public void testExistingWord(){
		assertTrue(dictionary.containsWord("gorgonzola"));
	}
	
	@Test
	public void testNonExistingWord(){
		assertFalse(dictionary.containsWord("gorganzola"));
	}
	
	@Test
	public void testInitialisingFromList(){
		
		List<String> words = toList("apple","pear","orange","banana","plum","peach","mango");
		
		dictionary.initialiseWords(words);
		
		for(String word : words){
			assertTrue("Dictionary does not contain word: "+word, 
					dictionary.containsWord(word));
		}
	}
	
	@Test
	public void testInitialisingFromFile() throws IOException{
		
		File wordsFile = readFileFromClasspath("/test-words.txt");
		
		dictionary.initialiseWords(wordsFile);
		
		BufferedReader reader = new BufferedReader(new FileReader(wordsFile));
		
		String line;
		
		while((line = reader.readLine()) != null)
			assertTrue("Dictionary does not contain word: "+line.trim(),
					dictionary.containsWord(line.trim()));
		
	}
}
