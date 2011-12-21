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
	public void testDictionaryIsCaseInsensitive(){
		assertTrue(dictionary.containsWord("Gorgonzola"));
		assertTrue(dictionary.containsWord("gorgOnzolA"));
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
	
	
	@Test
	public void testOneWordEdits(){
		assertEquals(getExpectedEdits(), dictionary.getOneLevelEdits("cats"));
	}
	
	
	private List<String> getExpectedEdits(){
		return toList("ats","cts","cas","cat",
				"acats","bcats","ccats","dcats","ecats","fcats","gcats",
				"hcats","icats","jcats","kcats","lcats","mcats","ncats",
				"ocats","pcats","qcats","rcats","scats","tcats","ucats",
				"vcats","wcats","xcats","ycats","zcats",
				"caats","cbats","ccats","cdats","ceats","cfats","cgats",
				"chats","ciats","cjats","ckats","clats","cmats","cnats",
				"coats","cpats","cqats","crats","csats","ctats","cuats",
				"cvats","cwats","cxats","cyats","czats",
				"caats","cabts","cacts","cadts","caets","cafts","cagts",
				"cahts","caits","cajts","cakts","calts","camts","cants",
				"caots","capts","caqts","carts","casts","catts","cauts",
				"cavts","cawts","caxts","cayts","cazts",
				"catas","catbs","catcs","catds","cates","catfs","catgs",
				"caths","catis","catjs","catks","catls","catms","catns",
				"catos","catps","catqs","catrs","catss","catts","catus",
				"catvs","catws","catxs","catys","catzs",
				"catsa","catsb","catsc","catsd","catse","catsf","catsg",
				"catsh","catsi","catsj","catsk","catsl","catsm","catsn",
				"catso","catsp","catsq","catsr","catss","catst","catsu",
				"catsv","catsw","catsx","catsy","catsz",
				"aats","bats","cats","dats","eats","fats","gats",
				"hats","iats","jats","kats","lats","mats","nats",
				"oats","pats","qats","rats","sats","tats","uats",
				"vats","wats","xats","yats","zats",
				"cats","cbts","ccts","cdts","cets","cfts","cgts",
				"chts","cits","cjts","ckts","clts","cmts","cnts",
				"cots","cpts","cqts","crts","csts","ctts","cuts",
				"cvts","cwts","cxts","cyts","czts",
				"caas","cabs","cacs","cads","caes","cafs","cags",
				"cahs","cais","cajs","caks","cals","cams","cans",
				"caos","caps","caqs","cars","cass","cats","caus",
				"cavs","caws","caxs","cays","cazs",
				"cata","catb","catc","catd","cate","catf","catg",
				"cath","cati","catj","catk","catl","catm","catn",
				"cato","catp","catq","catr","cats","catt","catu",
				"catv","catw","catx","caty","catz",
				"acts","ctas","cast");
	}
}
