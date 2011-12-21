package com.shafeentejani.spelling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionary {

	private Set<String> knownWords = new HashSet<String>();
	
	private static final char[] ALPHABET = new char[]{'a','b','c','d','e','f','g','h','i','j',
													  'k','l','m','n','o','p','q','r','s','t',
													  'u','v','w','x','y','z'};
	
	public boolean containsWord(String word){ 
		return knownWords.contains(word.toLowerCase());
	}
	
	public void initialiseWords(List<String> words){
		for(String word : words)
			addWord(word);
	}

	public void initialiseWords(File wordsFile) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(wordsFile));
		
		String line;
		
		while((line = reader.readLine()) != null)
			addWord(line.trim());
		
		reader.close();
		
	};

	private void addWord(String word){
		knownWords.add(word.toLowerCase().intern());
	}
	
	public List<String> getOneLevelEdits(String word){
		
		List<String> edits = new ArrayList<String>();
		
		determineDeletes(word, edits);
		
		determineInserts(word, edits);
		
		determineReplacements(word, edits);
		
		determineTranspositions(word, edits);

		return edits;
	}

	

	private void determineTranspositions(String word, List<String> edits) {
		char[] arr = word.toCharArray();

		for(int i=0; i < word.length() -1; i++){
			swap(arr, i, i+1);
			
			edits.add(String.valueOf(arr));
			
			swap(arr, i, i+1);
		}
	}
	
	private void determineReplacements(String word, List<String> edits) {
		for(int i=0; i < word.length(); i++){
			for(char c : ALPHABET){
				edits.add(word.substring(0,i)+c+word.substring(i+1));
			}
		}
	}

	private void determineInserts(String word, List<String> edits) {
		for(int i=0; i <= word.length(); i++){
			for(char c : ALPHABET){
				edits.add(word.substring(0,i)+c+word.substring(i));
			}
		}
	}

	private void determineDeletes(String word, List<String> edits) {
		for(int i=0; i < word.length(); i++){
			edits.add(word.substring(0, i) + word.substring(i+1));
		}
	}
	
	private void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
}
