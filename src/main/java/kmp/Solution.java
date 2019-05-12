package kmp;

public class Solution {
    public int strStr(String haystack, String needle) {
        /*int i = 0,j = 0;//定义源串和目标串的下标，从0开始
        char[] _source_chars = haystack.toCharArray();
        char[] _target_chars = needle.toCharArray();
        int[] next = null;
        while (i < _source_chars.length && j < _target_chars.length) {
            if (_source_chars[i] == _target_chars[j]) {
                i++;
                j++;
            } else if (j ==0){//如果j在第一位而且不匹配，则i直接向后移动
                i++;
            } else {//如果不相等，j回溯到next位置
                if(next == null){
                    next = next(_target_chars);
                }
                j = next[j];//因为保存的是最大长度，正好是下一位的下标
                if(_source_chars[i] != _target_chars[j] && j == 0){//如果新位置不相等
                    i++;
                } else if(_source_chars[i] != _target_chars[j]){
                    j = 0;
                }
            }
            if(j == _target_chars.length ){
                break;
            }
        }
        if(j == _target_chars.length){
            return i-j;
        } else {
            return -1;
        }*/
        char[] s_arr = haystack.toCharArray();
        char[] t_arr = needle.toCharArray();
        int[] next = next(t_arr);
        int i = 0, j = 0;
        while (i<s_arr.length && j<t_arr.length){
            if(j == -1 || s_arr[i]==t_arr[j]){//j == -1 表明是j=0就匹配不成功，j应该归0，
                                            // 而next[0]的值固定为-1刚好满足这个要求
                i++;
                j++;
            }
            else
                j = next[j];
        }
        if(j == t_arr.length)
            return i-j;
        else
            return -1;
    }

    /*
    求解next数组，长度为目标字符串的长度-1，值为下标长度的子串中最长的相同前缀后缀字符串的长度
    采用next[i-1]的值来推导next[i]的值，即当前匹配完的前缀的后一位和后缀的后一位相等，next[i]=next[i-1]+1即可，
    如果不相等，向前寻找前缀字符串当做目标串，查找该目标串是否有相同前后缀长度，如果有比较前缀的后一位和当前位是否
    相等，直到找不到前缀字符串为止
     */
    public int[] next(char[] chars){
        int length = chars.length;
        int[] next = new int[length];
        next[0] = -1;
        next[1] = 0;//next的第一个元素默认为0，即相等前后缀长度为0
        int k;//保存前一位的最长相等长度
        for (int j = 2; j < length; j++) {
            k = next[j-1];
            while (k!=-1) {//没有查询完毕
                if (chars[j - 1] == chars[k]) { //长度-1才是当前位的下标
                    next[j] = k + 1;
                    break;
                }
                else {
                    k = next[k];
                }
                next[j] = 0;  //当k==-1而跳出循环时，next[j] = 0，否则next[j]会在break之前被赋值
            }
        }
        return next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "";
        String s2 = "";
        System.out.println(solution.strStr(s1,s2));
        System.out.println(s1.indexOf(s2));

    }

    public void strStr2(String[] s1, String s2) {
        int[] next = next(s2.toCharArray());
        for (int i = 0; i < s1.length; i++) {
            strStr(s1[i],s2,next);
        }
    }
    public int strStr(String haystack, String needle, int[] next) {
        int i = 0,j = 0;//定义源串和目标串的下标，从0开始
        char[] _source_chars = haystack.toCharArray();
        char[] _target_chars = needle.toCharArray();
        while (i < _source_chars.length && j < _target_chars.length) {
            if (_source_chars[i] == _target_chars[j]) {
                i++;
                j++;
            } else if (j ==0){//如果j在第一位而且不匹配，则i直接向后移动
                i++;
            } else {//如果不相等，j回溯到next位置
                if(next == null){
                    next = next(_target_chars);
                }
                j = next[j];//因为保存的是最大长度，正好是下一位的下标
                if(_source_chars[i] != _target_chars[j]){//如果新位置不相等，j归0
                    j = 0;
                }
            }
            if(j == _target_chars.length ){
                break;
            }
        }
        if(j == _target_chars.length){
            return i-j;
        } else {
            return -1;
        }
    }
}
