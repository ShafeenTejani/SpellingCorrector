package com.shafeentejani.spelling;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

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
}
