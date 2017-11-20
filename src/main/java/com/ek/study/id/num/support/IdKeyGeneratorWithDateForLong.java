package com.ek.study.id.num.support;


import com.ek.study.id.AbstractedIdKeyGeneratorWithDate;
import com.ek.study.id.num.INumIdKeyGenerator;

import java.util.Calendar;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/11/13
 */
public class IdKeyGeneratorWithDateForLong extends AbstractedIdKeyGeneratorWithDate implements INumIdKeyGenerator {

    private static final byte DATE_BITS = 25;

    public IdKeyGeneratorWithDateForLong(byte workerIdBits, byte sequenceBits) {
        long w_s_bits = MAX_ID_BITS - timestampBits - DATE_BITS;
        if ((workerIdBits + sequenceBits) > w_s_bits) {
            workerIdBits = (byte) (w_s_bits - sequenceBits);
        }
        init(workerIdBits, sequenceBits);
    }

    @Override
    public int setWorkId(int workId) {
        return doSetWorkId(workId);
    }

    @Override
    public Long generateId() {
        Calendar calendar = getTodayCalendar();

        if (!isToday(calendar)) {
            epoch = calendar.getTimeInMillis();
        }
        long currentMillis = System.currentTimeMillis();
        if (lastTime == currentMillis) {
            if (0L == (sequence = ++sequence & sequenceMask)) {
                currentMillis = waitUntilNextTime(currentMillis);
            }
        } else {
            sequence = 0;
        }
        lastTime = currentMillis;
        long longPart = ((currentMillis - epoch) << timestampLeftShiftBits) | (workId << sequenceBits) | sequence;

        return Long.parseLong(getTodayStr(calendar) + longPart);
    }


    @Override
    public Class getIdType() {
        return Long.class;
    }

    @Override
    public long getMaxTps() {
        return maxTps;
    }

    @Override
    public long getMaxWorkId() {
        return maxWorkId;
    }
}
