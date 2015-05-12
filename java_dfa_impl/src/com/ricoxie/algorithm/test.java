package com.ricoxie.algorithm;

public final class test {
	public static void main(String[] args) {
		SimpleWordFilter obj = new SimpleWordFilter();
	
		String [] words = {"中文", "456", "8", "89", "", "fuck", "ffuck"};
		obj.insertToTree(words);
		
		
		String text = "中文中文f中文中文fuckffuk中文测试中文中文";
		int array[] = obj.string2intarray(text);

		for (int item : array) {
			System.out.print(item + " ");
		}
		System.out.print("\n");
		
		String result = obj.eliminateMatchingWords(text);

		System.out.println(result);
	}
}
