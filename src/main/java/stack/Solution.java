package stack;

import java.util.Stack;

public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(isLeftBrackets(c)){
                stack.push(c);
            }
            if(isRightBrackets(c)){
                if(stack.size()==0)return false;
                if(isMatch(stack.peek(),c)==false)return false;
                stack.pop();
            }
        }
        if(stack.size()==0)return true;
            return false;
    }
    public boolean isLeftBrackets(char c){
        return c == '[' ||  c == '{' || c == '(';
    }
    public boolean isRightBrackets(char c){
        return c == ']' || c == '}' || c == ')';
    }
    public  boolean isMatch(char a,char b){
        return (a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b =='}');
    }
}
