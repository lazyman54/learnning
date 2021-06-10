package com.ek.study.algorithm;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 根据权重轮询的方法
 *
 * @author lazyman
 * @version v1.0
 * @date 2017/10/17
 */
public class RoundRobinByWeight {

    private final AtomicInteger sequence = new AtomicInteger(0);

    public String select(List<String> origin) {
        int totalWeight = 0;
        int maxWeight = 0;
        for (String s : origin) {
            int weight = calcWeight(s);
            totalWeight += weight;
            maxWeight = Math.max(maxWeight, weight);
        }
        /*记录上次轮询到的位置*/
        int i = sequence.getAndIncrement();

        /*i会不断增大，这里保证mod在totalWeight之内*/
        int mod = i % totalWeight;

        /**
         * 假设有三个origin，第一个weight为40，第二个weight为50，第三个weight为10
         * 前面三十次轮询，每个人都有十次执行机会，那么后面七十次轮询，只有前面两个才有执行机会了，第一个有30次，第二个有40次执行机会。
         * 第101次访问，又回到第一次访问时的场景了。
         */
        /*轮询不能每个人只问一次，每一次轮询每个人都要问一次，从前往后，如果遇到合适的就返回*/
        for (int j = 0; j < maxWeight; j++) {
            for (String s : origin) {
                if (mod == 0 && calcWeight(s) > 0) {
                    return s;
                }
                if (calcWeight(s) > 0) {
                    int weightOfS = calcWeight(s) - 1;//这是一段伪代码，真实代码应该吧s的权重-1，直至s的权重减至0，那么s将无法获取执行权限了
                    mod--;
                }
            }
        }
        /*无权重概念时，便是这种基础轮询方式*/
        return origin.get(i % origin.size());

    }


    private int calcWeight(String string) {
        return System.identityHashCode(string);
    }
}
