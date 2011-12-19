package com.shafeentejani.spelling;

import java.util.List;

public class SpellingCorrector {

	private Dictionary dictionary;
	
	public SpellingCorrector(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	
	public boolean isSpeltCorrectly(String word){
		return dictionary.containsWord(word);
	}
	
	public String suggestion(String word){
		return null;
	}
	
	public List<String> suggestions(String word){
		return null;
	}

	
}
