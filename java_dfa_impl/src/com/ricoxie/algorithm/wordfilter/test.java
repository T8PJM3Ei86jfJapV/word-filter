package com.ricoxie.algorithm.wordfilter;

public final class test {
	public static void main(String[] args) {
		SimpleWordFilter swf = new SimpleWordFilter();
		
		String [] words = {"45", "456", "8", "89", ""};
		swf.insertToTree(words);
		System.out.println(swf.eliminateHittingWords("456789"));
	}
}
