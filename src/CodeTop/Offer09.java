package CodeTop;

/**
 * @author mxy
 * @create 2023-03-14 9:17
 */

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 */
public class Offer09 {

    Deque<Integer> inStack,outStack;

    public Offer09(){
        inStack = new LinkedList<>();
        outStack = new LinkedList<>();
    }

    public void appendTail(int e){
        inStack.addLast(e);
    }

    public int deleteHead(){
        if (!outStack.isEmpty()) return outStack.removeLast();
        if (inStack.isEmpty()) return -1;
        while (!inStack.isEmpty()){
            outStack.addLast(inStack.removeLast());
        }
        return outStack.removeLast();
    }

}
