package com.wyden.bibi.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}

	
	
	
	public static List<Integer> decodeIntList(String s){
		
		//split tem a funcao de pegar a String e dividir em padacos de acorodo com o caracter passado. 
		String[] vet = s.split(",");
		
		List<Integer> list = new ArrayList<>();
	
	    for(int i=0; i<vet.length; i++) {
	    	list.add(Integer.parseInt(vet[i]));
	    }
	    return list;
	    
	    //Essa linha utilizando Lambda faz a mesma coisa que o codigo anterior.
	    //return Arrays.asList(s.split(";")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
}
