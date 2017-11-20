package com.ek.study.id;

import com.ek.study.id.num.support.IdKeyGeneratorForLong;

import java.util.Calendar;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/11/9
 */
public abstract class AbstractedIdKeyGeneratorWithoutDate extends AbstractedIdKeyGenerator {


    private static final int WORKER_TPS_MAX_LENGTH = 22;

    protected final long timestampBits = 41;


    public AbstractedIdKeyGeneratorWithoutDate(byte workerIdBits, byte sequenceBits) {
        super(workerIdBits, sequenceBits);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.NOVEMBER, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        epoch = calendar.getTimeInMillis();

        maxWorkId = maxValue(workerIdBits);
        maxTps = maxValue(sequenceBits);
        maxIdLength = String.valueOf(maxValue((byte) (timestampBits + workerIdBits + sequenceBits))).length();
    }

    public static IdKeyGenerator<Long> instance(int maxTps, int maxWorkId) {
        byte tpsLength = getMaxBitCountForNum(maxTps, "maxTps can't not be larger than 22");
        byte wordIdLength = getMaxBitCountForNum(maxWorkId, "worker id can't not be larger than 22");

        if ((tpsLength + wordIdLength) > WORKER_TPS_MAX_LENGTH) {
            wordIdLength = (byte) (WORKER_TPS_MAX_LENGTH - tpsLength);
        }
        return instance(tpsLength, wordIdLength);
    }

    public static IdKeyGenerator<Long> instance(byte sequenceBits, byte workIdBits) {
        return new IdKeyGeneratorForLong(sequenceBits, workIdBits);
    }


    private static byte getMaxBitCountForNum(int maxTps, String errorMessage) {
        byte length = 0;
        while ((1 << ++length) > maxTps) {
            if (length >= WORKER_TPS_MAX_LENGTH) {
                throw new IllegalArgumentException(errorMessage);
            }
        }
        return length;
    }

}
