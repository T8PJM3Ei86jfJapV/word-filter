package com.ricoxie.algorithm.wordfilter;

public class SimpleWordFilter implements WordFilter {

	public TrieNode root = new TrieNode();
	private final static int MAX_WORD_LENGTH = 24;

	@Override
	public void insertToTree(String[] words) {
		for (String word : words) {
			int len = word.length();

			if (len < 1) {
				continue;
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
	}

	public void printTree(TrieNode start) {
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
	
	@Override
	public String eliminateHittingWords(String text) {
		TrieNode now = root;

		int count = 0, pivot = 0;

		for (int i = 0; i != text.length(); ++i) {

			int order = (int) text.charAt(i);
			now = now.child[order];

			if (now == null) {
								
				if (count > 0) {
					int start = i - pivot + 1;
					int end = start + count;
					// [start, end)
					text = text.substring(0, start - 1) + '*' + text.substring(end - 1);
					i -= count;
				}
				count = pivot = 0;
				now = root;
				continue;
			}
			
			if (now.isEnd) {
				count = ++pivot;
				
				if (i == text.length() - 1) {
					text = text.substring(0, i - count + 1) + '*';
					i -= count;
					break;
				}
			} else {
				pivot++;
			}
		}
		return text;
	}
}
