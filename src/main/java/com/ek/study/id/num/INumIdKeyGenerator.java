package com.ek.study.id.num;


import com.ek.study.id.IdKeyGenerator;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/11/13
 */
public interface INumIdKeyGenerator extends IdKeyGenerator<Long> {
    /**
     * long型id的最大可用位数值
     */
    int MAX_ID_BITS = 63;

    /**
     * 获取最大的tps
     *
     * @return 返回最大的tps
     */
    long getMaxTps();

    /**
     * 获取最大的workId
     *
     * @return 返回最大的workId
     */
    long getMaxWorkId();
}
