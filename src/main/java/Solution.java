import java.util.HashMap;
import java.util.Map;

/**
 * 判断字符串是否回文，即正着读和倒着读是一样的
 */
class Solution {
    public boolean isPalindrome(int x) {
        if(x == 0)return true;
        if(x < 0 || x %10 == 0)return false;
        int half = 0;
        while(x > half){
            half = x % 10  + half * 10;
            x /= 10;
        }
        return x == half || x == half / 10;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int b = target - nums[i];
            if(m.containsKey(b)){
                return new int[]{m.get(b),i};
            }
            m.put(nums[i],i);
        }
        throw new RuntimeException("No two sum solution");
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public static void main(String[] args) {
        String a = "abcdef";
        System.out.println(a.indexOf("bce"));
    }



}