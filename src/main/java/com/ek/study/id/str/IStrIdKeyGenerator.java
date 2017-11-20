package com.ek.study.id.str;


import com.ek.study.id.IdKeyGenerator;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/11/13
 */
public interface IStrIdKeyGenerator extends IdKeyGenerator<String> {

    int getMaxIdLength();
}
