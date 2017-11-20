package com.ek.study.id;

import com.dafy.base.nodepencies.strategy.id.num.support.IdKeyGeneratorForLong;
import com.dafy.base.nodepencies.strategy.id.num.support.IdKeyGeneratorWithDateForLong;
import com.dafy.base.nodepencies.strategy.id.num.support.IdKeyGeneratorWithShotDateForLong;
import com.dafy.base.nodepencies.strategy.id.str.support.IdKeyGeneratorForStr;
import com.dafy.base.nodepencies.strategy.id.str.support.IdKeyGeneratorWithDateForStr;
import com.dafy.base.nodepencies.strategy.id.str.support.IdKeyGeneratorWithShotDateForStr;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/11/13
 */
public class IdKeyGeneratorFactory {

    private static final byte DEFAULT_SEQUENCE_BITS = 12;
    private static final byte DEFAULT_WORKER_ID_BITS = 10;

    public static IdKeyGenerator instance() {
        return instance(IdType.WITH_OUT_DATE_LONG);
    }

    public static IdKeyGenerator instance(IdType type) {
        return instanceWithBits(DEFAULT_WORKER_ID_BITS, DEFAULT_SEQUENCE_BITS, type);
    }

    public static IdKeyGenerator instanceWithNum(int maxWorkId, long maxSequence, IdType type) {
        return instanceWithBits(getMaxBitCountForNum(maxWorkId), getMaxBitCountForNum(maxSequence), type);
    }

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
        while ((1 << ++length) > num) {
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
