package com.ek.study.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 依赖META-INF/services/{packageName}.interface文件
 *
 * @author lazyman
 * @version v1.0
 * @date 2017/6/23
 */
public class SearchMain {
    public static void main(String[] args) {
        ServiceLoader<ISearch> s = ServiceLoader.load(ISearch.class);
        Iterator<ISearch> searchs = s.iterator();
        if (searchs.hasNext()) {
            ISearch curSearch = searchs.next();
            curSearch.search("test");
        }
    }
}
