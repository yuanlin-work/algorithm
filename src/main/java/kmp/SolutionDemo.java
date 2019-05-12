package kmp;

import trie.TrieDemo;

public class SolutionDemo {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] s1 = TrieDemo.randomStr(1,10000,11);
//        String s2 = TrieDemo.randomStr(1,100,13)[0];
        String s2 = "abcdefghijklmn opqrstuvwxyzabcdefghi jklmnopqrstuvwxyzabcdefghi jklmnopqrstuvwxyz";
        long now = System.currentTimeMillis();
        solution.strStr2(s1,s2);
        System.out.println(String.format("kmp 耗时： %d",System.currentTimeMillis() - now)   );
        now = System.currentTimeMillis();
        for (int i = 0; i < s1.length; i++) {
            s1[i].indexOf(s2);
        }
        System.out.println(String.format("String.indexOf 耗时： %d",System.currentTimeMillis() - now)   );
        now = System.currentTimeMillis();
        bm.Solution solution1 = new bm.Solution();
        solution1.indexOf(s1,s2);
        System.out.println(String.format("bm 耗时：%d", System.currentTimeMillis() - now));
    }
}
