package com.ricoxie.algorithm.wordfilter;

public class CategoryNode {
	public String value;
	public CategoryNode next;

	public CategoryNode() {
		value = null;
		next = null;
	}

	public void add(String value) {
		CategoryNode node = this;

		while (node.value != null) {
			node = node.next;
		}
		
		node.value = value;
		node.next = new CategoryNode();
	}
	
	@Override
	public String toString() {
	
		String outcome = "[";
		CategoryNode node = this;
		
		if (node.value != null) {
			outcome = outcome.concat(node.value);
			node = node.next;
		}

		while (node.value != null) {
			outcome = outcome.concat(", " + node.value);
			node = node.next;
		}

		outcome = outcome.concat("]");
		return outcome;
	}
}
