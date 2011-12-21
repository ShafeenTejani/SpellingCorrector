package com.shafeentejani.spelling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;

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
	
	public Collection<String> suggestions(String word){
		
		if(isSpeltCorrectly(word)) return new ArrayList<String>();
		
		List<String> oneLevelEdits = dictionary.getOneLevelEdits(word);
		
		List<String> filtered = ImmutableList.copyOf(Collections2.filter(oneLevelEdits, wordInDictionary));
		
		if(!filtered.isEmpty()) return filtered;
		
		List<String> twoLevelEdits = new ArrayList<String>();
		
		for(String edit : oneLevelEdits){
			twoLevelEdits.addAll(dictionary.getOneLevelEdits(edit));
		}
		
		return ImmutableList.copyOf(Collections2.filter(twoLevelEdits, wordInDictionary));
			
	}

	
	private final Predicate<String> wordInDictionary = new Predicate<String>() {
		public boolean apply(String word) {
			return dictionary.containsWord(word);
		}
	};
}
