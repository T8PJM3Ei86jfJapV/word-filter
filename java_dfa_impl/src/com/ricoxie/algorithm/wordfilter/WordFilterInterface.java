package com.ricoxie.algorithm.wordfilter;

public interface WordFilterInterface {
	public void insertToTree(String words);
	public void insertToTree(String[] words);
	public String eliminateMatchingWords(String text, String substitute);
	public String eliminateMatchingWords(String text);
	public int getMatchCount(String text);
}
