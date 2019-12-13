package com.test.Medium;

import java.util.Stack;

/**
 * @author xgp
 * @version 1.0
 * @date 2019/11/12 10:15
 */
public class solution {

    /**
     * leetcode 227
     * @param s
     * @return
     */
    public int calculate(String s) {
        s = s.replaceAll("\\s", "");
        Stack<Character> stack_c = new Stack<>();
        Stack<Integer> stack_i = new Stack<>();
        char[] t = s.toCharArray();
        int i = 0;
        while (i<t.length){
            int sum = 0;
            while (i<t.length&&t[i]>='0'&&t[i]<='9'){
                sum = sum*10 + t[i]-'0';
                i++;
            }
            stack_i.push(sum);
            if (i>=t.length){
                break;
            }
            while (true){
                if (stack_c.isEmpty()){
                    stack_c.push(t[i++]);
                    break;
                }else if (ismd(t[i])&&isas(stack_c.peek())){
                    stack_c.push(t[i++]);
                    break;
                }else {
                    done(stack_c,stack_i);
                }
            }
        }
        while (!stack_c.isEmpty()){
            done(stack_c,stack_i);
        }
        return stack_i.pop();
    }

    private void done(Stack<Character> stack_c, Stack<Integer> stack_i){
        char c = stack_c.pop();
        int x = stack_i.pop();
        int y = stack_i.pop();
        switch (c){
            case '+': stack_i.push(x+y);break;
            case '-': stack_i.push(y-x);break;
            case '*': stack_i.push(x*y);break;
            case '/': stack_i.push(y/x);break;
        }
    }
    private boolean ismd(char c){
        return c=='*'||c=='/';
    }
    private boolean isas(char c){
        return c=='+'||c=='-';
    }
}
