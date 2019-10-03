package com.luciano.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	
	//formatando os id's da lista vindo na requisicao (removendo as virgulas)
	public static List<Integer> decodeIntTOList(String s){
		List<Integer> ids = new ArrayList<Integer>();
		String[] vet = s.split(",");
		
		for(int i = 0; i < vet.length; i++) {
			ids.add(Integer.parseInt(vet[i]));
		}
		//utilizando Lambda
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(s)).collect(Collectors.toList());
		
		return ids;
	}
	
	//garantindo que o parametro nao venha codificado caso haja espaço
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
		
	}
	

}
