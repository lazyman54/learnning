package com.ek.study.id;

import java.math.BigInteger;
import java.util.Calendar;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/11/9
 */
public abstract class AbstractedIdKeyGenerator {

    protected byte workerIdBits;

    protected byte sequenceBits;

    protected long sequenceMask;

    protected byte timestampBits = 41;

    protected byte timestampLeftShiftBits;

    protected long maxWorkId;
    protected long maxTps;
    protected int maxIdLength;

    protected int workId;
    protected long epoch;

    protected long sequence;
    protected long lastTime;

    public AbstractedIdKeyGenerator() {
    }

    public AbstractedIdKeyGenerator(byte workerIdBits, byte sequenceBits) {
        init(workerIdBits, sequenceBits);
    }

    protected void init(byte workerIdBits, byte sequenceBits) {
        this.workerIdBits = workerIdBits;
        this.sequenceBits = sequenceBits;
        this.sequenceMask = (1L << sequenceBits) - 1;
        this.timestampLeftShiftBits = (byte) (this.workerIdBits + this.sequenceBits);
        this.maxWorkId = maxValue(workerIdBits);
        this.maxTps = maxValue(sequenceBits);
        initEpoch();
    }

    protected void initEpoch() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.NOVEMBER, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        epoch = calendar.getTimeInMillis();
    }

    protected long waitUntilNextTime(final long lastTime) {
        long time = System.currentTimeMillis();
        while (time <= lastTime) {
            time = System.currentTimeMillis();
        }
        return time;
    }

    protected long maxValue(byte bitCount) {
        return (1L << bitCount) - 1;
    }

    protected int calcMaxLength(int bits) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bits; i++) {
            sb.append("1");
        }
        return new BigInteger(sb.toString(), 2).toString().length();
    }

    protected String toBinaryStr(long num, int bits) {
        String bitStr = Long.toBinaryString(num);
        StringBuilder sb = new StringBuilder(bitStr);
        for (int i = bits - bitStr.length(); i > 0; i--) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    public int doSetWorkId(int workId) {
        if (workId > this.maxWorkId) {
            throw new IllegalArgumentException("the work id is too big, please set smaller one ,smaller than " + this.maxWorkId);
        }
        this.workId = workId;
        return this.workId;
    }

}
