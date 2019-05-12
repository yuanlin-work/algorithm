package trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrieDemo {
    public static void main(String[] args) {
        Trie trie = new Trie();
//        trie.insert("abc");
//        System.out.println(trie.search("abc"));
        String[] ss = randomStr(200000,100);//生成2000个最大长度为100的随机字符串
        trie.insert(ss);
        long now = 0;
        String searched = randomStr(1,100,10)[0];
        System.out.println("load all done :"+(now = System.currentTimeMillis()));
        searched = ss[ss.length-1];
        trie.search(searched);

//        for (int i = 0; i < ss.length; i++) {
//            if(ss[i]==searched){
//                break;
//            }
//        }
        System.out.println("search done,spend :"+(System.currentTimeMillis() - now));
    }

    public static String[] randomStr(int size,int maxlength){
        return randomStr(size,maxlength,0);
    }
    public static String[] randomStr(int size,int maxlength,int seed){
        Random random = new Random(seed);
        char[] cs ;
        List<String> list = new ArrayList<String>();
        for (int i = 0; i <= size; i++) {
            int j = random.nextInt(maxlength)+1;
            cs = new char[j];
            for (int k = 0; k < j; k++) {
                cs[k] = (char) (random.nextInt(26)+ (int)'a');
            }
            list.add(new String(cs));
        }
        return list.toArray(new String[list.size()]);
    }
}
