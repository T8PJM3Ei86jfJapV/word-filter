package com.ricoxie.algorithm;

public class TrieNode {
	
	public boolean isEnd;
	public boolean isActive;
	public CategoryNode categories;
	
	public TrieNode[] child = new TrieNode[256];
	
	public TrieNode() {
		this.isEnd = false;
		this.isActive = false;
		categories = new CategoryNode();
	}

}
