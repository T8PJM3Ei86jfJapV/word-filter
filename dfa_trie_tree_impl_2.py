#-*-encoding:utf-8-*-

class WordFilter(object):
	"""A simple word filter implemented by trie tree."""

	def __init__(self):
		self.word_tree = [[False for _ in range(256)], 0]

		# UTF-8 breaks a Chinese character down to 3 bytes,
		# and length of 24 contains 8 Chinese characters at most.
		# Note: But there is no built-in byte type in python.
		self.MAX_WORD_LENGTH = 24

	def insert_to_tree(self, *words):
		"""Insert one or more words into the trie tree.

		Usages:
		a = '1'
		b = ['2', '3']
		insert_to_tree(a)
		insert_to_tree(*b)
		insert_to_tree(*['4', '5', '6'])
		"""
		for word in words:
			length = len(word)

			if length < 1:
				continue
			elif length > self.MAX_WORD_LENGTH:
				raise Exception('word length limited')

			node = self.word_tree[0]

			for i in range(length):
				letter = word[i]
				order = ord(letter)

				# Last letter of a word as a leaf node
				if i == length - 1:
					node[order] = True
				else:
					node[order] = [[False for _ in range(256)], 1]
					node = node[order][0]

	def eliminate_hitting_words(self, text):
		"""
		Replace hitting words with '*'.
		"""
		temp = self.word_tree
		hit_len = 0

		i = 0
		while i != len(text):
			letter = text[i]
			order = ord(letter)
			temp = temp[0][order]

			if temp == True:
				# Pattern matching success
				text = text[:i-hit_len] + '*' + text[i+1:]
				i -= hit_len
				temp = self.word_tree
				hit_len = 0
			elif temp == False:
				# Pattern matching failed
				temp = self.word_tree
				hit_len = 0
			else:
				# State transition
				hit_len += 1

			i += 1

		return text

def test():
	words = ['shit', '骂了隔壁']
	wf = WordFilter()
	wf.insert_to_tree(*words)
	print wf.eliminate_hitting_words('cdy骂了隔壁uishit6gg')

def main():
	test()

if __name__ == "__main__":
	main()
