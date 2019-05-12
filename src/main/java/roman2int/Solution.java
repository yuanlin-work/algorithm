package roman2int;

public class Solution {
    public int romanToInt(String s) {
        if(s.length()==0)return 0;
        int[] mapArr = new int[26];
        mapArr['I' - 'A'] = 1;
        mapArr['V' - 'A'] = 5;
        mapArr['X' - 'A'] = 10;
        mapArr['L' - 'A'] = 50;
        mapArr['C' - 'A'] = 100;
        mapArr['D' - 'A'] = 500;
        mapArr['M' - 'A'] = 1000;
        char cs[] = s.toCharArray();
        if(cs.length == 1)return mapArr[cs[0] - 'A'];
        int a = 0;
        int b = 0;
        int result = 0;
        for (int i = 0; i < cs.length-1; i++) {
            a = mapArr[cs[i] - 'A'];
            b = mapArr[cs[i + 1] - 'A'];
            if(a >= b){
                result += a;
            } else {
                result -= a;
            }
        }
        result += b;
        return result;
    }
}
