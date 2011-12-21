package com.shafeentejani.spelling;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;
import static com.shafeentejani.test.Utils.*;

import org.junit.Before;
import org.junit.Test;


public class SpellingCorrectorTest {

	
	private SpellingCorrector corrector;
	private Dictionary mockDictionary;
	
	@Before
	public void initialiseVariables(){
		
		mockDictionary = mock(Dictionary.class);
		corrector = new SpellingCorrector(mockDictionary);
	}
	
	@Test
	public void testCorrectWord(){
		
		when(mockDictionary.containsWord("gorgonzola")).thenReturn(true);
		
		assertTrue(corrector.isSpeltCorrectly("gorgonzola"));
	}
	
	@Test
	public void testIncorrectlySpeltWord(){
		
		when(mockDictionary.containsWord("gorganzola")).thenReturn(false);
		
		assertFalse(corrector.isSpeltCorrectly("gorganzola"));
	}
	
	@Test
	public void testSuggestionsForCorrectlySpeltWord(){
		
		when(mockDictionary.containsWord("bear")).thenReturn(true);
		
		assertTrue(corrector.suggestions("bear").isEmpty());
	}
	
	@Test
	public void testSuggestionsOneEditDistance(){
		
		when(mockDictionary.containsWord("acd")).thenReturn(false);
		when(mockDictionary.getOneLevelEdits("acd")).thenReturn(toList("cd","ad","ac","aca","ace","and"));
		when(mockDictionary.containsWord("ace")).thenReturn(true);
		when(mockDictionary.containsWord("and")).thenReturn(true);
		
		assertEquals(toList("ace","and"),corrector.suggestions("acd"));
	}
	
	@Test
	public void testSuggestionsTwoEditDistance(){
		
		when(mockDictionary.containsWord("yax")).thenReturn(false);
		when(mockDictionary.getOneLevelEdits("yax")).thenReturn(toList("ya","ax","yx","cax","hax","ybx"));
		when(mockDictionary.getOneLevelEdits("cax")).thenReturn(toList("can", "cab","cas","cay"));
		when(mockDictionary.containsWord("can")).thenReturn(true);
		when(mockDictionary.containsWord("cab")).thenReturn(true);
		
		assertEquals(toList("can","cab"),corrector.suggestions("yax"));
	}
}
