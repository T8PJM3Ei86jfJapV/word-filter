package com.ricoxie.algorithm.wordfilter;

public interface WordFilter {
	
	public void insertToTree(String[] words);
	
	public void eliminateHittingWords(String text);

}
