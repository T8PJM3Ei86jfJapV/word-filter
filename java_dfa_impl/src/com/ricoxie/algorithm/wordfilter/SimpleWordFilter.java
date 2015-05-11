package com.ricoxie.algorithm.wordfilter;

import java.io.UnsupportedEncodingException;

public class SimpleWordFilter implements WordFilterInterface {
	
	protected final static int MAX_WORD_LENGTH = 24;
	protected TrieNode root;
	
	public SimpleWordFilter() {
		root = new TrieNode();
	}
	
	public void insertToTree(String word) {
		
		int len = word.length();

		// word length interval: [1, MAX_WORD_LENGTH]
		if (len < 1) {
			return;
		} else if (len > MAX_WORD_LENGTH) {
			try {
				throw new Exception("word length limited");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		TrieNode node = root;

		for (int i = 0; i != len; ++i) {
			char letter = word.charAt(i);
			int order = (int) letter;

			if (node.child[order] == null) {
				node.child[order] = new TrieNode();
			}

			node.child[order].isActive = true;

			// Last letter of a word as a leaf of node
			if (i == len - 1) {
				node.child[order].isEnd = true;
			} else {
				// Shift to next state
				node = node.child[order];
			}
		}
	}

	public void insertToTree(String[] words) {
		for (String word : words) {
			insertToTree(word);
		}
	}
	
	public String eliminateMatchingWords(String text, String substitute) {
		TrieNode now = root;

		// matchingLen: longest matching word length
		// hittingLen: number of accumulated hitting characters
		int matchingLen = 0, hittingLen = 0;
		
		int textLen = text.length();
		int subLen = substitute.length();

		for (int i = 0; i != textLen; ++i) {

			int order = (int) text.charAt(i);
			now = now.child[order];

			if (now == null) {

				if (matchingLen > 0) {
					// matching success
					int start = i - hittingLen + 1;
					int end = start + matchingLen;

					// eliminating substring between the interval of [start, end)
					text = text.substring(0, start - 1) + substitute + text.substring(end - 1);
					textLen = textLen - matchingLen + subLen;
					i = i - matchingLen + subLen - 1;
				} else {
					// matching failed, rolling back
					i -= hittingLen;
				}

				matchingLen = hittingLen = 0;
				now = root;
				continue;
			}
			
			if (now.isEnd) {
				matchingLen = ++hittingLen;
				
				if (i == text.length() - 1) {
					// end of text, longest matching substring
					text = text.substring(0, i - matchingLen + 1) + substitute;
					textLen = textLen - matchingLen + subLen;
					i = i - matchingLen + subLen - 1;
					break;
				}
			} else {
				++hittingLen;
				// and shift to the next character
			}
		}
		return text;
	}
	
	public String eliminateMatchingWords(String text) {
		return eliminateMatchingWords(text, "*");
	}
	
	public int getMatchCount(String text) {
		int num = 0;
		TrieNode now = root;

		// matchingLen: longest matching word length
		// hittingLen: number of accumulated hitting characters
		int matchingLen = 0, hittingLen = 0;
		
		int textLen = text.length();

		for (int i = 0; i != textLen; ++i) {

			int order = (int) text.charAt(i);
			now = now.child[order];

			if (now == null) {

				if (matchingLen > 0) {
					// matching success
					num++;
				} else {
					// matching failed, rolling back
					i -= hittingLen;
				}

				matchingLen = hittingLen = 0;
				now = root;
				continue;
			}
			
			if (now.isEnd) {
				matchingLen = ++hittingLen;
				
				if (i == text.length() - 1) {
					// end of text, longest matching substring
					num++;
					break;
				}
			} else {
				++hittingLen;
				// and shift to the next character
			}
		}
		
		return num;
	}
	
	protected int[] string2ascii(String word) {
		int array[] = null;
		try {
			byte[] bytes = word.getBytes("utf-8");
			array = new int[bytes.length];
			for (int i = 0; i != bytes.length; ++i) {
				array[i] = Integer.valueOf(bytes[i]) + 128;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	protected void printTree(TrieNode start) {
		TrieNode node = start;

		for (int i = 0; i != 256; ++i) {
			if (node.child[i] == null) {
				continue;
			}

			if (node.child[i].isActive == true) {
				System.out.println((char) i);
				printTree(node.child[i]);
			}
		}
	}
}
