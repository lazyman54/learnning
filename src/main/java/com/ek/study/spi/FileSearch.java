package com.ek.study.spi;

import java.util.List;

/**
 * @author lazyman
 * @version v1.0
 * @date 2017/6/23
 */
public class FileSearch implements ISearch {
    @Override
    public List<String> search(String keyword) {
        System.out.println("fileSearch: " + keyword);
        return null;
    }
}
