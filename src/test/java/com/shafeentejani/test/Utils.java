package com.shafeentejani.test;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Utils {

	public static <T> List<T> toList(T... items){
		return Arrays.asList(items);
	}
	
	public static File readFileFromClasspath(String path){
		URL url = Utils.class.getResource(path);
		return new File(url.getFile());
	}
}
