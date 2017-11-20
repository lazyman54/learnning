package com.ek.study.id.str.support;


import com.ek.study.id.AbstractedIdKeyGenerator;
import com.ek.study.id.str.IStrIdKeyGenerator;

import java.math.BigInteger;

/**
 * 生成的id是全称日期+序列号，位数无需特别控制，可根据workId+sequence确定整体长度
 *
 * @author lazyman
 * @version v1.0
 * @date 2017/11/9
 */
public class IdKeyGeneratorForStr extends AbstractedIdKeyGenerator implements IStrIdKeyGenerator {

    public IdKeyGeneratorForStr(byte workerIdBits, byte sequenceBits) {

        super(workerIdBits, sequenceBits);

        maxIdLength = calcMaxLength(timestampBits + workerIdBits + sequenceBits);

        //maxIdLength = String.valueOf(maxValue((byte) (timestampBits + workerIdBits + sequenceBits))).length();
    }

    @Override
    public int setWorkId(int workId) {
        return doSetWorkId(workId);
    }

    @Override
    public synchronized String generateId() {


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
        long workIdPart = workId;
        long sequencePart = sequence;

        String tb = toBinaryStr(timePart, timestampBits);
        String wb = toBinaryStr(workId, workerIdBits);
        String sb = toBinaryStr(workId, sequenceBits);
        String ib = tb + wb + sb;
        System.out.println(ib);

        //long longPart = ((currentMillis - epoch) << timestampLeftShiftBits) | (workId << sequenceBits) | sequence;

        return new BigInteger(ib, 2).toString();


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
