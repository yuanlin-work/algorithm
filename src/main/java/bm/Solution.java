package bm;

public class Solution {
    public int indexOf(String source,String pattern,int[] bad ){
        //为了和String.indexOf结果一致
        if(pattern.length() == 0)return 0;
        if(source.length() == 0 || source.length() < pattern.length())return -1;

        int m = 0,n = pattern.length()-1;
        if(bad == null){
            bad = badCharPosition(pattern);
        }
        int[] right = new int[256];
        char texts[] = source.toCharArray();
        char patterns[] = pattern.toCharArray();
        while (m + n < source.length()){
            if(0 == n && texts[m + n] == patterns[n])break;
            right[texts[m + n]] = m + n;

            if(texts[m + n] == patterns[n]){
                n--;
            } else {
                int skip1 = n - bad[texts[m + n]];
                int skip2 = right[patterns[n]] - (m + n);
                int skip = skip1 > skip2 ? skip1 : skip2;
                if(skip < 0){
                    m++;
                } else {
                    m += skip;
                }

                n = pattern.length()-1;
                right = new int[256];
            }

        }

        return m;
    }

    //坏字符位置数组是不变的，可以一次性计算出来
    public int[] badCharPosition(String s){
        int[] bad = new int[256];//长度看情况而定，只有小写字符
        for (int i = 0; i < 256; i++) {
            bad[i] = -1;
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            bad[chars[i]] = i;
        }
        return bad;
    }

    public void indexOf(String[] source,String pattern){
        int[] bad = badCharPosition(pattern);
        for (int i = 0; i < source.length; i++) {
            indexOf(source[i],pattern,bad);
        }
    }
    public int indexOf(String source,String pattern){
        return indexOf(source,pattern,null);
    }

    public static void main(String[] args) {
        String s1 = "abcdefabcdef";
        String s2 = "fabcdef";
        Solution solution = new Solution();
        System.out.println(solution.indexOf(s1,s2));
        System.out.println(s1.indexOf(s2));
    }
}
