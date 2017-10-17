package com.ek.study.algorithm;

import java.util.List;
import java.util.Random;

/**
 * 根据权重的随机算法，来自于dubbo团队,这里只介绍核心算法
 *
 * @author lazyman
 * @version v1.0
 * @date 2017/10/17
 */
public class RandomByWeight {

    public String select(List<String> origin) {

        int totalWeight = 0;

        for (String s : origin) {
            totalWeight += calcWeight(s);

        }

        int randomNum = new Random().nextInt(totalWeight);

        for (String s : origin) {
            randomNum -= calcWeight(s);
            if (randomNum < 0) {
                return s;
            }
        }
        return origin.get(new Random().nextInt(origin.size()));

    }

    private int calcWeight(String string) {
        return System.identityHashCode(string);
    }
}
