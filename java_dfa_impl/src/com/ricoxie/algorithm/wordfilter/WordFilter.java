package com.ricoxie.algorithm.wordfilter;

public interface WordFilter {
	
	public void insertToTree(String[] words);
	
	public String eliminateHittingWords(String text);

}
