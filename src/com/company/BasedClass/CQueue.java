package com.company.BasedClass;

import java.util.Objects;
import java.util.Stack;

/**
 * Created by รอ on 2017/7/22.
 */
public class CQueue {
    private Stack<Object> stack1;
    private Stack<Object> stack2;

    public CQueue() {
    }

    public void appendTail(Object obj) {
        stack1.push(obj);
    }

    public Object deleteHead(){
        Object result = null;

        if (this.stack2.isEmpty()){
            result = stack2.peek();
            stack2.pop();
            return result;
        }else if (!stack1.isEmpty()){
            for (int i= 0; i<stack1.size()-1;i++) {
                stack2.push(stack1.pop());
            }
            result = stack1.peek();
            stack1.pop();
            return result;
        }else {
            return null;
        }
    }
}
