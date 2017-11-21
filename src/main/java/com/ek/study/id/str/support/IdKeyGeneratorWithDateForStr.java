package com.ek.study.id.str.support;


import com.ek.study.id.AbstractedIdKeyGeneratorWithDate;
import com.ek.study.id.str.IStrIdKeyGenerator;

import java.math.BigInteger;
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

        super(workerIdBits, sequenceBits, 8);

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
        long currentMillis = doGenerateId();

        long timePart = currentMillis - epoch;


        String tb = toBinaryStr(timePart, timestampBits);
        String wb = toBinaryStr(workId, workerIdBits);
        String sb = toBinaryStr(sequence, sequenceBits);
        String ib = tb + wb + sb;

        return getTodayStr(calendar) + new BigInteger(ib, 2).toString();


    }

    @Override
    public Class getIdType() {
        return String.class;
    }


    @Override
    public int getMaxIdLength() {
        return this.maxIdLength;
    }

    @Override
    public long getMaxWorkId() {
        return this.maxWorkId;
    }
}
