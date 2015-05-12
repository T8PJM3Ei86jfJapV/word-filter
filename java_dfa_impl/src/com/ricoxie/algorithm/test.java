package com.ricoxie.algorithm;

public final class test {
	public static void main(String[] args) {
		WordClassify obj = new WordClassify();
		
		// 测试中英文过滤
		String [] words = {"中文", "456", "8", "89", "", "fuck", "ffuck"};
		obj.insertToTree(words);
		String text = "中文中文f中文中文ffuckffuck中文测试中文中文";
		System.out.println(obj.eliminateMatchingWords(text));
		
		// 测试分类		
		obj.insertToTree("一群SB", "暴力");
		obj.insertToTree("一群SB", "骚扰");
		System.out.println(obj.getCategory("一群S"));
		System.out.println(obj.getCategory("一群SB"));
		System.out.println(obj.getCategory("一群SBB"));
		
		
	}
}
