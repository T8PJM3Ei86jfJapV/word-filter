package com.ricoxie.algorithm;

public interface WordFilterInterface {
	public void insertToTree(String words);
	public void insertToTree(String[] words);
	public String eliminateMatchingWords(String text);
	public int getMatchCount(String text);
}
