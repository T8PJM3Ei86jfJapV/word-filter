package com.ricoxie.algorithm.wordfilter;

import java.io.UnsupportedEncodingException;

public final class test {
	public static void main(String[] args) {
		WordClassify obj = new WordClassify();
	
/*		String [] words = {"45", "456", "8", "89", "", "fuck", "ffuck"};
		obj.insertToTree(words);
		obj.insertToTree("fuck", "sex");
		obj.insertToTree("fuck", "sen");
		System.out.println(obj.eliminateMatchingWords("fuckkkffffuck"));
		System.out.println(obj.getCategory("fuck"));*/
/*		
		byte[] bs;
		try {
			bs = "2".getBytes("utf-8");
			for(byte b : bs)
			    System.out.print(Integer.valueOf(b) + 128 + " ");
				System.out.print((int) '2');
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		String temp = "000000";
		for (int index : obj.string2ascii(temp)) {
			System.out.println(index);	
		}
	}
}
