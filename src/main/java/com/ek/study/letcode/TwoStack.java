package com.ek.study.letcode;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.storm.shade.org.eclipse.jetty.util.ArrayQueue;

/**
 * <p>please add class desc</p>
 *
 * @author lazyman
 * @version 1.0.0
 * @date 2021/8/14
 */
public class TwoStack {

}

class CQueue {

    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public CQueue(){
        queue1 = new LinkedBlockingQueue<>();
        queue2 = new LinkedBlockingQueue<>();
    }

    public void appendTail(int value){
        queue1.add(value);
    }

    public int deleteHead(){
        if( !queue2.isEmpty() ){
            queue2.remove();
        } else if( !queue1.isEmpty() ){
            while(!queue1.isEmpty()){
                int value = queue1.poll();
                queue2.add(value);
            }
            queue2.remove();
        } else{
            return 0;
        }
        return 1;

    }
}

/**
 * Your CQueue object will be instantiated and called as such: CQueue obj = new CQueue(); obj.appendTail(value); int param_2 = obj.deleteHead();
 */