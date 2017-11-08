package com.ek.study;

import org.apache.storm.shade.com.google.common.base.Preconditions;

import java.util.Calendar;

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

    default long waitUntilNextTime(final long lastTime) {
        long time = System.currentTimeMillis();
        while (time <= lastTime) {
            time = System.currentTimeMillis();
        }
        return time;
    }
}

/**
 * 返回的值不带日期的，
 */
abstract class AbstractIdGeneratorWithOutDate implements IdGenerator<Long> {

    private static final int WORKER_TPS_MAX_LENGTH = 22;
    protected final long EPOCH;

    protected final byte WORKER_ID_BITS;

    protected final byte SEQUENCE_BITS;

    protected final long SEQUENCE_MASK;

    protected final byte TIMESTAMP_LEFT_SHIFT_BITS;

    protected final int ID_LENGTH;

    public AbstractIdGeneratorWithOutDate(byte maxTps, byte maxWorkId) {

        SEQUENCE_BITS = maxTps;
        WORKER_ID_BITS = maxWorkId;
        SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;
        TIMESTAMP_LEFT_SHIFT_BITS = (byte) (SEQUENCE_BITS + WORKER_ID_BITS);
        Long maxId = (1L << TIMESTAMP_LEFT_SHIFT_BITS + 40) + ((1L << TIMESTAMP_LEFT_SHIFT_BITS + 40) - 1);
        ID_LENGTH = maxId.toString().length();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.NOVEMBER, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        EPOCH = calendar.getTimeInMillis();
    }

    public static IdGenerator getIdGenerator(int maxTps, int maxWorkId) {
        byte tpsLength = getMaxLengthForNum(maxTps, "maxTps can't not be larger than 22");
        byte wordIdLength = getMaxLengthForNum(maxWorkId, "worker id can't not be larger than 22");
        if ((tpsLength + wordIdLength) > WORKER_TPS_MAX_LENGTH) {
            wordIdLength = (byte) (WORKER_TPS_MAX_LENGTH - tpsLength);
        }
        return new CustomerKeyGenerator(tpsLength, wordIdLength);
    }

    private static byte getMaxLengthForNum(int maxTps, String errorMessage) {
        byte length = 0;
        while ((1 << ++length) > maxTps) {
            if (length >= WORKER_TPS_MAX_LENGTH) {
                throw new IllegalArgumentException(errorMessage);
            }
        }
        return length;
    }

}

final class CustomerKeyGenerator extends AbstractIdGeneratorWithOutDate {

    public CustomerKeyGenerator(byte maxTps, byte maxWorkId) {
        super(maxTps, maxWorkId);
    }

    @Override
    public Long generateId() {
        return null;
    }

    @Override
    public int getIdMaxLength() {
        return 0;
    }

    @Override
    public Class getIdType() {
        return null;
    }

    @Override
    public int getMaxTps() {
        return 0;
    }

    @Override
    public int getMaxWorkId() {
        return 0;
    }
}

final class DefaultKeyGenerator extends AbstractIdGeneratorWithOutDate {

    private static final long SEQUENCE_BITS = 12L;

    private static final long WORKER_ID_BITS = 10L;

    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;

    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;

    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;

    private static final long WORKER_ID_MAX_VALUE = 1L << WORKER_ID_BITS;

    private static long workerId;

    private long sequence;

    private long lastTime;

    public DefaultKeyGenerator(byte maxTps, byte maxWorkId, long sequence, long lastTime) {
        super(maxTps, maxWorkId);
        this.sequence = sequence;
        this.lastTime = lastTime;
    }

    public static void setWorkerId(final long workerId) {
        Preconditions.checkArgument(workerId >= 0L && workerId < WORKER_ID_MAX_VALUE);
        DefaultKeyGenerator.workerId = workerId;
    }

    @Override
    public Long generateId() {
        long currentMillis = System.currentTimeMillis();
        Preconditions.checkState(lastTime <= currentMillis, "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastTime, currentMillis);
        if (lastTime == currentMillis) {
            if (0L == (sequence = ++sequence & SEQUENCE_MASK)) {
                currentMillis = waitUntilNextTime(currentMillis);
            }
        } else {
            sequence = 0;
        }
        lastTime = currentMillis;
        /*if (log.isDebugEnabled()) {
            log.debug("{}-{}-{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(lastTime)), workerId, sequence);
        }*/
        return ((currentMillis - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (workerId << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
    }

    @Override
    public int getIdMaxLength() {
        return 0;
    }

    @Override
    public Class getIdType() {
        return null;
    }

    @Override
    public int getMaxTps() {
        return 0;
    }

    @Override
    public int getMaxWorkId() {
        return 0;
    }

    /*private long waitUntilNextTime(final long lastTime) {
        long time = System.currentTimeMillis();
        while (time <= lastTime) {
            time = System.currentTimeMillis();
        }
        return time;
    }*/
}




/**
 * 返回带日期的
 */
abstract class AbstractIdGeneratorWithDate implements IdGenerator {

}






