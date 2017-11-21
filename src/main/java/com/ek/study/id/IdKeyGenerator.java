package com.ek.study.id;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/11/9
 */
public interface IdKeyGenerator<T> {

    /**
     * 设置workId，请在设置前先调用getMaxWorkId方法,确保你设置得workId是有效的workId
     *
     * @param workId 需要设置得work id
     * @return 返回有效的workId
     */
    int setWorkId(int workId);

    /**
     * 生成全局唯一的id
     *
     * @return 返回全局唯一的id
     */
    T generateId();

    /**
     * 获取最大的workId
     *
     * @return 返回最大的workId
     */
    long getMaxWorkId();


    /**
     * 返回id类型
     *
     * @return long或者String
     */
    Class getIdType();


}
