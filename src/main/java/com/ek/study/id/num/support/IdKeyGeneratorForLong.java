package com.ek.study.id.num.support;


import com.ek.study.id.AbstractedIdKeyGenerator;
import com.ek.study.id.num.INumIdKeyGenerator;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/11/9
 */
public class IdKeyGeneratorForLong extends AbstractedIdKeyGenerator implements INumIdKeyGenerator {

    public IdKeyGeneratorForLong(byte sequenceBits, byte workerIdBits) {
        long w_s_bits = MAX_ID_BITS - timestampBits;
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
    public synchronized Long generateId() {
        long currentMillis = doGenerateId();
        
        return ((currentMillis - epoch) << timestampLeftShiftBits) | (workId << sequenceBits) | sequence;
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
