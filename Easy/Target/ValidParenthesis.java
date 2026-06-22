package Target;

import java.util.Stack;

public class ValidParenthesis {
    public boolean isValidParenthesis(String s){
        Stack<Character> stack = new Stack<>();
        for(char ch: s.toCharArray()){
            if(ch == '(') stack.push(')');
            else if(ch == '{') stack.push('}');
            else if (ch == '[') stack.push(']');
            else{
                if(stack.isEmpty() || ch != stack.pop()){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
