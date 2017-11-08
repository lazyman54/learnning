package com.ek.study;

/**
 * <ul> 返回带可读日期的时间
 * <li></li>
 * </ul>
 * <ul> 返回不带可读日期的时间
 * <li></li>
 * </ul>
 * <p>返回的long型的id</p>
 *
 * @author lazyman
 * @version v1.0
 * @date 2017/11/8
 */
public interface IdGenerator<T> {
    T generateId();

    int getIdMaxLength();

    Class getIdType();

    int getMaxTps();

    int getMaxWorkId();
}

/**
 * 返回的值不带日期的，
 */
abstract class AbstractIdGeneratorWithOutDate implements IdGenerator<Long> {

    //private static final IdGenerator DEFAULT_ID_GENERATOR = () -> null;

    public static IdGenerator getIdGenerator(int maxTps, int maxWorkId) {
        return null;
    }

}


/**
 * 返回带日期的
 */
abstract class AbstractIdGeneratorWithDate implements IdGenerator {

}






