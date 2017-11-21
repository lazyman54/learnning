package com.ek.study.id;

import java.util.Calendar;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/11/9
 */
public abstract class AbstractedIdKeyGeneratorWithDate extends AbstractedIdKeyGenerator {

    protected byte timestampBits = 27;

    public AbstractedIdKeyGeneratorWithDate() {
        super();
    }

    public AbstractedIdKeyGeneratorWithDate(byte workerIdBits, byte sequenceBits, int dateLength) {
        super(workerIdBits, sequenceBits);
        maxIdLength = calcMaxLength(timestampBits + workerIdBits + sequenceBits) + dateLength;
    }

    @Override
    protected void initEpoch() {
        Calendar calendar = getTodayCalendar();
        epoch = calendar.getTimeInMillis();
    }

    protected String getTodayStrForShort(Calendar calendar) {
        return getTodayStr(calendar).substring(2);
    }

    protected String getTodayStr(Calendar calendar) {
        return String.valueOf(calendar.get(Calendar.YEAR)) +
                (calendar.get(Calendar.MONTH) + 1) +
                calendar.get(Calendar.DAY_OF_MONTH);
    }


    protected Calendar getTodayCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    protected boolean isToday(Calendar calendar) {
        return calendar.getTimeInMillis() == epoch;
    }

}
