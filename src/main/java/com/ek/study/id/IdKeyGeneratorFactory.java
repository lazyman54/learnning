package com.ek.study.id;


import com.ek.study.id.num.support.IdKeyGeneratorForLong;
import com.ek.study.id.num.support.IdKeyGeneratorWithDateForLong;
import com.ek.study.id.num.support.IdKeyGeneratorWithShotDateForLong;
import com.ek.study.id.str.support.IdKeyGeneratorForStr;
import com.ek.study.id.str.support.IdKeyGeneratorWithDateForStr;
import com.ek.study.id.str.support.IdKeyGeneratorWithShotDateForStr;

/**
 * id生成器的工厂类
 *
 * @author lazyman
 * @version v1.0
 * @date 2017/11/13
 */
public class IdKeyGeneratorFactory {

    private static final byte DEFAULT_SEQUENCE_BITS = 12;
    private static final byte DEFAULT_WORKER_ID_BITS = 10;

    /**
     * 获取默认的id生成器，此id生成器支持最大workId是1023，最大的tps是4096000
     *
     * @return 返回默认的生成器，请用Long的泛型接收
     */
    public static IdKeyGenerator instance() {
        return instance(IdType.WITH_OUT_DATE_LONG);
    }

    /**
     * 根据id的样式获得id生成器，id样式参考{@link IdType}
     *
     * @param type id生成器的样式
     * @return 返回id生成器，请根据type来确定接收的泛型
     */
    public static IdKeyGenerator instance(IdType type) {
        return instanceWithBits(DEFAULT_WORKER_ID_BITS, DEFAULT_SEQUENCE_BITS, type);
    }

    /**
     * 指定id生成器的最大workId，最大tps，以及样式，如果是long的返回值，那么workId和tps的大小是反比，也即是tps越大，workId最大值越小
     * 如果workId+tps超过了long的最大值，那么会优先牺牲workId来保证提供足够的tps，建议这两个值都填2的n次方-1；如果参数为96，那么最大值会取127
     *
     * @param maxWorkId   最大的workId
     * @param maxSequence 最大的tps
     * @param type        id生成器的样式
     * @return 返回id生成器，请根据type来确定接收的泛型
     */
    public static IdKeyGenerator instanceWithNum(int maxWorkId, long maxSequence, IdType type) {
        return instanceWithBits(getMaxBitCountForNum(maxWorkId), getMaxBitCountForNum(maxSequence), type);
    }

    /**
     * 指定id生成器workId、tps的位数以及样式（workId、tps都是通过位运算来处理的）
     *
     * @param workIdBits   workId的位数
     * @param sequenceBits tps的位数
     * @param type         id生成器的样式
     * @return 返回id生成器，请根据type来确定接收的泛型
     */
    public static IdKeyGenerator instanceWithBits(byte workIdBits, byte sequenceBits, IdType type) {
        switch (type) {
            case WITH_DATE_STR:
                return new IdKeyGeneratorWithDateForStr(workIdBits, sequenceBits);
            case WITH_DATE_LONG:
                return new IdKeyGeneratorWithDateForLong(workIdBits, sequenceBits);
            case WITH_OUT_DATE_STR:
                return new IdKeyGeneratorForStr(workIdBits, sequenceBits);
            case WITH_OUT_DATE_LONG:
                return new IdKeyGeneratorForLong(workIdBits, sequenceBits);
            case WITH_DATE_SHORT_STR:
                return new IdKeyGeneratorWithShotDateForStr(workIdBits, sequenceBits);
            case WITH_DATE_SHORT_LONG:
                return new IdKeyGeneratorWithShotDateForLong(workIdBits, sequenceBits);
            default:
                return null;
        }
    }

    private static byte getMaxBitCountForNum(long num) {
        byte length = 0;
        while ((1 << ++length) <= num) {
        }
        return length;
    }


    enum IdType {
        /**
         * 不带可读时间，string类型
         */
        WITH_OUT_DATE_STR(),
        /**
         * 不带可读时间，long类型
         */
        WITH_OUT_DATE_LONG(),
        /**
         * 带可读时间，string类型
         */
        WITH_DATE_STR(),
        /**
         * 带可读时间，long类型
         */
        WITH_DATE_LONG(),
        /**
         * 带精简可读时间，string类型
         */
        WITH_DATE_SHORT_STR(),
        /**
         * 带精简可读时间，long类型
         */
        WITH_DATE_SHORT_LONG(),;
    }

}
