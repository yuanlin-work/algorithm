package trie;

public class Solution {
    class Trie {
        private TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        public Trie(String s) {
            root = new TrieNode();
            insert(s);
        }
        public Trie(String[] s) {
            root = new TrieNode();
            for (int i = 0; i < s.length; i++) {
                insert(s[i]);
            }
        }

        class TrieNode {

            // R links to node children
            private TrieNode[] links;
            private final int R = 26;
            int nonEmptySize=0;
            private boolean isEnd;
            public int getNonEmptySize(){
                return nonEmptySize;
            }
            public TrieNode() {
                links = new TrieNode[R];
            }

            public boolean containsKey(char ch) {
                return links[ch -'a'] != null;
            }
            public TrieNode get(char ch) {
                return links[ch -'a'];
            }
            public void put(char ch, TrieNode node) {
                links[ch -'a'] = node;
                nonEmptySize++;
            }
            public void setEnd() {
                isEnd = true;
            }
            public boolean isEnd() {
                return isEnd;
            }
        }

        private TrieNode searchPrefix(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char curLetter = word.charAt(i);
                if (node.containsKey(curLetter)) {
                    node = node.get(curLetter);
                } else {
                    return null;
                }
            }
            return node;
        }
        public boolean search(String word) {
            if(word.length()==0)return false;
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd();
        }
        public void insert(String[] s){
            for (int i = 0; i < s.length; i++) {
                insert(s[i]);
            }
        }
        public void insert(String s){
            if(s.length()==0)return;
            TrieNode temp = root;
            char[] cs = s.toCharArray();
            for ( int i = 0; i < cs.length-1; i++) {
                char c = cs[i];
                if(temp.containsKey(c)==false){
                    TrieNode trieNode =  new TrieNode();
                    temp.put(c,trieNode);
                    temp = trieNode;
                }else {
                    temp = temp.get(c);
                }
            }
            if(temp.containsKey(cs[cs.length-1])){
                temp.setEnd();
            }else {
                TrieNode trieNode =  new TrieNode();
                temp.put(cs[cs.length-1],trieNode);
                trieNode.setEnd();
            }
        }
        public String searchLongestPrefix(String word){
            TrieNode trieNode = root;
            StringBuffer sb = new StringBuffer();
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (trieNode != null && trieNode.containsKey(c) && trieNode.getNonEmptySize() == 1){
                    sb.append(c);
                    if(trieNode.isEnd){
                        return sb.toString();
                    }
                    trieNode = trieNode.get(c);
                }
            }
            return sb.toString();
        }
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0)return "";
        Trie trie = new Trie();
        for (int i = 0; i < strs.length; i++) {
            if(strs[i].length()==0)return "";
            trie.insert(strs[i]);
        }
//        trie.insert(strs);
        return trie.searchLongestPrefix(strs[0]);
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"aaa","a",""};
        Solution solution1 = new Solution();
        System.out.println(solution1.longestCommonPrefix(strs));
    }
}
