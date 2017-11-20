package com.ek.study.id.str.support;


import com.ek.study.id.AbstractedIdKeyGeneratorWithDate;
import com.ek.study.id.str.IStrIdKeyGenerator;

import java.util.Calendar;

/**
 * 生成的id是全称日期+序列号，位数无需特别控制，可根据workId+sequence确定整体长度
 *
 * @author lazyman
 * @version v1.0
 * @date 2017/11/9
 */
public class IdKeyGeneratorWithDateForStr extends AbstractedIdKeyGeneratorWithDate implements IStrIdKeyGenerator {

    public IdKeyGeneratorWithDateForStr(byte workerIdBits, byte sequenceBits) {

        super(workerIdBits, sequenceBits);

        maxIdLength = String.valueOf(maxValue(timestampBits)).length() +
                String.valueOf(maxValue(workerIdBits)).length() +
                String.valueOf(maxValue(sequenceBits)).length() + 8;

        //maxIdLength = String.valueOf(maxValue((byte) (timestampBits + workerIdBits + sequenceBits))).length() + 8;
    }

    @Override
    public int setWorkId(int workId) {
        return doSetWorkId(workId);
    }

    @Override
    public synchronized String generateId() {

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

        long timePart = currentMillis - epoch;
        //long longPart = ((currentMillis - epoch) << timestampLeftShiftBits) | (workId << sequenceBits) | sequence;

        return getTodayStr(calendar) + timePart + workId + sequence;


    }

    @Override
    public Class getIdType() {
        return String.class;
    }


    @Override
    public int getMaxIdLength() {
        return this.maxIdLength;
    }
}
