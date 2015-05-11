package com.ricoxie.algorithm.wordfilter;

public class WordClassify extends SimpleWordFilter {

	public WordClassify() {
		super();
	}
	
	public void insertToTree(String word, String category) {
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
				node.child[order].categories.add(category);
			} else {
				// Shift to next state
				node = node.child[order];
			}
		}
	}
	
	public CategoryNode getCategory(String keyword) {
		TrieNode now = root;
		for (int i = 0; i != keyword.length(); ++i) {
			int order = (int) keyword.charAt(i);
			
			now = now.child[order];
			
			if (now == null) {
				return null;
			}
		}
		return now.categories;
	}

}
