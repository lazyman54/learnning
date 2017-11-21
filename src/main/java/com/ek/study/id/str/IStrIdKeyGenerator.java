package com.ek.study.id.str;


import com.ek.study.id.IdKeyGenerator;

/**
 * String类型的id
 *
 * @author lazyman
 * @version v1.0
 * @date 2017/11/13
 */
public interface IStrIdKeyGenerator extends IdKeyGenerator<String> {

    /**
     * 获取id的最大长度，通常最大长度大于当前长度。
     *
     * @return 返回id的最大长度
     */
    int getMaxIdLength();
}
