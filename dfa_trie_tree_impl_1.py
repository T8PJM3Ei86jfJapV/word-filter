#-*-encoding:utf-8-*-

# author:admin@pythoner.net
# date:2012-03-33

# author:developer@ricoxie.com
# date:2015-04-14

words = [
'shit',
'fuck',
'功夫网'
]

def create_word_tree(words_list):
    wordTree = [None for i in range(0,256)]
    wordTree = [wordTree, 0]
    for word in words_list:
 
        # 每个单词对应一个tree
        tree = wordTree[0]
        for i  in range(0, len(word)):
            letter = word[i]
            index = ord(letter)
            # 已经到最后一个字母了
            if i ==  len(word) - 1:
                tree[index] = 1
            else:
                tree[index] = [[None for x in range(0, 256)], 1]
                tree = tree[index][0]
 
    return wordTree
 
def translate(string,tree):
    temp = tree
    result = ''
    for letter in string:
        index = ord(letter)
        temp = temp[0][index]
        if temp == None:
            temp = tree
            #print 'can not find',letter
            result = ''
        elif temp and temp <> tree:
            result += chr(index)
            #print 'hit',letter
 
        if temp == 1:
            string = string.replace(result, '*')
            temp = tree

    return string

# def printTree(root):
#     node = root[0] 
#     for i in range(len(node)):
#         if node[i] != None:
#             print chr(i)
#             if node[i] != 1:
#                 printTree(node[i])
            
tree = create_word_tree(words)

for s in ['dfskdjf fuck ffucks','fuck xxshitxfuckx功夫网']:
    print 'input  :\t', s
    print 'outcome:\t', translate(s,tree), '\n'
